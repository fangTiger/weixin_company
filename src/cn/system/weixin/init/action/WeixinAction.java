/*  
 * @(#) WeixinAction.java Create on 2014-5-27 下午6:02:25   
 *   
 * Copyright 2014 by xl.   
 */

package cn.system.weixin.init.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.system.basic.global.baseAbstract.BaseAction;
import cn.system.weixin.common.CommonData;
import cn.system.weixin.init.bean.pojo.SignBean;
import cn.system.weixin.init.bean.req.BaseMessage;
import cn.system.weixin.init.service.WeixinService;
import cn.system.weixin.util.WeixinUtil;
import cn.tools.CommonSendMeg;
import cn.tools.jackjson.JackJson;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 *  微信核心类
 * @author zhangshiyuan
 * @date   2016-6-6 上午10:53:03
 */
@Controller
@Scope("prototype")
public class WeixinAction extends BaseAction {
	private static final long serialVersionUID = -7055576662678120960L;
	
	@Resource
	private WeixinService service;
	
	public String toWordTest() throws Exception{
		List<BaseMessage> list = new ArrayList<BaseMessage>();
		BaseMessage test1 = new BaseMessage();
		test1.setFromUserName("时媛宝宝abc");
		BaseMessage test2 = new BaseMessage();
		test2.setFromUserName("测试宝宝123");
		list.add(test1);
		list.add(test2);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "toWordTest";
	}

	/**
	 * 确认请求来自微信
	 * @Title: interAction
	 * @data:2016-6-6下午1:40:05
	 * @author:zhangshiyuan
	 *
	 * @throws Exception
	 */
	public void interAction() throws Exception {
		String method = ServletActionContext.getRequest().getMethod();
		if (method.equals("GET")) {
			doGet();
		} else if (method.equals("POST")) {
			doPost();
		}
	}
	
	/**
	 * post处理
	 * 处理微信服务器发来的消息
     * 注意，企业在接收消息，以及发送被动响应消息时，消息体都以AES方式加密，以保证传输的安全 
     * 如果使用 柳峰 等普通微信开发的教程需要进行修改，支持加密
	 * @Title: doPost
	 * @data:2016-6-13上午11:49:39
	 * @author:zhangshiyuan
	 *
	 * @throws Exception
	 */
	private void doPost() throws Exception{
		 // 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        // 调用核心业务类接收消息、处理消息  
        String respMessage = service.processRequest(request);  
        // 响应消息  
        CommonSendMeg.writeMsg(respMessage);
	}
	
	/**
	 * get处理
     * 注意，企业在接收消息，以及发送被动响应消息时，消息体都以AES方式加密，以保证传输的安全 
     * 需要导入微信加密库：http://qydev.weixin.qq.com/wiki/index.php?title=%E5%8A%A0%E8%A7%A3%E5%AF%86%E5%BA%93%E4%B8%8B%E8%BD%BD%E4%B8%8E%E8%BF%94%E5%9B%9E%E7%A0%81 
     * WXBizMsgCrypt 建议认真阅读说明 
     * 步骤： 
     *  1、首要要有一个ICP备案的域名，一定要有ICP备案，后面需要； 
 
        2、EncodeAESKey 需要设置时候那个； 
 
        3、替换JCE包，重启服务器 
 
        4、JDK版本要大于等于1.6 
 
        5、回调模式和主动调用模式在消息发送上也有很大不同： 
 
              A：回调模式下，被动发送的消息需要时xml格式并进行加密，加密规则是首先进行AES加密，然后进行base64加密。 
 
              B：主动发送消息，格式为json格式，不需要加密，但需要token 
 
        6、回调模式接受到真正的消息内容之后，注意回复，空消息即可，否则微信会认为消息接受失败，会再次发送同一消息
	 * @Title: doGet
	 * @data:2016-6-6上午10:52:55
	 * @author:zhangshiyuan
	 *
	 */  
	private void doGet(){
		// 微信加密签名
		String signature = this.getFromRequestParameter("msg_signature");
		// 时间戳
		String timestamp = this.getFromRequestParameter("timestamp");
		// 随机数
		String nonce = this.getFromRequestParameter("nonce");
		// 随机字符串
		String echostr = this.getFromRequestParameter("echostr");
		//微信提供验证帮助类
		WXBizMsgCrypt wxcpt;  
        try {  
        	// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            wxcpt = new WXBizMsgCrypt(CommonData.CONFIG_TOKEN, 
            		CommonData.CONFIG_ENCODING_AES_KEY,CommonData.CONFIG_CORP_ID);  
            echostr = wxcpt.VerifyURL(signature, timestamp, nonce,echostr);
            CommonSendMeg.writeMsg(echostr);// 验证URL成功，将echostr返回  
        } catch (AesException e1) {  
            e1.printStackTrace();  
        }  
	}
	
	/**
	 * 获取签名对象
	 * @Title: getSignBean
	 * @data:2016-6-23下午5:52:48
	 * @author:liweiwei
	 *
	 * @throws Exception
	 */
	public void getSignBean()throws Exception{
		String url = this.getFromRequestParameter("pageUrl");
		SignBean bean = WeixinUtil.sign(WeixinUtil.getAccessToken(), url);
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(bean));
	}
}