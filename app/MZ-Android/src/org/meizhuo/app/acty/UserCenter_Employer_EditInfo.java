package org.meizhuo.app.acty;

import org.apache.http.Header;
import org.json.JSONObject;
import org.meizhuo.api.EmployerAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.model.Employer;
import org.meizhuo.utils.Constants;
import org.meizhuo.utils.StringUtils;
import org.meizhuo.view.WaittingDialog;

import butterknife.InjectView;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class UserCenter_Employer_EditInfo extends BaseActivity{
	
	@InjectView(R.id.tv_userinfo_emEdit_save) TextView edit_info;
	@InjectView(R.id.usercenter_em_editInfo_workplacename) EditText edit_workplace;
	@InjectView(R.id.usercenter_editInfo_em_contact_phone) EditText edid_contact_phone;
	@InjectView(R.id.usercenter_editInfo_em_workplace_addr) EditText edit_workplace_addr;
	Employer employer;
	EmployerAPI employerAPI;
	UCEEHandler handler =  new UCEEHandler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState, R.layout.acty_usercenter_employer_edit);
		setAppTitle("编辑用户信息");
		initLayout();
	}
	
	@OnClick(R.id.tv_userinfo_emEdit_save) public void saveinfo(){
		String workplace_name = "" ;
		if(!StringUtils.isNickname(edit_workplace.getText().toString()))
		{
			toast("请输入正确的公司名称");
			return ;
		}else{
			workplace_name = edit_workplace.getText().toString();
		}
		String work_addr = "";
		if(!StringUtils.isNickname(edit_workplace_addr.getText().toString()))
		{
			toast("请输入正确的公司名称");
			return ;
		}else{
			work_addr = edit_workplace_addr.getText().toString();
		}
		String contact_phone = "";
		if(!StringUtils.isPhone(edid_contact_phone.getText().toString())){
			toast("请输入正确的手机号码");
			return ;
		}else{
			 contact_phone = edid_contact_phone.getText().toString();
		}
	
		if (employerAPI == null)
			employerAPI = new EmployerAPI();
		handler.sendEmptyMessage(Constants.Start);
		final Message msg = handler.obtainMessage();
		employerAPI.updateEmployerInfo(workplace_name, contact_phone, work_addr, new JsonResponseHandler() {
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				toast("保存成功!");
				msg.what = Constants.Finish;
				handler.sendMessage(msg);
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	private void initLayout(){
		String workplace_name = getIntent().getStringExtra("workplace_name");
		String contact_phone = getIntent().getStringExtra("contact_phone");
		String work_addr = getIntent().getStringExtra("work_addr");
		edit_workplace.setText(workplace_name);
		edid_contact_phone.setText(contact_phone);
		edit_workplace_addr.setText(work_addr);
	}
	
	class UCEEHandler extends Handler{
		WaittingDialog dialog; 
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case Constants.Start:
				dialog =  new WaittingDialog(UserCenter_Employer_EditInfo.this);
				dialog.show();
				break;
			case  Constants.Finish:
				if(dialog.isShowing()){
					dialog.dismiss();
				}
			}
		}
	}
}
