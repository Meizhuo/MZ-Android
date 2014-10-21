package org.meizhuo.api;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class InstitutionAPI {

	public InstitutionAPI() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 列表
	 * 获取培训机构的信息 <br> limit ：就每次显示10项吧 ，所以不写入
	 * 
	 * @param status
	 * @param name
	 * @param type
	 * @param page
	 * @param responseHandler
	 * 
	 */
	public static void getInstitutionInfo( String name, String type,  String page,
			AsyncHttpResponseHandler responseHandler){
		RequestParams params =  new RequestParams();
		if(!(name == null ||name.equals("")))
			params.add("name", name);
		if(!(type == null || type.equals("")))
			params.add("type", type);
		if(!(page == null || page.equals("")))
			params.add("page", page);
		RestClient.get("/home/institution/lists", params, responseHandler);
	}
	
	/**
	 * 获取某个机构的信息
	 * @param institution_id 机构的id
	 * @param responseHandler
	 */
	public static void getOneInstitution(String institution_id, AsyncHttpResponseHandler responseHandler){
		RequestParams params =  new RequestParams();
		if (!(institution_id == null || institution_id.equals("")))
			params.add("institution_id", institution_id);
		RestClient.get("/home/institution/lists", params, responseHandler);
	}
	
	
	
	
	

}
