package org.meizhuo.app.acty;

import java.util.ArrayList;
import java.util.List;

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
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

/**
 * 机构信息详情
 * @author Jason
 *
 */
public class InstitutionInfo_Details extends BaseActivity {
	
	private static final String TAG = "InstitutionInfo_Details";

	@InjectView(R.id.institution_name) TextView institution_name;
	@InjectView(R.id.company_name) TextView company_name;
	@InjectView(R.id.institution_teach_type) TextView institution_teach_type;
	@InjectView(R.id.company_description) TextView company_description;
	@InjectView(R.id.institution_manager) TextView company_manager;
	@InjectView(R.id.institution_traning_type) TextView training_scope;
	@InjectView(R.id.company_teacher_resource) TextView teacher_resource;
	
	
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
			}
		});
		
	}
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
				dialog = new WaittingDialog(InstitutionInfo_Details.this);
				dialog.show();
				break;
	

			case Constants.Finish:
				if (dialog.isShowing())
					dialog.dismiss();
				dialog = null;
				institution_name.setText(institution.getNickname().toString());
				company_name.setText(institution.getName().toString());
				institution_teach_type.setText(institution.getType().toString());
				company_description.setText(institution.getDescription().toString());
				company_manager.setText(institution.getManager().toString());
				training_scope.setText(institution.getTraining_scope().toString());
				teacher_resource.setText(institution.getTeacher_resource().toString());
				
			default:
				break;
			}
		}
		
		
	}
	
	
	
}
