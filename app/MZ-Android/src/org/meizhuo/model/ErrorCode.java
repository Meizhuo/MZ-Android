package org.meizhuo.model;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;

/**
 * 错误码  根据文档来做吧
 * @author Jason
 *
 */
@SuppressLint("UseSparseArrays")
public class ErrorCode {

	public static Map<Integer, String> errorList;
	
	static {
		errorList = new HashMap<Integer, String>();
		errorList.put(20000, "operate success");
		errorList.put(40000, "通用的");
		errorList.put(40001, "请求方法错误");
		errorList.put(40002, "返回格式错误");
	}
	
	
}
