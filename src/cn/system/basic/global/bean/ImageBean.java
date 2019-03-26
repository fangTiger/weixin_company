/*  
 * @(#) ImageBean.java Create on 2013-12-10 下午06:30:59   
 *   
 * Copyright 2013 by xl.   
 */


package cn.system.basic.global.bean;

/**
 * 
 * @author wxy
 * @date   2013-12-10
 */
public class ImageBean extends BaseBean {
	private static final long serialVersionUID = -1175001555078859953L;
	private Integer id;
	private String imageString;
	private String imageLink;
	private Double width;
	private Double height;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImageString() {
		return imageString;
	}
	public void setImageString(String imageString) {
		this.imageString = imageString;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
}
