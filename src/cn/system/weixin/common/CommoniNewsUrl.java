/*  
 * @(#) CommoniNewsUrl.java Create on 2016-6-23 上午9:49:54   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.common;

/**
 * iNews接口常量类
 * @author liweiwei
 * @date   2016-6-23
 */
public class CommoniNewsUrl {

	//iNews接口基础地址
	private final static String URL_BASE = "http://60.247.62.100:8096";
//	private final static String URL_BASE = "http://118.144.32.93:8096";
	//private final static String URL_BASE = "http://192.168.10.198:8096";
	//private final static String URL_BASE = "http://192.168.0.215:8082";
	//获取客户的预警级别列表
	public final static String GET_WLEVEL_BY_CID = URL_BASE + "/api/findwLevelByCId?cId=V_CID";
	
	//获取客户的预警信息列表
	public final static String GET_WLIST_BY_CID = URL_BASE + "/api/findwListByCId?cId=V_CID&type=V_TYPE&lastTime=V_TIME&wLevelId=V_WLEVELID&pageNo=V_PAGENO&pageSize=V_PAGESIZE";

	//获取预警的详细内容
	public final static String GET_WINFO_BY_WID = URL_BASE + "/api/findwInfoById?id=V_ID&type=V_TYPE";
	
	//获取客户下历史报文列表
	public final static String GET_RHISTORY_BY_CID = URL_BASE + "/api/findrHistoryByCId?cId=V_CID";
	
	//获取历史报文下的报告列表
	public final static String GET_RLIST_BY_RID = URL_BASE + "/api/findrListById?id=V_ID&pageNo=V_PAGENO&pageSize=V_pageSize";

	/**
	 * 获取客户下预警级别列表
	 * @Title: getGetWlevelsByCid
	 * @data:2016-6-23上午10:13:14
	 * @author:liweiwei
	 *
	 * @param cId
	 * @return
	 */
	public static String getGetWlevelsByCid(String cId) {
		return GET_WLEVEL_BY_CID.replace("V_CID", cId);
	}

	/**
	 * 获取预警级别下预警信息列表
	 * @Title: getGetWlistByCid
	 * @data:2016-6-23上午10:13:42
	 * @author:liweiwei
	 *
	 * @param cId
	 * @param wLevelId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public static String getGetWlistByCid(String cId,String type,String dateTime,String wLevelId,String pageNo,String pageSize) {
		return GET_WLIST_BY_CID.replace("V_CID", cId)
				.replace("V_TIME", dateTime)
				.replace("V_WLEVELID", wLevelId)
				.replace("V_TYPE", type)
				.replace("V_PAGENO", pageNo)
				.replace("V_PAGESIZE", pageSize);
	}

	/**
	 * 获取预警信息详细内容
	 * @Title: getGetWinfoByWid
	 * @data:2016-6-23上午10:14:12
	 * @author:liweiwei
	 *
	 * @param wId
	 * @param type
	 * @return
	 */
	public static String getGetWinfoByWid(String wId,String type) {
		return GET_WINFO_BY_WID.replace("V_ID", wId)
				.replace("V_TYPE", type);
	}

	/**
	 * 获取客户下历史报文列表
	 * @Title: getGetReportsByCid
	 * @data:2016-6-23上午10:14:37
	 * @author:liweiwei
	 *
	 * @param cId
	 * @return
	 */
	public static String getGetReportsByCid(String cId) {
		return GET_RHISTORY_BY_CID.replace("V_CID", cId);
	}

	/**
	 * 获取历史报文下报告列表
	 * @Title: getGetRlistByRid
	 * @data:2016-6-23上午10:15:01
	 * @author:liweiwei
	 *
	 * @param rId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public static String getGetRlistByRid(String rId,String pageNo,String pageSize) {
		return GET_RLIST_BY_RID.replace("V_ID", rId)
				.replace("V_PAGENO", pageNo)
				.replace("V_pageSize", pageSize);
	}
}
