package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import butterknife.InjectView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class CourseDetails_description extends BaseActivity{
	
	/**
	   <TextView 
        android:id="@+id/tv_course_title"
        android:layout_height="wrap_content"
        style="@style/TextView"
        android:textSize="22sp"
        android:text=""
        />
    
     <TextView 
         
        android:id="@+id/tv_course_content"
        android:layout_marginTop="10dp"
        android:layout_height="wrap_content"
        style="@style/TextView"
        android:textSize="18sp"
        android:text=""
        />
         	Intent intent =  new Intent(CourseDetails_OneCourse.this, CourseDetails_description.class);
		intent.putExtra("content", introduction);
		startActivity(intent);
	 */

	@InjectView(R.id.tv_course_title) TextView tv_course_title;
	@InjectView(R.id.tv_course_content) TextView tv_course_content;
	String title = "";
	String content = "";
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState,R.layout.acty_course_description);
			setAppTitle("课程简介");
			initData();
			initLayout();
		}
		
		private void initData(){
			content = getIntent().getStringExtra("content");
			title = getIntent().getStringExtra("title");
		}
		
		private void initLayout(){
			tv_course_title.setText(title);
			tv_course_content.setMovementMethod(ScrollingMovementMethod.getInstance()); //滚动
			tv_course_content.setText(Html.fromHtml(content));
		}
	
}
