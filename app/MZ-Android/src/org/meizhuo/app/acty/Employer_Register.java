package org.meizhuo.app.acty;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.meizhuo.api.EmployerAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.utils.AndroidUtils;
import org.meizhuo.utils.EditTextUtils;
import org.meizhuo.utils.StringUtils;
import org.meizhuo.view.WaittingDialog;

import butterknife.InjectView;
import butterknife.OnClick;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class Employer_Register extends BaseActivity{
	private static final String TAG = "Employer_Register";
	
	@InjectView(R.id.acty_emRegister_et_email) EditText et_email;
	@InjectView(R.id.acty_emRegister_et_password) EditText et_password;
	@InjectView(R.id.acty_emRegister_et_comfirmpassword) EditText et_cofirmpassword;
	@InjectView(R.id.acty_emRegister_contact_phone) EditText et_contact_phone;
	@InjectView(R.id.acty_emRegister_et_phone) EditText et_phone;
	@InjectView(R.id.acty_emRegister_et_name) EditText et_name;
	@InjectView(R.id.acty_emRegister_et_addr) EditText et_addr;
	
	EmployerAPI  employerAPI;
	WaittingDialog dialog;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState, R.layout.frame_employer_register);
		employerAPI =  new EmployerAPI();
		dialog = new WaittingDialog(this);
	}
	
	/**用人单位注册*/
	@OnClick(R.id.acty_emRegister_btn_regist) public void Register(){
		if(!AndroidUtils.isNetworkConnected(Employer_Register.this))
		{
			toast("请先打开您的网络开关");
			return ;
		}
		if (StringUtils.isEmpty(EditTextUtils.getText(et_email)))
		{
				toast("邮箱不能为空");
				return ;
		}
		if (StringUtils.isEmpty(EditTextUtils.getText(et_password)))
		{
						toast("密码不能为空");
						return ;
		}
		if (StringUtils.isEmpty(EditTextUtils.getText(et_name)))
		{
						toast("单位名称不能为空");
						return ;
		}
		
		if (!EditTextUtils.getText(et_password).equals(EditTextUtils.getTextTrim(et_cofirmpassword))){
			toast("输入密码不一致");
			return ;
		}
		if (!StringUtils.isPassword(EditTextUtils.getText(et_password))){
			toast("请输入8-16位数字或者字母");
			return ;
		}
		if (!StringUtils.isEmail(EditTextUtils.getText(et_email))){
			toast("请输入正确的邮箱格式");
			return ;
		}
		if (!StringUtils.isNickname(EditTextUtils.getText(et_name))){
			toast("请输入公司名称");
			return ;
		}
		//这里昵称其实就是用人公司的名称
		String nickname = EditTextUtils.getText(et_name);
		String phone  = EditTextUtils.getText(et_phone);
		String email = EditTextUtils.getText(et_email);
		String psw = EditTextUtils.getText(et_password);
		String contact_phone = EditTextUtils.getText(et_contact_phone);
		String address = EditTextUtils.getText(et_addr);
		if(employerAPI == null){
			employerAPI =  new EmployerAPI();
		}
		employerAPI.register(nickname, email, psw, phone, contact_phone, address, new JsonResponseHandler() {
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				if (dialog == null)
					dialog = new WaittingDialog(Employer_Register.this);
				dialog.setText("正在注册");
				dialog.show();
			}
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				try {
					if(obj.getString("code").equals("20000"))
					{
						toast("注册成功!");
						Employer_Register.this.finish();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				toast("注册失败");
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				if (dialog.isShowing())
					dialog.dismiss();
				dialog = null;
			}
		});
		
	}
	
	@OnClick(R.id.acty_emRegister_btn_cancle) public void back(){
		closeActivity();
	}

}
