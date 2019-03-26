package cn.system.basic.global.baseAbstract;

import java.lang.reflect.ParameterizedType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import cn.system.basic.global.BaseDao;
import cn.system.basic.global.bean.BaseBean;
import cn.system.basic.global.bean.PageBean;
import cn.system.basic.global.bean.SortBean;
import cn.system.basic.global.bean.SortFieldBean;
import cn.system.basic.global.bean.TypeSelectBean;
import cn.system.basic.global.fetchUtil.FetchFactory;
import cn.system.basic.global.tools.SqlHelper;

/**
 * 基础dao
 * 
 * @author zhl
 * @date 20112011-8-26下午01:48:57
 * 
 * @param <T>
 */
@SuppressWarnings("unchecked")
public abstract class BaseDaoA<T> implements BaseDao<T> {

	protected Class<T> clazz;

	public BaseDaoA() {
		clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * 取分页数据
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午10:20:29
	 * 
	 * @param queryMap
	 *            带有的query key与数据库字段对应
	 * @param tableName
	 *            表名
	 * @param pageNow
	 *            当前页
	 * @param pageSize
	 *            每页显示多少个
	 * @return
	 * @throws Exception
	 */
	public PageBean getPageBean(Map<String, Object> queryMap, String tableName, int pageNow, int pageSize) throws Exception {
		StringBuffer sql = new StringBuffer();
		StringBuffer sqlCount = new StringBuffer();

		sql.append("SELECT * FROM " + tableName);
		sqlCount.append("SELECT COUNT(*) FROM " + tableName);

		String queryCondition = SqlHelper.getQueryCondition(queryMap);

		sql.append(queryCondition);
		sqlCount.append(queryCondition);

		sql.append(" ORDER BY id desc");
		String sqlFetch = FetchFactory.getFetchPage(this.getDataType()).appendFetchSql(sql.toString(), pageNow, pageSize);

		List<Object> items = this.getNamedJdbc().query(sqlFetch, queryMap, new BeanPropertyRowMapper(clazz));
		long count = this.getNamedJdbc().queryForLong(sqlCount.toString(), queryMap);

		PageBean result = new PageBean(count, items);
		return result;
	}

	public List<Object> getList(Map<String, Object> queryMap, String tableName, int pageNow, int pageSize) throws Exception {
		List<Object> result = null;
		StringBuffer sql = new StringBuffer();
		String queryCondition = SqlHelper.getQueryCondition(queryMap);
		sql.append("SELECT * FROM " + tableName);
		sql.append(queryCondition);
		sql.append(" ORDER BY id desc");
		String sqlFetch = FetchFactory.getFetchPage(this.getDataType()).appendFetchSql(sql.toString(), pageNow, pageSize);
		try {
			result = this.getNamedJdbc().query(sqlFetch, queryMap, new BeanPropertyRowMapper(clazz));
		} catch (EmptyResultDataAccessException e) {
		}
		return result;
	}

	/**
	 * 取list
	 * 
	 * @author zhl
	 * @date 2011-8-30下午04:29:48
	 * 
	 * @param queryMap
	 * @param tableName
	 * @param clazz
	 *            用来指定RowMapper 的class
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<Object> getList(Map<String, Object> queryMap, String tableName, Class clazz, int pageNow, int pageSize) throws Exception {
		List<Object> result = null;
		StringBuffer sql = new StringBuffer();
		String queryCondition = SqlHelper.getQueryCondition(queryMap);
		sql.append("SELECT * FROM " + tableName);
		sql.append(queryCondition);
		sql.append(" ORDER BY id desc");
		String sqlFetch = FetchFactory.getFetchPage(this.getDataType()).appendFetchSql(sql.toString(), pageNow, pageSize);
		try {
			result = this.getNamedJdbc().query(sqlFetch, queryMap, new BeanPropertyRowMapper(clazz));
		} catch (EmptyResultDataAccessException e) {
		}
		return result;
	}

	public long getListCount(Map<String, Object> queryMap, String tableName) throws Exception {
		StringBuffer sql = new StringBuffer();
		String queryCondition = SqlHelper.getQueryCondition(queryMap);
		sql.append("SELECT COUNT(*) FROM " + tableName);
		sql.append(queryCondition);
		return this.getNamedJdbc().queryForLong(sql.toString(), queryMap);
	}

	/**
	 * 更新bean中有值属性
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午08:44:49
	 * 
	 * @param oprBean
	 * @return
	 * @throws Exception
	 */
	public int updateBeanByPrimary(Map<String, Object> oprMap, String tableName, String primaryName) throws Exception {
		String updateSql = SqlHelper.getUpdateSql(oprMap.keySet(), tableName, primaryName);
		return this.getNamedJdbc().update(updateSql, oprMap);
	}

	/**
	 * 新增
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午08:44:49
	 * 
	 * @param oprBean
	 * @return
	 * @throws Exception
	 */
	public long insertBean(Map<String, Object> oprMap, String tableName, String primaryName) throws Exception {
		String insertSql = SqlHelper.getInserSql(oprMap, tableName, primaryName);
		/*
		 * int oprCount = this.getNamedJdbc().update(insertSql, oprMap); long
		 * lastInsertId = this.getlastInsertId();
		 */
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getNamedJdbc().update(insertSql, ((SqlParameterSource) (new MapSqlParameterSource(oprMap))), keyHolder);
		long lastInsertId = keyHolder.getKey().longValue();
		return lastInsertId;
	}

	/**
	 * 删除一个bean
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午08:56:58
	 * 
	 * @param tableName
	 * @param primary
	 * @param keyVaule
	 * @return
	 * @throws Exception
	 */
	public int deleteBean(String tableName, String primaryName, Object primaryValue) throws Exception {
		String sql = "DELETE FROM " + tableName + " WHERE " + primaryName + "=?";
		return this.getNamedJdbc().getJdbcOperations().update(sql, new Object[] { primaryValue });
	}

	/**
	 * 删除bean 条件不只一个
	 * 
	 * @author zhl
	 * @date 2011-8-30下午10:41:55
	 * 
	 * @param tableName
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	public int delteBeanByMap(String tableName, Map<String, Object> queryMap) throws Exception {
		Set<String> keys = null;
		if (queryMap != null && queryMap.size() > 0) {
			keys = queryMap.keySet();
		}
		String deleteSql = SqlHelper.getDeleteSql(tableName, keys);
		return this.getNamedJdbc().update(deleteSql, queryMap);
	}

	/**
	 * 删除一个bean
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午08:56:58
	 * 
	 * @param tableName
	 * @param primary
	 * @param keyVaule
	 * @return
	 * @throws Exception
	 */
	public int batchdeleteBean(String tableName, String primaryName, final Collection<String> primaryValues) throws Exception {
		int result = 0;
		final String sql = "DELETE FROM " + tableName + " WHERE " + primaryName + "=?";

		result = (Integer) this.getNamedJdbc().getJdbcOperations().execute(sql, new PreparedStatementCallback() {
			public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				Iterator<String> ids = primaryValues.iterator();
				int count = 0;
				while (ids.hasNext()) {
					ps.setObject(1, ids.next());
					ps.addBatch();
					count++;
					if (count % 100000 == 0) {
						ps.executeBatch();
					}
				}
				ps.executeBatch();
				return count;
			}

		});
		return result;
	}

