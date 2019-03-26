/*  
 * @(#) UnileverDownloadBean.java Create on 2013-11-29 下午04:30:29   
 *   
 * Copyright 2013 by xl.   
 */


package cn.system.weixin.report.bean;

import cn.system.basic.global.bean.BaseBean;

/**
 * 联合利华下载实体
 * @author bagen
 * @date   2016-6-23 下午4:29:49
 */
public class UnileverDownloadBean extends BaseBean{
	private static final long serialVersionUID = -1192623149167980335L;
	
	private Integer id;
	private String fileName;//文件名称
	private String path;//路径
	private Integer reportClass;//报告类型 1：日报 2：周报 3：月报 4：季报 5：年报 6 : 微信报告
	private Integer creator;
	private String createTime;
	
	private String tableName;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getReportClass() {
		return reportClass;
	}
	public void setReportClass(Integer reportClass) {
		this.reportClass = reportClass;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
