/*  
 * @(#) PageBean.java Create on 2012-4-18 上午10:50:37   
 *   
 * Copyright 2012 by xl.   
 */

package cn.system.basic.common;

import java.util.List;

/**
 * 
 * @author zhanghongliang
 * @date 2012-4-18
 */
public class PageBean {
	private int pageSize;
	private int pageNow;
	private int pageCount;
	private int rowCount;
	private int rp; //每页显示条数
	private List<?> rows;
	private List<?> _unileverList;
	
	private int _flag;//标识

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public List<?> get_unileverList() {
		return _unileverList;
	}

	public void set_unileverList(List<?> _unileverList) {
		this._unileverList = _unileverList;
	}

	public int getRp() {
		return rp;
	}

	public void setRp(int rp) {
		this.rp = rp;
	}

	public int get_flag() {
		return _flag;
	}

	public void set_flag(int _flag) {
		this._flag = _flag;
	}

}
