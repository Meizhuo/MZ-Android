package org.meizhuo.model;

import java.io.Serializable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
/**
 * 新闻列表以及内容
 * @author Jason
 *
 */
@SuppressWarnings("serial")
public class DocumentInfo implements Serializable{
	/**
	 * 解析一篇文章
	 * @param json
	 * @return
	 */
	public static DocumentInfo create_by_json(String json){
		try {
			Gson gson =  new Gson();
			return (DocumentInfo)gson.fromJson(json, DocumentInfo.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 解析文章列表
	 * @param jsonarray
	 * @return
	 */
	public static List<DocumentInfo> create_by_jsonarray(String jsonarray){
		List<DocumentInfo> list =  null;
		JSONObject obj = null;
		JSONArray array = null;
		try {
			obj = new JSONObject(jsonarray);
			array = obj.getJSONArray("response");
			for(int i =0 ; i < array.length() ; i++)
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
	

	public DocumentInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getViews() {
		return views;
	}
	public void setViews(String views) {
		this.views = views;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "DocumentList [id=" + id + ", uid=" + uid + ", title=" + title
				+ ", category_id=" + category_id + ", display=" + display
				+ ", status=" + status + ", views=" + views + ", create_time="
				+ create_time + ", update_time=" + update_time + ", from="
				+ from + ", level=" + level + ", description=" + description
				+ ", order_num=" + order_num + ", content=" + content + "]";
	}

	/**文章id */
	private String id;
	/**作者id*/
	private String uid;
	/**文章标题*/
	private String title;
	/**文章分类(栏目)*/
	private String category_id;
	/**可见性(0不可见 1可见 默认1可见)*/
	private String display;
	/**审核状态 审核 未审核 未通过*/
	private String status;
	/**浏览量*/
	private String views;
	/**创建时间*/
	private String create_time;
	/**最后更新时间*/
	private String update_time;
	/**新闻来源*/
	private String from;
	/**文章等级*/
	private String level;
	/***简单描述*/
	private String description;
	/**序号*/
	private String order_num;
	/***具体内容*/
	private String content;
	
}
