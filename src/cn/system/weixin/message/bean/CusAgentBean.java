/*  
 * @(#) CusAgentBean.java Create on 2018-6-8 上午10:49:52   
 *   
 * Copyright 2018 by xl.   
 */


package cn.system.weixin.message.bean;

/**
 * 机构对应配置实体
 * @author bagen
 * @date   2018-6-8 上午10:50:06
 */
public class CusAgentBean {
	
	public String name;
    public String code;
    public String corpID;//企业号对应corpID
    public String secret;//企业号对应secret
    public String cId;//客户ID
    public String agentId;//应用ID
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCorpID() {
		return corpID;
	}
	public void setCorpID(String corpID) {
		this.corpID = corpID;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
    
    

}
