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
public class Subsidy_Series implements Serializable {
	
	
	/**
	 * 
	 * @param json
	 * @return
	 */
	public static Subsidy_Series create_by_json(String json) {
		Subsidy_Series kind=  null;
		Gson gson = new Gson();
		try {
			kind = (Subsidy_Series) gson.fromJson(json, Subsidy_Series.class);
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
	public static ArrayList<Subsidy_Series> create_by_jsonObject(JSONObject obj) {
		ArrayList<Subsidy_Series> list = new ArrayList<Subsidy_Series>();
		try {
			JSONArray array = obj.getJSONArray("response");
			for(int i = 0; i < array.length();i++){
				JSONObject jsonObject = array.getJSONObject(i);
				Subsidy_Series series = new Subsidy_Series();
				series.setSeries(jsonObject.getString("series"));
				list.add(series);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	public Subsidy_Series() {
		// TODO Auto-generated constructor stub
	}
	
	private String series;




	public String getSeries() {
		return series;
	}


	public void setSeries(String series) {
		this.series = series;
	}

	@Override
	public String toString() {
		return "Subsidy_Series [series=" + series + "]";
	}


}
