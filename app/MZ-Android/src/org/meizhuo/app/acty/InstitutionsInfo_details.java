package org.meizhuo.app.acty;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONObject;
import org.meizhuo.api.CourseAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.model.Course;
import org.meizhuo.utils.Constants;
import org.meizhuo.view.WaittingDialog;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.TextView;
import butterknife.InjectView;

/**
 * 某机构的信息
 * 
 * @author Jayin
 * 
 */
public class InstitutionsInfo_details extends BaseActivity  {

	
	@InjectView(R.id.institution_course_title) TextView title; 
	@InjectView(R.id.institution_course_start_time) TextView time;
	@InjectView(R.id.institution_course_teacher) TextView teacher;
	@InjectView(R.id.institution_course_introdution) TextView introdution;
	List<Course>data;
	int id ;
	
	boolean hasMore = true, isloading = false;
	
	IIHandler handler = new IIHandler();

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState, R.layout.acty_institutioninfo_details);
		setAppTitle("课程信息");
		initData();
		initLayout();
	}
	
	private void initData(){
		Intent intent =  getIntent();
		 id = intent.getIntExtra("id", 1);
		data = new ArrayList<Course>();
	}
	
	private void initLayout(){
		handler.sendEmptyMessage(Constants.Start);
		final Message msg = handler.obtainMessage();
		CourseAPI.getCourseListInfo(new JsonResponseHandler() {
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				data = Course.create_by_jsonarray(obj.toString());
				msg.what = Constants.Finish;
				handler.sendMessage(msg);
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	class IIHandler extends Handler{
		WaittingDialog dialog;
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case Constants.Start:
				dialog = new WaittingDialog(InstitutionsInfo_details.this);
				dialog.show();
				break;
				/**
				  @InjectView(R.id.institution_course_title) TextView title; 
	@InjectView(R.id.institution_course_start_time) TextView time;
	@InjectView(R.id.institution_course_teacher) TextView teacher;
	@InjectView(R.id.institution_course_introdution) TextView introdution;
				 */
			case Constants.Finish:
				if (dialog.isShowing())
					dialog.dismiss();
				dialog = null;
				title.setText(data.get(id).getName().toString());
				time.setText(data.get(id).getStart_time().toString());
				teacher.setText(data.get(id).getTeacher().toString());
				introdution.setText(data.get(id).getIntroduction().toString());
			default:
				break;
			}
		}
		
		
	}


}
