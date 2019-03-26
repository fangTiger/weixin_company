/*  
 * @(#) CommonUrl.java Create on 2016-6-6 下午3:04:01   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.common;

import cn.system.weixin.init.bean.resp.Article;

/**
 * 微信接口地址常量类
 * @author zhangshiyuan
 * @date   2016-6-6
 */
public class CommonWxUrl {
	
	//微信接口基础地址
	private final static String URL_BASE = "https://qyapi.weixin.qq.com/cgi-bin/";
	
	// 获取access_token的接口地址（GET） 限200（次/天）  
	public final static String URL_ACCESS_TONKEN = URL_BASE + "gettoken?corpid=V_CORPID&corpsecret=V_CORPSECRET";
	
	// 主动发消息给成员，每天可发的数量为：帐号上限数*30人次/天
	public final static String URL_MESSAGE_SEND = URL_BASE + "message/send?access_token=ACCESS_TOKEN";
	
	// 获取secret所在管理组内的应用概况，会返回管理组内应用的id及名称、头像等信息
	public final static String URL_AGENT_LIST = URL_BASE + "agent/list?access_token=ACCESS_TOKEN";
	
	// 获取jsapi_ticket的接口地址（GET） 限200（次/天）  
	public final static String URL_JSAPITICKET = URL_BASE + "get_jsapi_ticket?access_token=ACCESS_TOKEN";
	

	/**------------------------常量参数------------------------------**/
	//推送消息JSON参数值
	private final static  String MSG_STR_JSON = "{" +
			"\"touser\": \"V_TOUSERNAME\"," +
			"\"msgtype\": \"V_MSGTYPE\"," +
			"\"agentid\": V_AGENTID," +
			"\"text\": {" +
				"\"content\": \"V_CONTENT\"" +
			"}," +
			"\"safe\":\"V_SAFE\"" +
	"}";


	/**
	 * 获取推送消息JSON参数值
	 * @Title: getMsgStrJson
	 * @data:2016-6-23上午9:24:59
	 * @author:liweiwei
	 *
	 * @param toUserName(成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送 )
	 * @param msgType(媒体类型-目前仅text)
	 * @param agentId(企业应用的id，整型。可在应用的设置页面查看 )
	 * @param content(正文内容)
	 * @param safe(表示是否是保密消息，0表示否，1表示是，默认0 )
	 * @return
	 */
	public static String getMsgStrJson(String toUserName,String msgType,String agentId,String content,String safe) {
		return MSG_STR_JSON.replace("V_TOUSERNAME", toUserName)
				.replace("V_MSGTYPE", msgType)
				.replace("V_AGENTID", agentId)
				.replace("V_CONTENT", content)
				.replace("V_SAFE", safe);
	}
	
	//推送图文消息JSON参数值
	private final static  String PIC_MSG_STR_JSON = "{" +
			"\"touser\": \"V_TOUSERNAME\"," +
			"\"msgtype\": \"V_MSGTYPE\"," +
			"\"agentid\": V_AGENTID," +
			"\"news\": {" +
				"\"articles\": [{" +
						"\"title\": \"V_TITLE\"," +
						"\"description\":\"V_DESCRIPTION\"," +
						"\"picurl\":\"V_PICURL\"," +
						"\"url\":\"V_URL\"" +
				"}]"+
			"}," +
	"}";
	
	/**
	 * 获取推送图文消息JSON参数值
	 * @Title: getMsgStrJson
	 * @data:2016-6-23上午9:24:59
	 * @author:liweiwei
	 *
	 * @param toUserName(成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送 )
	 * @param msgType(媒体类型-目前仅text)
	 * @param agentId(企业应用的id，整型。可在应用的设置页面查看 )
	 * @param content(正文内容)
	 * @param safe(表示是否是保密消息，0表示否，1表示是，默认0 )
	 * @return
	 */
	public static String getPicMsgStrJson(Article bean, String agentId) {
		return PIC_MSG_STR_JSON.replace("V_TOUSERNAME", bean.getToUserName())
				.replace("V_AGENTID", agentId)
				.replace("V_MSGTYPE", bean.getMsgType())
				.replace("V_TITLE", bean.getTitle())
				.replace("V_DESCRIPTION", bean.getDescription())
				.replace("V_PICURL", bean.getPicUrl())
				.replace("V_URL", bean.getUrl());
	}

	
}
