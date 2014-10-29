package org.meizhuo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * 培训结构课程
 * @author Jason
 *
 */
@SuppressWarnings("serial")
public class Course implements Serializable{
	
	/**
	 * 解析单个课程对象
	 * @param json
	 * @return
	 */
	public static Course create_by_json(String json){
		try {
			Gson gson = new Gson();
			return (Course)gson.fromJson(json, Course.class);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	/***
	 * 解析单个课程列表
	 * @param jsonarray
	 * @return
	 */
	public static List<Course>create_by_jsonarray(String jsonarray){
		List<Course>list =  new ArrayList<Course>();
		JSONObject obj = null;
		JSONArray array =  null;
		try {
			obj =  new JSONObject(jsonarray);
			array =  obj.getJSONArray("response");
			for(int i = 0 ; i < array.length() ; i++ )
			{
				list.add(create_by_json(array.getJSONObject(i).toString()));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			 list = null;
		}
		
		return list;
	}
	
	public Course() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInstitution_id() {
		return institution_id;
	}

	public void setInstitution_id(String institution_id) {
		this.institution_id = institution_id;
	}

	public String getSubsidy_id() {
		return subsidy_id;
	}

	public void setSubsidy_id(String subsidy_id) {
		this.subsidy_id = subsidy_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getCertificate_type() {
		return certificate_type;
	}

	public void setCertificate_type(String certificate_type) {
		this.certificate_type = certificate_type;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	//文章id
	private String id;
	@Override
	public String toString() {
		return "Course [id=" + id + ", institution_id=" + institution_id
				+ ", subsidy_id=" + subsidy_id + ", name=" + name
				+ ", start_time=" + start_time + ", address=" + address
				+ ", teacher=" + teacher + ", introduction=" + introduction
				+ ", cost=" + cost + ", certificate_type=" + certificate_type
				+ ", kind=" + kind + ", level=" + level + ", money=" + money
				+ ", series=" + series + ", title=" + title + "]";
	}

	/**机构id*/
	private String institution_id ;
	/**对应的补贴项目id*/
	private String subsidy_id;
	/**课程名称*/
	private String name;
	/**开课时间*/
	private String start_time;
	/**开课地址*/
	private String address;
	/** 开课教师*/
	private String teacher;
	/**课程介绍*/
	private String introduction;
	/**课程费用*/
	private String cost;
	/**证书类别*/
	private String certificate_type;
	/**项目类别*/
	private String kind;
	/**项目等级*/
	private String level;
	/**补贴金额*/
	private String money;
	/**系列*/
	private String series;
	/**资格名称*/
	private String title;
	
	
	
	
}
