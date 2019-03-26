package cn.system.basic.global.baseAbstract;

import org.apache.struts2.ServletActionContext;

import cn.system.basic.global.BaseService;
import cn.system.basic.global.bean.BaseBean;
import cn.system.basic.global.bean.PageBean;
import cn.system.basic.global.constant.ConstantActionJsp;
import cn.tools.CommonSendMeg;

/**
 * 基本模块action
 * 包含基础的方法
 * 如： toIndex   到主页面
 *      toEdit    到修改页面
 *      delete    删除
 *      update    修改
 *      betchDelete   批量删除
 * @author zhl
 * @date   20112011-8-25下午07:31:46
 *
 */
public abstract class BaseModuleAction extends BaseAction{
	
	/**
	 * to 主页面
	 * @author zhl
	 * @date   20112011-8-25下午08:10:40
	 *
	 * @return
	 */
	public String toIndex()throws Exception{
		BaseBean queryBean = this.getQueryBean();
		
		if(queryBean.get_pageNow()==0){
			queryBean.set_pageSize(PageBean.PAGE_DEFAULT_SIZE);
			queryBean.set_pageNow(1);
		}
		
		PageBean pageBean = this.getService().getPageBean(queryBean);
		queryBean.set_sumCount(pageBean.getSumCount());
		this.setQueryBean(queryBean);
		this.putToContext(ConstantActionJsp.PAGE_ITEMS_KEY, pageBean.getItems());
		return TOINDEX;
	}
	
	/**
	 * to 欢迎页
	 * @author zhl
	 * @date   20112011-8-26下午07:11:01
	 *
	 * @return
	 */
	public String toWelcome(){
		return TOWELCOME;
	}
	/**
	 * to 修改页面
	 * @author zhl
	 * @date   20112011-8-25下午08:10:51
	 *
	 * @return
	 */
	public String toEdit()throws Exception{
		BaseBean oprBean = this.getOprBean();
		oprBean = this.getService().findBeanById(oprBean);
		this.setOprBean(oprBean);
		return TOEDIT;
	}
	
	/**
	 * ajax删除
	 * @author zhl
	 * @date   20112011-8-25下午08:11:01
	 *
	 */
	public void deleteAjax()throws Exception{
		int count = this.getService().deleteByIdBean(this.getOprBean());
		CommonSendMeg.writeMsg(""+count);
	}
	
	/**
	 * 删除
	 * @author zhl
	 * @date   20112011-8-25下午08:11:01
	 *
	 */
	public String delete()throws Exception{
		int count = this.getService().deleteByIdBean(this.getOprBean());
		if(isHaveMsg()){
			String msg = "";
			if(count>0){
				msg = "删除已成功!";
			}else{
				msg = "删除失败!可能数据已经被删除，请刷新后重试!";
			}
			this.setMeg(msg);
		}
		
		return this.toIndex();
	}
	
	/**
	 * 修改
	 * @author zhl
	 * @date   20112011-8-25下午08:11:11
	 *
	 * @return
	 */
	public String update()throws Exception{
		int count = this.getService().updateByIdBean(this.getOprBean());
		if(isHaveMsg()){
			String msg = "";
			if(count>0){
				msg = "修改已成功!";
			}else{
				msg = "修改失败!可能数据已经被删除，请刷新后重试!";
			}
			this.setMeg(msg);
		}
		return this.toIndex();
	}
	

	/**
	 * 批量删除
	 * @author zhl
	 * @date   20112011-8-25下午08:57:39
	 *
	 * @return
	 */
	public String batchDelete()throws Exception{
		String[] ids = ServletActionContext.getRequest().getParameterValues(ConstantActionJsp.PAGE_ITEMS_KEY);
		int count = this.getService().batchDeleteById(this.getOprBean(), ids);
		if(isHaveMsg()){
			String msg = "";
			if(count>0){
				msg = "删除成功";
			}else{
				msg = "删除失败，可能已经删除，请刷新重试";
			}
			this.setMeg(msg);
		}
		
		return this.toIndex();
	}
	
	/**
	 * 继承此action，通过此方式上传子action的Service
	 * @author zhl
	 * @date   20112011-8-25下午07:30:25
	 *
	 * @return
	 */
	protected abstract BaseService getService();

	/**
	 * 取查询bean
	 * @author zhl
	 * @date   20112011-8-25下午08:19:06
	 *
	 * @return
	 */
	public abstract BaseBean getQueryBean();
	/*
	 * 放值
	 */
	public abstract void setQueryBean(BaseBean queryBean);
	
	/**
	 * 取操作bean
	 * @author zhl
	 * @date   20112011-8-25下午08:18:59
	 *
	 * @return
	 */
	public abstract BaseBean getOprBean();
	/*
	 * 放值
	 */
	public abstract void setOprBean(BaseBean oprBean);

	/*
	 * 设置页面提示信息
	 */
	protected void setMeg(String msg){
		if(isHaveMsg){
			this.setMegs(new String[]{msg});
		}
	}
	
	public void setHaveMsg(boolean isHaveMsg) {
		this.isHaveMsg = isHaveMsg;
	}

	public boolean isHaveMsg() {
		return isHaveMsg;
	}

	/*
	 * 是否有提示信息，页面可以控制，默认是有
	 * 没有  页面多传一参数  isHaveMsg = false
	 */
	private boolean isHaveMsg = true;
	
	private static final long serialVersionUID = -5261140660128205790L;
}
