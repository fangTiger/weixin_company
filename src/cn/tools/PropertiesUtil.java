package cn.tools;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 加载properties工具类
 * @author bagen
 * @date   2016-6-27 下午12:43:34
 */
public class PropertiesUtil {

	private static Properties properties;
	
	private static Map<String ,Properties> propertiesMap = new HashMap<String ,Properties>();
	
	/**
	 * 初始化properties文件
	 * @Title: initProperties
	 * @data:2016-6-27下午12:44:19
	 * @author:bagen
	 *
	 * @param file
	 */
	private static void initProperties(String file) {
		properties = new Properties();
		try {
			ClassLoader loader = PropertiesUtil.class.getClassLoader();
			java.io.InputStream inStream = loader.getResourceAsStream(file);
			if(inStream != null) {
				properties.load(inStream);
			}
			propertiesMap.put(file, properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 从缓存Map获取值
	 * @Title: getProperties
	 * @data:2016-6-27下午12:59:01
	 * @author:bagen
	 *
	 * @param file
	 * @return
	 */
	public static Properties getProperties(String file){
		Properties properties = null;
		if(propertiesMap.containsKey(file)){
			properties = (Properties) propertiesMap.get(file);
		}
		return properties;
	}
	
	/**
	 * 获取properties中的值
	 * @Title: getProperty
	 * @data:2016-6-27下午12:51:03
	 * @author:bagen
	 *
	 * @param file
	 * @param key
	 * @return
	 */
	public static String getProperty(String file, String key) {
		if(!propertiesMap.containsKey(file))
			initProperties(file);
		return getProperties(file).getProperty(key);
	}
}
