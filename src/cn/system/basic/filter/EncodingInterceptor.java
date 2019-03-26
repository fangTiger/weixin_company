package cn.system.basic.filter;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.system.basic.global.baseAbstract.BaseAction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@Controller
@Scope("prototype")
public class EncodingInterceptor extends BaseAction implements  Interceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4508246269359526710L;

	public String intercept(ActionInvocation arg0) throws Exception {
		
		ActionContext actionContext = arg0.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest)actionContext.get(StrutsStatics.HTTP_REQUEST);
		if(request.getMethod().compareToIgnoreCase("post")>=0){
			try{
			
				 request.setCharacterEncoding("GBK");  
			}	catch (UnsupportedEncodingException e) {  
			       // TODO Auto-generated catch block  
			       e.printStackTrace();  
			      } 
			
		}else{
			
	  Iterator<String[]> iter = request.getParameterMap().values().iterator();
	  	
	     while (iter.hasNext()){
	    	 String []params = (String[])iter.next();
	    	 
	    	 for(int i=0;i<params.length;i++){
	    		 try{
	    			 params[i]= new String(params[i].getBytes("iso8859-1"),"utf-8");
	    			 
	    		 }catch(UnsupportedEncodingException e){
	    			 e.printStackTrace();
	    			 
	    		 }
	    		 
	    	 }
	    	 
	     }
	    
	  
		}
		
	
		
		// TODO Auto-generated method stub
		return arg0.invoke();
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

}
