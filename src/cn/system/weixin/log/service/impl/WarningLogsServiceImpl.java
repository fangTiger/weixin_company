/*  
 * @(#) ReportLogServiceImpl.java Create on 2016-5-3 上午11:39:17   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.log.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.system.basic.global.BaseDao;
import cn.system.basic.global.baseAbstract.BaseServiceA;
import cn.system.weixin.common.CommonTable;
import cn.system.weixin.log.bean.WarningLogsBean;
import cn.system.weixin.log.dao.WarningLogsDao;
import cn.system.weixin.log.service.WarningLogsService;
import cn.tools.BeanHelper;
import cn.tools.DateHelper;

/**
 * 预警日志管理
 * @author liweiwei
 * @date   2016-6-23 上午11:15:24
 */
@Service
public class WarningLogsServiceImpl extends BaseServiceA implements WarningLogsService {

	@Resource
	private WarningLogsDao dao;

	/*
	 * 插入日志(non-Javadoc)
	 * @see cn.system.weixin.service.WarningLogService#insertLog(java.lang.String, java.lang.String, java.lang.String, int)
	 * @param openId
	 * @param orgId
	 * @param reportId
	 * @param state
	 * @throws Exception
	 * @date: 2016-5-4上午9:46:25
	 * @author: liweiwei
	 */
	@Override
	public void insertLog(String fromUser, String agentId, String levelId,String reportId,String typeValue,int state) throws Exception {
		WarningLogsBean logBean = new WarningLogsBean();
		logBean.setAgentId(agentId);//机构ID
		logBean.setReportId(reportId);//报告ID
		logBean.setFromUser(fromUser);//微信用户唯一标识
		logBean.setTypeValue(typeValue);//预警类型
		logBean.setLevelId(levelId);
		logBean.setState(state);//状态（1-点击预警订阅菜单 2-点击查看预警图文 3-默认选择唯一机构 4-多机构中主动选择机构 5-选择预警ID
		logBean.setCreateTime(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME));//创建时间
		this.dao.insertBean(BeanHelper.beanToMap(logBean), CommonTable.WARNING_LOGS, "id");
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getDao() {
		return null;
	}
}
