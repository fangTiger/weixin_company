/*  
 * @(#) TextMessage.java Create on 2014-5-29 上午9:34:11   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.init.bean.resp;

import java.util.Date;

/**
 * 文本消息
 * @author zhangshiyuan
 * @date   2014-5-29
 */
public class TextMessage extends BaseMessage {
	private static final long serialVersionUID = -5149411975890127105L;
	
	public TextMessage(){}
	
	/**
	 * 有参构造
	 * @param fromUserName
	 * @param toUserName
	 * @param msgType
	 * @param respContent
	 */
	public TextMessage(String fromUserName,String toUserName,String msgType,String respContent){
		this.setFromUserName(toUserName);
		this.setToUserName(fromUserName);
		this.setMsgType(msgType);
		this.setCreateTime(new Date().getTime());
		this.setFuncFlag(0);
		this.Content = respContent;  
	}

	// 回复的消息内容  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }  
	
}
