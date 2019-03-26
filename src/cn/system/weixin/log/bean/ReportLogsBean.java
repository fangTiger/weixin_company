/*  
 * @(#) ReportLogsBean.java Create on 2016-6-23 上午10:48:40   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.log.bean;

import cn.system.basic.global.bean.BaseBean;

/**
 * 报告操作日志表
 * @author liweiwei
 * @date   2016-6-23
 */
public class ReportLogsBean extends BaseBean{
	private static final long serialVersionUID = -8004399746861587931L;

	private Integer id;
	private Integer state;//状态（1-点击日报订阅菜单 2-加载文章-报告列表 3-选择报告ID（inews报告无报告ID，直接打开报告地址，故此处无记录，仅记录166报告ID）
	private String fromUser;//推送用户
	private String agentId;//应用ID
	private String reportId;//报告ID
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
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
