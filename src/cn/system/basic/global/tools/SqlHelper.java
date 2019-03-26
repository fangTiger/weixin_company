package cn.system.basic.global.tools;

import java.util.Map;
import java.util.Set;

import org.springframework.util.Assert;

public class SqlHelper {
	/**
	 * 取查询条件
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午11:07:50
	 * 
	 * @param queryMap
	 * @return
	 */
	public static String getQueryCondition(Map<String, Object> queryMap) {
		StringBuffer result = new StringBuffer();
//		Assert.notNull(queryMap);
		if (queryMap != null) {
			result.append(" WHERE 1=1");
			if (queryMap.size() > 0) {
				for (String key : queryMap.keySet()) {
					if (!key.startsWith("_") && !key.equals("tableName") && !key.equals("class") && !key.equals("serialVersionUID")) {
						if (queryMap.get(key) instanceof String) {
							result.append(" and " + key + " like :" + key);
						} else {
							result.append(" and " + key + "=:" + key);
						}
					}
				}
			}

		}
		return result.toString();
	}

	/**
	 * 把map转换成inser sql语句
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午11:18:25
	 * 
	 * @param oprMap
	 * @param tableName
	 * @param primaryName
	 *            不要的主键
	 * @return
	 */
	public static String getInserSql(Map<String, Object> oprMap, String tableName, String primaryName) {
		Assert.notNull(oprMap);
		Assert.notNull(tableName);
		Assert.notNull(primaryName);
		StringBuffer result = new StringBuffer();
		result.append("INSERT INTO " + tableName + " (");

		StringBuffer keys = new StringBuffer();
		StringBuffer values = new StringBuffer();

		if (oprMap.size() > 0) {
			for (String key : oprMap.keySet()) {
				if (!key.equals(primaryName) && !key.startsWith("_") && !key.equals("tableName") && !key.equals("class") && !key.equals("serialVersionUID")) {
					keys.append("`" + key + "`,");
					values.append(":" + key + ",");
				}
			}
		}
		result.append(keys.toString().replaceAll(",$", ""));
		result.append(")");
		result.append(" VALUES (");
		result.append(values.toString().replaceAll(",$", "") + ")");
		return result.toString();
	}

	/**
	 * 取update sql
	 * 
	 * @author zhl
	 * @date 20112011-8-26上午09:02:41
	 * 
	 * @param keys
	 * @param tableName
	 * @param primaryName
	 * @return
	 */
	public static String getUpdateSql(Set<String> keys, String tableName, String primaryName) {
		StringBuffer result = new StringBuffer();
		Assert.notNull(keys);
		Assert.notNull(tableName);
		Assert.notNull(primaryName);

		result.append("UPDATE " + tableName + " SET ");
		StringBuffer upBuffer = new StringBuffer();

		if (keys.size() > 0) {
			for (String key : keys) {
				if (!key.equals(primaryName) && !key.startsWith("_") && !key.equals("tableName") && !key.equals("class") && !key.equals("serialVersionUID")) {
					upBuffer.append("`"+key + "`=:" + key + ",");
				}
			}
		}

		result.append(upBuffer.toString().replaceAll(",$", ""));
		result.append(" WHERE " + primaryName + "=:" + primaryName + "");
		return result.toString();
	}

	/**
	 * 取删除sql
	 * 
	 * @author zhl
	 * @date 2011-8-30下午10:44:23
	 * 
	 * @param tableName
	 * @param keys
	 * @return
	 */
	public static String getDeleteSql(String tableName, Set<String> keys) {
		StringBuffer result = new StringBuffer();
		Assert.notNull(tableName);
		result.append("DELETE FROM " + tableName);
		if (keys != null && keys.size() > 0) {
			result.append(" WHERE 1=1");
			for (String key : keys) {
				if (!key.startsWith("_") && !key.equals("tableName") && !key.equals("class") && !key.equals("serialVersionUID")) {
					result.append(" AND " + key + "=:" + key);

				}
			}
		}
		return result.toString();
	}
}
