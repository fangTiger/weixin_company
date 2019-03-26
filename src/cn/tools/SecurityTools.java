package cn.tools;

import cn.goso.utility.MD5;

public class SecurityTools {
	/**
	 * 取md5 值
	 * @author zhl
	 * @date   20112011-8-26上午11:31:11
	 *
	 * @param key
	 * @return
	 */
	public static String getMd5Str(String key) {
		if(key==null){
			key = "";
		}
		return new MD5().getMD5ofStr(key);
	}
}
