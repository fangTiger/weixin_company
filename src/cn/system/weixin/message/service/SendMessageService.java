/*  
 * @(#) SendMessageService.java Create on 2016-6-20 下午4:11:43   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.message.service;

import java.util.List;

import cn.system.basic.global.BaseService;
import cn.system.weixin.init.bean.resp.Article;
import cn.system.weixin.warning.bean.WarningBean;

/**
 * 消息推送
 * @author liweiwei
 * @date   2016-6-20
 */
public interface SendMessageService extends BaseService{

	/**
	 * 发送消息至应用
	 * @Title: sendMessage
	 * @data:2016-6-20下午5:08:35
	 * @author:liweiwei
	 *
	 * @param agentId
	 * @param list
	 * @param accessToken
	 * @param prefix
	 * @return
	 * @throws Exception
	 */
	public String sendAgentMsg(String cId,String agentId, List<WarningBean> list, String prefix)throws Exception;
	
	/**
	 * 推送图文消息至应用
	 * @Title: sendAgentPicMsg
	 * @data:2016-8-9下午5:49:31
	 * @author:bagen
	 *
	 * @param cId
	 * @param list
	 * @param accessToken
	 * @param prefix
	 * @return
	 * @throws Exception
	 */
	public String sendAgentPicMsg(String cId, String agentId, List<Article> list)throws Exception;

}
