/*  
 * @(#) MusicBean.java Create on 2014-5-29 上午9:37:12   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.init.bean.resp;

import cn.system.basic.global.bean.BaseBean;

/**
 * 音乐实体
 * @author zhangshiyuan
 * @date   2014-5-29
 */
public class Music extends BaseBean {
	private static final long serialVersionUID = -1328995470543837332L;

	// 音乐名称  
    private String Title;  
    // 音乐描述  
    private String Description;  
    // 音乐链接  
    private String MusicUrl;  
    // 高质量音乐链接，WIFI环境优先使用该链接播放音乐  
    private String HQMusicUrl;  
  
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
  
    public String getMusicUrl() {  
        return MusicUrl;  
    }  
  
    public void setMusicUrl(String musicUrl) {  
        MusicUrl = musicUrl;  
    }  
  
    public String getHQMusicUrl() {  
        return HQMusicUrl;  
    }  
  
    public void setHQMusicUrl(String musicUrl) {  
        HQMusicUrl = musicUrl;  
    }  
    
}
