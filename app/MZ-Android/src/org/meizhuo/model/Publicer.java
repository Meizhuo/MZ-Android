package org.meizhuo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * 普通用户
 * @author Jason
 *
 */
@SuppressWarnings("serial")
public class Publicer implements Serializable{
	
	/**
	 * 解析单个用户
	 * @param json
	 * @return
	 */
	public static Publicer create_by_json(String json){
		try {
			Gson gson = new Gson();
			return (Publicer) gson.fromJson(json, Publicer.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 解析一个用户列表的列表
	 * @param jsonarray
	 * @return
	 */
	public static List<Publicer> create_by_jsonarray(String jsonarray) {
		List<Publicer> list =  new ArrayList<Publicer>();
		JSONObject obj = null;
		JSONArray array = null;
		try {
			obj = new JSONObject(jsonarray);
			array = obj.getJSONArray("publicer");
			for (int i = 0 ; i < array.length(); i++) {
				list.add(create_by_json(array.getJSONObject(i).toString()));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	

	public Publicer() {
		// TODO Auto-generated constructor stub
	}

	/**用户id*/
	private int id;
	/**邮箱*/
	private String email;
	/**电话号码*/
	private String phoneNumber;
	/**名字*/
	private String name;
	/** 性别*/
	private String gender;
	/**工作地点*/
	private String workPlace;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getWorkPlace() {
		return workPlace;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	@Override
	public String toString() {
		return "Publicer [id=" + id + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", name=" + name + ", gender=" + gender
				+ ", workPlace=" + workPlace + "]";
	}
	

}
