package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnItemClick;


/**
 * 课程里面的一个详细情况
 * @author Jason
 *
 */
public class CourseDetails_OneCourse extends  BaseActivity {
	
	/**课程名称*/
	@InjectView(R.id.institution_course_name) TextView course_name;
	/**开课时间*/
	@InjectView(R.id.institution_course_time) TextView course_time;
	/**开课地址*/
	@InjectView(R.id.institution_course_addr) TextView course_addr;
	/**开课教师*/
	@InjectView(R.id.institution_course_teacher) TextView course_teacher;
	/**课程费用*/
	@InjectView(R.id.institution_course_cost) TextView course_cost;
	/**课程介绍*/
	@InjectView(R.id.institution_course_introdution) TextView course_introdution;

	String name,start_time,address,teacher,introduction,cost;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState , R.layout.acty_institution_course_item);
		setAppTitle("课程信息");
		initData();
		initLayout();
	}
	
	private void initData(){
		Intent intent = getIntent();
		 name = intent.getStringExtra("name");
		 start_time = intent.getStringExtra("start_time");
		 address = intent.getStringExtra("address");
		 teacher = intent.getStringExtra("teacher");
		 introduction = intent.getStringExtra("introduction");
		 cost = intent.getStringExtra("cost");
	}
	
	private void initLayout(){
		 course_name.setText(name);
		 course_time.setText(start_time);
		 course_addr.setText(address);
		 course_teacher.setText(teacher);
		 course_cost.setText(cost);
		 course_introdution.setText(introduction);
	}



}
