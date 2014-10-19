package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import butterknife.InjectView;

public class InstitutionInfo_Details_com_intro extends BaseActivity{

	/*
	   <TextView 
        android:id="@+id/tv_intro_title"
        android:layout_height="wrap_content"
        style="@style/TextView"
        android:text=""
        />
    
     <TextView 
        android:id="@+id/tv_intro_content"
        android:layout_height="wrap_content"
        style="@style/TextView"
        android:text=""
        />
         * 
        	Intent it =  new Intent(this, InstitutionInfo_Details_com_intro.class);
		it.putExtra("content", institution.getDescription().toString());
		startActivity(it);
	 */
	@InjectView(R.id.tv_intro_title) TextView tv_intro_title;
	@InjectView(R.id.tv_intro_content) TextView tv_intro_content;
	String title = "";
	String content = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState ,R.layout.acty_institution_intro);
		setAppTitle("公司简介");
		initData();
		initLayout();
	}
	
	private void initData() {
		content = getIntent().getStringExtra("content");
		title =getIntent().getStringExtra("title");
		
	}
	
	private void initLayout(){
		tv_intro_title.setText(title);
		tv_intro_content.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动 
		tv_intro_content.setText(Html.fromHtml(content));
		
	}
	
	

}
