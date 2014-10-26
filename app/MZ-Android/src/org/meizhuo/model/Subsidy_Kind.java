package org.meizhuo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.meizhuo.model.Subsidy_certificateTypes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 
 * @author Jason
 *
 */
@SuppressWarnings("serial")
public class Subsidy_Kind implements Serializable {
	
	
	/**
	 * 解析一个补贴项目的类别
	 * @param json
	 * @return
	 */
	public static Subsidy_Kind create_by_json(String json) {
		try {
			Gson gson = new Gson();
			return (Subsidy_Kind)gson.fromJson(json, Subsidy_Kind.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Subsidy_Kind> create_by_jsonarray(String jsonarray) {
		List<Subsidy_Kind> list = new ArrayList<Subsidy_Kind>();
		JSONObject obj = null;
		JSONArray array = null;
		try {
			obj =  new JSONObject(jsonarray);
			array = obj.getJSONArray("response");
			for(int i = 0; i < array.length();i++){
				list.add(create_by_json(array.getJSONObject(i).toString()));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	
	
	/**
	 * jason 直接解析返回的json
	 * @param obj
	 * @return
	 */
	public static ArrayList<Subsidy_Kind> create_by_jsonObject(JSONObject obj) {
		ArrayList<Subsidy_Kind> list = new ArrayList<Subsidy_Kind>();
		try {
			JSONArray array = obj.getJSONArray("response");
			for(int i = 0; i < array.length();i++){
				JSONObject jsonObject = array.getJSONObject(i);
				Subsidy_Kind kind = new Subsidy_Kind();
				kind.setKind(jsonObject.getString("kind"));
				list.add(kind);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	public Subsidy_Kind() {
		// TODO Auto-generated constructor stub
	}
	/***
	 * 种类
	 */
	private String kind;
	

	public String getKind() {
		return kind;
	}


	public void setKind(String kind) {
		this.kind = kind;
	}
	

	@Override
	public String toString() {
		return "Subsidy_Kind [kind=" + kind + "]";
	}


	
	
	

}
