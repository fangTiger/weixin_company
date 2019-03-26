/*  
 * @(#) UnileverDownloadDaoImpl.java Create on 2016-3-18 下午4:03:48   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.report.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.system.basic.common.ModuleDao;
import cn.system.weixin.report.bean.UnileverDownloadBean;
import cn.system.weixin.report.common.ReportCommonTable;
import cn.system.weixin.report.dao.UnileverDownloadDao;

/**
 * 联合利华下载管理
 * @author liweiwei
 * @date   2016-3-18
 */
@Repository
public class UnileverDownloadDaoImpl extends ModuleDao<UnileverDownloadBean> implements UnileverDownloadDao{
	
	/*
	 * 根据时间获取下载列表信息
	 * (non-Javadoc)
	 * @see cn.system.weixin.dao.UnileverDownloadDao#getListByTime(java.lang.String, java.lang.String)
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 * @date: 2016-3-18下午4:17:37
	 * @author: liweiwei
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UnileverDownloadBean> getListByTime(String startTime,
			String endTime,int pageNo,int pageSize) throws Exception {
		String sql = "select * from "+ReportCommonTable.UNILEVER_DOWNLOAD+" where reportClass = 6 and " + this.getSpaceTime(startTime, endTime, "createTime");
		sql += " order by createTime desc  limit " + pageNo * pageSize + "," + pageSize;
		List<UnileverDownloadBean> list = this.classJdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(UnileverDownloadBean.class));
		return list;
	}
	
	/*
	 * 根据时间统计列表
	 * (non-Javadoc)
	 * @see cn.system.weixin.dao.UnileverDownloadDao#getCountByTime(java.lang.String, java.lang.String)
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 * @date: 2016-3-18下午5:25:04
	 * @author: liweiwei
	 */
	@Override
	public int getCountByTime(String startTime, String endTime)
			throws Exception {
		String sql = "select count(*) from "+ReportCommonTable.UNILEVER_DOWNLOAD+" where reportClass = 6 and " + this.getSpaceTime(startTime, endTime, "createTime");
		int row = this.classJdbcTemplate.queryForInt(sql);
		return row;
	}

}
