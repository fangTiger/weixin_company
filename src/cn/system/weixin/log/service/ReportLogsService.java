/*  
 * @(#) ReportLogService.java Create on 2016-5-3 上午11:29:38   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.log.service;

import cn.system.basic.global.BaseService;

/**
 * 查看日报日志管理
 * @author zhangshiyuan
 * @date   2016-5-3 上午11:29:50
 */
public interface ReportLogsService extends BaseService {

	/**
	 * 插入日志
	 * @Title: insertLog
	 * @data:2016-5-3下午12:05:15
	 * @author:xinlian
	 *
	 * @param openId
	 * @param orgId
	 * @param reportId
	 * @param state（1-点击日报订阅菜单 2-加载文章-报告列表 3-默认选择唯一机构 4-多机构中主动选择机构 5-选择报告ID）
	 * @throws Exception
	 */
	public void insertLog(String fromUser,String agentId,String reportId,int state) throws Exception;
	
}
