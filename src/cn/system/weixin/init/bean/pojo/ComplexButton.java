/*  
 * @(#) ComplexButton.java Create on 2014-8-28 下午4:35:25   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.init.bean.pojo;

/**
 * 复杂按钮（父按钮） 
 * @author zhangshiyuan
 * @date   2014-8-28
 */
public class ComplexButton extends Button {

	private Button[] sub_button;  
	  
    public Button[] getSub_button() {  
        return sub_button;  
    }  
  
    public void setSub_button(Button[] sub_button) {  
        this.sub_button = sub_button;  
    }  
	
}
