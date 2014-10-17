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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getApproval_number() {
		return approval_number;
	}

	public void setApproval_number(String approval_number) {
		this.approval_number = approval_number;
	}

	public String getValidity_date() {
		return validity_date;
	}

	public void setValidity_date(String validity_date) {
		this.validity_date = validity_date;
	}

	public String getTraining_scope() {
		return training_scope;
	}

	public void setTraining_scope(String training_scope) {
		this.training_scope = training_scope;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTeacher_resource() {
		return teacher_resource;
	}

	public void setTeacher_resource(String teacher_resource) {
		this.teacher_resource = teacher_resource;
	}

	public String getContact_member() {
		return contact_member;
	}

	public void setContact_member(String contact_member) {
		this.contact_member = contact_member;
	}

	public String getContact_phone() {
		return contact_phone;
	}

	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}

	public String getContact_email() {
		return contact_email;
	}

	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}






	@Override
	public String toString() {
		return "Institution [nickname=" + nickname + ", phone=" + phone
				+ ", email=" + email + ", reg_time=" + reg_time + ", level="
				+ level + ", status=" + status + ", uid=" + uid + ", name="
				+ name + ", address=" + address + ", manager=" + manager
				+ ", type=" + type + ", approval_number=" + approval_number
				+ ", validity_date=" + validity_date + ", training_scope="
				+ training_scope + ", description=" + description
				+ ", teacher_resource=" + teacher_resource
				+ ", contact_member=" + contact_member + ", contact_phone="
				+ contact_phone + ", contact_email=" + contact_email + "]";
	}






	/**公司昵称*/
	private String nickname;
	/**电话*/
	private String phone;
	/**电子邮件 */
	private String email;
	/**注册 时间*/
	private String reg_time;
	/**机构级别*/
	private String level;
	/**机构状态*/
	private String status;
	/**机构id*/
	private String uid;
	/**公司名称*/
	private String name;
	/**办学地址*/
	private String address;
	/**负责人*/
	private String manager;
	/**办学类型*/
	private String type;
	/**许可日期*/
	private String approval_number;
	/**验证日期*/
	private String validity_date;
	/**培训范围 */
	private String training_scope;
	/**公司描述*/
	private String description;
	/**教师资源*/
	private String teacher_resource;
	/**联系人*/
	private String contact_member;
	/**联系电话*/
	private String contact_phone;
	/**联系邮箱*/
	private String contact_email;

}
