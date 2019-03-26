package cn.system.basic.global.bean;

import java.util.List;

public class PageBean {
	public final static int PAGE_DEFAULT_SIZE=15;
	
	private long sumCount;
	private List<Object> items;

	public PageBean() {
	}

	public PageBean(long sumCount, List<Object> items) {
		this.sumCount = sumCount;
		this.items = items;
	}

	public void setItems(List<Object> items) {
		this.items = items;
	}

	public List<Object> getItems() {
		return items;
	}

	public void setSumCount(long sumCount) {
		this.sumCount = sumCount;
	}

	public long getSumCount() {
		return sumCount;
	}
}
