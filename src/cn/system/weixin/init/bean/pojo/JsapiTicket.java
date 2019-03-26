/*  
 * @(#) JsapiTicket.java Create on 2016-4-11 上午9:58:18   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.init.bean.pojo;

/**
 * 微信通用接口签名对象
 * @author liweiwei
 * @date   2016-4-11
 */
public class JsapiTicket {
	// 获取到的凭证  
    private String token;  
    // 凭证有效时间，单位：秒  
    private int expiresIn;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}  
    
}
