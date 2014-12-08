package org.meizhuo.app.acty;


import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.meizhuo.api.InstitutionAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.model.Institution;
import org.meizhuo.utils.Constants;
import org.meizhuo.view.WaittingDialog;

import butterknife.InjectView;
import butterknife.OnClick;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 机构信息详情
 * @author Jason
 *
 */
public class InstitutionInfoDetails extends BaseActivity {
	
	private static final String TAG = "InstitutionInfo_Details";

	@InjectView(R.id.institution_name) TextView institution_name;
//	@InjectView(R.id.company_name) TextView company_name;
	@InjectView(R.id.institution_teach_type) TextView institution_teach_type;
	@InjectView(R.id.institution_manager) TextView company_manager;
	@InjectView(R.id.institution_traning_type) TextView training_scope;
	@InjectView(R.id.company_teacher_resource) TextView teacher_resource;
	@InjectView(R.id.institution_traning_contach_phone) TextView contach_phone;
	@InjectView(R.id.institution_email) TextView institution_email;
	@InjectView(R.id.institution_traning_institution_addr) TextView company_addr;
	@InjectView(R.id.institution_traning_contact_people) TextView contact_people;
	
	
	
	Institution institution;
	String institution_id;
	IIDHandler handler = new IIDHandler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState, R.layout.acty_institutioninfo_details);
		setAppTitle("机构详情");
		initData();
		initLayout();
	}
	
	private void initData(){
		Intent intent =  getIntent();
		institution_id = intent.getStringExtra("uid");
	}
	
	private void initLayout(){
		handler.sendEmptyMessage(Constants.Start);
		final Message msg = handler.obtainMessage();
		InstitutionAPI.getOneInstitution(institution_id, new JsonResponseHandler() {
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				Log.i(TAG, "" + obj);
				
				if (institution == null)
					institution = new Institution();
				try {
					institution = Institution.create_by_json(obj.getString("response"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				msg.what = Constants.Finish;
				handler.sendMessage(msg);
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				toast("出错了，请检查你的网络设置!");
				closeActivity();
			}
		});
		
	}
	@OnClick(R.id.institution_traning_contach_phone_dial) public void dial(){
		if(institution.getContact_phone()==null || institution.getContact_phone().equals("")){
			toast("暂无电话号码，无法拨打");
			return ;
		}
		Intent intent =  new Intent();
		intent.setAction("android.intent.action.DIAL");
		intent.setData(Uri.parse("tel:" + institution.getContact_phone().toString()));
		startActivity(intent);
	}
	
	// 进入公司简介页面
	@OnClick(R.id.btn_company_description) public void enter_company_intro(){
		Intent it =  new Intent(this, InstitutionInfoDetailsComIntro.class);
//		it.putExtra("content", institution.getDescription().toString());
//		it.putExtra("title", institution.getNickname().toString());
		it.putExtra("ins_id", institution_id);
		startActivity(it);
		
	}
	//进入课程详情页面
	@OnClick(R.id.institution_course_detail_btn) public void enter_course(){
		Intent intent =  new Intent(this, CourseDetails.class);
		intent.putExtra("institution_id", institution_id);
		startActivity(intent);
	}
	
	class IIDHandler extends Handler{
		WaittingDialog dialog;
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case Constants.Start:
				dialog = new WaittingDialog(InstitutionInfoDetails.this);
				dialog.show();
				break;
	

			case Constants.Finish:
				if (dialog.isShowing())
					dialog.dismiss();
				dialog = null;
				if (institution.getNickname() == null)
				{
					institution_name.setText("");
				}else
				{
					institution_name.setText(institution.getNickname().toString());
				}
				if (institution.getType() == null)
				{
					institution_teach_type.setText("");
				}
				else{
					institution_teach_type.setText(institution.getType().toString());
				}
				if (institution.getManager() == null)
				{
					company_manager.setText("");
				}else{
					company_manager.setText(institution.getManager().toString());
				}
				if (institution.getTraining_scope() == null)
				{
					training_scope.setText("");
				}else{
					training_scope.setText(institution.getTraining_scope().toString());
				}
				if (institution.getTeacher_resource() == null)
				{
					teacher_resource.setText("");
				}else{
					teacher_resource.setText(institution.getTeacher_resource().toString());
				}
				if(institution.getContact_phone() == null)
				{
				}else
				{
					contach_phone.setText(institution.getContact_phone().toString());
				}
				if(institution.getContact_email() == null)
				{
					institution_email.setText("");
				}else{
					institution_email.setText(institution.getContact_email().toString());
				}
				if (institution.getAddress() == null)
				{
					company_addr.setText("");
				}else{
					company_addr.setText(institution.getAddress().toString());
				}
				if (institution.getContact_member() == null)
				{
					contact_people.setText("");
				}else {
					contact_people.setText(institution.getContact_member());
				}
				
			default:
				break;
			}
		}
		
		
	}
	
	
	
}
