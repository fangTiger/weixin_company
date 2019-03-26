package cn.system.basic.global.baseAbstract;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.system.basic.global.GlobalConstants;
import cn.system.weixin.common.CommonData;
import cn.system.weixin.util.LogUtil;
import cn.tools.StringHelper;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 最基本action 其他aciton 需时他的子类
 * 
 * @author zhl
 * @date 20112011-8-24下午02:30:06
 * 
 */
public abstract class BaseAction extends ActionSupport {

	protected Logger logger = Logger.getLogger(BaseAction.class);
	/*
	 * 跳转到主页面
	 */
	protected final static String TOINDEX = "toIndex";

	/*
	 * 跳转到编辑页面
	 */
	protected final static String TOEDIT = "toEdit";

	/*
	 * 退出系统
	 */
	protected final static String LOGOUT = "logout";

	/*
	 * to 欢迎页面
	 */
	protected final static String TOWELCOME = "toWelcome";

	/*
	 * 没有权限页面
	 */
	protected final static String NOPRIVILEGE = "noPrivilege";

	private static final long serialVersionUID = 263091553793684762L;

	/**
	 * 放入context数据
	 * 
	 * @author zhl
	 * @date 20112011-8-24下午02:20:42
	 * 
	 * @param key
	 * @param value
	 */
	protected void putToContext(String key, Object value) {
		ActionContext.getContext().put(key, value);
	}

	/**
	 * 从context中取数据
	 * 
	 * @author zhl
	 * @date 20112011-8-24下午02:28:42
	 * 
	 * @param key
	 * @return
	 */
	protected Object getFromContext(String key) {
		return ActionContext.getContext().get(key);
	}

	/**
	 * 放入session数据
	 * 
	 * @author zhl
	 * @date 20112011-8-24下午02:20:56
	 * 
	 * @param key
	 * @param value
	 */
	protected void putToSession(String key, Object value) {
		ServletActionContext.getRequest().getSession().setAttribute(key, value);
	}

	/**
	 * 从session中取数据
	 * 
	 * @author zhl
	 * @date 20112011-8-24下午02:26:22
	 * 
	 * @param key
	 * @return
	 */
	protected static Object getFromSession(String key) {
		return ServletActionContext.getRequest().getSession().getAttribute(key);
	}

	/**
	 * 从request Attribute中取数据
	 * 
	 * @author zhl
	 * @date 20112011-8-24下午04:00:17
	 * 
	 * @param key
	 * @return
	 */
	protected Object getFromRequstAttribute(String key) {
		return ServletActionContext.getRequest().getAttribute(key);
	}

	/**
	 * 从request Parameter中取数据
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午09:33:11
	 * 
	 * @param key
	 * @return
	 */
	protected String getFromRequestParameter(String key) {
		return ServletActionContext.getRequest().getParameter(key);
	}

	/**
	 * 设置页面提示信息
	 * 
	 * @author zhl
	 * @date 20112011-8-24下午02:21:03
	 * 
	 * @param msgs
	 */
	protected void setMegs(String[] msgs) {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(GlobalConstants.GLOBAL_MESSAGES, msgs);
	}
	
	/**
	 * 验证是否属于限定IP范围内
	 * @Title: checkIps
	 * @data:2016-6-15上午10:42:47
	 * @author:zhangshiyuan
	 *
	 * @return true：范围内, false：范围外
	 */
//	protected boolean checkIps(){
//		boolean isPass = false;
//		String ipAddr = ServletActionContext.getRequest().getRemoteAddr();
//		String[] portIps = StringHelper.toTrim(CommonData.CONFIG_PORT_IPS).split(";");
//		for (String ip : portIps) {
//			ip = StringHelper.toTrim(ip);//防止配置文件书写不规范
//			if(ipAddr.equals(ip))//如果当前访问IP属于限定IP范围内
//				return true;//返回合格
//		}
//		LogUtil.outPrint("范围外IP访问接口", this.getActionMethod(), "对方IP："+ipAddr);
//		return isPass;
//	}
	
	/**
	 * 获取当前被请求的action和方法名称
	 * @Title: getActionMethod
	 * @data:2016-6-15下午2:03:27
	 * @author:zhangshiyuan
	 *
	 * @return
	 */
	protected String getActionMethod() {
		ActionInvocation invocation = ActionContext.getContext().getActionInvocation();
		//System.out.println("Action："+invocation.getAction().getClass().getName());  
		//System.out.println("Struts2 中配置的Action："+invocation.getProxy().getActionName());  
		//System.out.println("调用的方法："+invocation.getProxy().getMethod()); 
		return invocation.getAction().getClass().getName()+"."+invocation.getProxy().getMethod()+"()";
	}

}
