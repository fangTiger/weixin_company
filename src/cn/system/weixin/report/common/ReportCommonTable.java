/*  
 * @(#) CommonTable.java Create on 2016-1-20 上午10:08:11   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.report.common;

/**
 * 166数据库数据表信息
 * @author bagen
 * @date   2016-6-23 下午4:38:13
 */
public class ReportCommonTable {

	/**
	 * 166数据库
	 */
	private static final String DATA_NAME = "report_manage.";
	
	/**
	 * 联合利华下载表
	 */
	public static final String UNILEVER_DOWNLOAD = DATA_NAME + "unilever_download";
	
	/**
	 * 联合利华周报信息
	 */
	public static final String UNILEVER_WEEK_INFO = DATA_NAME + "unilever_week_info";
	
	/**
	 * 联合利华分类信息
	 */
	public static final String UNILEVER_CLASS_INFO = DATA_NAME + "unilever_class_info";
}
