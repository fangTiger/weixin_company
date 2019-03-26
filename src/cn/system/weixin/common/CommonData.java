/*  
 * @(#) CommonData.java Create on 2016-4-6 下午4:49:54   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.common;

import cn.tools.PropertiesUtil;

/**
 * 全局常量类
 * @author zhangshiyuan
 * @date   2016-6-6 上午11:06:56
 */
public class CommonData {
	
	//系统配置文件
	public static final String PROPERTIES_FILE_CONFIG = "config.properties";
	
	/*
	 * 文件属性名称及属性值缓存
	 */
	//微信方定义，企业号的标识，每个企业号拥有一个唯一的CorpID
	public static final String NAME_CONFIG_CORP_ID = "corpID";
	public static final String CONFIG_CORP_ID = PropertiesUtil.getProperty(PROPERTIES_FILE_CONFIG, NAME_CONFIG_CORP_ID);
	
	//管理组凭证密钥
	public static final String NAME_CONFIG_SECRET = "secret";
	public static final String CONFIG_SECRET = PropertiesUtil.getProperty(PROPERTIES_FILE_CONFIG, NAME_CONFIG_SECRET);
	
	//个人根据应用定义（定义相同会比较方便）
	public static final String NAME_CONFIG_TOKEN = "token";
	public static final String CONFIG_TOKEN = PropertiesUtil.getProperty(PROPERTIES_FILE_CONFIG, NAME_CONFIG_TOKEN);
	
	//可个人定义，但一般随机码即可
	public static final String NAME_CONFIG_ENCODING_AES_KEY = "encodingAESKey";
	public static final String CONFIG_ENCODING_AES_KEY = PropertiesUtil.getProperty(PROPERTIES_FILE_CONFIG, NAME_CONFIG_ENCODING_AES_KEY);
	
	//本项目提供接口的可用IP范围,多值请用分号";"分隔
	public static final String NAME_CONFIG_PORT_IPS = "portIps";
	public static final String CONFIG_PORT_IPS = PropertiesUtil.getProperty(PROPERTIES_FILE_CONFIG, NAME_CONFIG_PORT_IPS);

	//本项目的项目域名地址
	public static final String NAME_CONFIG_COMPANY_URL = "companyUrl";
	public static final String CONFIG_COMPANY_URL = PropertiesUtil.getProperty(PROPERTIES_FILE_CONFIG, NAME_CONFIG_COMPANY_URL);

	//预警查询菜单KEY值
	public static final String BTN_WARNING_INFO = "WARNING_INFO";
	//报告查询菜单KEY值
	public static final String BTN_REPORT_INFO = "REPORT_INFO";
	
	//预警信息类型
	public static final String WARNING_TYPES = "web,press,app,weixin,broadcast,weibo";
}
