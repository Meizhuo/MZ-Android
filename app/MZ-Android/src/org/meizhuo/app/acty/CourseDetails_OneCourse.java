package org.meizhuo.app.acty;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.meizhuo.api.InstitutionAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.model.Institution;
import org.meizhuo.utils.AndroidUtils;
import org.meizhuo.view.WaittingDialog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;


/**
 * 课程里面的一个详细情况
 * @author Jason
 *
 */
public class CourseDetails_OneCourse extends  BaseActivity {
	private static final String TAG = "CourseDetails_OneCourse";
	
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
	/**所属机构*/
	@InjectView(R.id.institution_course_belong_institution) Button belong_institution;

	String name,start_time,address,teacher,introduction,cost ,institution_id;
	WaittingDialog dialog;
	Institution institution;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState , R.layout.acty_institution_course_item);
		setAppTitle("课程信息");
		dialog  = new WaittingDialog(CourseDetails_OneCourse.this);
		institution =  new Institution();
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
		 institution_id = intent.getStringExtra("institution_id");
		 cost = intent.getStringExtra("cost");
		 
	}
	
	private void initLayout(){
		 course_name.setText(name);
		 course_time.setText(start_time);
		 course_addr.setText(address);
		 course_teacher.setText(teacher);
		 course_cost.setText(cost);
		 course_introdution.setText(introduction);
		 InstitutionAPI.getOneInstitution(institution_id, new JsonResponseHandler() {
			 
			 @Override
			public void onStart() {
				// TODO Auto-generated method stub
				 if (dialog == null)
					 dialog = new WaittingDialog(CourseDetails_OneCourse.this);
				 dialog.show();
			}
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				try {
					institution = Institution.create_by_json(obj.getString("response"));
					belong_institution.setText(institution.getNickname());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				toast("网络不给力,请检查您的网络设置!");
				CourseDetails_OneCourse.this.finish();
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				if (dialog.isShowing())
					dialog.dismiss();
			}
		});
	}
	
  @OnClick(R.id.institution_course_belong_institution) public void toTheInstitution(){
	  if (!AndroidUtils.isNetworkConnected(CourseDetails_OneCourse.this))
	  {
		  toast("请连接网络");
		  return ;
	  }
	  Intent it = new Intent(this, InstitutionInfo_Details.class);
	  it.putExtra("uid", institution_id);
	  startActivity(it);
  }


}
