package org.meizhuo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.nfc.NfcAdapter.CreateBeamUrisCallback;
import android.util.Log;

import com.google.gson.Gson;


/**
 * 证书类别
 * @author Jason
 *
 */
public class Subsidy_certificateTypes implements Serializable{
	private static final String TAG ="Subsidy_certificateTypes";
	
	
	public static Subsidy_certificateTypes create_by_json(String json) {
		Subsidy_certificateTypes certificateTypes = null;
		Gson gson = new Gson();
		try {
			certificateTypes = (Subsidy_certificateTypes) gson.fromJson(json, Subsidy_certificateTypes.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			certificateTypes = null;
		}
		return certificateTypes;
	}
	
	public static ArrayList<Subsidy_certificateTypes> create_by_jsonarray(String jsonarray) {
		ArrayList<Subsidy_certificateTypes> list = new ArrayList<Subsidy_certificateTypes>();
		JSONObject obj = null;
		JSONArray array = null;
		try {
			obj =  new JSONObject(jsonarray);
			array = obj.getJSONArray("response");
			for(int i = 0; i < array.length();i++){
				list.add(create_by_json(array.getJSONArray(i).toString()));
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
	public static ArrayList<Subsidy_certificateTypes> create_by_jsonObject(JSONObject obj) {
		ArrayList<Subsidy_certificateTypes> list = new ArrayList<Subsidy_certificateTypes>();
		try {
			JSONArray array = obj.getJSONArray("response");
			for(int i = 0; i < array.length();i++){
				JSONObject jsonObject = array.getJSONObject(i);
				Subsidy_certificateTypes certificateTypes = new Subsidy_certificateTypes();
				certificateTypes.setCertificate_type(jsonObject.getString("certificate_type"));
				list.add(certificateTypes);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	public Subsidy_certificateTypes() {
		// TODO Auto-generated constructor stub
	}
	
	private String certificate_type;



	public String getCertificate_type() {
		return certificate_type;
	}

	public void setCertificate_type(String certificate_type) {
		this.certificate_type = certificate_type;
	}

	@Override
	public String toString() {
		return "Subsidy_certificateTypes [certificate_type=" + certificate_type
				+ "]";
	}
}
