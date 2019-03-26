/*  
 * @(#) DailyReportAction.java Create on 2016-1-22 下午5:25:20   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.report.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.system.basic.global.baseAbstract.BaseAction;
import cn.system.weixin.common.CommonClient;
import cn.system.weixin.common.CommoniNewsUrl;
import cn.system.weixin.log.service.ReportLogsService;
import cn.system.weixin.report.service.ReportService;
import cn.system.weixin.util.HttpUtil;
import cn.system.weixin.util.WeixinUtil;
import cn.tools.CommonSendMeg;
import cn.tools.NumberHelper;
import cn.tools.jackjson.JackJson;

/**
 * 查看报告
 * @author bagen
 * @date   2016-6-23 下午5:07:29
 */
@Controller
@Scope("prototype")
public class ReportAction extends BaseAction {
	private static final long serialVersionUID = -7577857107493619122L;
	
	private String fromUser;//推送用户
	private String agentId;//应用ID
	private String reportId;//报告ID
	private String messageId;//历史报文ID
	private String weekId;
	private String weekName;
	
	@Resource
	private ReportService service;
	@Resource
	private ReportLogsService reportLogService;
	
	/**
	 * 跳转至日报标题页面
	 * @Title: toTitleList
	 * @data:2016-6-23下午3:59:48
	 * @author:bagen
	 *
	 * @return
	 * @throws Exception
	 */
	public String toTitleList() throws Exception{
		agentId = this.getFromRequestParameter("agentId");
		fromUser = WeixinUtil.getCIdByAgentId(NumberHelper.stringToInt(agentId))+"";
//		fromUser = this.getFromRequestParameter("agentId");
		reportLogService.insertLog(fromUser, agentId, null, 1);//1-点击日报订阅菜单 2-加载文章-报告列表 3-选择报告ID
		if(fromUser.equals(CommonClient.CLIENT_UNILEVER)){
			return "report_week_list";
		}else {
			return "inews_message_list";
		}
	}
	
	/**
	 * 获取166下载报告（周报）列表
	 * @Title: getReportDownloadList
	 * @data:2016-6-23下午3:58:45
	 * @author:bagen
	 *
	 * @throws Exception
	 */
	//TODO 第一个周报日期列表
	public void getReportDownloadList() throws Exception{
		String pageNow = this.getFromRequestParameter("pageNo");
		String pageSizeNum = this.getFromRequestParameter("pageSize");
		String msg = service.getUnileverDownload(pageNow,pageSizeNum);
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(msg));
	}
	
	/**
	 * 跳转至166周报数据列表页
	 * @Title: toReportList
	 * @data:2016-6-23下午4:11:13
	 * @author:bagen
	 *
	 * @return
	 * @throws Exception
	 */
	public String toReportList() throws Exception{
		ServletActionContext.getRequest().getSession().removeAttribute("weekId");
		reportId = this.getFromRequestParameter("reportId");
		agentId = this.getFromRequestParameter("agentId");
		fromUser = this.getFromRequestParameter("fromUser");
		reportLogService.insertLog(fromUser, agentId, reportId, 2);//1-点击日报订阅菜单 2-加载文章-报告列表 3-选择报告ID
		weekName = service.getUnileverDownloadBean(reportId);
		return "report_list";
	}
	
	/**
	 * 获取166周报列表信息
	 * @Title: getReportList
	 * @data:2016-6-23下午5:06:19
	 * @author:bagen
	 *
	 * @throws Exception
	 */
	//TODO 第二个分类列表方法
	public void getReportList() throws Exception{
		String pageNow = this.getFromRequestParameter("pageNo");
		String pageSizeNum = this.getFromRequestParameter("pageSize");
		reportId = this.getFromRequestParameter("reportId");
		String msg = service.getUnileverWeekList(reportId,pageNow,pageSizeNum);
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(msg));
	}
	
	/**
	 * 跳转至166周报详细页面
	 * @Title: toUnileverReportDetail
	 * @data:2016-6-23下午5:05:27
	 * @author:bagen
	 *
	 * @return
	 * @throws Exception
	 */
	public String toReportDetail() throws Exception{
		weekId = this.getFromRequestParameter("weekId");
		fromUser = this.getFromRequestParameter("fromUser");
		agentId = this.getFromRequestParameter("agentId");
		weekName = this.getFromRequestParameter("weekName");
		reportLogService.insertLog(fromUser, agentId, null, 3);//1-点击日报订阅菜单 2-加载文章-报告列表 3-选择报告ID
		return "to_report_detail";
	}
	
	/**
	 * 获取166周报详细信息
	 * @Title: getWeekInfo
	 * @data:2016-6-23下午5:55:34
	 * @author:bagen
	 *
	 * @throws Exception
	 */
	//TODO 第三个详细页面方法
	@SuppressWarnings("static-access")
	public void getReportDetailInfo() throws Exception{
		weekId = this.getFromRequestParameter("weekId");
		String msg = service.getUnileverWeekBean(weekId);
		Integer flag =  (Integer) this.getFromSession("isHistoryBack");
		if (flag!=null&&flag==1 ){//如果isHistoryBack的session有值且为1
			this.putToSession("isHistoryBack", 0);	
		}else{
			this.putToSession("isHistoryBack", 1);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(msg));
	}

	/**
	 * 获取历史报文列表
	 * @Title: getInewsReportList
	 * @data:2016-6-24上午9:12:46
	 * @author:bagen
	 *
	 * @throws Exception
	 */
	public void getInewsReportList() throws Exception{
		fromUser = this.getFromRequestParameter("fromUser");
		String msg = HttpUtil.httpRequestGetJson(CommoniNewsUrl.getGetReportsByCid(fromUser), "GET", null);
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(msg));
	}
	
	/**
	 * 跳转至历史报文下报告信息列表
	 * @Title: toInewsReportList
	 * @data:2016-6-24上午9:44:58
	 * @author:bagen
	 *
	 * @return
	 * @throws Exception
	 */
	public String toInewsReportList() throws Exception{
		messageId = this.getFromRequestParameter("messageId");
		fromUser = this.getFromRequestParameter("fromUser");
		agentId = this.getFromRequestParameter("agentId");
		weekName = this.getFromRequestParameter("messageName");
		reportLogService.insertLog(fromUser, agentId, messageId, 2);//1-点击日报订阅菜单 2-加载文章-报告列表 3-选择报告ID
		return "inews_report_list";
	}
	
	/**
	 * 查询历史报文下报告信息列表接口
	 * @Title: getInewsRlistByRid
	 * @data:2016-6-24上午9:39:01
	 * @author:bagen
	 *
	 * @throws Exception
	 */
	public void getInewsRlistByRid() throws Exception{
		String messageId = this.getFromRequestParameter("messageId");//历史报文ID
		String pageNow = this.getFromRequestParameter("pageNo");
		String pageSizeNum = this.getFromRequestParameter("pageSize");
		String msg = HttpUtil.httpRequestGetJson(CommoniNewsUrl.getGetRlistByRid(messageId, pageNow, pageSizeNum), "GET", null);
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(msg));
	}
	
	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
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

	public String getWeekId() {
		return weekId;
	}

	public void setWeekId(String weekId) {
		this.weekId = weekId;
	}

	public String getWeekName() {
		return weekName;
	}

	public void setWeekName(String weekName) {
		this.weekName = weekName;
	}

	public ReportService getService() {
		return service;
	}

	public void setService(ReportService service) {
		this.service = service;
	}

	public ReportLogsService getReportLogService() {
		return reportLogService;
	}

	public void setReportLogService(ReportLogsService reportLogService) {
		this.reportLogService = reportLogService;
	}
}
