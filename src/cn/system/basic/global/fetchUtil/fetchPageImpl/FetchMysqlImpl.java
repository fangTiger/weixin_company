package cn.system.basic.global.fetchUtil.fetchPageImpl;

import org.springframework.util.Assert;

import cn.system.basic.global.fetchUtil.FetchPage;

public class FetchMysqlImpl implements FetchPage{
	
	/**
	 * 给sql 添加分页 sql
	 * @author zhl
	 * @date   20112011-8-26上午10:14:08
	 *
	 * @param sql
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public String appendFetchSql(String sql, int pageNow, int pageSize) {
		Assert.notNull(sql);
		if(pageNow!=0&&pageSize!=0){
			return sql + " LIMIT " + (pageNow-1)* pageSize + "," + pageSize;
		}
		return sql;
	}
}
