/*  
 * @(#) JQReturn.java Create on 2012-3-7 上午9:41:23   
 *   
 * Copyright 2012 by xl.   
 */

package cn.system.basic.common;

/**
 * jquery 返回对象
 * 
 * @author zhanghongliang
 * @date 2012-3-7
 */
public class JQReturn {

	public JQReturn() {
	}

	/**
	 * 是否更新成功的构造方法
	 * 
	 * @param status
	 * @param msg
	 */
	public JQReturn(boolean status, Object msg) {
		this.status = status;
		this.msg = msg;
	}

	/**
	 * 是否成功
	 */
	private boolean status;
	/**
	 * 返回消息
	 */
	private Object msg;

	/**
	 * @return
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return
	 */
	public Object getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 */
	public void setMsg(Object msg) {
		this.msg = msg;
	}

}
