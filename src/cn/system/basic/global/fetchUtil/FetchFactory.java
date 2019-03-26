package cn.system.basic.global.fetchUtil;

import java.util.HashMap;
import java.util.Map;

import cn.system.basic.global.fetchUtil.fetchPageImpl.FetchMysqlImpl;

/**
 * 取不通数据库类型的分页帮助类实例
 * @author zhl
 * @date   20112011-8-26上午10:46:30
 *
 */
public class FetchFactory {
	/*
	 * 定义数据库类别
	 */
	public final static String DATA_TYPE_MYSQL = "mysql";
	/*
	 * 定义数据库类别
	 */
	public final static String DATA_TYPE_ORACLE = "oracle";
	private static Map<String,FetchPage> fetchMap;
	
	private static void initMap(){
		synchronized (DATA_TYPE_ORACLE) {
			if(fetchMap==null){
				fetchMap = new HashMap<String, FetchPage>();
			}
		}
	}
	
	/**
	 * 应用工程模式
	 * @author zhl
	 * @date   20112011-8-26上午10:46:11
	 *
	 * @param dataType
	 * @return
	 */
	public static FetchPage getFetchPage(String dataType){
		if(fetchMap==null){
			initMap();
		}
		
		if(!fetchMap.containsKey(dataType)){
			fetchMap.put(dataType, getFetchPageByType(dataType));
		}
		return fetchMap.get(dataType);
	}
	
	/**
	 * 根据不通数据库类型取 分页的实例
	 * @author zhl
	 * @date   20112011-8-26上午10:45:38
	 *
	 * @param dataType
	 * @return
	 */
	private static FetchPage getFetchPageByType(String dataType){
		FetchPage result = null;
		if(DATA_TYPE_MYSQL.equals(dataType)){
			result = new FetchMysqlImpl();
		}else if(DATA_TYPE_ORACLE.equals(dataType)){
			
		}
		return result;
	}
}
