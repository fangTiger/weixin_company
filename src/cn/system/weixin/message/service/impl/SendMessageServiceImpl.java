/*  
 * @(#) SendMessageServiceImpl.java Create on 2016-6-20 下午4:12:16   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.message.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.system.basic.global.BaseDao;
import cn.system.basic.global.baseAbstract.BaseServiceA;
import cn.system.weixin.common.CommonData;
import cn.system.weixin.common.CommonTable;
import cn.system.weixin.common.CommonWxUrl;
import cn.system.weixin.init.bean.pojo.ResultBean;
import cn.system.weixin.init.bean.resp.Article;
import cn.system.weixin.init.bean.resp.TextMessage;
import cn.system.weixin.log.bean.MessageLogsBean;
import cn.system.weixin.log.dao.MessageLogsDao;
import cn.system.weixin.message.bean.CusAgentBean;
import cn.system.weixin.message.service.SendMessageService;
import cn.system.weixin.util.HttpUtil;
import cn.system.weixin.util.MessageUtil;
import cn.system.weixin.util.WeixinUtil;
import cn.system.weixin.warning.bean.WarningBean;
import cn.tools.BeanHelper;
import cn.tools.DateHelper;
import cn.tools.NumberHelper;
import cn.tools.StringHelper;
import cn.tools.jackjson.JackJson;

/**
 * 消息推送至应用
 * @author liweiwei
 * @date   2016-6-20
 */
@Service
public class SendMessageServiceImpl extends BaseServiceA implements SendMessageService{

	
	@Resource
	private MessageLogsDao logDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getDao() {
		return null;
	}
	
