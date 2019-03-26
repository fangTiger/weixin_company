/*  
 * @(#) VoiceMessage.java Create on 2014-5-28 下午6:05:14   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.init.bean.req;

/**
 * 语音消息
 * @author zhangshiyuan
 * @date   2014-5-28
 */
public class VoiceMessage extends BaseMessage {
	private static final long serialVersionUID = 7793265459459409046L;

	// 媒体ID  
    private String MediaId;  
    // 语音格式  
    private String Format;  
  
    public String getMediaId() {  
        return MediaId;  
    }  
  
    public void setMediaId(String mediaId) {  
        MediaId = mediaId;  
    }  
  
    public String getFormat() {  
        return Format;  
    }  
  
    public void setFormat(String format) {  
        Format = format;  
    }  
    
}
