/*  
 * @(#) AgentServiceImpl.java Create on 2016-6-15 下午1:44:50   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.agent.service.impl;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import cn.system.basic.common.ErrorFlag;
import cn.system.basic.global.BaseDao;
import cn.system.basic.global.baseAbstract.BaseServiceA;
import cn.system.weixin.agent.service.AgentService;
import cn.system.weixin.common.CommonWxUrl;
import cn.system.weixin.util.HttpUtil;
import cn.system.weixin.util.WeixinUtil;

/**
 * 企业号应用管理
 * @author zhangshiyuan
 * @date   2016-6-15
 */
@Service
public class AgentServiceImpl extends BaseServiceA implements AgentService {

	/*
	 * 获取secret所在管理组内的应用概况，会返回管理组内应用的id及名称、头像等信息
	 * (non-Javadoc)
	 * @see cn.system.weixin.agent.service.AgentService#findAgentList()
	 * @return
	 * @throws Exception
	 * @date: 2016-6-15下午1:45:05
	 * @author: zhangshiyuan
	 */
	@Override
	public String findAgentList() throws Exception {
		String result = ErrorFlag.OPR_FAIL + "";
		// 拼装获取管理组内的应用概况的url
	    String url = CommonWxUrl.URL_AGENT_LIST.replace("ACCESS_TOKEN", WeixinUtil.getAccessToken());
	    JSONObject jsonObj = HttpUtil.httpsRequest(url, "GET", null);
	    if(jsonObj!=null)
	    	result = jsonObj.toString();
	    return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getDao() {
		return null;
	}

}
