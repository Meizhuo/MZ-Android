package org.meizhuo.api;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ArticleAPI {

	/**
	 * 获取栏目分类
	 * @param responseHandler
	 */
	public static void getDocumentCategory(AsyncHttpResponseHandler responseHandler ){
		RestClient.get("/home/document/getCategory", null, responseHandler);
	}
	
	/**
	 * 获取文章列表以及其对应信息
	 * @param category_id 栏目id
	 * @param title 类似的标题
	 * @param content 类似的内容
	 * @param page 页码
	 */
	public static void getArticleInfo(String category_id, String title, String content,
			String page,AsyncHttpResponseHandler responseHandler){
		RequestParams params= new RequestParams();
		if(!(category_id == null || category_id.equals("")))
			params.add("category_id", category_id);
		if(!(title == null || title.equals("")))
			params.add("title", title);
		if(!(content == null || content.equals("")))
			params.add("content", content);
		if(!(page == null || page.equals("")))
			params.add("page", page);
		RestClient.get("/home/document/search", params, responseHandler);
	}
	
	
}
