/*  
 * @(#) UnileverDownloadDao.java Create on 2016-3-18 下午4:01:01   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.report.dao;

import java.util.List;

import cn.system.basic.global.BaseDao;
import cn.system.weixin.report.bean.UnileverDownloadBean;

/**
 * 联合利华下载管理
 * @author bagen
 * @date   2016-6-23 下午4:30:16
 */
public interface UnileverDownloadDao  extends BaseDao<UnileverDownloadBean>{
	
	/**
	 * 根据时间获取列表
	 * @Title: getListByTime
	 * @data:2016-6-23下午4:30:31
	 * @author:bagen
	 *
	 * @param startTime
	 * @param endTime
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<UnileverDownloadBean> getListByTime(String startTime,
			String endTime,int pageNo,int pageSize)throws Exception;
	
	/**
	 * 统计总列表数
	 * @Title: getCountByTime
	 * @data:2016-6-23下午4:30:38
	 * @author:bagen
	 *
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public int getCountByTime(String startTime,String endTime)throws Exception;
}