	/**
	 * 取一个bean根据主键
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午09:01:43
	 * 
	 * @param tableName
	 * @param primaryName
	 * @param primaryValue
	 * @return
	 * @throws Exception
	 */
	public BaseBean findByPrimary(String tableName, String primaryName, Object primaryValue) throws Exception {
		BaseBean result = null;
		String sql = "SELECT * FROM " + tableName + " WHERE " + primaryName + "=?";
		try {
			result = (BaseBean) this.getNamedJdbc().getJdbcOperations().queryForObject(sql, new Object[] { primaryValue }, new BeanPropertyRowMapper(clazz));
		} catch (EmptyResultDataAccessException e) {
		}
		return result;
	}

	/**
	 * 唯一检查
	 * 
	 * @author zhl
	 * @date 20112011-8-26下午02:03:39
	 * 
	 * @param tableName
	 * @param fieldName
	 * @param primaryName
	 * @param conditon
	 * @return
	 * @throws Exception
	 */
	public int onlyCheck(String tableName, String fieldName, String primaryName, Map<String, Object> conditon) throws Exception {
		String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + fieldName + "=:" + fieldName + " AND " + primaryName + "!=:" + primaryName;
		return this.getNamedJdbc().queryForInt(sql, conditon);
	}

	/**
	 * 更改排序
	 * 
	 * @author zhl
	 * @date 2011-8-31下午02:32:40
	 * 
	 * @param sortBean
	 * @return
	 * @throws Exception
	 */
	public int updateSortBean(SortBean sortBean) throws Exception {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append(" UPDATE ");
		String sortName = sortBean.getSortName();
		String sortTable = sortBean.getSortTable();
		int newSort = sortBean.getNewSort();
		long pkId = sortBean.getPkId();
		sbSQL.append(sortTable);
		sbSQL.append(" set " + sortName + "=" + newSort);
		sbSQL.append(" where id= " + pkId);
		return this.getNamedJdbc().getJdbcOperations().update(sbSQL.toString());
	}

