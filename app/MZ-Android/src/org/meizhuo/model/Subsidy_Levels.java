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
public class Subsidy_Levels implements Serializable {
	
	
	/**
	 * 
	 * @param json
	 * @return
	 */
	public static Subsidy_Levels create_by_json(String json) {
		Subsidy_Levels kind=  null;
		Gson gson = new Gson();
		try {
			kind = (Subsidy_Levels) gson.fromJson(json, Subsidy_Levels.class);
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
	public static ArrayList<Subsidy_Levels> create_by_jsonObject(JSONObject obj) {
		ArrayList<Subsidy_Levels> list = new ArrayList<Subsidy_Levels>();
		try {
			JSONArray array = obj.getJSONArray("response");
			for(int i = 0; i < array.length();i++){
				JSONObject jsonObject = array.getJSONObject(i);
				Subsidy_Levels level = new Subsidy_Levels();
				level.setLevel(jsonObject.getString("level"));
				list.add(level);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	public Subsidy_Levels() {
		// TODO Auto-generated constructor stub
	}
	
	private String level;




	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Subsidy_Levels [level=" + level + "]";
	}

	
	
	

}
