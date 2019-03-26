/*  
 * @(#) TodayReportService.java Create on 2016-3-18 下午3:48:01   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.report.service;

import cn.system.basic.global.BaseService;

/**
 * 日报订阅管理
 * @author liweiwei
 * @date   2016-3-18
 */
public interface ReportService  extends BaseService{
	
	/**
	 * 获取联合利华下载信息列表
	 * @Title: getUnilever
	 * @data:2016-3-18下午3:52:37
	 * @author:liweiwei
	 *
	 * @return
	 * @throws Exception
	 */
	public String getUnileverDownload(String pageNo,String pageSize)throws Exception;
	
	/**
	 * 获取联合利华周报数据
	 * @Title: getUnileverWeek
	 * @data:2016-3-18下午3:54:05
	 * @author:liweiwei
	 *
	 * @return
	 * @throws Exception
	 */
	public String getUnileverWeekList(String did,String pageNo,String pageSize)throws Exception;
	
	/**
	 * 获取联合利华周报单个文章
	 * @Title: getUnileverWeekBean
	 * @data:2016-3-18下午3:55:33
	 * @author:liweiwei
	 *
	 * @param weekId
	 * @return
	 * @throws Exception
	 */
	public String getUnileverWeekBean(String weekId)throws Exception;

	/**
	 * 获取报告名称
	 * @Title: getUnileverDownloadBean
	 * @data:2016-3-22下午5:23:00
	 * @author:liweiwei
	 *
	 * @param did
	 * @return
	 */
	public String getUnileverDownloadBean(String did)throws Exception;

	
}
