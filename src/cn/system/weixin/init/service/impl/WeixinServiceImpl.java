/*  
 * @(#) WeixinServiceImpl.java Create on 2014-5-28 上午9:28:55   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.init.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.system.basic.global.BaseDao;
import cn.system.basic.global.baseAbstract.BaseServiceA;
import cn.system.weixin.common.CommonClient;
import cn.system.weixin.common.CommonData;
import cn.system.weixin.init.bean.resp.Article;
import cn.system.weixin.init.bean.resp.TextMessage;
import cn.system.weixin.init.service.WeixinService;
import cn.system.weixin.util.MessageUtil;
import cn.system.weixin.util.WeixinUtil;
import cn.tools.NumberHelper;
import cn.tools.StringHelper;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * 微信核心处理
 * @author zhangshiyuan
 * @date   2016-6-13 下午3:43:57
 */
@Service
public class WeixinServiceImpl extends BaseServiceA implements WeixinService {
	
	/*
	 * 处理微信发来的请求 
	 * (non-Javadoc)
	 * @see cn.system.weixin.init.service.WeixinService#processRequest(javax.servlet.http.HttpServletRequest)
	 * @param request
	 * @return
	 * @throws Exception
	 * @date: 2016-6-13下午3:47:37
	 * @author: zhangshiyuan
	 */
	@Override
	public String processRequest(HttpServletRequest request) throws Exception {
		String respMessage = null;  
        try {  
        	// 微信加密签名
    		String msg_signature = request.getParameter("msg_signature");
    		// 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 加密解密帮助类
            WXBizMsgCrypt  wxcpt = new WXBizMsgCrypt(CommonData.CONFIG_TOKEN, CommonData.CONFIG_ENCODING_AES_KEY, CommonData.CONFIG_CORP_ID);
            Map<String, String> requestMap = MessageUtil.parseXml(request,wxcpt,msg_signature,timestamp,nonce);// xml请求解析    
            String fromUserName = requestMap.get("FromUserName");// 发送方帐号（open_id）  
            String toUserName = requestMap.get("ToUserName");// corpID    
            String msgType = requestMap.get("MsgType");// 消息类型  
            int agentId = NumberHelper.stringToInt(requestMap.get("AgentID"));// 应用ID
            String respContent = "\ue137 抱歉,您的请求暂无处理。"; // 默认返回的文本消息内容
    		if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {// 事件推送
                String eventType = requestMap.get("Event");// 事件类型
                if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {// 自定义菜单点击事件
                	//System.out.println("应用ID["+agentId+"]:"+requestMap.get("EventKey"));
                	String eventKey = requestMap.get("EventKey");
                	respMessage = this.getBtnEvent(eventKey,fromUserName,toUserName,agentId);
                }else if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){//成员关注事件
                	respContent = "\ue03f 恭喜您已获得新的应用服务。";//更改一下文本提示即可
                }//其他类型事件有需要也可在此补充
            }
    		if("".equals(StringHelper.toTrim(respMessage))){//增设默认返回文本消息
    			TextMessage textMessage = new TextMessage(fromUserName, toUserName, MessageUtil.RESP_MESSAGE_TYPE_TEXT, respContent);
    			respMessage = MessageUtil.textMessageToXml(textMessage);
    		}
    		respMessage = wxcpt.EncryptMsg(respMessage, timestamp, nonce);//消息加密
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        return respMessage;  
    }
	
	/**
	 * 获取按钮事件
	 * @Title: getBtnEvent
	 * @data:2016-6-23上午12:18:28
	 * @author:liweiwei
	 *
	 * @param eventKey
	 * @param fromUserName
	 * @param toUserName
	 * @return
	 * @throws Exception
	 */
	private String getBtnEvent(String eventKey,String fromUserName,String toUserName,int agentId) throws Exception {
		String result = null;
		//按钮事件
        if(eventKey.equals(CommonData.BTN_WARNING_INFO)){// 预警信息
        	result = this.btnWarningInfo(fromUserName,toUserName,agentId);
        }else if(eventKey.equals(CommonData.BTN_REPORT_INFO)){// 日报订阅
        	result = this.btnTodayReport(fromUserName,toUserName,agentId);
        }
        return result;
	}
	
	/**
	 * 预警信息按钮事件
	 * @Title: btnWarningInfo
	 * @data:2014-9-12下午4:32:45
	 * @author:zhangshiyuan
	 *
	 * @param fromUserName
	 * @param toUserName
	 * @return
	 * @throws Exception 
	 */
	private String btnWarningInfo(String openId, String toUserName,int agentId) throws Exception {
		List<Article> list = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("点击查看预警信息");
		article.setDescription("欢迎使用新联财通企业号!点击此处查看预警信息列表!");
		article.setUrl(CommonData.CONFIG_COMPANY_URL+"/warning/warning_toWaringLevel?agentId="+agentId);
		list.add(article);
		String result = MessageUtil.createNewsMessage(openId, toUserName, list);
		return result;
	}
	
	/**
	 * 日报订阅按钮事件
	 * @Title: btnTodayReport
	 * @data:2016-1-25下午3:08:57
	 * @author:liweiwei
	 *
	 * @param openId
	 * @param toUserName
	 * @return
	 * @throws Exception
	 */
	private String btnTodayReport(String openId, String toUserName,int agentId) throws Exception {
		List<Article> list = new ArrayList<Article>();
		Article article = new Article();
		int agent = WeixinUtil.getAgentIdByCId(NumberHelper.stringToInt(CommonClient.CLIENT_UNILEVER));
		if (agentId == agent){//如果是联合利华客户则出下列内容
			article.setTitle("点击查看历史报告");
			article.setDescription("本报告由北京新联财通咨询有限公司制作并发布，若有更多需求请致电 (010)51295500");
			article.setPicUrl(CommonData.CONFIG_COMPANY_URL+"/images/history_unilever.jpg");
		}else{
			article.setTitle("点击查看报告信息");
			article.setDescription("欢迎使用新联财通企业号!点击此处查看报告信息列表!");
			article.setPicUrl(CommonData.CONFIG_COMPANY_URL+"/images/report.png");
		}
		article.setUrl(CommonData.CONFIG_COMPANY_URL+"/report/report_toTitleList?agentId="+agentId);
		list.add(article);
		String result = MessageUtil.createNewsMessage(openId, toUserName, list);
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getDao() {
		return null;
	}

}
