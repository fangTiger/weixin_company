/*  
 * @(#) MessageLogBean.java Create on 2016-6-20 下午5:15:27   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.log.bean;

import cn.system.basic.global.bean.BaseBean;

/**
 * 消息日志表
 * @author liweiwei
 * @date   2016-6-20
 */
public class MessageLogsBean extends BaseBean{
	private static final long serialVersionUID = -2617197538829823116L;

	private Integer id;
	private Integer agentId;//应用Id
	private Integer mId;//消息Id
	private String title;//标题
	private String link;//链接
	private String type;//消息类型
	private String backMsg;//返回错误信息
	private String backCode;//返回错误代码
	private String createTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAgentId() {
		return agentId;
	}
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}
	public Integer getmId() {
		return mId;
	}
	public void setmId(Integer mId) {
		this.mId = mId;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBackMsg() {
		return backMsg;
	}
	public void setBackMsg(String backMsg) {
		this.backMsg = backMsg;
	}
	public String getBackCode() {
		return backCode;
	}
	public void setBackCode(String backCode) {
		this.backCode = backCode;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