	public int updateSortList(SortBean sortBean) throws Exception {
		int newSort = sortBean.getNewSort();
		int oldSort = sortBean.getOldSort();
		int maxSort = SortFieldBean.sortMaxNum;
		String sortTable = sortBean.getSortTable();
		String sortName = sortBean.getSortName();
		StringBuffer sbSQL = new StringBuffer();
		if (newSort < oldSort) {
			sbSQL.append(" UPDATE ");
			sbSQL.append(sortTable);
			sbSQL.append(" set " + sortName + "=" + sortName + "+1");
			sbSQL.append(" WHERE 1=1");
			sbSQL.append(" and (" + sortName + " between ");
			sbSQL.append(newSort);
			sbSQL.append(" and ");
			sbSQL.append(oldSort - 1);
			sbSQL.append(")");
			sbSQL.append("and " + sortName + "<" + maxSort);
		} else if (newSort > oldSort) {
			sbSQL.append(" UPDATE ");
			sbSQL.append(sortTable);
			sbSQL.append(" set " + sortName + "=" + sortName + "-1");
			sbSQL.append(" WHERE 1=1");
			sbSQL.append(" and (" + sortName + " between ");
			sbSQL.append(oldSort + 1);
			sbSQL.append(" and ");
			sbSQL.append(newSort);
			sbSQL.append(")");
			sbSQL.append("and " + sortName + "<" + maxSort);
		} else if (newSort == oldSort) {
			return 0;
		}
		List<SortFieldBean> sortFieldBeanList = sortBean.getSortFieldBeanList();
		if (sortFieldBeanList != null) {
			for (SortFieldBean sortFieldBean : sortFieldBeanList) {
				if (sortFieldBean != null) {
					sbSQL.append(sortFieldBean.getFieldSQL());
				}
			}
		}
		return this.getNamedJdbc().getJdbcOperations().update(sbSQL.toString());
	}

	/**
	 * 取排序的下来选项
	 * 
	 * @author zhl
	 * @date 2011-8-31下午02:39:20
	 * 
	 * @param sortBean
	 * @return
	 * @throws Exception
	 */
	public List<TypeSelectBean> getSortTypeSelectList(SortBean sortBean) throws Exception {

		List<TypeSelectBean> typeBeanList = new ArrayList<TypeSelectBean>();
		StringBuffer sbSQL = new StringBuffer();
		int maxSort = SortFieldBean.sortMaxNum;
		String sortTable = sortBean.getSortTable();
		String sortName = sortBean.getSortName();
		sbSQL.append("select " + sortName + " typeId ," + sortName + " typeName from " + sortTable + " where " + sortName + "<" + maxSort + " and " + sortName + ">0");
		List<SortFieldBean> sortFieldBeanList = sortBean.getSortFieldBeanList();
		if (sortFieldBeanList != null) {
			for (SortFieldBean sortFieldBean : sortFieldBeanList) {
				if (sortFieldBean != null) {
					sbSQL.append(sortFieldBean.getFieldSQL());
				}
			}
		}
		sbSQL.append(" order by " + sortName);
		typeBeanList = this.getNamedJdbc().getJdbcOperations().query(sbSQL.toString(), new BeanPropertyRowMapper(TypeSelectBean.class));
		return typeBeanList;

	}

	/**
	 * 重置排序
	 * 
	 * @author zhl
	 * @date 2011-9-27下午3:22:55
	 * 
	 * @param sortBean
	 * @return
	 * @throws Exception
	 */
	public int resetSort(SortBean sortBean) throws Exception {
		StringBuffer querySql = new StringBuffer();
		String sortTable = sortBean.getSortTable();
		String sortName = sortBean.getSortName();
		List<SortFieldBean> sortFieldBeanList = sortBean.getSortFieldBeanList();

		querySql.append("SELECT id FROM " + sortTable + " WHERE 1=1 ");
		if (sortFieldBeanList != null && sortFieldBeanList.size() > 0) {
			for (SortFieldBean sortFieldBean : sortFieldBeanList) {
				querySql.append(sortFieldBean.getFieldSQL());
			}
		}
		querySql.append(" ORDER BY id asc");
		List<Integer> ids = null;
		try {
			ids = this.getNamedJdbc().getJdbcOperations().query(querySql.toString(), new RowMapper() {
				public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
					return arg0.getInt(1);
				}
			});
		} catch (EmptyResultDataAccessException e) {
		}

		if (ids != null && ids.size() > 0) {
			int length = ids.size();
			String updateSql = "UPDATE " + sortTable + " SET " + sortName + "=? WHERE id=?";
			for (int i = 0; i < length; i++) {
				this.getNamedJdbc().getJdbcOperations().update(updateSql.toString(), new Object[] { i + 1, ids.get(i) });
			}
		}
		return 1;
	}

	/**
	 * 取新增的bean id
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午10:50:38
	 * 
	 * @return
	 */
	public long getlastInsertId() {
		String sql = "select LAST_INSERT_ID()";
		return this.getNamedJdbc().getJdbcOperations().queryForInt(sql);
	}

	/*
	 * 取jdbc的数据库类型 用于分页查询
	 */
	protected abstract String getDataType();

	/**
	 * 取不通模块的jdbc
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午10:42:05
	 * 
	 * @return
	 */
	abstract protected NamedParameterJdbcTemplate getNamedJdbc();
}
