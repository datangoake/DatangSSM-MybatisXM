package com.newer.mysm.data.util;

public class StringUtil {
	//Servlet判断取得的字符串是否为空
	public static boolean isBlank(String s) {
		return s == null || "".equals(s.trim());
	}
}
