/*  
 * @(#) LogUtil.java Create on 2014-11-20 上午10:44:33   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.util;

import cn.tools.DateHelper;

/**
 * 统一输出格式
 * @author zhangshiyuan
 * @date   2016-6-15 下午1:58:55
 */
public class LogUtil {

	/**
	 * System.out.print方式输出
	 * @Title: outPrint
	 * @data:2016-6-15下午1:59:08
	 * @author:zhangshiyuan
	 *
	 * @param title
	 * @param url
	 * @param resultMsg
	 */
	public static void outPrint(String title,String url,String resultMsg){
		System.out.println(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME)+
				" [ "+title+" ] url:{"+url+"} , msg:{"+resultMsg+"} ");
	}
	
}