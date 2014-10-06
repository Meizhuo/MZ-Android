package org.meizhuo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * 用人单位用户
 * @author Jason
 *
 */
@SuppressWarnings("serial")
public class Employer implements Serializable{
	
	/**
	 * 解析单个用户
	 * @param json 单个用户的json字符串 
	 * @return Employer
	 */
	public static Employer create_by_json(String json) {
		try {
			Gson gson =  new Gson();
			return (Employer)gson.fromJson(json, Employer.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 *  解析一个用户列表的列表
	 * @param jsonarray
	 * @return
	 */
	public static List<Employer> create_by_jsonarray(String jsonarray) {
		ArrayList<Employer> list =  new ArrayList<Employer>();
		JSONObject obj = null;
		JSONArray array = null;
		try {
			obj =  new JSONObject(jsonarray);
			array =  obj.getJSONArray("response");
			for (int i = 0 ; i < array.length() ; i++) {
				list.add(create_by_json(array.getJSONObject(i).toString()));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			list =  null;
		}
		return list;
	}

	public Employer() {
		// TODO Auto-generated constructor stub
	}


	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReg_time() {
		return reg_time;
	}

	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}

	public String getContact_phone() {
		return contact_phone;
	}

	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}


	@Override
	public String toString() {
		return "Employer [uid=" + uid + ", email=" + email + ", phone=" + phone
				+ ", level=" + level + ", status=" + status + ", reg_time="
				+ reg_time + ", contact_phone=" + contact_phone + ", nickname="
				+ nickname + ", address=" + address + ", isLogin=" + isLogin
				+ "]";
	}


	/**id */
	private int uid;
	/**邮箱*/
	private String email;

	/**电话号码,默认是单位座机*/
	private String phone;
	/**用户等级*/
	private String level;
	/**用户状态*/
	private String status;
	/**注册事件*/
	private String reg_time;
	/**联系电话*/
	private String contact_phone;
	/**企业名称*/
	private String nickname;
	/**办公地址*/
	private String address;
	/**是否已登录*/
	private boolean isLogin;
	
	
	
	

}
