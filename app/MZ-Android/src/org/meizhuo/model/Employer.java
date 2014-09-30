package org.meizhuo.model;

import java.io.Serializable;

/**
 * 用人单位用户
 * @author Jason
 *
 */
@SuppressWarnings("serial")
public class Employer implements Serializable{

	public Employer() {
		// TODO Auto-generated constructor stub
	}
	
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
		return "Employer [email=" + email + ", phoneNumber=" + phoneNumber
				+ ", name=" + name + ", address=" + address + "]";
	}

}
