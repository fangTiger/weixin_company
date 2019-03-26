/*  
 * @(#) SendMessage.java Create on 2016-6-20 下午4:09:47   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.message.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.system.basic.global.baseAbstract.BaseAction;
import cn.system.weixin.common.CommonData;
import cn.system.weixin.init.bean.resp.Article;
import cn.system.weixin.message.service.SendMessageService;
import cn.system.weixin.warning.bean.WarningBean;
import cn.tools.CommonSendMeg;
import cn.tools.DateHelper;
import cn.tools.FileHelper;
import cn.tools.StringHelper;

/**
 * 消息推送
 * @author liweiwei
 * @date   2016-6-20
 */
@Controller
@Scope("prototype")
public class SendMessageAction  extends BaseAction {
	private static final long serialVersionUID = -2714470777186861586L;
	
	public static void main(String[] args) {
		System.out.println(CommonData.CONFIG_COMPANY_URL);
	}
	
	@Resource
	private SendMessageService service;
	
	/**
	 * 发送消息至应用
	 * @Title: sendMessage
	 * @data:2016-6-20下午4:19:03
	 * @author:liweiwei
	 *
	 * @throws Exception
	 */
	public void sendAgentMsg()throws Exception{
		StringBuffer content = new StringBuffer();
		
		System.out.println(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME)+" [主动推送信息] 接收请求");
		String strJson = this.getFromRequestParameter("wList");//接收json包
		if("".equals(StringHelper.toTrim(strJson))){
			CommonSendMeg.writeMsg("no data");
			System.out.println(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME)+" [主动推送信息] 无可推送数据，结束请求");
			return;
		}
		System.out.println(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME)+" [解码前] "+strJson);
		strJson = URLDecoder.decode(strJson, "utf-8");
		strJson = StringHelper.toTrim(strJson).replaceAll("\\n", "");//json无法转换\n
		System.out.println(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME)+" [解码后] "+strJson);
		JSONObject baseParam = JSONObject.fromObject(strJson);
		String cId = baseParam.getString("cId");
		String agentId = "";
		if(baseParam.containsKey("agentId"))
			agentId = baseParam.getString("agentId");
		System.out.println(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME)+" [发送客户] "+cId);
		String prefix = baseParam.getString("prefix");
		List<WarningBean> list = this.jsonToList(strJson);//将接收的json数据包转换成指定list格式
		content.append("strJson:"+strJson+"\n");
		FileHelper.getTxt("数据日志_"+DateHelper.getNowDate(DateHelper.FMT_DATE_YYYYMMDD)+".txt",content.toString());
		
		if (list!=null&&list.size()>0) {
			CommonSendMeg.writeMsg(service.sendAgentMsg(cId, agentId, list,prefix));//返回码
		}
	}
	
	/**
	 * 将json解析成list
	 * @Title: jsonToList
	 * @data:2016-6-20下午5:00:22
	 * @author:liweiwei
	 *
	 * @param strJson
	 * @param prefix
	 * @return
	 * @throws Exception
	 */
	private List<WarningBean> jsonToList(String strJson)throws Exception{
		List<WarningBean> list = new ArrayList<WarningBean>();
		WarningBean bean = null;
		try {
			JSONObject json = JSONObject.fromObject(strJson);
			String wList = json.getString("wList");
			JSONArray array = JSONArray.fromObject(wList);
			for (int i = 0; i < array.size(); i++) {
				bean = new WarningBean();
				JSONObject obj = (JSONObject) array.get(i);
				bean.setId(obj.getString("id"));
				if(obj.containsKey("wid"))
					bean.setWid(obj.getString("wid"));
				bean.setType(obj.getString("type"));
				bean.setTitle(obj.getString("title"));
				bean.setLink(obj.getString("link"));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 主动推送图文消息
	 * @Title: sendAgentPicMsg
	 * @data:2016-8-9下午5:42:14
	 * @author:bagen
	 *
	 * @throws Exception
	 */
	public void sendAgentPicMsg()throws Exception{
		System.out.println(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME)+" [主动推送图文信息] 接收请求");
		String strJson = this.getFromRequestParameter("reportJson");//接收json包
		
		if("".equals(StringHelper.toTrim(strJson))){
			CommonSendMeg.writeMsg("no data");
			System.out.println(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME)+" [主动推送图文信息] 无可推送数据，结束请求");
			return;
		}
		System.out.println(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME)+" [解码前] "+strJson);
		strJson = URLDecoder.decode(strJson, "utf-8");
		strJson = StringHelper.toTrim(strJson).replaceAll("\\n", "");//json无法转换\n
		System.out.println(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME)+" [解码后] "+strJson);
		JSONObject baseParam = JSONObject.fromObject(strJson);
		String cId = baseParam.getString("cId");
		String agentId = "";
		if(baseParam.containsKey("agentId"))
			agentId = baseParam.getString("agentId");
		
		List<Article> list = this.dealPushReport(strJson);//将接收的json数据包转换成指定list格式
		if (list!=null&&list.size()>0) {
			CommonSendMeg.writeMsg(service.sendAgentPicMsg(cId, agentId, list));//返回码
		}
	}
	
	/**
	 * 将图文json解析成list
	 * @Title: dealPushReport
	 * @data:2016-8-9下午5:48:26
	 * @author:bagen
	 *
	 * @param strJson
	 * @return
	 * @throws Exception
	 */
	private List<Article> dealPushReport(String strJson) throws Exception {
		List<Article> list = new ArrayList<Article>();
		JSONObject jsonObject = JSONObject.fromObject(strJson);
		if ( jsonObject !=null ){
			Article article = new Article();
			article.setTitle(jsonObject.getString("title"));
			article.setDescription(jsonObject.getString("summary"));
			article.setPicUrl(jsonObject.getString("picUrl"));
			article.setUrl(jsonObject.getString("link"));
			list.add(article);
		}
		return list;
	}
}
