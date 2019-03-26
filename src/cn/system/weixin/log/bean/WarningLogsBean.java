/*  
 * @(#) WarningLogsBean.java Create on 2016-6-23 上午10:50:08   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.log.bean;

import cn.system.basic.global.bean.BaseBean;

/**
 * 预警操作日志
 * @author liweiwei
 * @date   2016-6-23
 */
public class WarningLogsBean extends BaseBean{
	private static final long serialVersionUID = -3074625415391957823L;

	private Integer id;
	private Integer state;//状态（1-点击预警菜单 2-点击查看预警图文 3-选择预警ID）
	private String fromUser;//推送用户
	private String agentId;//应用ID
	private String levelId;//预警级别
	private String reportId;//预警ID
	private String typeValue;//预警类型
	private String createTime;//创建时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
