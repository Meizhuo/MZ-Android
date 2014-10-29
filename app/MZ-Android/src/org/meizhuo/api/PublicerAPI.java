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
		if(!(nickname == null || nickname.equals("")))
		params.add("nickname", nickname);
		if(!(phone == null || phone.equals("")))
		params.add("phone", phone);
		if(!(email == null || email.equals("")))
		params.add("email", email);
		if(!(password == null || password.equals("")))
		params.add("psw", password);
		if(!(sex == null || sex.equals("")))
		params.add("sex", sex);
		if(!(work_place == null || work_place.equals("")))
		params.add("work_place", work_place);
		RestClient.post("/home/user/register", params, responseHandler);
	}
	
	
	/**
	 *   登录账号
	 * @param account 用户名
	 * @param psw 密码
	 * @param responseHandler 处理器
	 */
	public  void Login(String account, String psw, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = new RequestParams();
		if (!(account == null || account.equals("")))
		params.add("account", account);
		if (!(psw == null || psw.equals("")))
		params.add("psw", psw);
		RestClient.post("/home/user/login", params, responseHandler);
	}
	
	/**
	 *   登出
	 * @param responseHandler
	 */
	public void logoff(AsyncHttpResponseHandler responseHandler) {
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
	public void updateProfile(String nickname, String sex, String work_place, AsyncHttpResponseHandler responseHandler){
		RequestParams params =  new RequestParams();
		params.add("nickname", nickname);
		params.add("sex", sex);
		params.add("work_place", work_place);
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
	
	/**
	 * 修改密码
	 * @param old_psw
	 * @param new_psw
	 * @param asyncHttpResponseHandler
	 */
	public static void change_psw(String old_psw, String new_psw, AsyncHttpResponseHandler asyncHttpResponseHandler) {
		RequestParams params = new RequestParams();
		if(!(old_psw == null && old_psw.equals("")))
		params.add("old_psw", old_psw);
		if(!(new_psw == null && new_psw.equals("")))
		params.add("new_psw", new_psw);
		RestClient.post("/home/security/changePsw", params, asyncHttpResponseHandler);
	}
	
	/**
	 * 忘记密码 发送邮箱
	 * @param email
	 * @param responseHandler
	 */
	public static void forgot_psw(String email, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = new RequestParams();
		if(!(email == null &&email.equals("")))
			params.add("email", email);
		RestClient.post("/home/security/createLink", params, responseHandler);
	}
	

}
