package org.meizhuo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * 补贴项目
 * @author Jason
 *
 */
@SuppressWarnings("serial")
public class Subsidy implements Serializable{
	
	/**
	 * 解析单个补贴项目用户
	 * @param json
	 * @return
	 */
	public static Subsidy create_by_json(String json){
		try {
			Gson gson =  new Gson();
			return (Subsidy) gson.fromJson(json, Subsidy.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 解析一个补贴项目的列表
	 * @param jsonarray
	 * @return
	 */
	public static List<Subsidy> create_by_jsonarray(String jsonarray){
		List<Subsidy> list = new ArrayList<Subsidy>();
		JSONObject obj = null;
		JSONArray array = null;
		try {
			obj = new JSONObject(jsonarray);
			array  = obj.getJSONArray("response");
			for (int i = 0; i < array.length(); i++ ){
				list.add(create_by_json(array.getJSONObject(i).toString()));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	
	

	public Subsidy() {
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	private String id;
	@Override
	public String toString() {
		return "Subsidy [id=" + id + ", certificate_type=" + certificate_type
				+ ", kind=" + kind + ", level=" + level + ", money=" + money
				+ ", series=" + series + ", title=" + title + "]";
	}

	/**证书类别*/
	private String certificate_type;
	/**项目类别*/
	private String kind;
	/**等级*/
	private String level;
	/**补贴金额*/
	private String money;
	/**系列*/
	private String series;
	/**资格名称*/
	private String title;

	


}
