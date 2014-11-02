package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import butterknife.InjectView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * 广告显示页面
 * @author Jason
 *
 */
@SuppressLint("SetJavaScriptEnabled")
public class Main_Advertise extends BaseActivity{
	
	@InjectView(R.id.webview) WebView webview;
	@InjectView(R.id.pb)ProgressBar pb;
	
	String url = "";
	String description = "";
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState, R.layout.acty_professional_artilecontent);
		
		initData();
		initLayout();
		setAppTitle("新闻详情");//到时候填写的是新闻的description
	}
	/*
	 intent.putExtra("url", ad.get(position).getUrl());
				intent.putExtra("description", ad.get(position).getDescription());
	 */
	private void initData(){
		url = getIntent().getStringExtra("url");
		description = getIntent().getStringExtra("description");
	}
	
	@SuppressLint("JavascriptInterface")
	private void initLayout(){
		webview.getSettings().setJavaScriptEnabled(true);
		webview.loadUrl(url);
		webview.setWebViewClient(new MyWebViewClient());
		webview.setWebChromeClient(new MyWebChromeClient());
		//添加js交互接口类 
		webview.addJavascriptInterface(new JavascriptInterface(this), "imagelistner");
	}
	
	
	private void addImageClickListner() {
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
	public class JavascriptInterface {
		private Context context;
		
		public JavascriptInterface(Context context) {
			// TODO Auto-generated constructor stub
			this.context =  context;
		}
		
		public void openImage(String img){
			
			
			Intent intent = new Intent();
			intent.putExtra("image", img);
			intent.setClass(context, ShowWebImageActivity.class);
			context.startActivity(intent);
		}
		
	}
	
	

	private class MyWebViewClient extends WebViewClient{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			return super.shouldOverrideUrlLoading(view, url);
		}
		
		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			view.getSettings().setJavaScriptEnabled(true);
			super.onPageFinished(view, url);
			//html加载完成之后,添加监听图片点击的js函数
			addImageClickListner();
		}
		
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
	
	private class MyWebChromeClient extends WebChromeClient{
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			// TODO Auto-generated method stub
			pb.setMax(100);
			pb.setProgress(newProgress);
			if(newProgress==100){
				pb.setVisibility(View.GONE);
			}
			super.onProgressChanged(view, newProgress);
		}
	}

}
