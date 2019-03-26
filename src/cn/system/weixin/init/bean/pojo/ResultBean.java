/*  
 * @(#) ResultBean.java Create on 2015-10-22 下午2:08:57   
 *   
 * Copyright 2015 by xl.   
 */


package cn.system.weixin.init.bean.pojo;

/**
 * 微信返回结果类
 * @author zhangshiyuan
 * @date   2015-10-22
 */
public class ResultBean {

	private String errcode;//错误代码
	private String errmsg;//错误信息
	private String invaliduser;
	
	
	public String getInvaliduser() {
		return invaliduser;
	}
	public void setInvaliduser(String invaliduser) {
		this.invaliduser = invaliduser;
	}
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
}
