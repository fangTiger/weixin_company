/*  
 * @(#) LinkMessage.java Create on 2014-5-28 下午6:04:14   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.init.bean.req;

/**
 * 链接消息
 * @author zhangshiyuan
 * @date   2014-5-28
 */
public class LinkMessage extends BaseMessage {
	private static final long serialVersionUID = -4810524956576111445L;

	// 消息标题  
    private String Title;  
    // 消息描述  
    private String Description;  
    // 消息链接  
    private String Url;  
  
    public String getTitle() {  
        return Title;  
    }  
  
    public void setTitle(String title) {  
        Title = title;  
    }  
  
    public String getDescription() {  
        return Description;  
    }  
  
    public void setDescription(String description) {  
        Description = description;  
    }  
  
    public String getUrl() {  
        return Url;  
    }  
  
    public void setUrl(String url) {  
        Url = url;  
    }  
	
}
