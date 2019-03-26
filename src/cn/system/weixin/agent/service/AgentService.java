/*  
 * @(#) AgentService.java Create on 2016-6-15 下午1:43:40   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.agent.service;

import cn.system.basic.global.BaseService;

/**
 * 企业号应用管理
 * @author zhangshiyuan
 * @date   2016-6-15
 */
public interface AgentService extends BaseService {
	
	/**
	 * 获取secret所在管理组内的应用概况，会返回管理组内应用的id及名称、头像等信息
	 * @Title: findAgentList
	 * @data:2016-6-15下午1:44:21
	 * @author:zhangshiyuan
	 *
	 * @return
	 * @throws Exception
	 */
	public String findAgentList() throws Exception;

}
