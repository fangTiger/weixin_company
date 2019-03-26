/*  
 * @(#) MusicMessage.java Create on 2014-5-29 上午9:36:23   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.init.bean.resp;

/**
 * 音乐消息
 * @author zhangshiyuan
 * @date   2014-5-29
 */
public class MusicMessage extends BaseMessage {
	private static final long serialVersionUID = -8641807555557229882L;
	
	// 音乐  
    private Music Music;  
  
    public Music getMusic() {  
        return Music;  
    }  
  
    public void setMusic(Music music) {  
        Music = music;  
    }  

}
