/*  
 * @(#) UnileverWeekDaoImpl.java Create on 2016-3-18 下午4:05:32   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.report.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.system.basic.common.ModuleDao;
import cn.system.weixin.report.bean.UnileverWeekBean;
import cn.system.weixin.report.common.ReportCommonTable;
import cn.system.weixin.report.dao.UnileverWeekDao;

/**
 * 联合利华周报管理
 * @author bagen
 * @date   2016-6-23 下午4:32:31
 */
@Repository
public class UnileverWeekDaoImpl extends ModuleDao<UnileverWeekBean> implements UnileverWeekDao{
	
	/*
	 * 根据DId获取文章列表
	 * (non-Javadoc)
	 * @see cn.system.weixin.report.dao.UnileverWeekDao#getListByDid(java.lang.String, int, int)
	 * @param did
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 * @date: 2016-6-23下午4:33:18
	 * @author: bagen
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UnileverWeekBean> getListByDid(String did,int pageNo,int pageSize) throws Exception {
		String sql = "select *,(select `name` from "+ReportCommonTable.UNILEVER_CLASS_INFO+" where id=uwi.firstClassId and deleteFlag = 0) _firstClass from "+ReportCommonTable.UNILEVER_WEEK_INFO+" uwi where uwi.did = ? ";
		sql += " limit " + pageNo * pageSize + "," + pageSize;
		List<UnileverWeekBean> list = this.classJdbcTemplate.query(sql, new Object[]{did}, new BeanPropertyRowMapper(UnileverWeekBean.class));
		return list;
	}
	
	/*
	 * 根据周报Id获取文章信息
	 * (non-Javadoc)
	 * @see cn.system.weixin.dao.UnileverWeekDao#getBeanByWeekId(java.lang.String)
	 * @param weekId
	 * @return
	 * @throws Exception
	 * @date: 2016-3-18下午4:47:55
	 * @author: liweiwei
	 */
	@SuppressWarnings("unchecked")
	@Override
	public UnileverWeekBean getBeanByWeekId(String weekId) throws Exception {
		String sql = "select * from "+ReportCommonTable.UNILEVER_WEEK_INFO+" where id = ? ";
		List<UnileverWeekBean> list = this.classJdbcTemplate.query(sql, new Object[]{weekId}, new BeanPropertyRowMapper(UnileverWeekBean.class));
		return list!=null&&list.size()>0?list.get(0):null;
	}
	
	/*
	 * 获取周报文章统计量
	 * (non-Javadoc)
	 * @see cn.system.weixin.dao.UnileverWeekDao#getCountByTime(java.lang.String, java.lang.String)
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 * @date: 2016-3-18下午6:16:23
	 * @author: liweiwei
	 */
	@Override
	public int getCountByTime(String did)throws Exception {
		String sql = "select count(*) from "+ReportCommonTable.UNILEVER_WEEK_INFO+" where did = ? ";
		int row = this.classJdbcTemplate.queryForInt(sql,new Object[]{did});
		return row;
	}


}
