package org.meizhuo.app.acty;

import org.meizhuo.api.RestClient;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import butterknife.InjectView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint("SetJavaScriptEnabled")
public class CourseDetails_description extends BaseActivity{

	/*@InjectView(R.id.tv_course_title) TextView tv_course_title;
	@InjectView(R.id.tv_course_content) TextView tv_course_content;
	String title = "";
	String content = "";*/
	@InjectView(R.id.webview) WebView webview;
	String course_id = "";
	String url  = "";
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState,R.layout.acty_professional_artilecontent);
			setAppTitle("课程简介");
			initData();
			initLayout();
		}
		
		private void initData(){
		/*	content = getIntent().getStringExtra("content");
			title = getIntent().getStringExtra("title");*/
			course_id = getIntent().getStringExtra("course_id");
			String baseurl = RestClient.BASE_URL;
			url = baseurl + "/admin/view/courseIntro" + "/" + "course_id" + "/" + course_id;
			
		}
		
		private void initLayout(){
//			tv_course_title.setText(title);
//			tv_course_content.setMovementMethod(ScrollingMovementMethod.getInstance()); //滚动
//			tv_course_content.setText(Html.fromHtml(content));
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
