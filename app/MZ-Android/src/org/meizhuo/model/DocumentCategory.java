package org.meizhuo.model;

import java.io.Serializable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

/***
 * 栏目列表 就是Tab上面的那几个列表
 * 目前数据暂时写死，因为有些地方需要商议一下 这个model以后可能会用到
 * @author Jason
 *
 */

@SuppressWarnings("serial")
public class DocumentCategory implements Serializable{
	
	
	/**
	 * 解析单个文档栏目列表
	 * @param json
	 * @return
	 */
	public static DocumentCategory create_by_json(String json){
		try {
			Gson gson = new Gson();
			return (DocumentCategory)gson.fromJson(json, DocumentCategory.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	/***
	 * 解析一个列表的文档
	 * @param jsonarray
	 * @return
	 */
	public static List<DocumentCategory> create_by_jsonarray(String jsonarray){
		List<DocumentCategory>list = null;
		JSONObject obj = null;
		JSONArray array = null;
		try {
			obj =  new JSONObject(jsonarray);
			array = obj.getJSONArray("response");
			for(int i = 0 ; i < array.length(); i++)
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

	public DocumentCategory() {
		// TODO Auto-generated constructor stub
	}
	
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "DocumentList [category_id=" + category_id + ", name=" + name
				+ ", description=" + description + "]";
	}

	/**分类id*/
	private String category_id;
	/**栏目名字*/
	private String name;
	/**栏目描述*/
	private String description;
	

}
