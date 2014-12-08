package org.meizhuo.app.acty;

import java.util.List;

import org.meizhuo.api.RestClient;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import butterknife.InjectView;

/**
 * 新闻显示
 * 
 * @author Jayin
 * 
 */
@SuppressLint("SetJavaScriptEnabled")
public class ProfessionalArticle extends BaseActivity  {
	
	private static final String TAG = "Professional_Article";
	@InjectView(R.id.webview) WebView webview;
	@InjectView(R.id.pb)ProgressBar pb;
	String content = "";
	String title = "";
	List<Integer> imageIdList;
	String url;
	String doc_id;

	  @SuppressLint("SetJavaScriptEnabled")  
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
		webview.getSettings().setJavaScriptEnabled(true);
	
		webview.loadUrl(url);
		webview.setWebViewClient(new MyWebViewClient());
		webview.setWebChromeClient(new MyWebChromeViewClient());
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
	 *
	 */
	public class JavascriptInterface {
		private Context context;
		
		public JavascriptInterface(Context context) {
			// TODO Auto-generated constructor stub
			this.context = context;
		}
		
		@android.webkit.JavascriptInterface
		public void openImage(String img) {
			Log.i(TAG, "执行了");
			
			Intent intent = new Intent();
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
			//html加载完成之后,添加监听图片的点击js函数
			addImageClickListner();
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
	
	
//	private class MyWebChromeViewClient extends WebChromeClient {  
//	    @Override  
//	    public void onProgressChanged(WebView view, int newProgress) {  
//	        pb.setProgress(newProgress);  
//	        if(newProgress==100){  
//	            pb.setVisibility(View.GONE);  
//	        }  
//	        super.onProgressChanged(view, newProgress);  
//	    }  
	
	private class MyWebChromeViewClient extends WebChromeClient{
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			// TODO Auto-generated method stub
			if(newProgress != 0){
				pb.setVisibility(View.VISIBLE);
				pb.setProgress(newProgress);
			}
			
			if(newProgress == 100){
				pb.setVisibility(View.GONE);
			}
			super.onProgressChanged(view, newProgress);
		}
	}
//	

	


}
