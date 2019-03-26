/*  
 * @(#) WarningAction.java Create on 2016-6-23 上午10:17:06   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.warning.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.system.basic.global.baseAbstract.BaseAction;
import cn.system.weixin.common.CommoniNewsUrl;
import cn.system.weixin.log.service.WarningLogsService;
import cn.system.weixin.util.HttpUtil;
import cn.system.weixin.util.WeixinUtil;
import cn.tools.CommonSendMeg;
import cn.tools.DateHelper;
import cn.tools.NumberHelper;
import cn.tools.jackjson.JackJson;

/**
 * 预警信息
 * @author liweiwei
 * @date   2016-6-23
 */
@Controller
@Scope("prototype")
public class WarningAction extends BaseAction{
	private static final long serialVersionUID = -3315060028793588664L;
	
	@Resource
	private WarningLogsService logsService;
	
	private String cid;
	private String agentId;
	private String wlevelId;
	private String warningId;
	private String typeValue;
	private String dateTime;
	
	/**
	 * 跳转至预警级别列表
	 * @Title: toWaringLevel
	 * @data:2016-6-23上午11:51:46
	 * @author:liweiwei
	 *
	 * @return
	 * @throws Exception
	 */
	public String toWaringLevel() throws Exception{
		agentId = this.getFromRequestParameter("agentId");
		cid = WeixinUtil.getCIdByAgentId(NumberHelper.stringToInt(agentId))+"";
		logsService.insertLog(cid, agentId, null, null, null, 1);//记录用户选择的预警ID
		return "to_wLevel";
	}
	
	/**
	 * 获取预警级别列表
	 * @Title: gettoWLevel
	 * @data:2016-6-23上午11:39:59
	 * @author:liweiwei
	 * 
	 * @throws Exception
	 */
	public void getWaringLevel() throws Exception{
		cid = this.getFromRequestParameter("cid");
		String msg = HttpUtil.httpRequestGetJson(CommoniNewsUrl.getGetWlevelsByCid(cid), "GET", null);
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(msg));
	}
	
	/**
	 * 跳转至预警信息列表
	 * @Title: toWarningList
	 * @data:2016-6-23上午11:51:20
	 * @author:liweiwei
	 *
	 * @return
	 * @throws Exception
	 */
	public String toWarningList() throws Exception{
		agentId = this.getFromRequestParameter("agentId");
		wlevelId = this.getFromRequestParameter("wlevelId");
		cid = this.getFromRequestParameter("cid");
		dateTime = DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME);
		logsService.insertLog(cid, agentId, wlevelId, null, null, 2);//记录用户主动选择机构
		return "to_wList";
	}
	
	/**
	 * 获取预警信息列表
	 * @Title: getWarningList
	 * @data:2016-6-23上午11:50:27
	 * @author:liweiwei
	 *
	 * @throws Exception
	 */
	public void getWarningList() throws Exception{
		cid = this.getFromRequestParameter("cid");
		wlevelId = this.getFromRequestParameter("wlevelId");
		String type = this.getFromRequestParameter("type"); 
		String pageNo = this.getFromRequestParameter("pageNo");
		String pageSize = this.getFromRequestParameter("pageSize");
		dateTime = this.getFromRequestParameter("dateTime");
		String msg = HttpUtil.httpRequestGetJson(CommoniNewsUrl.getGetWlistByCid(cid,type,dateTime.replace(" ", "T"),wlevelId,pageNo,pageSize), "GET", null);
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(msg));
	}
	
	/**
	 * 跳转至预警详细内容页
	 * @Title: toWarning
	 * @data:2016-6-23上午11:27:16
	 * @author:liweiwei
	 *
	 * @return
	 * @throws Exception
	 */
	public String toWarningInfo() throws Exception{
		warningId = this.getFromRequestParameter("warningId");
		typeValue = this.getFromRequestParameter("typeValue");
		cid = this.getFromRequestParameter("cid");
		agentId = this.getFromRequestParameter("agentId");
		wlevelId = this.getFromRequestParameter("wlevelId");
		logsService.insertLog(cid, agentId, wlevelId, warningId, typeValue, 3);//记录用户选择的预警ID
		return "to_warning";
	}
	
	/**
	 * 获取预警详细内容
	 * @Title: getWarningInfo
	 * @data:2016-6-23上午11:51:59
	 * @author:liweiwei
	 *
	 * @throws Exception
	 */
	public void getWarningInfo() throws Exception{
		warningId = this.getFromRequestParameter("warningId");
		typeValue = this.getFromRequestParameter("type");
		String msg = HttpUtil.httpRequestGetJson(CommoniNewsUrl.getGetWinfoByWid(warningId,typeValue), "GET", null);
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(msg));
	}
	
	public static void main(String[] args) {
		String msg = HttpUtil.httpRequestGetJson(CommoniNewsUrl.getGetWinfoByWid("4060519105455671","weibo"), "GET", null);
		System.out.println(CommoniNewsUrl.getGetWinfoByWid("4060519105455671","weibo"));
		System.out.println(msg);
		
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	
	public String getWlevelId() {
		return wlevelId;
	}

	public void setWlevelId(String wlevelId) {
		this.wlevelId = wlevelId;
	}

	public String getWarningId() {
		return warningId;
	}

	public void setWarningId(String warningId) {
		this.warningId = warningId;
	}

	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
