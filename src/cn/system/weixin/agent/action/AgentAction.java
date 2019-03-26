/*  
 * @(#) AgentAction.java Create on 2016-6-14 下午5:38:36   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.agent.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.system.basic.global.baseAbstract.BaseAction;
import cn.system.weixin.agent.service.AgentService;
import cn.tools.CommonSendMeg;

/**
 * 企业号应用管理
 * @author zhangshiyuan
 * @date   2016-6-14
 */
@Controller
@Scope("prototype")
public class AgentAction extends BaseAction {
	private static final long serialVersionUID = -5503778193052310083L;
	
	@Resource
	private AgentService service;

	/**
	 * 获取secret所在管理组内的应用概况，会返回管理组内应用的id及名称、头像等信息
	 * @Title: findAgentList
	 * @data:2016-6-14下午5:40:13
	 * @author:zhangshiyuan
	 *
	 * @throws Exception
	 */
	public void findAgentList() throws Exception{
//		if(!this.checkIps()){//不符合接口限定IP范围内，不可进行后续访问
//			CommonSendMeg.writeMsg(ErrorFlag.ERROR_PATH);
//			return;
//		}
		String result = service.findAgentList();//获得微信接口数据
		CommonSendMeg.writeMsg(result);
	}
	
}
