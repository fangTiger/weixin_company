/*  
 * @(#) CommonData.java Create on 2016-4-6 下午4:49:54   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.report.common;

import cn.tools.PropertiesUtil;

/**
 * 166报告常量
 * @author bagen
 * @date   2016-6-23 下午4:40:31
 */
public class ReportCommonData {

	/*图片网址链接*/
	public static final String  PIC_HTTP= "http://file.xlmediawatch.com/pics/";
	//word中显示图片的最大宽度
	public static final double WEIGH = 300d;
	//word中显示图片的最大高度
	public static final double HEIGHT = 400d;
	
	public static final String LOGO_PROPERTIES = "logo.properties";
	
	public static final String CLLIPINGAPI_PROPERTIES = "cllipingApi.properties";
	
	public static String getLogo166(String agentId) {
		return PropertiesUtil.getProperty(LOGO_PROPERTIES, agentId);
	}

	public static String getLogoXL(String agentId) {
		return PropertiesUtil.getProperty(LOGO_PROPERTIES, agentId);
	}
	
	/** 联合利华周报列表URL */
	public static String unileverWeekListUrl = PropertiesUtil.getProperty(CLLIPINGAPI_PROPERTIES, "unileverWeekList");
	/** 联合利华周报总数URL */
	public static String unileverWeekCountUrl = PropertiesUtil.getProperty(CLLIPINGAPI_PROPERTIES, "unileverWeekCount");
	/** 联合利华报告列表URL */
	public static String unileverReportListUrl = PropertiesUtil.getProperty(CLLIPINGAPI_PROPERTIES, "unileverReportList");
	/** 联合利华报告总数URL */
	public static String unileverReportCountUrl = PropertiesUtil.getProperty(CLLIPINGAPI_PROPERTIES, "unileverReportCount");
	/** 联合利华最终页面URL */
	public static String unileverWeekDetailUrl = PropertiesUtil.getProperty(CLLIPINGAPI_PROPERTIES, "unileverWeekDetail");
	/** 联合利华下载实体 */
	public static String unileverDownloadUrl = PropertiesUtil.getProperty(CLLIPINGAPI_PROPERTIES, "unileverDownloadBean");
	
	
	/** 获取联合利华周报列表  */
	public static String getUnileverWeekList(String pageNo, String pageSize) {
		return unileverWeekListUrl.replace("PAGENO", pageNo)
				.replace("PAGESIZE", pageSize);
	}
	
	/** 获取联合利华报告列表 */
	public static String getUnilevelReportList(String pageNo, String pageSize, String did) {
		return unileverReportListUrl.replace("PAGENO", pageNo)
				.replace("PAGESIZE", pageSize)
				.replace("DID", did);
	}
	
	/** 获取联合利华报告列表 */
	public static String getUnilevelWeekDetail(String weekId) {
		return unileverWeekDetailUrl.replace("WEEKID", weekId);
	}
	
	/** 获取联合利华下载实体 */
	public static String getUnilevelDownLoadBean(String did) {
		return unileverDownloadUrl.replace("DID", did);
	}

}
