package com.newer.person.data.util;

public class StringUtil {
	//Servlet�ж�ȡ�õ��ַ����Ƿ�Ϊ��
	public static boolean isBlank(String s) {
		return s == null || "".equals(s.trim());
	}
}
