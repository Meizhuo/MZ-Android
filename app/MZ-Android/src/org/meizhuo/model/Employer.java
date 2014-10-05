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
			array =  obj.getJSONArray("employer");
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


	/**id */
	private int id;
	/**邮箱*/
	private String email;

	/**电话号码,默认是单位座机*/
	private String phoneNumber;
	/**企业名称*/
	private String name;
	/**办公地址*/
	private String address;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	@Override
	public String toString() {
		return "Employer [id=" + id + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", name=" + name + ", address=" + address + "]";
	}
	

}
