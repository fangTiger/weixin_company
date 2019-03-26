/*  
 * @(#) MyX509TrustManager.java Create on 2014-8-28 下午4:12:48   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 证书信任管理器（用于https请求） 
 * @author zhangshiyuan
 * @date   2014-8-28
 */
public class MyX509TrustManager implements X509TrustManager {

	// 这个证书管理器的作用就是让它信任我们指定的证书，下面的代码意味着信任所有证书，不管是否权威机构颁发。
	 
	
	@Override
	public void checkClientTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkServerTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
		// TODO Auto-generated method stub

	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		// TODO Auto-generated method stub
		return null;
	}

}
