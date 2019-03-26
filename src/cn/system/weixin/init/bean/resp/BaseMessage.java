/*  
 * @(#) BaseRespMessage.java Create on 2014-5-29 上午9:19:14   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.init.bean.resp;

import cn.system.basic.global.bean.BaseBean;

/**
 * 消息基类
 * @author zhangshiyuan
 * @date   2014-5-29
 */
public class BaseMessage extends BaseBean {
	private static final long serialVersionUID = -1281721974158191469L;
	
	// 接收方帐号（收到的OpenID）  
    private String ToUserName;  
    // 开发者微信号  
    private String FromUserName;  
    // 消息创建时间 （整型）  
    private long CreateTime;  
    // 消息类型（text/music/news）  
    private String MsgType;  
    // 位0x0001被标志时，星标刚收到的消息  
    private int FuncFlag;  
  
    public String getToUserName() {  
        return ToUserName;  
    }  
  
    public void setToUserName(String toUserName) {  
        ToUserName = toUserName;  
    }  
  
    public String getFromUserName() {  
        return FromUserName;  
    }  
  
    public void setFromUserName(String fromUserName) {  
        FromUserName = fromUserName;  
    }  
  
    public long getCreateTime() {  
        return CreateTime;  
    }  
  
    public void setCreateTime(long createTime) {  
        CreateTime = createTime;  
    }  
  
    public String getMsgType() {  
        return MsgType;  
    }  
  
    public void setMsgType(String msgType) {  
        MsgType = msgType;  
    }  
  
    public int getFuncFlag() {  
        return FuncFlag;  
    }  
  
    public void setFuncFlag(int funcFlag) {  
        FuncFlag = funcFlag;  
    }  

}
