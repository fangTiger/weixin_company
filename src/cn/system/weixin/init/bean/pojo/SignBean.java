/*  
 * @(#) SignBean.java Create on 2016-4-11 下午1:55:25   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.init.bean.pojo;

/**
 * 签名对象
 * @author liweiwei
 * @date   2016-4-11
 */
public class SignBean {

	private String url;//页面地址
	
	private String jsapiTicket;//签名权限码
	
	private String nonceStr;//随机字符串
	
	private String timestamp;//时间戳
	
	private String signature;//签名（sha1加密（url,jsapiTicket,nonceStr,timestamp））
	
	private String appId;// APP_ID 第三方用户唯一凭证

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}
