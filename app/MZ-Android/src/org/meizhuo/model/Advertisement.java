package org.meizhuo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class Advertisement implements Serializable{
	
	
	
	/**
	 * 解析单个广告对象
	 * @param json
	 * @return
	 */
	public static Advertisement create_by_json(String json){
		try {
			Gson gson = new Gson();
			return (Advertisement)gson.fromJson(json, Advertisement.class);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	/**
	 * 解析广告列表
	 * @param jsonarray
	 * @return
	 */
	public static List<Advertisement> create_by_jsonarray(String jsonarray){
		List<Advertisement>list = new ArrayList<Advertisement>();
		JSONObject obj = null;
		JSONArray array = null;
		try {
			obj =  new JSONObject(jsonarray);
			array =  obj.getJSONArray("response");
			for(int i = 0 ; i < array.length();i++)
			{
				list.add(create_by_json(array.getJSONObject(i).toString()));
			}
		} catch (Exception e) {
			// TODO: handle exception
			list = null;
		}
		return list;
	}
	
	public Advertisement() {
		// TODO Auto-generated constructor stub
	}
	public Advertisement(String id, String description, String url,
			String pic_url) {
		super();
		this.id = id;
		this.description = description;
		this.url = url;
		this.pic_url = pic_url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

	
	@Override
	public String toString() {
		return "Advertisement [id=" + id + ", description=" + description
				+ ", url=" + url + ", pic_url=" + pic_url + "]";
	}
	/**广告id*/
	private String id;
	/**广告描述*/
	private String description;
	/***广告url*/
	private String url;
	/**图片url**/
	private String pic_url;
	


}
