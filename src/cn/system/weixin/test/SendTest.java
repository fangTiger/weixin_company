/*  
 * @(#) SendTest.java Create on 2016-6-6 下午3:14:52   
 *   
 * Copyright 2016 by xl.   
 */


package cn.system.weixin.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.system.weixin.common.CommonWxUrl;
import cn.system.weixin.init.bean.resp.TextMessage;
import cn.system.weixin.util.WeixinUtil;

/**
 * 发送消息测试
 * @author zhangshiyuan
 * @date   2016-6-6
 */
public class SendTest {

	/**
	 * 测试入口
	 * @Title: main
	 * @data:2016-6-6下午3:14:52
	 * @author:zhangshiyuan
	 *
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		TextMessage bean = new TextMessage();
		//bean.setToUserName("0000000014");
		bean.setToUserName("@all");
		int  index = 1;
		while (true) {
			bean.setContent("hello word! ["+(index++)+"]");
			SendTest test = new SendTest();
			String result = test.sendMessage(bean,30);
			System.out.println(result);
			if(!"{\"errcode\":0,\"errmsg\":\"ok\"}".equals(result))//不成功才跳出
				break;
			break;//TODO 先只发一条
		}
	}

	/**
	 * 发送消息
	 * @Title: sendMessage
	 * @data:2014-9-12下午3:42:18
	 * @author:zhangzhiyuan
	 *
	 * @param bean
	 * @throws Exception
	 */
	public String sendMessage(TextMessage bean,int agentId)throws Exception{
		String result = "-1";
		StringBuffer buffer = new StringBuffer();
		StringBuffer strJson = new StringBuffer();
		strJson.append("{");
		strJson.append("		\"touser\": \""+bean.getToUserName()+"\",");
		strJson.append("		\"msgtype\": \"text\",");
		strJson.append("		\"agentid\": "+agentId+",");
		strJson.append("		\"text\": {");
		strJson.append("			\"content\": \""+bean.getContent()+"\"");
		strJson.append("		},");
		strJson.append("		\"safe\":\"0\"");
		strJson.append("}");
		try {
			URL url = new URL(CommonWxUrl.URL_MESSAGE_SEND.replace("ACCESS_TOKEN", WeixinUtil.getAccessToken()));  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setRequestMethod("POST");// 提交模式
            httpUrlConn.setDoOutput(true);//是否输入参数
            byte[] bypes = strJson.toString().getBytes("UTF-8");
            httpUrlConn.getOutputStream().write(bypes);// 输入参数
            
            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
            result = buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		return result;
	}
	
}
