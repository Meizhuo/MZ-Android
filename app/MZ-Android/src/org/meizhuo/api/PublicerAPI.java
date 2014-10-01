package org.meizhuo.api;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * 用户系统接口
 * 
 * @author Jason
 * 
 */
public class PublicerAPI {

	public PublicerAPI() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 注册<br>
	 *  use:{@link#regist()}}
	 * @param nickname
	 * @param phone
	 * @param email
	 * @param password
	 * @param sex
	 * @param work_place
	 * @param responseHandler
	 */
	public void regist(String nickname, String phone, String email,
			String password, String sex, String work_place,
			AsyncHttpResponseHandler responseHandler) {
		RequestParams params = new RequestParams();
		params.add("nickname", nickname);
		params.add("phone", phone);
		params.add("email", email);
		params.add("psw", password);
		params.add("sex", sex);
		params.add("work_place", work_place);
		RestClient.post("/home/user/register", params, responseHandler);
	}
	
	
	/**
	 *   登录账号
	 * @param account 用户名
	 * @param psw 密码
	 * @param responseHandler 处理器
	 */
	public void Login(String account, String psw, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = new RequestParams();
		params.add("account", account);
		params.add("psw", psw);
		RestClient.post("/home/user/login", params, responseHandler);
	}
	
	/**
	 *   登出
	 * @param responseHandler
	 */
	public void logout(AsyncHttpResponseHandler responseHandler) {
		RequestParams params =  new RequestParams();
		RestClient.get("/home/user/logout", params, responseHandler);
	}
	

	
	/**
	 * 更新用户信息
	 * @param id
	 * @param nickname
	 * @param sex
	 * @param work_place
	 * @param responseHandler
	 */
	public void updateProfile(int id, String nickname, String sex, String work_place, AsyncHttpResponseHandler responseHandler){
		RequestParams params =  new RequestParams();
		params.put("nickname", nickname);
		params.put("sex", sex);
		params.put("work_place", work_place);
		RestClient.post("/home/user/update", params, responseHandler);
	}
	
	
	/**
	 * 查看我的信息 
	 * @param responseHandler
	 */
	public void getProfile(AsyncHttpResponseHandler asyncHttpResponseHandler) {
		RestClient.get("/home/user/info", null, asyncHttpResponseHandler);
	}
	
	/**
	 * 通过id  从数据库查询相应用户的信息，然后获取
	 * @param id
	 * @param asyncHttpResponseHandler
	 */
	public void view(int id, AsyncHttpResponseHandler asyncHttpResponseHandler) {
		RequestParams params =  new RequestParams();
		params.add("id", id+"");
		RestClient.get("/home/user/info", params, asyncHttpResponseHandler);
	}
	
	

}
