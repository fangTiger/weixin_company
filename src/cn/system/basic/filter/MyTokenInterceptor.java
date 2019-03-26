package cn.system.basic.filter;

import org.apache.struts2.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionInvocation;
/**
 * 用户控制重复提交问题的拦截器
 * @author zhl
 *
 */
@Controller @Scope("prototype")
public class MyTokenInterceptor extends TokenInterceptor{
	  private static final long serialVersionUID = 1L;  
      @Override  
      protected String doIntercept(ActionInvocation invocation) throws Exception {  
    	 //System.out.println("MyTokenInterceptor===");
//         if (!MyTokenHelper.validToken()) {  
//             return handleInvalidToken(invocation);  
//         }  
//   
//         return handleValidToken(invocation);  
    	  return invocation.invoke();
      }  
}
