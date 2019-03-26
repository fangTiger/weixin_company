/*  
 * @(#) ViewButton.java Create on 2014-9-1 下午5:02:10   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.init.bean.pojo;

/**
 * view类型的菜单 
 * @author zhangshiyuan
 * @date   2014-9-1 下午5:02:20
 */
public class ViewButton extends Button {  
	
    private String type;  
    private String url;  
  
    public String getType() {  
        return type;  
    }  
  
    public void setType(String type) {  
        this.type = type;  
    }  
  
    public String getUrl() {  
        return url;  
    }  
  
    public void setUrl(String url) {  
        this.url = url;  
    }  
} 