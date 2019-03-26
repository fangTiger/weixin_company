package cn.system.basic.filter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.system.basic.global.baseAbstract.BaseAction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
/**
 * 清除服务器端缓存的拦截器
 * @author zhl
 *
 */
@Controller @Scope("prototype")
public class BaseFilter extends BaseAction implements Interceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1671123146973547291L;
	
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("BaseFilter---->destroy");
	}

	public void init() {
		// TODO Auto-generated method stub
		System.out.println("BaseFilter----> start");
	}
    /**
     * 清除缓存
     */
	public String intercept(ActionInvocation arg0) throws Exception {
	    HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Pragma","No-cache"); 
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0); 
       
        return  arg0.invoke();
	}
}
