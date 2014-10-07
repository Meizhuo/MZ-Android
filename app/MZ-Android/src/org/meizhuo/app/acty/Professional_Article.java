package org.meizhuo.app.acty;

import java.util.List;

import org.meizhuo.api.ArticleAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import butterknife.InjectView;

/**
 * 单位信息列表
 * 
 * @author Jayin
 * 
 */
public class Professional_Article extends BaseActivity  {
	
	private static final String TAG = "Professional_Article";
	@InjectView(R.id.tv_atricle_title) TextView tv_atricle_title;
	@InjectView(R.id.tv_atricle_content) TextView tv_atricle_content;
	String content = "";
	String title = "";
	List<Integer> imageIdList;

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState, R.layout.acty_professional_artilecontent);
		setAppTitle("详情");
		
		
		initData();
		initLayout();
	}
	/*
	 * Intent intent =  new Intent(getActivity(), Professional_Article.class);
		intent.putExtra("Category_id", data.get(position).getCategory_id());
		intent.putExtra("id", data.get(position).getId());
		intent.putExtra("content", data.get(position).getContent());
	 */
	private void initData(){
		 content = getIntent().getStringExtra("content");
		 title =  getIntent().getStringExtra("title");
	}
	
	private void initLayout(){
		tv_atricle_title.setText(title);
		tv_atricle_content.setMovementMethod(ScrollingMovementMethod
				.getInstance());// 滚动
		tv_atricle_content
		.setText(Html.fromHtml(content));
	}


}
