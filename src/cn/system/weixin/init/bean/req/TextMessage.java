/*  
 * @(#) TextMessage.java Create on 2014-5-28 下午6:00:06   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.init.bean.req;

/**
 * 文本消息 
 * @author zhangshiyuan
 * @date   2014-5-28
 */
public class TextMessage extends BaseMessage {
	private static final long serialVersionUID = 5300407911806533057L;
	
	// 消息内容  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }
    
}
