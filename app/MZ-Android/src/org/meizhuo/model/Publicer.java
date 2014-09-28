package org.meizhuo.model;

/**
 * 普通用户
 * @author Jason
 *
 */

public class Publicer {

	public Publicer() {
		// TODO Auto-generated constructor stub
	}

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
		return "Publicer [email=" + email + ", phoneNumber=" + phoneNumber
				+ ", name=" + name + ", gender=" + gender + ", workPlace="
				+ workPlace + "]";
	}

}
