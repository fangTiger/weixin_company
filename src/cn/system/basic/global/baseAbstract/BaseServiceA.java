package cn.system.basic.global.baseAbstract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.system.basic.global.BaseDao;
import cn.system.basic.global.BaseService;
import cn.system.basic.global.GlobalConstants;
import cn.system.basic.global.bean.BaseBean;
import cn.system.basic.global.bean.PageBean;
import cn.system.basic.global.bean.SortBean;
import cn.system.basic.global.bean.TypeSelectBean;
import cn.tools.BeanUtil;

@SuppressWarnings("unchecked")
public abstract class BaseServiceA implements BaseService {

	/**
	 * 取分页数据
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午08:43:19
	 * 
	 * @param queryBean
	 * @return
	 * @throws Exception
	 */
	public PageBean getPageBean(BaseBean queryBean) throws Exception {
		String tableName = BeanUtil.getBeanFieldValue(queryBean, GlobalConstants.TABLE_NAME).toString();
		Map<String, Object> queryMap = BeanUtil.beanToQueryMap(queryBean);
		return this.getDao().getPageBean(queryMap, tableName, queryBean.get_pageNow(), queryBean.get_pageSize());
	}

	/**
	 * 取一个bean根据id
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午08:59:13
	 * 
	 * @return
	 * @throws Exception
	 */
	public BaseBean findBeanById(BaseBean oprBean) throws Exception {
		String tableName = BeanUtil.getBeanFieldValue(oprBean, GlobalConstants.TABLE_NAME).toString();
//		Map<String, Object> propertyMap = BeanUtil.beanToMap(oprBean);
		Object primaryValue = BeanUtil.getBeanFieldValue(oprBean, "id");
		
		if(primaryValue==null||"-1".equals(primaryValue.toString())){
			return oprBean;
		}
		
		return this.getDao().findByPrimary(tableName, "id", primaryValue);
	}

	/**
	 * 取一个bean根据主键
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午08:59:13
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public BaseBean findBeanByPrimary(BaseBean oprBean, String primaryName) throws Exception {
		String tableName = BeanUtil.getBeanFieldValue(oprBean, GlobalConstants.TABLE_NAME).toString();
		Map<String, Object> propertyMap = BeanUtil.beanToMap(oprBean);
		Object primaryValue = BeanUtil.getBeanFieldValue(oprBean, primaryName);

		return this.getDao().findByPrimary(tableName, primaryName, primaryValue);
	}

	/**
	 * 更新
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午08:44:49
	 * 
	 * @param oprBean
	 * @return
	 * @throws Exception
	 */
	public int updateByIdBean(BaseBean oprBean) throws Exception {
		String tableName = BeanUtil.getBeanFieldValue(oprBean, GlobalConstants.TABLE_NAME).toString();
		Map<String, Object> propertyMap = BeanUtil.beanToMap(oprBean);
		return this.getDao().updateBeanByPrimary(propertyMap, tableName, "id");

	}

	/**
	 * 更新 根据主键
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午08:44:49
	 * 
	 * @param oprBean
	 * @return
	 * @throws Exception
	 */
	public int updateBeanByPrimary(BaseBean oprBean, String primaryName) throws Exception {
		String tableName = BeanUtil.getBeanFieldValue(oprBean, GlobalConstants.TABLE_NAME).toString();
		Map<String, Object> propertyMap = BeanUtil.beanToMap(oprBean);

		return this.getDao().updateBeanByPrimary(propertyMap, tableName, primaryName);
	}

	/**
	 * 更新 所有属性根据主键
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午08:44:49
	 * 
	 * @param oprBean
	 * @return
	 * @throws Exception
	 */
	public int updateAllBeanByPrimary(BaseBean oprBean, String primaryName) throws Exception {

		String tableName = BeanUtil.getBeanFieldValue(oprBean, GlobalConstants.TABLE_NAME).toString();
		Map<String, Object> propertyMap = BeanUtil.beanAllToMap(oprBean);

		return this.getDao().updateBeanByPrimary(propertyMap, tableName, primaryName);

	}

	/**
	 * 更新 所有属性
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午08:44:49
	 * 
	 * @param oprBean
	 * @return
	 * @throws Exception
	 */
	public int updateAllByIdBean(BaseBean oprBean) throws Exception {
		String tableName = BeanUtil.getBeanFieldValue(oprBean, GlobalConstants.TABLE_NAME).toString();
		Map<String, Object> propertyMap = BeanUtil.beanAllToMap(oprBean);

		return this.getDao().updateBeanByPrimary(propertyMap, tableName, "id");
	}

