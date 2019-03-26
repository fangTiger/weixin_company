/*  
 * @(#) AccessToken.java Create on 2014-8-28 下午4:31:49   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.init.bean.pojo;

import java.util.Date;

/**
 * 微信通用接口凭证 
 * @author zhangshiyuan
 * @date   2014-8-28
 */
public class AccessToken {
	
	// 获取到的凭证  
    private String token;  
    // 凭证有效时间，单位：秒  
    private int expiresIn;  
    //获取时间
    private Date tokeTime;
  
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

	public Date getTokeTime() {
		return tokeTime;
	}

	public void setTokeTime(Date tokeTime) {
		this.tokeTime = tokeTime;
	}  
    
}
