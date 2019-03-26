/*  
 * @(#) CommonTable.java Create on 2016-1-20 上午10:08:11   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.common;

/**
 * 微信后台管理数据库数据表信息
 * @author liweiwei
 * @date   2016-1-20
 */
public class CommonTable {

	/*
	 * 数据库名称
	 */
	private static final String DATA_NAME = "company_manage.";
	
	/**
	 * 消息日志表
	 */
	public static final String MESSAGE_LOGS = DATA_NAME + "message_logs";
	
	/**
	 * 报告日志表
	 */
	public static final String REPORT_LOGS = DATA_NAME + "report_logs";
	
	/**
	 * 预警日志表
	 */
	public static final String WARNING_LOGS = DATA_NAME + "warning_logs";

}