	/**
	 * 新增
	 * 主键为id
	 * @author zhl
	 * @date 20112011-8-25下午09:06:57
	 * 
	 * @param oprBean
	 * @return
	 * @throws Exception
	 */
	public long insertBean(BaseBean oprBean) throws Exception {
		String tableName = BeanUtil.getBeanFieldValue(oprBean, GlobalConstants.TABLE_NAME).toString();
		Map<String, Object> propertyMap = BeanUtil.beanAllToMap(oprBean);

		return this.getDao().insertBean(propertyMap, tableName,"id");
	}

	/**
	 * 删除bean 有id值
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午08:47:24
	 * 
	 * @param oprBean
	 * @return
	 * @throws Exception
	 */
	public int deleteByIdBean(BaseBean oprBean) throws Exception {
		String tableName = BeanUtil.getBeanFieldValue(oprBean, GlobalConstants.TABLE_NAME).toString();
		Object primaryValue = BeanUtil.getBeanFieldValue(oprBean, "id");
		return this.getDao().deleteBean(tableName, "id", primaryValue);
	}

	/**
	 * 根据指定主键删除
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午08:49:08
	 * 
	 * @param oprBean
	 * @param primary
	 * @return
	 * @throws Exception
	 */
	public int deleteBeanByPrimary(BaseBean oprBean, String primaryName) throws Exception {
		String tableName = BeanUtil.getBeanFieldValue(oprBean, GlobalConstants.TABLE_NAME).toString();
		Object primaryValue = BeanUtil.getBeanFieldValue(oprBean, primaryName);

		return this.getDao().deleteBean(tableName, primaryName, primaryValue);
	}

	/**
	 * 批量删除根据id
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午08:51:07
	 * 
	 * @param oprBean
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public int batchDeleteById(BaseBean oprBean, String[] ids) throws Exception {
		String tableName = BeanUtil.getBeanFieldValue(oprBean, GlobalConstants.TABLE_NAME).toString();
		List<Object> primaryList = new ArrayList<Object>();
		if (ids != null && ids.length > 0) {
			for (String tmpKey : ids) {
				primaryList.add(tmpKey);
			}
		}

		return this.getDao().batchdeleteBean(tableName, "id", primaryList);
	}

	/**
	 * 根据指定主键批量删除
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午08:52:38
	 * 
	 * @param oprBean
	 * @param keys
	 * @param primary
	 * @return
	 * @throws Exception
	 */
	public int batchDeleteByPrimary(BaseBean oprBean, String[] primaryValues, String primaryName) throws Exception {
		String tableName = BeanUtil.getBeanFieldValue(oprBean, GlobalConstants.TABLE_NAME).toString();
		List<Object> primaryList = new ArrayList<Object>();
		if (primaryValues != null && primaryValues.length > 0) {
			for (String tmpKey : primaryValues) {
				primaryList.add(tmpKey);
			}
		}

		return this.getDao().batchdeleteBean(tableName, primaryName, primaryList);
	}

	/**
	 * 唯一检查
	 * @author zhl
	 * @date   20112011-8-26下午01:58:53
	 *
	 * @param oprBean
	 * @param fieldName   要检查的字段
	 * @param primary     对应表的主键
	 * @return
	 * @throws Exception
	 */
	public boolean onlyCheck(BaseBean oprBean,String fieldName,String primary)throws Exception{

		String tableName = BeanUtil.getBeanFieldValue(oprBean, GlobalConstants.TABLE_NAME).toString();
		String fieldValue = BeanUtil.getBeanFieldValue(oprBean,fieldName).toString();
		Object primaryvalue = BeanUtil.getBeanFieldValue(oprBean,primary);
		
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put(fieldName, fieldValue);
		condition.put(primary, primaryvalue);
		
		return this.getDao().onlyCheck(tableName, fieldName, primary, condition)>0;
	}
	
	 /**
	  * 取排序的下来选项
	  * @author zhl
	  * @date   2011-8-31下午02:39:20
	  *
	  * @param sortBean
	  * @return
	  * @throws Exception
	  */
	 public List<TypeSelectBean> getSortTypeSelectList(SortBean sortBean)throws Exception{
		 return getDao().getSortTypeSelectList(sortBean);
	 }
	 /**
	  * 更改排序
	  * @author zhl
	  * @date   2011-8-31下午02:41:55
	  *
	  * @param sortBean
	  * @return
	  * @throws Exception
	  */
	 public boolean updateSort(SortBean sortBean)throws Exception{
		 this.getDao().updateSortList(sortBean);
		 this.getDao().updateSortBean(sortBean);
		 return true;
	 }
	@SuppressWarnings("rawtypes")
	protected abstract BaseDao getDao();
}
