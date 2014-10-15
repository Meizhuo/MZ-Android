package org.meizhuo.app.acty;

import java.util.List;

import org.apache.http.Header;
import org.json.JSONObject;
import org.meizhuo.api.ArticleAPI;
import org.meizhuo.api.RestClient;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.webkit.WebView;
import butterknife.InjectView;

/**
 * 单位信息列表
 * 
 * @author Jayin
 * 
 */
public class Professional_Article extends BaseActivity  {
	
	private static final String TAG = "Professional_Article";
	@InjectView(R.id.webview) WebView webview;
	String content = "";
	String title = "";
	List<Integer> imageIdList;
	String url;
	String doc_id;

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState, R.layout.acty_professional_artilecontent);
		
		
		initData();
		initLayout();
		setAppTitle(title);
	}
	/*
	 * Intent intent =  new Intent(getActivity(), Professional_Article.class);
		intent.putExtra("title", data.get(position).getTitle());
		intent.putExtra("doc_id", data.get(position).getId());
		intent.putExtra("content", data.get(position).getContent());
	 */
	private void initData(){
		Intent intent = getIntent();
		String doc_id = intent.getStringExtra("doc_id");
		title  = intent.getStringExtra("title");
		String baseurl = RestClient.BASE_URL;
		url = baseurl + "/admin/index/viewDocument" + "/" + "doc_id" + "/" + doc_id;
	}
	
	private void initLayout(){
		webview.loadUrl(url);
		
	}
	


}
