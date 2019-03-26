/*  
 * @(#) ReportLogService.java Create on 2016-5-3 上午11:29:38   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.log.service;

import cn.system.basic.global.BaseService;

/**
 * 查看预警日志管理
 * @author liweiwei
 * @date   2016-5-4 上午9:44:26
 */
public interface WarningLogsService extends BaseService {

	/**
	 * 插入日志
	 * @Title: insertLog
	 * @data:2016-5-4上午9:44:39
	 * @author:liweiwei
	 *
	 * @param openId
	 * @param orgId
	 * @param reportId
	 * @param state 状态（1-点击预警订阅菜单 2-点击查看预警图文 3-默认选择唯一机构 4-多机构中主动选择机构 5-选择预警ID
	 * @throws Exception
	 */
	public void insertLog(String fromUser,String agentId,String levelId,String reportId,String typeValue,int state) throws Exception;
	
}
