/*  
 * @(#) ImageMessage.java Create on 2014-5-28 下午6:01:40   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.init.bean.req;

/**
 * 图片消息
 * @author zhangshiyuan
 * @date   2014-5-28
 */
public class ImageMessage extends BaseMessage {
	private static final long serialVersionUID = -3223610193749838764L;

	 // 图片链接  
    private String PicUrl;  
  
    public String getPicUrl() {  
        return PicUrl;  
    }  
  
    public void setPicUrl(String picUrl) {  
        PicUrl = picUrl;  
    } 
    
}
