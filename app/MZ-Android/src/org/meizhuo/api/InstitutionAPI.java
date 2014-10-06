package org.meizhuo.api;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class InstitutionAPI {

	public InstitutionAPI() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 获取培训机构的信息 <br> limit ：就每次显示10项吧 ，所以不写入
	 * 
	 * @param status
	 * @param name
	 * @param type
	 * @param page
	 * @param responseHandler
	 */
	public static void getInstitutionInfo(String status, String name, String type,  String page,
			AsyncHttpResponseHandler responseHandler){
		RequestParams params =  new RequestParams();
		if(status.equals("-1") || status.equals("0") || status.equals("1"))
			params.add("status", status);
		if(!(name == null ||name.equals("")))
			params.add("name", name);
		if(!(type == null || type.equals("")))
			params.add("type", type);
		if(!(page == null || page.equals("")))
			params.add("page", page);
		RestClient.get("/home/institution/search", params, responseHandler);
	}
	
	
	

}
