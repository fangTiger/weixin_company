/*  
 * @(#) TodayReportServiceImpl.java Create on 2016-3-18 下午3:49:03   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.report.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Encoder;
import cn.system.basic.common.PageBean;
import cn.system.basic.global.BaseDao;
import cn.system.basic.global.baseAbstract.BaseServiceA;
import cn.system.basic.global.bean.ImageBean;
import cn.system.weixin.report.bean.UnileverDownloadBean;
import cn.system.weixin.report.bean.UnileverWeekBean;
import cn.system.weixin.report.common.ReportCommonData;
import cn.system.weixin.report.service.ReportService;
import cn.tools.HttpUtil;
import cn.tools.ImageHelper;
import cn.tools.NumberHelper;
import cn.tools.StringHelper;
import cn.tools.jackjson.JackJson;

/**
 * 日报订阅管理
 * @author liweiwei
 * @date   2016-3-18
 */
@Service
public class ReportServiceImpl  extends BaseServiceA implements ReportService{

//	@Resource
//	private UnileverDownloadDao downloadDao;
//	@Resource
//	private UnileverWeekDao weekDao;
	
	public static int i = 3;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getDao() {
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
		ReportService service = (ReportService)ac.getBean("reportServiceImpl");
		String result = service.getUnileverDownload("1","10");
		System.out.println(result);
	}
	
	/*
	 * 获取两个月内的下载列表信息
	 * (non-Javadoc)
	 * @see cn.system.weixin.service.TodayReportService#getUnileverDownload()
	 * @return
	 * @throws Exception
	 * @date: 2016-3-18下午3:56:11
	 * @author: liweiwei
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getUnileverDownload(String pageNo,String pageSize) throws Exception {
		String result = null;
		String listUrl = ReportCommonData.getUnileverWeekList(pageNo, pageSize);
		String returnJSON;
		String count;
		ObjectMapper mapper = new ObjectMapper(); 
		JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, UnileverDownloadBean.class); 
		List<UnileverDownloadBean> list = null;
		String countUrl = ReportCommonData.unileverWeekCountUrl;
		try {
			returnJSON = HttpUtil.httpRequestGetString(listUrl, "GET", null);
			list = (List<UnileverDownloadBean>)mapper.readValue(returnJSON, javaType);
			count = HttpUtil.httpRequestGetString(countUrl, "GET", null);
			PageBean bean = new PageBean();
			bean.setPageCount(NumberHelper.stringToInt(count));
			bean.setPageNow(NumberHelper.stringToInt(pageNo));
			bean.setPageSize(NumberHelper.stringToInt(pageSize));
			bean.setRows(list);
			if(list!=null&&list.size()>0){
				result = JackJson.fromObjectToJson(bean);
			}else {
				result = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
//		List<UnileverDownloadBean> list = downloadDao.getListByTime(startTime, nowTime,NumberHelper.stringToInt(pageNo),NumberHelper.stringToInt(pageSize));
//		int count = downloadDao.getCountByTime(startTime, nowTime);
		return result;
	}
	
	/*
	 * 根据下载对象获取其文章列表
	 * (non-Javadoc)
	 * @see cn.system.weixin.service.TodayReportService#getUnileverWeekList(java.lang.String)
	 * @param did
	 * @return
	 * @throws Exception
	 * @date: 2016-3-18下午3:56:32
	 * @author: liweiwei
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getUnileverWeekList(String did,String pageNo,String pageSize) throws Exception {
		String result = null;
		String listUrl = ReportCommonData.getUnilevelReportList(pageNo, pageSize, did);
		String countUrl = ReportCommonData.unileverReportCountUrl;
		String count;
		String returnJSON;
		
		ObjectMapper mapper = new ObjectMapper(); 
		JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, UnileverWeekBean.class); 
		List<UnileverWeekBean> list = null;
		try {
			returnJSON = HttpUtil.httpRequestGetString(listUrl, "GET", null);
			count = HttpUtil.httpRequestGetString(countUrl, "GET", null);
			list = (List<UnileverWeekBean>)mapper.readValue(returnJSON, javaType);
			PageBean bean = new PageBean();
			bean.setPageCount(NumberHelper.stringToInt(count));
			bean.setPageNow(NumberHelper.stringToInt(pageNo));
			bean.setPageSize(NumberHelper.stringToInt(pageSize));
			bean.setRows(list);
			if(list!=null&&list.size()>0){
				result = JackJson.fromObjectToJson(bean);
			}else{
				result = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
//		List<UnileverWeekBean> list = weekDao.getListByDid(did,NumberHelper.stringToInt(pageNo),NumberHelper.stringToInt(pageSize));
//		int count = weekDao.getCountByTime(did);
		return result;
	}
	
	/*
	 * 根据文章Id获取文章详细信息
	 * (non-Javadoc)
	 * @see cn.system.weixin.service.TodayReportService#getUnileverWeekBean(java.lang.String)
	 * @param weekId
	 * @return
	 * @throws Exception
	 * @date: 2016-3-18下午3:57:00
	 * @author: liweiwei
	 */
	@Override
	public String getUnileverWeekBean(String weekId) throws Exception {
		String result = null;
		String url = ReportCommonData.getUnilevelWeekDetail(weekId);
		String returnJSON = HttpUtil.httpRequestGetString(url, "GET", null);
		if(!"".equals(StringHelper.toTrim(returnJSON))){
			UnileverWeekBean bean = JackJson.fromJsonToObject(returnJSON, UnileverWeekBean.class);
			handelBean(bean);
			result = JackJson.fromObjectToJson(bean);
		}
		/*UnileverWeekBean bean = weekDao.getBeanByWeekId(weekId);
		if(bean!=null){
			handelBean(bean);
			result = JackJson.fromObjectToJson(bean);
		}*/
		return result;
	}
	
