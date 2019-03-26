/*  
 * @(#) CommonButton.java Create on 2014-8-28 下午4:34:17   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.init.bean.pojo;

/**
 * 普通按钮（子按钮） 
 * @author zhangshiyuan
 * @date   2014-8-28
 */
public class CommonButton extends Button {

	private String type;  
    private String key;  
  
    public String getType() {  
        return type;  
    }  
  
    public void setType(String type) {  
        this.type = type;  
    }  
  
    public String getKey() {  
        return key;  
    }  
  
    public void setKey(String key) {  
        this.key = key;  
    }  
	
}
