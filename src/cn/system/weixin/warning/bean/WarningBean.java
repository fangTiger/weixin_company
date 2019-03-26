/*  
 * @(#) WarningBean.java Create on 2014-9-12 下午2:25:21   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.warning.bean;

import cn.system.basic.global.bean.BaseBean;

/**
 * 预警信息表
 * @author liweiwei
 * @date   2016-6-20 下午5:05:24
 */
public class WarningBean extends BaseBean {

	private static final long serialVersionUID = 1L;
	
	private String id;//预警id
	private String wid;//wid
	private String type;//预警类型
	private String title;//预警标题
	private String link;//原网链接(若此值不空,优先出此值)
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	
}