	/*
	 * 获取报告名称
	 * (non-Javadoc)
	 * @see cn.system.weixin.service.TodayReportService#getUnileverDownloadBean(java.lang.String)
	 * @param did
	 * @return
	 * @throws Exception
	 * @date: 2016-3-22下午5:23:31
	 * @author: liweiwei
	 */
	@Override
	public String getUnileverDownloadBean(String did) throws Exception {
		String result = null;
		String url = ReportCommonData.getUnilevelDownLoadBean(did);
		String returnJSON = HttpUtil.httpRequestGetString(url, "GET", null);
		if(!"".equals(StringHelper.toTrim(returnJSON))){
			UnileverDownloadBean bean = JackJson.fromJsonToObject(returnJSON, UnileverDownloadBean.class);
			if(bean!=null){
				result = bean.getFileName().substring(13, bean.getFileName().length());
			}else {
				result = "";
			}
		}
		/*UnileverDownloadBean bean = (UnileverDownloadBean) downloadDao.findByPrimary(ReportCommonTable.UNILEVER_DOWNLOAD, "id", did);
		if(bean!=null){
			result = bean.getFileName().substring(13, bean.getFileName().length());
		}else {
			result = "";
		}*/
		return result;
	}
	
	/**
	 * 文章处理
	 * @Title: handelBean
	 * @data:2016-4-5下午5:42:34
	 * @author:liweiwei
	 *
	 * @param list
	 * @throws Exception
	 */
	private void handelBean(UnileverWeekBean bean) throws Exception {
		StringHelper.ntos(bean);
		String content = bean.getContent();
		if (!"".equals(content)) {
			content = content.replaceAll("<p>", "<div>");
			content = content.replaceAll("<P>", "<div>");
			content = content.replaceAll("</p>", "</div>");
			content = content.replaceAll("</P>", "</div>");
			if(bean.getMediaType()==2){//平面特殊处理
				content = content.replaceAll("\r\n", "");
				String [] contents = content.split("　　");
				StringBuffer contentBuffer = new StringBuffer();
				for (int j = 1; j < contents.length; j++) {
					String temp = StringHelper.toTrim(contents[j]);
					if (!"".equals(temp)) {
						contentBuffer.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+temp+"</div>");
					}
				}
				bean.setContent(contentBuffer.toString());
			}else{
				bean.setContent(content);
			}
			content = StringHelper.getCNString2(content);
			bean.set_words(content.length());
		}else {
			bean.set_words(0);
		}
		if(bean.getNature()==0){
			bean.set_nature("Negative");
		}else if(bean.getNature()==1){
			bean.set_nature("Neutral");
		}else {
			bean.set_nature("Positive");
		}
//		ImageBean _mediaImage = this.getImag(bean.getLogoLink());
//		if (_mediaImage!=null) {
//			bean.set_mediaImage(_mediaImage);
//		}
		String imageLink = StringHelper.toTrim(bean.getImageLink());
		String imageArea = StringHelper.toTrim(bean.getImageArea());
		if (!"".equals(StringHelper.toTrim(imageLink))&&!"".equals(StringHelper.toTrim(imageArea))) {
				List<ImageBean> imageBeans = this.getImages(imageLink,imageArea);
				if (imageBeans.size()>0) {
					bean.set_images(imageBeans);
				}
		}
	}
	
	/**
	 * 获取图片信息
	 * @Title: getImages
	 * @data:2016-4-6下午4:54:10
	 * @author:liweiwei
	 *
	 * @param imageLink
	 * @param imageArea
	 * @return
	 * @throws Exception
	 */
	private List<ImageBean> getImages(String imageLink, String imageArea) throws Exception {
		List<ImageBean> imageBeans = new ArrayList<ImageBean>();
		if(!"".equals(imageLink)){
			String[] links = imageLink.split(";");
			for(String link : links){
				if(!"".equals(StringHelper.toTrim(link))){
					if (!"".equals(StringHelper.toTrim(link))&& !"".equals(StringHelper.toTrim(imageArea))) {
						String urlString = ReportCommonData.PIC_HTTP + imageArea + "/" + link;
						ImageBean imageBean = this.getImag(urlString);
						if(imageBean != null){
							imageBeans.add(imageBean);
						}
					}
				}
			}
		}
		return imageBeans;
	}
	
	/**
	 * 获取图片信息
	 * @Title: getImag
	 * @data:2016-4-6下午4:53:49
	 * @author:liweiwei
	 *
	 * @param link
	 * @return
	 * @throws Exception
	 */
	private ImageBean getImag(String link)throws Exception{
		String result = "";
        ImageBean bean = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] data = null;
        URL url = null;
        try {
			if (!"".equals(link)) {
				url = new URL(link);
				BufferedImage image = ImageIO.read(url);
				if (image!=null) {
					bean = new ImageBean();
					ImageIO.write(image, "jpg", out);
					data = out.toByteArray();
					double width = image.getWidth(null);
					double height = image.getHeight(null);
					double scall = ImageHelper.getScaling(width,height,ReportCommonData.WEIGH,ReportCommonData.HEIGHT);
					BASE64Encoder encoder = new BASE64Encoder();
					result = encoder.encode(data);
					bean.setWidth(width*scall);
					bean.setHeight(height*scall);
					bean.setImageString(result);
					bean.setId(i++);
					bean.setImageLink(link);
				}
			}
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }finally{
        	if(out != null){
        		out.close();
        	}
        }
		return bean;
	}
}
