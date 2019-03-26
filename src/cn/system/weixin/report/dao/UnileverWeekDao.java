/*  
 * @(#) UnileverWeekDao.java Create on 2016-3-18 下午4:02:10   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.report.dao;

import java.util.List;

import cn.system.basic.global.BaseDao;
import cn.system.weixin.report.bean.UnileverWeekBean;

/**
 * 联合利华周报管理
 * @author bagen
 * @date   2016-6-23 下午4:30:50
 */
public interface UnileverWeekDao extends BaseDao<UnileverWeekBean>{

	/**
	 * 根据下载Id获取周报信息列表
	 * @Title: getListByDid
	 * @data:2016-6-23下午4:31:05
	 * @author:bagen
	 *
	 * @param reportId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<UnileverWeekBean> getListByDid(String reportId,int pageNo,int pageSize)throws Exception;
	
	/**
	 * 根据id获取文章信息
	 * @Title: getBeanByWeekId
	 * @data:2016-6-23下午4:31:50
	 * @author:bagen
	 *
	 * @param weekId
	 * @return
	 * @throws Exception
	 */
	public UnileverWeekBean getBeanByWeekId(String weekId)throws Exception;
	
	/**
	 * 统计周报数据量
	 * @Title: getCountByTime
	 * @data:2016-6-23下午4:32:03
	 * @author:bagen
	 *
	 * @param reportId
	 * @return
	 * @throws Exception
	 */
	public int getCountByTime(String reportId)throws Exception;
}
