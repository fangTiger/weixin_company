package cn.system.basic.global.fetchUtil;

public interface FetchPage {
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
	public String appendFetchSql(String sql,int pageNow,int pageSize);
}
