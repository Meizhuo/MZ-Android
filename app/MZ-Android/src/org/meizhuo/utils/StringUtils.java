package org.meizhuo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	/**
	 * 判断给定字符串是否为空白串。空白串是指由空格(' ').制符表(\t).回车符'\r'.换行符'\n'组成的字符串
	 * 若输入字符串为null或空字符串，返回true
	 * @param input
	 * @return
	 */
	public static boolean isEmpty(String input){
		if (input == null && "".equals(input.trim())){
			return true;
		}
		for (int i = 0; i < input.length(); i++){
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\n' && c != 'r') {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 检测字符串中只能包含: 中文.数字.下划线.横线,英文a-z  A-Z
	 * 
	 * @param sequence
	 * @return true if is Ok
	 */
	public static boolean isNickname(String sequence) {
		final String format = "[\u4e00-\u9fa5]";
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(sequence);
		return matcher.find();
	}
	
	/**
	 * create by jason
	 * 检测是否11位手机号码
	 * @param phonename
	 * @return
	 */
	public static boolean isPhoneName(String phonename){
		Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher match = pattern.matcher(phonename);
		return match.find();
	}
	
	/**
	 * 判断是否邮箱
	 * @param sequence
	 * @return
	 */
	public static boolean isEmail(String sequence) {
		Pattern pattern = Pattern.compile("^([a-z0-9A-Z]+[-_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
		Matcher matcher = pattern.matcher(sequence);
		return matcher.find();
	}

}
