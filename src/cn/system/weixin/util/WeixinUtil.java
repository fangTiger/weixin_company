/*  
 * @(#) WeixinUtil.java Create on 2014-8-28 下午4:16:35   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.json.JSONArray;
import org.json.JSONException;

import cn.system.weixin.common.CommonCenterUrl;
import cn.system.weixin.common.CommonData;
import cn.system.weixin.common.CommonWxUrl;
import cn.system.weixin.init.bean.pojo.AccessToken;
import cn.system.weixin.init.bean.pojo.JsapiTicket;
import cn.system.weixin.init.bean.pojo.SignBean;
import cn.system.weixin.message.bean.CusAgentBean;
import cn.tools.DateHelper;
import cn.tools.StringHelper;

/**
 * 微信工具类
 * @author zhangshiyuan
 * @date   2016-6-6 下午1:42:35
 */
public class WeixinUtil {
	
	//全局刷新accessToken缓存变量
	private static AccessToken accessToken = null;
	//使用权限签名
	private static String jsapiTicket = null;
	//全局使用应用与客户对应关系缓存变量
	private static Map<Integer, Integer> agentCustomerR = null;
	/** 全局缓存刷新各个企业号accessToken变量 */
	private static Map<String, AccessToken> accessTokenMap = new ConcurrentHashMap<String, AccessToken>();
	
	
	/**
	 * 使用应用ID换取客户ID方法[新]
	 * @Title: getCIdByAgentId
	 * @data:2016-6-22下午5:40:51
	 * @author:zhangshiyuan
	 *
	 * @param agentId 若查不到对应的客户ID则返回-1
	 * @return
	 */
	public static List<CusAgentBean> getCusAgentList(String cusId, String agtId) throws Exception{
		CusAgentBean cusAgentBean;
		List<CusAgentBean> list = null;
		try {
			if("".equals(StringHelper.toTrim(cusId))){
				cusAgentBean = new CusAgentBean();
				list = new ArrayList<CusAgentBean>();
				cusAgentBean.setAgentId(agtId);
				list.add(cusAgentBean);
			}else{
				String result = HttpUtil.httpRequestGetJson(CommonCenterUrl.URL_FIND_CUST_AND_AGENTS, "GET", null);
//				String result = "[{\"name\": \"test\",\"code\": \"code\",\"corpID\": \"wxc8c8428d0d0fb353\",\"secret\": \"k_0iPsR5uAc_b6purmUFUhUSH4oFKxulqyD-B8RYi9y-ll9DO6gSNWNoVoFnSMi3\",\"cId\": \"313 \",\"agentId\": \"36\"}]";
				result = StringHelper.toTrim(result);
				if("".equals(result)){//如果没查到
					return list;//返回
				}
				net.sf.json.JSONArray arrResult = net.sf.json.JSONArray.fromObject(result);
				if(arrResult!=null){//如果获得到了返回内容
					list = new ArrayList<CusAgentBean>();
					JSONObject jsonObject;
					for (int i = 0; i < arrResult.size(); i++) {
						jsonObject = arrResult.getJSONObject(i);//获得对应关系
						cusAgentBean = new CusAgentBean();
						if(jsonObject.containsKey("cId") && StringHelper.toTrim(jsonObject.get("cId").toString()).equals(StringHelper.toTrim(cusId)) ){
							cusAgentBean.setcId(jsonObject.get("cId").toString());
						}else
							continue;
						if(jsonObject.containsKey("name"))
							cusAgentBean.setName(jsonObject.get("name").toString());
						if(jsonObject.containsKey("code"))
							cusAgentBean.setCode(jsonObject.get("code").toString());
						if(jsonObject.containsKey("corpID") && !"".equals(StringHelper.toTrim(jsonObject.get("corpID").toString())))
							cusAgentBean.setCorpID(jsonObject.get("corpID").toString());
						if(jsonObject.containsKey("secret")  && !"".equals(StringHelper.toTrim(jsonObject.get("secret").toString())))
							cusAgentBean.setSecret(jsonObject.get("secret").toString());
						if(jsonObject.containsKey("agentId"))
							cusAgentBean.setAgentId(jsonObject.get("agentId").toString());
						list.add(cusAgentBean);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("-----------"+DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME) + ",客户ID获取企业号接口报错!");
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 使用应用ID换取客户ID方法[旧]
	 * @Title: getCIdByAgentId
	 * @data:2016-6-22下午5:40:51
	 * @author:zhangshiyuan
	 *
	 * @param agentId 若查不到对应的客户ID则返回-1
	 * @return
	 */
	public static Integer getCIdByAgentId(int agentId){
		Integer cId = -1;//默认-1表示无对应关系
		if(agentCustomerR != null && agentCustomerR.containsKey(agentId)){//若缓存不为空，并且当前缓存存在对应关系
			cId = agentCustomerR.get(agentId);//返回对应客户ID即可
		}else{//若不满足上述条件，即表示缓存为空或当前缓存无此对应关系，则继续处理
			refreshAgentCustomerR();// 接口刷新缓存
			if(agentCustomerR.containsKey(agentId))//若存在对应关系，则赋值cId
				cId = agentCustomerR.get(agentId);
		}
		return cId;
	}
	
	/**
	 * 使用客户ID换取应用ID方法
	 * @Title: getAgentIdByCId
	 * @data:2016-7-4下午8:16:04
	 * @author:zhangshiyuan
	 *
	 * @param agentId
	 */
	public static Integer getAgentIdByCId(int cId){
		Integer agentId = checkAgentIdByCId(cId);//默认-1表示无对应关系
		if(agentId==-1){//当前缓存中可能为空，或可能没有及时刷新所以上述代码没有拿到
			refreshAgentCustomerR();// 接口刷新缓存
			agentId = checkAgentIdByCId(cId);
		}
		return agentId;
	}
	
	/**
	 * 检查是否当前缓存中存在客户ID与应用ID关系
	 * @Title: checkAgentIdByCId
	 * @data:2016-7-4下午8:23:42
	 * @author:zhangshiyuan
	 *
	 * @param cId
	 * @return
	 */
	private static Integer checkAgentIdByCId(int cId){
		if(agentCustomerR!=null && agentCustomerR.size()>0){//拿到数据了
			for(Entry<Integer, Integer> map  : agentCustomerR.entrySet()){
				if(map.getValue()!=null && cId==map.getValue().intValue()){
					return map.getKey();
				}
			}
		}
		return -1;
	}
	
	/**
	 * 调用接口刷新对应关系缓存
	 * @Title: refreshAgentCustomerR
	 * @data:2016-6-23上午11:04:11
	 * @author:zhangshiyuan
	 *
	 */
	private static void refreshAgentCustomerR(){
		agentCustomerR = new HashMap<Integer, Integer>();//清空缓存
	      // 发起POST请求获取凭证  
		String result = HttpUtil.httpRequestGetJson(CommonCenterUrl.URL_FIND_CUST_AND_AGENTS, "GET", null);
		//String result = "[{\"cId\": \"313\",\"agentId\": \"30\"},{\"cId\": \"324\",\"agentId\": \"26\"}]";
		result = StringHelper.toTrim(result);
		if("".equals(result)){//如果没查到
			return;//返回
		}
		 try {
			JSONArray arrResult = new JSONArray(result);
			if(arrResult!=null){//如果获得到了返回内容
				org.json.JSONObject jsonObject;
				for (int i = 0; i < arrResult.length(); i++) {
					jsonObject = arrResult.getJSONObject(i);//获得对应关系
					agentCustomerR.put(jsonObject.getInt("agentId"), jsonObject.getInt("cId"));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取access_token方法，方便调用(当前企业号)[旧]
	 * @Title: getAccessToken
	 * @data:2016-6-6下午3:21:32
	 * @author:zhangshiyuan
	 *
	 * @return
	 */
	public static String getAccessToken(){
		return WeixinUtil.refreshAccessToken(CommonData.CONFIG_CORP_ID, CommonData.CONFIG_SECRET).getToken();
	}
	
	/**
	 * 获取access_token方法,根据corp_id,secret获取[新]
	 * @Title: getAccessToken
	 * @data:2018-6-8上午9:42:22
	 * @author:bagen
	 *
	 * @return
	 */
	public static String getNewAccessToken(String copiId, String secret){
		return WeixinUtil.refreshAccessTokenMap(copiId, secret).getToken();
	}
	
	/**
	 * 刷新access_token(旧方法，获取的当前企业号的token)
	 * @Title: getAccessToken
	 * @data:2016-6-6下午2:30:22
	 * @author:zhangshiyuan
	 *
	 * @param corpid 凭证 
	 * @param corpsecret 密钥 
	 * @return
	 */
	private static AccessToken refreshAccessToken(String corpid, String corpsecret) {
		//如果没有获取过accessToken或accessToken过期
		 if (null == accessToken || "".equals(accessToken.getToken()) || 
				 (new Date().getTime() - accessToken.getTokeTime().getTime()) >= (accessToken.getExpiresIn() * 1000)) {  
			 String requestUrl = CommonWxUrl.URL_ACCESS_TONKEN.replace("V_CORPID", corpid).replace("V_CORPSECRET", corpsecret);  
		      // 发起GET请求获取凭证  
			 JSONObject jsonObject = HttpUtil.httpsRequest(requestUrl, "GET", null);  
			 if (null != jsonObject) {
                try {  
                	accessToken = new AccessToken();  
                	accessToken.setToken(jsonObject.getString("access_token"));  
                	accessToken.setExpiresIn(jsonObject.getInt("expires_in")); 
                	accessToken.setTokeTime(new Date());
                	// 获取token成功
    	            System.out.println(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME)+
    	            		" - 获取token成功  access_token:{"+jsonObject.getString("access_token")+
    	            		"} expires_in:{"+jsonObject.getInt("expires_in")+"}");  
                } catch (Exception e) {  
                	accessToken = null;  
                    // 获取token失败  
                	System.out.println(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME)+
    	            		" - 获取token失败 errcode:{"+jsonObject.getInt("errcode")+
    	            		"} errmsg:{"+jsonObject.getString("errmsg")+"}");  
                }  
            }  
			refreshAgentCustomerR();//帮忙带着刷新一下应用对应关系，省掉一个计时器
//			refreshJsapiTicket();//刷新签名
        }  
	    return accessToken;  
	} 
	
	
	/**
	 * 刷新access_token(多个企业号)[新]
	 * @Title: refreshAccessToken
	 * @data:2018-6-8上午9:57:25
	 * @author:bagen
	 *
	 * @param corpid
	 * @param corpsecret
	 * @return
	 */
	private static AccessToken refreshAccessTokenMap(String corpid, String corpsecret) {
		if(!accessTokenMap.containsKey(corpid)){//如果没有该key,则说明未缓存过该企业号accessToken
			getWxTokenApi(corpid, corpsecret);
		}else{//说明之前已经缓存过，判断其是否再有效期内
			if((new Date().getTime() - accessTokenMap.get(corpid).getTokeTime().getTime()) >= (accessTokenMap.get(corpid).getExpiresIn() * 1000) ){//如果超出了有效日期，则重新调接口获取有效token
				getWxTokenApi(corpid, corpsecret);
			}
		}
	    return accessTokenMap.get(corpid);  
	} 
	
	
	/** 从微信处获取accessToken并放入全局缓存accessTokenMap中  */
	private static void getWxTokenApi(String corpid, String corpsecret) {
		AccessToken accessTokenBean = new AccessToken();
		String requestUrl = CommonWxUrl.URL_ACCESS_TONKEN.replace("V_CORPID", corpid).replace("V_CORPSECRET", corpsecret);  
		JSONObject jsonObject = HttpUtil.httpsRequest(requestUrl, "GET", null);
		if(jsonObject != null){
			try {
				accessTokenBean.setToken(jsonObject.getString("access_token"));  
				accessTokenBean.setExpiresIn(jsonObject.getInt("expires_in")); 
				accessTokenBean.setTokeTime(new Date());
				accessTokenMap.put(corpid, accessTokenBean);
				System.out.println(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME)+"corpid为:"+corpid+" - 获取token成功  access_token:{"+jsonObject.getString("access_token")+"} expires_in:{"+jsonObject.getInt("expires_in")+"}");	
			} catch (Exception e) {// 获取token失败
				accessTokenMap.remove(corpid);
            	System.out.println(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME)+"corpid为:"+corpid+" - 获取token失败 errcode:{"+jsonObject.getInt("errcode")+"} errmsg:{"+jsonObject.getString("errmsg")+"}");  
			}
		}
	}
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	//------------------------签名,旧版联合利华报告用到，其他未用到
	
	/**
	 * 获取权限签名
	 * @Title: getJsapiTicket
	 * @data:2016-6-23下午5:31:14
	 * @author:liweiwei
	 *
	 * @return
	 */
	public static String refreshJsapiTicket() {
		if ("".equals(StringHelper.toTrim(jsapiTicket))) {//如果为空就重新获取
			jsapiTicket = WeixinUtil.getJsapiTicket(getAccessToken()).getToken();
		} 
		return jsapiTicket;
	}
	
	
	
	/**
	 * 获取jsapi_ticket签名权限 
	 * @Title: getJsapiTicket
	 * @data:2016-4-11上午11:10:13
	 * @author:liweiwei
	 *
	 * @param accessToken
	 * @return
	 */
	private static JsapiTicket getJsapiTicket(String accessToken) {
		JsapiTicket jsapiTicket = null;  
		  
	    String requestUrl = CommonWxUrl.URL_JSAPITICKET.replace("ACCESS_TOKEN", accessToken);  
	    JSONObject jsonObject = HttpUtil.httpsRequest(requestUrl, "GET", null);  
	    // 如果请求成功  
	    if (null != jsonObject) {  
	        try {  
	        	jsapiTicket = new JsapiTicket();  
	        	jsapiTicket.setToken(jsonObject.getString("ticket"));
	        	jsapiTicket.setExpiresIn(jsonObject.getInt("expires_in")); 
	            // 获取jsapi_ticket成功
	            System.out.println(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME)+
	            		" - 获取签名成功  jsapi_ticket:{"+jsonObject.getString("ticket")+
	            		"} expires_in:{"+jsonObject.getInt("expires_in")+"}");  
	        } catch (Exception e) {  
	        	jsapiTicket = null;  
	            // 获取jsapi_ticket失败
	            System.out.println(DateHelper.getNowDate(DateHelper.FMT_DATE_DATETIME)+
	            		" - 获取签名失败 errcode:{"+jsonObject.getInt("errcode")+
	            		"} errmsg:{"+jsonObject.getString("errmsg")+"}");  
	        }  
	    }  
	    return jsapiTicket;  
	}
	
	/**
	 * 计算签名
	 * @Title: sign
	 * @data:2016-6-23下午5:43:51
	 * @author:liweiwei
	 *
	 * @param jsapi_ticket
	 * @param url
	 * @return
	 */
	public static SignBean sign(String jsapi_ticket, String url) {
    	SignBean signBean = new SignBean();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        
        signBean.setUrl(url);
        signBean.setJsapiTicket(jsapi_ticket);
        signBean.setNonceStr(nonce_str);
        signBean.setTimestamp(timestamp);
        signBean.setSignature(signature);
        signBean.setAppId(CommonData.CONFIG_CORP_ID);//企业号唯一标识
        
        return signBean;
    }
 	
 	/**
 	 * 获取签名方法-辅助参数
 	 * @Title: byteToHex
 	 * @data:2016-6-23下午5:43:41
 	 * @author:liweiwei
 	 *
 	 * @param hash
 	 * @return
 	 */
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    
    /**
     * 获取签名方法-辅助参数
     * @Title: create_nonce_str
     * @data:2016-6-23下午5:42:50
     * @author:liweiwei
     *
     * @return
     */
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取签名方法-辅助参数
     * @Title: create_timestamp
     * @data:2016-6-23下午5:43:16
     * @author:liweiwei
     *
     * @return
     */
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
	
	/**
	 * 判断是否是QQ表情 
	 * @Title: isQqFace
	 * @data:2014-8-27下午1:49:17
	 * @author:zhangshiyuan
	 *
	 * @param content
	 * @return
	 */
	public static boolean isQqFace(String content) {  
	    boolean result = false;  
	    // 判断QQ表情的正则表达式  
	    String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";  
	    Pattern p = Pattern.compile(qqfaceRegex);  
	    Matcher m = p.matcher(content);  
	    if (m.matches()) {  
	        result = true;  
	    }  
	    return result;  
	}  
	
	/**
	 * 将微信消息中的CreateTime转换成标准格式的时间（yyyy-MM-dd HH:mm:ss）  
	 * @Title: formatTime
	 * @data:2014-8-27下午2:45:08
	 * @author:zhangshiyuan
	 *
	 * @param createTime
	 * @return
	 */
	public static String formatTime(String createTime) {  
	    // 将微信传入的CreateTime转换成long类型，再乘以1000  
	    long msgCreateTime = Long.parseLong(createTime) * 1000L;
	    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    return format.format(new Date(msgCreateTime));  
	}  
	
	/**
	 * emoji表情转换(hex -> utf-16)  
	 * @Title: emoji
	 * @data:2014-8-27下午2:45:00
	 * @author:zhangshiyuan
	 *
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {  
	    return String.valueOf(Character.toChars(hexEmoji));  
	}

	
}
