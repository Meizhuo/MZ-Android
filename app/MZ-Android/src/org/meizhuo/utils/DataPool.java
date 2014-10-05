package org.meizhuo.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.Map;

import com.loopj.android.http.Base64;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Use To Save K-V info 
 * 
 * DataPool is tool that helps you easier to do some operation in the
 * SharedPreference <br>
 * <h1>Like : add(),remove(),get(),set()</h1> <h1>Note :</h1> <li>In currrent
 * version of android SDK,DataPool is not support multi thread
 *
 */
public class DataPool {
	
	/**普通用户账号密码以及信息的存储*/
	
	public static final String SP_Name_Publicer = "Publicer"; //SharedPreference's name 用来保存公共用户账号密码的数据池
	public final static String SP_Key_Publicer = "publicer";//key name in SharedPreference  保存用户信息
	public static final String SP_Key_Publicer_Name = "publicername"; //登录账号 可以是邮箱或者手机号码
	public static final String SP_Key_Publicer_PSW = "publicerpsw"; //登录密码
	
	/**用人单位以及相关信息的存储*/
	public static final String SP_Name_Employer = "Employer"; //用来保存用人单位的信息的sharePreference
	public static final String SP_Key_Employer = "employer";  //Key name in SharePreference  保存用人单位信息
	public static final String SP_Key_Employer_Name = "employer_name"; // 用人用户的账号
	public static final String SP_Key_Employer_PSW = "employerpsw";  //登录密码
	
	
	
	private Context context;
	private String DataPoolName = "DataPool";
	private SharedPreferences sp;

	/**初始化这个数据池*/
	public DataPool(Context context) {
		this("DataPool", context);
	}
	
	public DataPool(String dataPoolName, Context context) {
		this.DataPoolName = dataPoolName;
		this.context = context;
		sp = context.getSharedPreferences(this.DataPoolName, Context.MODE_PRIVATE);
	}

	/**
	 *  将对象序列化  <br>
	 *  <li>  使用base64编码  对value进行加密 
	 * add a key(String)-value(Serialize object) into SharedPreference
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean put(String key, Serializable value) {
		boolean flag = false;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			 oos = new ObjectOutputStream(bos);
			 oos.writeObject(value);
			 String base64String = Base64.encodeToString(bos.toByteArray(), Base64.DEFAULT);
			 sp.edit().putString(key, base64String).commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			if (oos != null){
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * add a key(String, default is "temp")-value(object) into SharedPreference
	 * @param value
	 * @return
	 */
	public boolean put(Serializable value) {
		return put("temp",value);
	}
	
	/**
	 *  get value(Serialized Object)from DataPool(SharedPreference)with the given key
	 * @param key
	 *  		key of this pair, with the default key= "temp"
	 * @return  one Serialized Object
	 */
	public Serializable get(String key) {
		if (!contains(key))
			return null;
		String base64String = sp.getString(key, "");
		byte[] buf = Base64.decode(base64String, Base64.DEFAULT);
		ByteArrayInputStream bis =  new ByteArrayInputStream(buf);
		ObjectInputStream ois = null;
		Serializable result = null;
		try {
			ois = new ObjectInputStream(bis);
			result = (Serializable)ois.readObject();
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			if (ois != null){
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}
		}
		return result;
	}
	
	/**
	 * check if DataPool(SharedPreference) contain the given key
	 * 
	 * @param key the given key
	 * @return true if it contains
	 */
	public boolean contains(String key) {
		return sp.contains(key);
	}

	/**
	 * check if DataPool(SharedPreference) is empty
	 * @return true if it's empty
	 */
	public boolean isEmpty(){
		return sp.getAll().size() == 0;
	}
	
	/**
	 * remove a K-V of this pair
	 * 
	 * @param key
	 * @return true if it removes successfully
	 */
	public void remove(String key) {
		if (!contains(key))
			return ;
		sp.edit().remove(key).commit();
	}
	
	/**
	 * remove all the K-V of this pair
	 * 
	 * @return
	 */
	public void removeAll(){
		if (isEmpty())
			return;
		Map<String, ?>map = sp.getAll();
		for (String key : map.keySet()){
			remove(key);
		}
	}
	
	/**
	 * update the K-V
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, Serializable value){
		if (!contains(key))
			return false;
		return put(key, value);
	}
}
