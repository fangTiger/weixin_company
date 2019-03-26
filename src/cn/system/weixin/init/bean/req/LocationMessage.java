/*  
 * @(#) LocationMessage.java Create on 2014-5-28 下午6:02:41   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.init.bean.req;


/**
 * 地理位置信息
 * @author zhangshiyuan
 * @date   2014-5-28
 */
public class LocationMessage extends BaseMessage {
	private static final long serialVersionUID = 8693462345558413966L;

	// 地理位置维度  
    private String Location_X;  
    // 地理位置经度  
    private String Location_Y;  
    // 地图缩放大小  
    private String Scale;  
    // 地理位置信息  
    private String Label;  
  
    public String getLocation_X() {  
        return Location_X;  
    }  
  
    public void setLocation_X(String location_X) {  
        Location_X = location_X;  
    }  
  
    public String getLocation_Y() {  
        return Location_Y;  
    }  
  
    public void setLocation_Y(String location_Y) {  
        Location_Y = location_Y;  
    }  
  
    public String getScale() {  
        return Scale;  
    }  
  
    public void setScale(String scale) {  
        Scale = scale;  
    }  
  
    public String getLabel() {  
        return Label;  
    }  
  
    public void setLabel(String label) {  
        Label = label;  
    }  
    
}
