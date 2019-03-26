package cn.system.basic.common;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import cn.system.basic.global.baseAbstract.BaseDaoA;
import cn.system.basic.global.fetchUtil.FetchFactory;

/**
 * 模块dao 用来住人jdbcTemplate
 * @author zhl
 * @date   20112011-8-24下午02:46:46
 *
 */
public abstract class ModuleDaoA<T> extends BaseDaoA<T>{
	@Resource protected JdbcTemplate systemJdbcTemplate;//连接 company_manage
	protected NamedParameterJdbcTemplate systemNameJdbcTemplate;

	@PostConstruct
	public void initJdbcTemplae() {
		systemNameJdbcTemplate = new NamedParameterJdbcTemplate(this.systemJdbcTemplate);
	}
	
	@Override
	protected String getDataType(){
		return FetchFactory.DATA_TYPE_MYSQL;
	}
	
	@Override
	protected NamedParameterJdbcTemplate getNamedJdbc(){
		return systemNameJdbcTemplate;
	}
}
