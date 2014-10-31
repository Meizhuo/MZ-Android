package org.meizhuo.api;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class EmployerAPI {

	public EmployerAPI() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 注册<br>
	 *  use:{@link#register}}
	 * @param nickname
	 * @param phone
	 * @param email
	 * @param password
	 * @param responseHandler
	 */
	public void register(String nickname,  String email,
			String psw,String phone,String contact_phone, String address,AsyncHttpResponseHandler responseHandler) {
		RequestParams params =  new RequestParams();
		params.add("nickname", nickname);
		params.add("email", email);
		params.add("psw", psw);
		if(!(phone == null || phone.equals("")))
		params.add("phone", phone);
		if(!(contact_phone == null || contact_phone.equals("")))
		params.add("contact_phone", contact_phone);
		if(!(address == null || address.equals("")))
		params.add("address", address);
		RestClient.post("/home/employer/register", params, responseHandler);
	}
	
	/**
	 * 登录
	 * @param account
	 * @param psw
	 */
	public void Login(String account, String psw, AsyncHttpResponseHandler responseHandler){
		RequestParams params = new RequestParams();
		params.add("account", account);
		params.add("psw", psw);
		RestClient.post("/home/user/login", params, responseHandler);
	}
	
	/**
	 * 登出
	 * @param account
	 * @param psw
	 * @param responseHandler
	 */
	public void Logout(String account, String psw, AsyncHttpResponseHandler responseHandler){
		RequestParams params =  new RequestParams();
		params.add("account", account);
		params.add("psw", psw);
		RestClient.post("/home/employer/logout", params, responseHandler);
	}
	
	/**
	 * 获取用人单位信息
	 * @param responseHandler
	 */
	public void getEmployerInfo(AsyncHttpResponseHandler responseHandler){
		RestClient.get("/home/employer/info", null, responseHandler);
	}
	
	/**
	 * 更新用人单位信息
	 * @param nickname
	 * @param contact_phone
	 * @param address
	 */
	public void updateEmployerInfo(String nickname, String contact_phone, String address, 
			AsyncHttpResponseHandler responseHandler) {
		RequestParams params = new RequestParams();
		params.add("nickname", nickname);
		params.add("contact_phone", contact_phone);
		params.add("address", address);
		RestClient.post("/home/employer/update", params, responseHandler);
	}
	

}
