/*  
 * @(#) BackCodeUtil.java Create on 2014-5-28 下午2:57:59   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.util;

/**
 * 全局返回码工具类
 * @author zhangshiyuan
 * @date   2014-5-28
 */
public class BackCodeUtil {
	
	/**
	 * 根据返回码获得实际意义
	 * @Title: getCodeMean
	 * @data:2014-5-28下午3:38:26
	 * @author:zhangshiyuan
	 *
	 * @param backCode
	 * @return
	 * @throws Exception
	 */
	public static String getCodeMean(String backCode) throws Exception{
		//不保证微信提供的返回码是否永远是纯数字,故此处未用switch
		String codeMean = "未知的返回码:"+backCode;
		if("-1".equals(backCode))
			codeMean = "系统繁忙";
		else if("0".equals(backCode))
			codeMean = "请求成功";
		else if("40001".equals(backCode))
			codeMean = "获取access_token时AppSecret错误，或者access_token无效";
		else if("40002".equals(backCode))
			codeMean = "不合法的凭证类型";
		else if("40003".equals(backCode))
			codeMean = "不合法的OpenID";
		else if("40004".equals(backCode))
			codeMean = "不合法的媒体文件类型";
		else if("40005".equals(backCode))
			codeMean = "不合法的文件类型";
		else if("40006".equals(backCode))
			codeMean = "不合法的文件大小";
		else if("40007".equals(backCode))
			codeMean = "不合法的媒体文件id";
		else if("40008".equals(backCode))
			codeMean = "不合法的消息类型";
		else if("40009".equals(backCode))
			codeMean = "不合法的图片文件大小";
		else if("40010".equals(backCode))
			codeMean = "不合法的语音文件大小";
		else if("40011".equals(backCode))
			codeMean = "不合法的视频文件大小";
		else if("40012".equals(backCode))
			codeMean = "不合法的缩略图文件大小";
		else if("40013".equals(backCode))
			codeMean = "不合法的APPID";
		else if("40014".equals(backCode))
			codeMean = "不合法的access_token";
		else if("40015".equals(backCode))
			codeMean = "不合法的菜单类型";
		else if("40016".equals(backCode))
			codeMean = "不合法的按钮个数";
		else if("40017".equals(backCode))
			codeMean = "不合法的按钮个数";
		else if("40018".equals(backCode))
			codeMean = "不合法的按钮名字长度";
		else if("40019".equals(backCode))
			codeMean = "不合法的按钮KEY长度";
		else if("40020".equals(backCode))
			codeMean = "不合法的按钮URL长度";
		else if("40021".equals(backCode))
			codeMean = "不合法的菜单版本号";
		else if("40022".equals(backCode))
			codeMean = "不合法的子菜单级数";
		else if("40023".equals(backCode))
			codeMean = "不合法的子菜单按钮个数";
		else if("40024".equals(backCode))
			codeMean = "不合法的子菜单按钮类型";
		else if("40025".equals(backCode))
			codeMean = "不合法的子菜单按钮名字长度";
		else if("40026".equals(backCode))
			codeMean = "不合法的子菜单按钮KEY长度";
		else if("40027".equals(backCode))
			codeMean = "不合法的子菜单按钮URL长度";
		else if("40028".equals(backCode))
			codeMean = "不合法的自定义菜单使用用户";
		else if("40029".equals(backCode))
			codeMean = "不合法的oauth_code";
		else if("40030".equals(backCode))
			codeMean = "不合法的refresh_token";
		else if("40031".equals(backCode))
			codeMean = "不合法的openid列表";
		else if("40032".equals(backCode))
			codeMean = "不合法的openid列表长度";
		else if("40033".equals(backCode))
			codeMean = "不合法的请求字符，不能包含\\uxxxx格式的字符";
		else if("40035".equals(backCode))
			codeMean = "不合法的参数";
		else if("40038".equals(backCode))
			codeMean = "不合法的请求格式";
		else if("40039".equals(backCode))
			codeMean = "不合法的URL长度";
		else if("40050".equals(backCode))
			codeMean = "不合法的分组id";
		else if("40051".equals(backCode))
			codeMean = "分组名字不合法";
		else if("41001".equals(backCode))
			codeMean = "缺少access_token参数";
		else if("41002".equals(backCode))
			codeMean = "缺少appid参数";
		else if("41003".equals(backCode))
			codeMean = "缺少refresh_token参数";
		else if("41004".equals(backCode))
			codeMean = "缺少secret参数";
		else if("41005".equals(backCode))
			codeMean = "缺少多媒体文件数据";
		else if("41006".equals(backCode))
			codeMean = "缺少media_id参数";
		else if("41007".equals(backCode))
			codeMean = "缺少子菜单数据";
		else if("41008".equals(backCode))
			codeMean = "缺少oauth code";
		else if("41009".equals(backCode))
			codeMean = "缺少openid";
		else if("42001".equals(backCode))
			codeMean = "access_token超时";
		else if("42002".equals(backCode))
			codeMean = "refresh_token超时";
		else if("42003".equals(backCode))
			codeMean = "oauth_code超时";
		else if("43001".equals(backCode))
			codeMean = "需要GET请求";
		else if("43002".equals(backCode))
			codeMean = "需要POST请求";
		else if("43003".equals(backCode))
			codeMean = "需要HTTPS请求";
		else if("43004".equals(backCode))
			codeMean = "需要接收者关注";
		else if("43005".equals(backCode))
			codeMean = "需要好友关系";
		else if("44001".equals(backCode))
			codeMean = "多媒体文件为空";
		else if("44002".equals(backCode))
			codeMean = "POST的数据包为空";
		else if("44003".equals(backCode))
			codeMean = "图文消息内容为空";
		else if("44004".equals(backCode))
			codeMean = "文本消息内容为空";
		else if("45001".equals(backCode))
			codeMean = "多媒体文件大小超过限制";
		else if("45002".equals(backCode))
			codeMean = "消息内容超过限制";
		else if("45003".equals(backCode))
			codeMean = "标题字段超过限制";
		else if("45004".equals(backCode))
			codeMean = "描述字段超过限制";
		else if("45005".equals(backCode))
			codeMean = "链接字段超过限制";
		else if("45006".equals(backCode))
			codeMean = "图片链接字段超过限制";
		else if("45007".equals(backCode))
			codeMean = "语音播放时间超过限制";
		else if("45008".equals(backCode))
			codeMean = "图文消息超过限制";
		else if("45009".equals(backCode))
			codeMean = "接口调用超过限制";
		else if("45010".equals(backCode))
			codeMean = "创建菜单个数超过限制";
		else if("45015".equals(backCode))
			codeMean = "回复时间超过限制";
		else if("45016".equals(backCode))
			codeMean = "系统分组，不允许修改";
		else if("45017".equals(backCode))
			codeMean = "分组名字过长";
		else if("45018".equals(backCode))
			codeMean = "分组数量超过上限";
		else if("46001".equals(backCode))
			codeMean = "不存在媒体数据";
		else if("46002".equals(backCode))
			codeMean = "不存在的菜单版本";
		else if("46003".equals(backCode))
			codeMean = "不存在的菜单数据";
		else if("46004".equals(backCode))
			codeMean = "不存在的用户";
		else if("47001".equals(backCode))
			codeMean = "解析JSON/XML内容错误";
		else if("48001".equals(backCode))
			codeMean = "api功能未授权";
		else if("50001".equals(backCode))
			codeMean = "用户未授权该api";
		return codeMean;
	}
	
}