package org.meizhuo.app.acty;

import java.util.List;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.meizhuo.api.EmployerAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.model.Employer;
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

public class UserCenter_Employer extends BaseActivity {
	private static final String TAG = "UserCenter_Employer";
	/**工作单位名称*/
	@InjectView(R.id.mz_usercenter_em_workplace_name) TextView workplace_name;
	/**联系电话**/
	@InjectView(R.id.mz_usercenter_em_contact_phone) TextView contact_phone;
	/**工作地址*/
	@InjectView(R.id.mz_usercenter_em_addr) TextView work_addr;
	/**编辑信息*/
	@InjectView(R.id.tv_userinfo_employer_edit) TextView employer_edit;
	
	EmployerAPI employerAPI ;
	private Employer employer;
	UCEHandler  handler =  new UCEHandler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState,R.layout.acty_usercenter_employer);
		setAppTitle("用户中心");
		employer =  new Employer();
		initData();
	}
	
		@OnClick(R.id.tv_userinfo_employer_edit) public void edit_info(){
			Intent intent  =  new Intent(this, UserCenter_Employer_EditInfo.class);
			intent.putExtra("work_addr", employer.getAddress());
			intent.putExtra("workplace_name", employer.getNickname());
			intent.putExtra("contact_phone", employer.getContact_phone());
			startActivity(intent);
		}
	
	private void initData() {
		if (employerAPI == null)
		employerAPI =  new EmployerAPI();
		handler.sendEmptyMessage(Constants.Start);
		final Message msg =  handler.obtainMessage();
		employerAPI.getEmployerInfo(new JsonResponseHandler() {
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				Log.v(TAG, obj.toString());
				try {
					JSONObject response = obj.getJSONObject("response");
					employer = Employer.create_by_json(response.toString());
					Log.v(TAG, employer.toString());
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
				toast("网络不给力,请检查你的网络设置!");
				handler.sendMessage(msg);
			}
		});
	}
	
	private void initLayout(){
		workplace_name.setText(employer.getNickname());
 		contact_phone .setText(employer.getContact_phone());
 		work_addr.setText(employer.getAddress());
	}
	
	class UCEHandler extends Handler {
		WaittingDialog waittingDialog;
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case Constants.Start:
				waittingDialog =  new WaittingDialog(UserCenter_Employer.this);
				waittingDialog.show();
				break;
			case Constants.Finish:
				if(waittingDialog.isShowing())
					waittingDialog.dismiss();
				initLayout();
				waittingDialog = null;
				break;
			}
		}
	}

}
