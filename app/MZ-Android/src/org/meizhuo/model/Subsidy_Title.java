package org.meizhuo.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.meizhuo.model.Subsidy_certificateTypes;

import com.google.gson.Gson;

/**
 * 
 * @author Jason
 *
 */
public class Subsidy_Title implements Serializable {
	
	
	/**
	 * 
	 * @param json
	 * @return
	 */
	public static Subsidy_Title create_by_json(String json) {
		Subsidy_Title kind=  null;
		Gson gson = new Gson();
		try {
			kind = (Subsidy_Title) gson.fromJson(json, Subsidy_Title.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			kind = null;
		}
		return kind;
	}
	
	
	/**
	 * jason 直接解析返回的json
	 * @param obj
	 * @return
	 */
	public static ArrayList<Subsidy_Title> create_by_jsonObject(JSONObject obj) {
		ArrayList<Subsidy_Title> list = new ArrayList<Subsidy_Title>();
		try {
			JSONArray array = obj.getJSONArray("response");
			for(int i = 0; i < array.length();i++){
				JSONObject jsonObject = array.getJSONObject(i);
				Subsidy_Title title = new Subsidy_Title();
				title.setTitle(jsonObject.getString("series"));
				list.add(title);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	public Subsidy_Title() {
		// TODO Auto-generated constructor stub
	}
	
	private String title;




	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	@Override
	public String toString() {
		return "Subsidy_Title [title=" + title + "]";
	}



}
