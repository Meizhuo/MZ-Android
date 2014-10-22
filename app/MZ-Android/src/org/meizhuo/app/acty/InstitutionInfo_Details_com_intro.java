package org.meizhuo.app.acty;

import org.meizhuo.api.RestClient;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.InjectView;

@SuppressLint("SetJavaScriptEnabled")
public class InstitutionInfo_Details_com_intro extends BaseActivity{
	

//	@InjectView(R.id.tv_intro_title) TextView tv_intro_title;
//	@InjectView(R.id.tv_intro_content) TextView tv_intro_content;
//	String title = "";
//	String content = "";
	@InjectView(R.id.webview) WebView webview;
	String ins_id;
	String url = "";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState ,R.layout.acty_institution_intro);
		super.onCreate(savedInstanceState, R.layout.acty_professional_artilecontent);
		initData();
		initLayout();
		setAppTitle("公司简介");
	}
	
	private void initData() {
//		content = getIntent().getStringExtra("content");
//		title =getIntent().getStringExtra("title");
		ins_id = getIntent().getStringExtra("ins_id");
		String baseurl = RestClient.BASE_URL;
		url = baseurl + "/admin/view/insIntro" + "/" + "ins_id" + "/" + ins_id; 
		
		
	}
	
	/**
	 * 
	 */
	private void initLayout(){
//		tv_intro_title.setText(title);
//		tv_intro_content.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动 
//		tv_intro_content.setText(Html.fromHtml(content));
		webview.getSettings().setJavaScriptEnabled(true);
		webview.loadUrl(url);
		webview.setWebViewClient(new MyWebViewClient());
		//添加js交互接口类
		webview.addJavascriptInterface(new JavascriptInterface(this), "imagelistner");
	}
	
	
	private void addImageClickListener() {
		webview.loadUrl("javascript:(function(){" +
				"var objs = document.getElementsByTagName(\"img\"); " + 
						"for(var i=0;i<objs.length;i++)  " + 
				"{"
						+ "    objs[i].onclick=function()  " + 
				"    {  " 
						+ "        window.imagelistner.openImage(this.src);  " + 
				"    }  " + 
				"}" + 
				"})()");
	}
	
	/**
	 * js通信接口类
	 * @author Jason
	 *
	 */
	public class JavascriptInterface{
		private Context context;
		
		public JavascriptInterface(Context context) {
			// TODO Auto-generated constructor stub
			this.context = context;
		}
		
		@android.webkit.JavascriptInterface
		public void openImage(String img) {
			Intent intent =  new Intent();
			intent.putExtra("image", img);
			intent.setClass(context, ShowWebImageActivity.class);
			context.startActivity(intent);
		}
	}
	
	
	private class MyWebViewClient extends WebViewClient {
		
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			return super.shouldOverrideUrlLoading(view, url);
		}
		
		@SuppressLint("SetJavaScriptEnabled")
		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			view.getSettings().setJavaScriptEnabled(true);
			super.onPageFinished(view, url);
			//html加载完成之后，添加监听图片的js函数
			addImageClickListener();
		}
		
		@SuppressLint("SetJavaScriptEnabled")
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// TODO Auto-generated method stub
			view.getSettings().setJavaScriptEnabled(true);
			super.onPageStarted(view, url, favicon);
		}
		
		@Override
		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			// TODO Auto-generated method stub
			super.onReceivedError(view, errorCode, description, failingUrl);
		}
	}
	

}
