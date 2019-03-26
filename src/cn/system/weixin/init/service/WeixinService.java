/*  
 * @(#) WeixinService.java Create on 2014-5-28 上午9:28:35   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.init.service;

import javax.servlet.http.HttpServletRequest;

import cn.system.basic.global.BaseService;

/**
 * 微信消息核心处理
 * @author zhangshiyuan
 * @date   2016-6-13 下午3:45:09
 */
public interface WeixinService extends BaseService {

	/**
	 * 处理微信发来的请求
	 * @Title: processRequest
	 * @data:2016-6-13下午3:47:11
	 * @author:zhangshiyuan
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String processRequest(HttpServletRequest request) throws Exception;

}
