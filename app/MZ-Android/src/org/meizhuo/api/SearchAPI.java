package org.meizhuo.api;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class SearchAPI {

	public SearchAPI() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 专业补贴项的查询
	 * @param title
	 * @param kind
	 * @param level
	 * @param series
	 * @param limit
	 * @param page
	 * @param asyncHttpResponseHandler
	 */
	public void getSubsidy(String certificate_type, String title,String kind, String level,
			String page,
			  AsyncHttpResponseHandler asyncHttpResponseHandler) {
		RequestParams  params =  new RequestParams();
		if(!(certificate_type == null || certificate_type.equals("")))
			params.add("certificate_type", certificate_type);
		if(!(title == null || title.equals("")))
			params.add("title", title);
		if(!(kind == null || kind.equals("")))
			params.add("kind", kind);
		if(!(level == null || level.equals("")))
			params.add("level", level);
			params.add("page", page);
		RestClient.get("/home/subsidy/search", params, asyncHttpResponseHandler);
	}
	
	/**
	 * 获取证书类别的种类
	 * @param responseHandler
	 */
	public static void getCertificateTypes(AsyncHttpResponseHandler responseHandler){
		RestClient.get("/home/subsidy/getCertificateTypes", null, responseHandler);
	}
	
	/**
	 * 获取项目类别的种类
	 * @param responseHandler
	 */
	public static void getkinds(AsyncHttpResponseHandler responseHandler) {
		RestClient.get("/home/subsidy/getkinds", null, responseHandler);
	}
	
	/**
	 * 获取级别的种类
	 * @param responseHandler
	 */
	public static void getLevels(AsyncHttpResponseHandler responseHandler) {
		RestClient.get("/home/subsidy/getLevels", null, responseHandler);
	}
	
	/**
	 * 获取系列的种类
	 * @param responseHandler
	 */
	public static void getSeries(AsyncHttpResponseHandler responseHandler) {
		RestClient.get("/home/subsidy/getSeries", null, responseHandler);
	}
	
	/**
	 * 获得资格名称的种类
	 * @param responseHandler
	 */
	public void getTitles(AsyncHttpResponseHandler responseHandler) {
		RestClient.get("/home/subsidy/getTitles", null, responseHandler);
	}
	

}
