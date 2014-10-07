package org.meizhuo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
/**
 * 机构信息
 * @author Jason
 *
 */
@SuppressWarnings("serial")
public class Institution implements Serializable{
	
	/**
	 * 解析单个用户
	 * @param json 单个用户的字符串
	 * @return
	 */
	public static Institution create_by_json(String json){
		try {
			Gson gson =  new Gson();
			return (Institution)gson.fromJson(json, Institution.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 解析一个用户列表的列表
	 * @param jsonarray 传进来的json对象或者json数组 然后根据这个获得json对象 再解析
	 * @return
	 */
	public static List<Institution> create_by_jsonarray(String jsonarray) {
		ArrayList<Institution> list = new ArrayList<Institution>();
		JSONObject obj = null;
		JSONArray array = null;
		try {
			obj = new JSONObject(jsonarray);
			array = obj.getJSONArray("response");
			for(int i = 0; i < array.length() ; i++){
				list.add(create_by_json(array.getJSONObject(i).toString()));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			list =  null;
		}
		return list;
	}

	public Institution() {
		// TODO Auto-generated constructor stub
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getReg_time() {
		return reg_time;
	}
	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	@Override
	public String toString() {
		return "Institution [uid=" + uid + ", nickname=" + nickname
				+ ", phone=" + phone + ", email=" + email + ", reg_time="
				+ reg_time + ", level=" + level + ", status=" + status
				+ ", name=" + name + ", address=" + address + ", type=" + type
				+ ", description=" + description + "]";
	}
	private String uid;
	private String nickname;
	private String phone;
	private String email;
	private String reg_time;
	private String level;
	private String status;
	private String name;
	private String address;
	private String type;
	private String description;
	
	

}
