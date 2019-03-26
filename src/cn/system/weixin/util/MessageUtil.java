package cn.system.weixin.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.system.weixin.init.bean.resp.Article;
import cn.system.weixin.init.bean.resp.BaseMessage;
import cn.system.weixin.init.bean.resp.MusicMessage;
import cn.system.weixin.init.bean.resp.NewsMessage;
import cn.system.weixin.init.bean.resp.TextMessage;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 消息工具类
 * 
 * @author zhangshiyuan
 * @date 2014-5-29 上午10:20:33
 */
public class MessageUtil {

	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";
	
	/**
	 * 返回消息类型：多客服
	 */
	public static final String RESP_MESSAGE_CUSTOMER = "transfer_customer_service";

	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	
	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";
	
	/**
	 * 请求消息类型：news
	 */
	public static final String REQ_MESSAGE_TYPE_NEWS = "news";

	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";
	
	 /** 
     * 事件类型：subscribe(订阅) 
     */  
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";  
  
    /** 
     * 事件类型：unsubscribe(取消订阅) 
     */  
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";  
  
    /** 
     * 事件类型：CLICK(自定义菜单点击事件) 
     */  
    public static final String EVENT_TYPE_CLICK = "click";
    
    /** 
     * 事件类型：kf_create_session(客服接入) 
     */  
    public static final String EVENT_KF_CREATE_SESSION = "kf_create_session";
    
    /** 
     * 事件类型：kf_close_session(客服关闭) 
     */  
    public static final String EVENT_KF_CLOSE_SESSION = "kf_close_session";
    
    /** 
     * 事件类型：kf_switch_session(客服转接) 
     */  
    public static final String EVENT_KF_SWITCH_SESSION = "kf_switch_session";

	/**
	 * 解析微信发来的请求（XML）企业号加密版
	 * @Title: parseXml
	 * @data:2016-6-13下午4:54:33
	 * @author:zhangshiyuan
	 *
	 * @param request
	 * @param wxcpt
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request,WXBizMsgCrypt  wxcpt,String msg_signature,String timestamp,String nonce) throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();
		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		//从请求中读取整个post数据
		String postData = IOUtils.toString(inputStream, "UTF-8");
		try {  
        	postData = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, postData);  
        } catch (AesException e) {
            e.printStackTrace();
            return null;
        }
		// 读取输入流
		SAXReader reader = new SAXReader();
		// 防止中文报错
		reader.setEncoding("UTF-8");
		// 转换解密后内容节点
		Document document = reader.read(new ByteArrayInputStream(postData.getBytes("UTF-8")));
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getText());
		// 释放资源
		inputStream.close();
		inputStream = null;
		return map;
	}
	
	/**
	 * 基础消息对象转换成xml
	 * @Title: baseMessageToXml
	 * @data:2016-1-26下午2:42:09
	 * @author:zhangshiyuan
	 *
	 * @param textMessage
	 * @return
	 */
	public static String baseMessageToXml(BaseMessage message) {
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
	}

	/**
	 * 文本消息对象转换成xml
	 * @Title: textMessageToXml
	 * @data:2014-5-29上午10:23:37
	 * @author:zhangshiyuan
	 *
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	/**
	 * 音乐消息对象转换成xml
	 * @Title: musicMessageToXml
	 * @data:2014-5-29上午10:23:51
	 * @author:zhangshiyuan
	 *
	 * @param musicMessage
	 * @return
	 */
	public static String musicMessageToXml(MusicMessage musicMessage) {
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}

	/**
	 * 图文消息对象转换成xml
	 * 
	 * @param newsMessage
	 *            图文消息对象
	 * @return xml
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}

	/**
	 * 扩展xstream，使其支持CDATA块
	 * 
	 * @date 2013-05-19
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	/**
	 * 创建基础信息
	 * @Title: createBaseMessage
	 * @data:2016-6-24上午9:53:46
	 * @author:liweiwei
	 *
	 * @param fromUserName
	 * @param toUserName
	 * @return
	 */
	public static String createBaseMessage(String fromUserName,String toUserName){
		BaseMessage message = new BaseMessage();  
		message.setToUserName(fromUserName);  
		message.setFromUserName(toUserName);  
		message.setCreateTime(new Date().getTime()); 
		message.setMsgType(MessageUtil.RESP_MESSAGE_CUSTOMER);  
		message.setFuncFlag(0);
        return MessageUtil.baseMessageToXml(message);
	}
	
	/**
	 * 创建文本信息
	 * @Title: createTextMessage
	 * @data:2016-6-24上午9:53:53
	 * @author:liweiwei
	 *
	 * @param fromUserName
	 * @param toUserName
	 * @param respContent
	 * @return
	 */
	public static String createTextMessage(String fromUserName,String toUserName,String respContent){
		TextMessage textMessage = new TextMessage();  
        textMessage.setToUserName(fromUserName);  
        textMessage.setFromUserName(toUserName);  
        textMessage.setCreateTime(new Date().getTime()); 
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
        textMessage.setFuncFlag(0);
		textMessage.setContent(respContent);  
        return MessageUtil.textMessageToXml(textMessage);
	}
	
	/**
	 * 创建图文消息
	 * @Title: createNewsMessage
	 * @data:2016-6-24上午9:53:59
	 * @author:liweiwei
	 *
	 * @param fromUserName
	 * @param toUserName
	 * @param list
	 * @return
	 */
	public static String createNewsMessage(String fromUserName,String toUserName,List<Article> list){
		NewsMessage news = new NewsMessage();
		news.setToUserName(fromUserName);  
		news.setFromUserName(toUserName);  
		news.setCreateTime(new Date().getTime());  
		news.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
		news.setFuncFlag(0);
		// 设置图文消息个数  
		news.setArticleCount(list==null?0:list.size());  
		// 设置图文消息包含的图文集合  
		news.setArticles(list);
		if(news!=null &&news.getArticles()!=null && news.getArticles().size()>0){//如果有回复的图文消息
			return MessageUtil.newsMessageToXml(news);
		}
		return null;//如果没有图文消息就返回空
	}
	
}