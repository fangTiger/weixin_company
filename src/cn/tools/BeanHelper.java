/*  
 * @(#) BeanHelper.java Create on 2011-12-14 下午12:31:19   
 *   
 * Copyright 2011 by xl.   
 */

package cn.tools;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

/**
 * 
 * @author zhanghongliang
 * @date 2011-12-14
 */
@SuppressWarnings("rawtypes")
public class BeanHelper {
	private static final Logger logger = Logger.getLogger(BeanHelper.class);

	/**
	 * 设置属性
	 * 
	 * @param bean
	 * @param name
	 * @param value
	 */
	public static void setProperty(Object bean, String name, Object value) {
		try {
			BeanUtils.setProperty(bean, name, value);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置属性
	 * 
	 * @param bean
	 * @param name
	 * @param value
	 */
	public static Object getProperty(Object bean, String name) {
		Object result = null;
		try {
			result = BeanUtils.getProperty(bean, name);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将资源文件的值写到bean中
	 * 
	 * @param pros
	 * @param bean
	 */
	public static void populate(Properties pros, Object bean) {
		Set<Entry<Object, Object>> set = pros.entrySet();
		for (Entry<Object, Object> entry : set) {
			setProperty(bean, (String) entry.getKey(), entry.getValue());
		}
	}

	/**
	 * 将ResultSet的值写到bean中
	 * 
	 * @param pros
	 * @param bean
	 */
	public static void populate(ResultSet rs, Object bean) {
		Field[] f = bean.getClass().getDeclaredFields();
		Map<String, Object> columnMap = getMysqlResultMap(rs);
		String name = "";
		for (Field temp : f) {
			name = temp.getName();
			if (columnMap.containsKey(name)) {
				setProperty(bean, name, columnMap.get(name));
			}
		}
	}

	/**
	 * 获取数据库查询结果集对应的map
	 * 
	 * @param rs
	 * @return
	 */
	private static Map<String, Object> getMysqlResultMap(ResultSet rs) {
		Map<String, Object> map = new HashMap<String, Object>();
		ResultSetMetaData rsm;
		String columnName = "";
		Object result = null;
		try {
			rsm = rs.getMetaData();
			for (int i = 0; i < rsm.getColumnCount(); i++) {
				columnName = rsm.getColumnLabel(i + 1);
				result = rs.getObject(columnName);
				if (null != result) {
					map.put(columnName, rs.getObject(columnName));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 用来对象之间的同属性拷贝
	 * 
	 * @param sourceO
	 * @param clazz
	 * @param targetO
	 * @data:2011-11-29下午2:30:18
	 * @author:zhanghongliang
	 */
	public static void populate(Object sourceO, Class<?> clazz, Object targetO) {
		Field[] fields = clazz.getDeclaredFields();
		if (fields != null && fields.length > 0) {
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					Object value = field.get(sourceO);
					setProperty(targetO, field.getName(), value);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		Class[] classes = clazz.getDeclaredClasses();
		if (classes != null && classes.length > 0) {
			for (Class class1 : classes) {
				populate(sourceO, class1, targetO);
			}
		}
	}

	/**
	 * bean to map
	 * 
	 * @param obj
	 * @param clazz
	 * @param map
	 * @data:2011-12-14下午12:32:22
	 * @author:zhanghongliang
	 */
	private static void beanToMap(Object obj, Class<?> clazz, Map<String, Object> map) {
		if (obj != null && clazz != null && map != null) {
			Field[] fields = clazz.getDeclaredFields();
			if (fields != null && fields.length > 0) {
				for (Field field : fields) {
					field.setAccessible(true);
					try {
						Object value = field.get(obj);
						 if (value != null) {
						map.put(field.getName(), value);
						 }
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
			Class[] classes = clazz.getDeclaredClasses();
			if (classes != null && classes.length > 0) {
				for (Class class1 : classes) {
					populate(obj, class1, map);
				}
			}
		}
	}
	
	public static Map<String, Object> beanToMap(Object obj) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (obj != null) {
			beanToMap(obj, obj.getClass(), result);
		}
		return result;
	}
	/**
	 * 删除放入MAP中的方法
	 * @Title: beanToMap_del
	 * @data:2012-4-23下午02:30:43
	 * @author:zxd
	 *
	 * @param obj
	 * @return
	 */
	public static Map<String,Object> beanToMap2(Object obj){
		Map<String, Object> result = new HashMap<String, Object>();
		if (obj != null) {
			beanToMap2(obj, obj.getClass(), result);
		}
		return result;
	}
	/**
	 * 删除
	 * @Title: beanToMap_del
	 * @data:2012-4-23下午02:31:49
	 * @author:zxd
	 *
	 * @param obj
	 * @param clazz
	 * @param map
	 */
	private static void beanToMap2(Object obj, Class<?> clazz, Map<String, Object> map) {
		if (obj != null && clazz != null && map != null) {
			Field[] fields = clazz.getDeclaredFields();
			if (fields != null && fields.length > 0) {
				for (Field field : fields) {
					field.setAccessible(true);
					try {
						Object value = field.get(obj);
						 //if (value != null) {
						map.put(field.getName(), value);
						// }
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
			Class[] classes = clazz.getDeclaredClasses();
			if (classes != null && classes.length > 0) {
				for (Class class1 : classes) {
					populate(obj, class1, map);
				}
			}
		}
	}

	public static void dealObject(Object obj, Class<?> clazz) {
		if (obj != null && clazz != null) {
			Field[] fields = clazz.getDeclaredFields();
			if (fields != null && fields.length > 0) {
				for (Field field : fields) {
					field.setAccessible(true);
					try {
						Object value = field.get(obj);
						if (value == null) {
							String typeName = field.getType().getSimpleName().toLowerCase();
							if ("int".equals(typeName) || "integer".equals(typeName)) {
								field.set(obj, 0);
							} else if ("long".equals(typeName)) {
								field.set(obj, 0l);
							} else if ("string".equals(typeName)) {
								field.set(obj, "");
							}
						}

					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
			Class[] classes = clazz.getDeclaredClasses();
			if (classes != null && classes.length > 0) {
				for (Class class1 : classes) {
					dealObject(obj, class1);
				}
			}
		}
	}

	public static void Map2Bean(Map<String, Object> dataMap, Object o, Class<?> valueType) {
		if (dataMap != null && o != null && valueType != null) {
			Field[] fields = valueType.getDeclaredFields();
			if (fields != null && fields.length > 0) {
				for (Field field : fields) {
					String key = field.getName().toLowerCase();
					Object value = null;
					if (dataMap.containsKey(key) && (value = dataMap.get(key)) != null) {
						field.setAccessible(true);
						String type = field.getType().getSimpleName().toLowerCase();
						value = transformByType(value, type);
						try {
							field.set(o, value);
						} catch (IllegalArgumentException e) {
							logger.error(ExceptionHelper.getStackTrace(e));
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							logger.error(ExceptionHelper.getStackTrace(e));
							e.printStackTrace();
						}
					}
				}
			}
			Class[] classes = valueType.getDeclaredClasses();
			if (classes != null && classes.length > 0) {
				for (Class class1 : classes) {
					dealObject(o, class1);
				}
			}
		}
	}

	private static Object transformByType(Object value, String type) {
		Object result = null;
		if ("string".equals(type)) {
			result = value.toString();
		} else if ("int".equals(type) || "integer".equals(type)) {
			result = new Integer(value.toString());
		} else if ("long".equals(type)) {
			result = new Long(value.toString());
		} else if ("double".equals(type)) {
			result = new Double(value.toString());
		} else if ("boolean".equals(type)) {
			result = new Boolean(value.toString());
		} else if ("date".equals(type)) {
			try {
				String data = value.toString();
				int tIndex = -1;
				if ((tIndex = data.indexOf("T")) != -1) {
					data = data.substring(0, tIndex);
					result = dateFormat.parse(data);
				}
			} catch (ParseException e) {
				logger.error(ExceptionHelper.getStackTrace(e));
				e.printStackTrace();
			}
		}
		return result;
	}

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat4Time = new SimpleDateFormat("yyyy-MM-dd");
		String data = "2005-08-01T00:00:00+08:00";
		data = data.substring(0, data.indexOf("T"));
		try {
			Date da = simpleDateFormat4Time.parse(data);
			System.out.println(da);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
