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
public class CourseDetailsOneCourse extends  BaseActivity {
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
	@InjectView(R.id.institution_course_introdution) Button course_introdution;
	/**所属机构*/
	@InjectView(R.id.institution_course_belong_institution) Button belong_institution;
	/**证书类别**/
	@InjectView(R.id.institution_course_certificate_type) TextView course_certificate_type;
	/**项目类别**/
	@InjectView(R.id.institution_course_kind) TextView course_kind;
	/**等级**/
	@InjectView(R.id.institution_course_level) TextView course_level;
	/**系列*/
	@InjectView(R.id.institution_course_series) TextView course_series;
	/**资格证书*/
	@InjectView(R.id.institution_course_title) TextView course_title;
	/**补贴金额*/
	@InjectView(R.id.institution_course_money) TextView course_money;
	

	String name,start_time,address,teacher,introduction,cost ,institution_id,course_id,certificate_type,kind,level,money,series,title;
	WaittingDialog dialog;
	Institution institution;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState , R.layout.acty_institution_course_item);
		setAppTitle("课程信息");
		dialog  = new WaittingDialog(CourseDetailsOneCourse.this);
		institution =  new Institution();
		initData();
		initLayout();
	}
	
	private void initData(){
		/*
		 	intent.putExtra("certificate_type", certificate_type);
		intent.putExtra("kind", kind);
		intent.putExtra("level", level);
		intent.putExtra("money", money);
		intent.putExtra("series", series);
		intent.putExtra("title", title);
		 */
		Intent intent = getIntent();
		 name = intent.getStringExtra("name");
		 start_time = intent.getStringExtra("start_time");
		 address = intent.getStringExtra("address");
		 teacher = intent.getStringExtra("teacher");
		 introduction = intent.getStringExtra("introduction");
		 institution_id = intent.getStringExtra("institution_id");
		 cost = intent.getStringExtra("cost");
		 course_id = intent.getStringExtra("course_id");
		certificate_type = intent.getStringExtra("certificate_type");
		kind = intent.getStringExtra("kind");
		level = intent.getStringExtra("level");
		money = intent.getStringExtra("money");
		series = intent.getStringExtra("series");
		title = intent.getStringExtra("title");
		
	}
	
	/*
	 certificate_type = intent.getStringExtra("certificate_type");
		kind = intent.getStringExtra("kind");
		level = intent.getStringExtra("level");
		money = intent.getStringExtra("money");
		series = intent.getStringExtra("series");
		title = intent.getStringExtra("title");
	 */
	private void initLayout(){
		 course_name.setText(name);
		 course_time.setText(start_time);
		 course_addr.setText(address);
		 course_teacher.setText(teacher);
		 course_cost.setText(cost);
		 course_certificate_type.setText(certificate_type);
		 course_kind.setText(kind);
		 course_level.setText(level);
		 course_series.setText(series);
		 course_title.setText(title);
		 course_money.setText(money);
		 
		 
		 InstitutionAPI.getOneInstitution(institution_id, new JsonResponseHandler() {
			 
			 @Override
			public void onStart() {
				// TODO Auto-generated method stub
				 if (dialog == null)
					 dialog = new WaittingDialog(CourseDetailsOneCourse.this);
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
				CourseDetailsOneCourse.this.finish();
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				if (dialog.isShowing())
					dialog.dismiss();
			}
		});
	}
	
	@OnClick(R.id.institution_course_introdution) public void ToCourseDescription(){
		Intent intent =  new Intent(CourseDetailsOneCourse.this, CourseDetailsDescription.class);
		intent.putExtra("content", introduction);
		intent.putExtra("title", name);
		intent.putExtra("course_id", course_id);
		startActivity(intent);
		
	}
	
  @OnClick(R.id.institution_course_belong_institution) public void toTheInstitution(){
	  if (!AndroidUtils.isNetworkConnected(CourseDetailsOneCourse.this))
	  {
		  toast("请连接网络");
		  return ;
	  }
	  Intent it = new Intent(this, InstitutionInfoDetails.class);
	  it.putExtra("uid", institution_id);
	  startActivity(it);
  }


}