	/*
	 * 发送消息至应用(应用全体人员)
	 * (non-Javadoc)
	 * @see cn.system.weixin.message.service.SendMessageService#sendMessage(java.lang.String, java.util.List, java.lang.String, java.lang.String)
	 * @param agentId
	 * @param list
	 * @param accessToken
	 * @param prefix
	 * @return
	 * @throws Exception
	 * @date: 2016-6-20下午5:08:55
	 * @author: liweiwei
	 */
	@Override
	public String sendAgentMsg(String cId,String agtId, List<WarningBean> list, String prefix) throws Exception {
		String result = "-1";
		TextMessage bean = null;
		Integer agentId;
		String accessToken = "";
		try {
			if (list!=null && list.size()>0) {
				System.out.print(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME)+" [主动推送信息] 推送微信 >>> ");
				String clickUrl;
				prefix = StringHelper.toTrim(prefix);
				//用客户id去取应用id
				List<CusAgentBean> cusAgentList = WeixinUtil.getCusAgentList(cId, agtId);
				if(cusAgentList != null && cusAgentList.size() > 0){
					for (CusAgentBean cusAgentBean : cusAgentList) {
						if(!"".equals(StringHelper.toTrim(cusAgentBean.getCorpID()))){//如果该值不为空，说明是新接口，有可能对应多个企业号
							accessToken = WeixinUtil.getNewAccessToken(cusAgentBean.getCorpID(), cusAgentBean.getSecret());
							agentId = NumberHelper.stringToInt(cusAgentBean.getAgentId());
						}else{//否则是老接口，只对应当前企业号token
							if(!"".equals(StringHelper.toTrim(cId)))
								agentId = WeixinUtil.getAgentIdByCId(NumberHelper.stringToInt(cId));
							else 
								agentId = NumberHelper.stringToInt(agtId);
							accessToken = WeixinUtil.getAccessToken();
						}
						
						for (WarningBean wBean : list) {
							bean = new TextMessage();
							bean.setToUserName("@all");// 发送至应用全体人员@all 
							bean.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
							String title = StringHelper.toTrim(wBean.getTitle());
							title  = title.replaceAll("\"", "“");//替换引号,否则会使a标签拼接出错
							if(!"".equals(StringHelper.toTrim(wBean.getLink()))){//如果原网链接不是空的,优先出原网链接
								clickUrl = wBean.getLink();
							}else{//默认的链接
								clickUrl = CommonData.CONFIG_COMPANY_URL + "?wId=" + wBean.getId() + "&typeValue="+wBean.getType();
							}
							if(!"".equals(StringHelper.toTrim(wBean.getLink()))||!"".equals(StringHelper.toTrim(wBean.getId()))){
								bean.setContent(prefix+title+"<a href='" + clickUrl + "'>点此阅读</a>");
							}else {
								bean.setContent(prefix+title);
							}
							result = this.post(bean,accessToken,agentId+"");//发送消息
							this.createMessageLog(agentId+"",wBean,result);//记录日志,录入结果
						}
						System.out.println(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME)+" 推送完成!推送结果："+result);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 创建消息日志
	 * @Title: createMessageLog
	 * @data:2016-6-20下午5:18:04
	 * @author:liweiwei
	 *
	 * @param openId
	 * @param wBean
	 * @param result
	 * @throws Exception
	 */
	public void createMessageLog(String agentId,WarningBean wBean, String result) throws Exception{
		MessageLogsBean logBean = new MessageLogsBean();
		logBean.setAgentId(NumberHelper.stringToInt(agentId));
		logBean.setmId(NumberHelper.stringToInt(wBean.getId()));
		logBean.setTitle(wBean.getTitle());
		logBean.setLink(wBean.getLink());
		logBean.setType(wBean.getType());
		if(!"".equals(StringHelper.toTrim(result))&&!"-1".equals(StringHelper.toTrim(result))){
			System.out.println("-----------"+result);
			ResultBean resultBean = JackJson.fromJsonToObject(result, ResultBean.class);
			if(resultBean!=null){
				logBean.setBackMsg(resultBean.getErrmsg());
				logBean.setBackCode(resultBean.getErrcode());
			}else{
				logBean.setBackMsg("未转换应用ID，并且未调用向客服消息接口post接口");
				logBean.setBackCode("-1");
			}
		}else{
			logBean.setBackMsg("未转换应用ID，并且未调用向客服消息接口post接口");
			logBean.setBackCode("-1");
		}
		logBean.setCreateTime(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME));
		logDao.insertBean(BeanHelper.beanToMap(logBean), CommonTable.MESSAGE_LOGS, "id");
	}

	/**
	 * 向客服消息接口post数据
	 * @Title: post
	 * @data:2016-6-20下午5:17:55
	 * @author:liweiwei
	 *
	 * @param bean
	 * @param access_token
	 * @return
	 * @throws Exception
	 */
	public String post(TextMessage bean, String access_token, String agentId)throws Exception{
		String result = "-1";
		String strJson = CommonWxUrl.getMsgStrJson(bean.getToUserName(), bean.getMsgType(), agentId, bean.getContent(), "0");
		try {
			result = HttpUtil.httpRequestGetJson(
					CommonWxUrl.URL_MESSAGE_SEND.replace("ACCESS_TOKEN", access_token),
					"POST", strJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 推送图文消息至应用
	 * (non-Javadoc)
	 * @see cn.system.weixin.message.service.SendMessageService#sendAgentPicMsg(java.lang.String, java.util.List, java.lang.String, java.lang.String)
	 * @param cId
	 * @param list
	 * @param accessToken
	 * @param prefix
	 * @return
	 * @throws Exception
	 * @date: 2016-8-9下午5:50:07
	 * @author: bagen
	 */
	@Override
	public String sendAgentPicMsg(String cId, String agtId, List<Article> list) throws Exception {
		String result = "-1";
		int agentId = 0;
		Article bean = null;
		String accessToken = "";
		try {
			if (list!=null && list.size()>0) {
				System.out.print(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME)+" [主动推送图文信息] 推送微信 >>> ");
				// 用客户id去企业号信息
				List<CusAgentBean> cusAgentList = WeixinUtil.getCusAgentList(cId, agtId);
				if(cusAgentList != null && cusAgentList.size() > 0){
					for (CusAgentBean cusAgentBean : cusAgentList) {
						if(!"".equals(StringHelper.toTrim(cusAgentBean.getCorpID()))){//如果该值不为空，说明是新接口，有可能对应多个企业号
							accessToken = WeixinUtil.getNewAccessToken(cusAgentBean.getCorpID(), cusAgentBean.getSecret());
							agentId = NumberHelper.stringToInt(cusAgentBean.getAgentId());
						}else{//否则是老接口，只对应当前企业号token
							accessToken = WeixinUtil.getAccessToken();
							agentId = WeixinUtil.getAgentIdByCId(NumberHelper.stringToInt(cId));
							agentId = 36;
						}
						
						for (Article wBean : list) {
							bean = new Article();
							bean.setToUserName("@all");// 发送至应用全体人员@all 
							bean.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_NEWS);
							String title = StringHelper.toTrim(wBean.getTitle());
							title  = title.replaceAll("\"", "“");//替换引号,否则会使a标签拼接出错
							bean.setTitle(title);
							bean.setUrl(wBean.getUrl());
							bean.setDescription(wBean.getDescription());
							bean.setPicUrl(wBean.getPicUrl());
							result = this.postPic(bean,accessToken, agentId+"");//发送消息
							this.createPicMessageLog(agentId+"", wBean, result);//记录日志,录入结果
						}
						System.out.println(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME)+" 推送图文消息完成!推送结果："+result);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 向客服图文消息接口post数据
	 * @Title: post
	 * @data:2016-8-9下午6:26:20
	 * @author:bagen
	 *
	 * @param bean
	 * @param access_token
	 * @param agentId
	 * @return
	 * @throws Exception
	 */
	public String postPic(Article bean, String access_token, String agentId)throws Exception{
		String result = "-1";
		String strJson = CommonWxUrl.getPicMsgStrJson(bean, agentId);
		try {
			result = HttpUtil.httpRequestGetJson(
					CommonWxUrl.URL_MESSAGE_SEND.replace("ACCESS_TOKEN", access_token),
					"POST", strJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 创建图文消息日志
	 * @Title: createPicMessageLog
	 * @data:2016-8-9下午5:57:20
	 * @author:bagen
	 *
	 * @param agentId
	 * @param wBean
	 * @param result
	 * @throws Exception
	 */
	public void createPicMessageLog(String agentId,Article wBean, String result) throws Exception{
		MessageLogsBean logBean = new MessageLogsBean();
		logBean.setAgentId(NumberHelper.stringToInt(agentId));
		logBean.setmId(0);
		logBean.setTitle(wBean.getTitle());
		logBean.setLink(wBean.getUrl());
		logBean.setType(wBean.getMsgType());
		if(!"".equals(StringHelper.toTrim(result))&&!"-1".equals(StringHelper.toTrim(result))){
			ResultBean resultBean = JackJson.fromJsonToObject(result, ResultBean.class);
			logBean.setBackMsg(resultBean.getErrmsg());
			logBean.setBackCode(resultBean.getErrcode());
		}else{
			logBean.setBackMsg("未转换应用ID，并且未调用向客服消息接口post接口");
			logBean.setBackCode("-1");
		}
		logBean.setCreateTime(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME));
		logDao.insertBean(BeanHelper.beanToMap(logBean), CommonTable.MESSAGE_LOGS, "id");
	}
}
