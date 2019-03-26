package cn.system.basic.filter;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.system.basic.global.baseAbstract.BaseAction;
import cn.tools.StringHelper;
import cn.tools.Util;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
/**
 * 记录用户操作信息日志的拦截器
 * @author zhl
 *
 */
@Controller @Scope("prototype")
public class LogFilter extends BaseAction implements Interceptor{
    /**
     * 记录操作日志
     */
	@SuppressWarnings({ "rawtypes", "unused" })
	public String intercept(ActionInvocation arg0) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String url=request.getRequestURI().toString();
		StringBuffer param = new StringBuffer("");
		String ip = Util.getIpAddr(request);
		if(url.indexOf("insert")!=-1||url.indexOf("_save")!=-1||url.indexOf("_update")!=-1){    //判断是增加或修改
			Enumeration params=request.getParameterNames();
			while(params.hasMoreElements()){
				String key=(String)params.nextElement();
				if(key.indexOf("struts")!=-1||key.indexOf("_page")!=-1||key.indexOf("_search")!=-1){        //是struts或页数或查询条件的不记录
					continue;
				}
				
				String value=request.getParameter(key);
				String value2 = "";
				if (!"".equals(StringHelper.toTrim(value))&&value.length()>100) {
					value2 = value.substring(0, 100);
				}
				if(!Util.getInstance().NVL(key).equals("")&&!Util.getInstance().NVL(value2).equals("")){      //值不为空的
					param.append(key + "==" + value2 + ",");
				}
			}
		}else if (url.indexOf("_delete")!=-1) {                     //删除时只记录id
			Enumeration params=request.getParameterNames();
			while(params.hasMoreElements()){
				String key=(String)params.nextElement();
				if(key.indexOf("id")!=-1||key.indexOf("Id")!=-1){
					String value=request.getParameter(key);
					param.append(key + "==" + value + ",");
				}
			}
		}else {
			Enumeration params=request.getParameterNames();
			while(params.hasMoreElements()){
				String key=(String)params.nextElement();
				if(key.indexOf("struts")!=-1||
						key.indexOf("_page")!=-1||
						key.indexOf("_search")!=-1||
						key.indexOf("_search")!=-1||
						key.indexOf("query_json")!=-1){        //是struts或页数或查询条件的不记录
					continue;
				}
				
				String value=request.getParameter(key);
				String value2 = "";
				if (!"".equals(StringHelper.toTrim(value))&&value.length()>100) {
					value2 = value.substring(0, 100);
				}
				if(!Util.getInstance().NVL(key).equals("")&&!Util.getInstance().NVL(value2).equals("")){      //值不为空的
					param.append(key + "==" + value2 + ",");
				}
			}
		}
		
		String result =  arg0.invoke();
		return result;
	}
	
	
	public void destroy() {
	}

	public void init() {
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1573729708573532059L;
	static Logger logger = Logger.getLogger(LogFilter.class);
	
}
