/*  
 * @(#) UnileverWeekBean.java Create on 2013-12-10 下午01:44:10   
 *   
 * Copyright 2013 by xl.   
 */


package cn.system.weixin.report.bean;

import java.util.List;

import cn.system.basic.global.bean.BaseBean;
import cn.system.basic.global.bean.ImageBean;

/**
 * 联合利华
 * @author bagen
 * @date   2016-6-23 下午4:29:24
 */
public class UnileverWeekBean extends BaseBean{
	private static final long serialVersionUID = 8143946574537239463L;
	
	private Integer id;
	private Integer firstClassId;//行业分类
	private Integer mediaType;//文章的媒体性质1-网络  2- 报刊 3-TV
	private Integer nature;//新闻属性0-负面  1-中性  2-正面
	private String titleCN;//新闻中文标题
	private String titleEN;//新闻英文标题
	private String summary;//中文摘要
	private String content;//文章内容
	private String sourceNameEN;//新闻来源网站英文名称
	private String sourceNameCN;//新闻来源网站中文名称
	private String reporterNameEN;//记者英文姓名
	private String reporterNameCN;//记者中文名称
	private String pageName;//版面名称
	private String link;//外部网址
	private String imageArea;//图片位置
	private String imageLink;//图片链接
	private String logoLink;//logo链接
	private Double circulation;//发行量
	private String publishTime;//新闻发布时间
	private String generateId;//随机码id
	private String unid;//唯一标识码
	private Integer did;//所属报告下载表的Id
	private Integer creator;
	private String createTime;
	
	private String _nature;//文章性质英文
	private String _firstClass;//分类名称
	private List<String> _contentList;//文章段落
	private Integer _words;//文章字数
	private ImageBean _mediaImage;//媒体logo
	private List<ImageBean> _images;//平面图片
	
	private String tableName;
	private String _base64Image;//图片转码后的字符串形式
	private Double _imageHeight;//图片高度
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String get_base64Image() {
		return _base64Image;
	}
	public void set_base64Image(String _base64Image) {
		this._base64Image = _base64Image;
	}
	public Double get_imageHeight() {
		return _imageHeight;
	}
	public void set_imageHeight(Double _imageHeight) {
		this._imageHeight = _imageHeight;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFirstClassId() {
		return firstClassId;
	}
	public void setFirstClassId(Integer firstClassId) {
		this.firstClassId = firstClassId;
	}
	public Integer getMediaType() {
		return mediaType;
	}
	public void setMediaType(Integer mediaType) {
		this.mediaType = mediaType;
	}
	public Integer getNature() {
		return nature;
	}
	public void setNature(Integer nature) {
		this.nature = nature;
	}
	public String getTitleCN() {
		return titleCN;
	}
	public void setTitleCN(String titleCN) {
		this.titleCN = titleCN;
	}
	public String getTitleEN() {
		return titleEN;
	}
	public void setTitleEN(String titleEN) {
		this.titleEN = titleEN;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSourceNameEN() {
		return sourceNameEN;
	}
	public void setSourceNameEN(String sourceNameEN) {
		this.sourceNameEN = sourceNameEN;
	}
	public String getSourceNameCN() {
		return sourceNameCN;
	}
	public void setSourceNameCN(String sourceNameCN) {
		this.sourceNameCN = sourceNameCN;
	}
	public String getReporterNameEN() {
		return reporterNameEN;
	}
	public void setReporterNameEN(String reporterNameEN) {
		this.reporterNameEN = reporterNameEN;
	}
	public String getReporterNameCN() {
		return reporterNameCN;
	}
	public void setReporterNameCN(String reporterNameCN) {
		this.reporterNameCN = reporterNameCN;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImageArea() {
		return imageArea;
	}
	public void setImageArea(String imageArea) {
		this.imageArea = imageArea;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public String getLogoLink() {
		return logoLink;
	}
	public void setLogoLink(String logoLink) {
		this.logoLink = logoLink;
	}
	public Double getCirculation() {
		return circulation;
	}
	public void setCirculation(Double circulation) {
		this.circulation = circulation;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getGenerateId() {
		return generateId;
	}
	public void setGenerateId(String generateId) {
		this.generateId = generateId;
	}
	public String getUnid() {
		return unid;
	}
	public void setUnid(String unid) {
		this.unid = unid;
	}
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String get_nature() {
		return _nature;
	}
	public void set_nature(String _nature) {
		this._nature = _nature;
	}
	public String get_firstClass() {
		return _firstClass;
	}
	public void set_firstClass(String _firstClass) {
		this._firstClass = _firstClass;
	}
	public List<String> get_contentList() {
		return _contentList;
	}
	public void set_contentList(List<String> _contentList) {
		this._contentList = _contentList;
	}
	public Integer get_words() {
		return _words;
	}
	public void set_words(Integer _words) {
		this._words = _words;
	}
	public ImageBean get_mediaImage() {
		return _mediaImage;
	}
	public void set_mediaImage(ImageBean _mediaImage) {
		this._mediaImage = _mediaImage;
	}
	public List<ImageBean> get_images() {
		return _images;
	}
	public void set_images(List<ImageBean> _images) {
		this._images = _images;
	}
}
