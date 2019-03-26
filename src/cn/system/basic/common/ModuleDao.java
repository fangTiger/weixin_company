package cn.system.basic.common;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import cn.system.basic.global.baseAbstract.BaseDaoA;
import cn.system.basic.global.fetchUtil.FetchFactory;
import cn.tools.DateHelper;

/**
 * 模块dao 用来住人jdbcTemplate
 * @author zhl
 * @date   20112011-8-24下午02:46:46
 *
 */
public abstract class ModuleDao<T> extends BaseDaoA<T>{
	
	@Resource protected JdbcTemplate classJdbcTemplate;//连接166 report_manage
	protected NamedParameterJdbcTemplate classNameJdbcTemplate;

	@PostConstruct
	public void initJdbcTemplae() {
		classNameJdbcTemplate = new NamedParameterJdbcTemplate(this.classJdbcTemplate);
	}
	
	protected String getDataType(){
		return FetchFactory.DATA_TYPE_MYSQL;
	}
	
	protected NamedParameterJdbcTemplate getNamedJdbc(){
		return classNameJdbcTemplate;
	}
	/**
	 * 取一个关联下面总数
	 * @Title: getListCount_relate
	 * @data:2012-5-9下午03:07:29
	 * @author:zxd
	 *
	 * @param queryMap
	 * @param fieldName
	 * @param primaryName
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public int getListCount_relate(Map<String, Object> queryMap,String fieldName,Integer fieldValue,String tableName)throws Exception {
		String sql = "SELECT  COUNT(*) FROM " + tableName+" WHERE "+ fieldName + "=" + fieldValue +"";
		int total = this.classNameJdbcTemplate.queryForInt(sql, queryMap);
		return total;
	}
	/**
	 * 检查是否添加过
	 * @Title: checkExitRelateArea
	 * @data:2012-5-10上午10:14:50
	 * @author:zxd
	 *
	 * @param map
	 * @param tableName
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	public boolean checkExit(Map<String,Object> map,String tableName, String fieldName,String mainField) throws Exception {
		String sql = "SELECT Count(*) FROM " + tableName + " WHERE " + fieldName + "=:" + fieldName +" AND"+ mainField + "=:" +mainField+"";
		int total = this.classNameJdbcTemplate.queryForInt(sql, map);
		if(total>0){
			return true;
			}
		return false;
		}
	/**
	 * 当天时间范围内
	 * @Title: getTodayStr
	 * @data:2012-8-24下午03:24:07
	 * @author:liweidong
	 *
	 * @param str
	 * @return
	 */
	public String getTodayStr(String str){
		String todayStartTime = DateHelper.getNowDate(DateHelper.FMT_DATE_YYYY_MM_DD)+" 00:00:00";
		String todayEndTime = DateHelper.getNowDate(DateHelper.FMT_DATE_YYYY_MM_DD)+" 23:59:59";
		return " " + str + " >= \"" + todayStartTime +"\" and " + str + " <=\"" + todayEndTime + "\" ";
	}
	/**
	 * 获取当月数据
	 * @Title: getMonthStr
	 * @data:2013-1-31下午05:19:15
	 * @author:zxd
	 *
	 * @param str
	 * @return
	 */
	public String getMonthStr(String str){
		String todayStartTime = DateHelper.getNowDate(DateHelper.FMT_DATE_YYYY_MM)+"-01"+" 00:00:00";
		String todayEndTime = DateHelper.getNowDate(DateHelper.FMT_DATE_YYYY_MM)+"-31"+" 23:59:59";
		return " " + str + " >= \"" + todayStartTime +"\" and " + str + " <=\"" + todayEndTime + "\" ";
	}
	/**
	 * 获取当年的数据
	 * @Title: getYearStr
	 * @data:2013-2-1上午10:11:59
	 * @author:zxd
	 *
	 * @param str
	 * @return
	 */
	public String getYearStr(String str){
		String todayStartTime = DateHelper.getNowDate(DateHelper.FMT_DATE_YYYY)+"-01-01"+" 00:00:00";
		String todayEndTime = DateHelper.getNowDate(DateHelper.FMT_DATE_YYYY)+"-12-31"+" 23:59:59";
		return " " + str + " >= \"" + todayStartTime +"\" and " + str + " <=\"" + todayEndTime + "\" ";
	}
	
	/**
	 * 时间格式：yyyy-MM-dd
	 * @Title: getSpaceTime
	 * @data:2013-10-25上午09:32:53
	 * @author:wxy
	 *
	 * @param startTime
	 * @param endTime
	 * @param timeType
	 * @return
	 */
	public String getSpaceTime(String startTime,String endTime,String timeType){
		startTime = startTime+" 00:00:00";
		endTime = endTime+" 23:59:59";
		return " " + timeType + " >= \"" + startTime +"\" and " + timeType + " <=\"" + endTime + "\" ";
	}
	/**
	 * 时间格式：yyyy-MM-dd
	 * @Title: getSpaceTime
	 * @data:2013-10-25上午09:32:53
	 * @author:wxy
	 *
	 * @param startTime
	 * @param endTime
	 * @param timeType
	 * @return
	 */
	public String getSpaceTime2(String startTime,String endTime,String timeType){
		return " " + timeType + " >= \"" + startTime +"\" and " + timeType + " <=\"" + endTime + "\" ";
	}
	
}
