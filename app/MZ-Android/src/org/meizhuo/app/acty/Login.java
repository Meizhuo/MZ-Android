package org.meizhuo.app.acty;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.meizhuo.api.EmployerAPI;
import org.meizhuo.api.PublicerAPI;
import org.meizhuo.app.AppInfo;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.model.Employer;
import org.meizhuo.model.ErrorCode;
import org.meizhuo.model.Publicer;
import org.meizhuo.utils.AndroidUtils;
import org.meizhuo.utils.Constants;
import org.meizhuo.utils.EditTextUtils;
import org.meizhuo.utils.JsonUtils;
import org.meizhuo.utils.StringUtils;
import org.meizhuo.view.WaittingDialog;


import butterknife.InjectView;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ViewFlipper;

/**
 * 登录注册页面
 * @author Jason
 *
 */
public class Login extends BaseActivity{
	private static final String TAG = "Login";
	
	
	@InjectView(R.id.acty_login_flipper) ViewFlipper flipper;
	@InjectView(R.id.acty_login_et_username) EditText et_login_username;
	@InjectView(R.id.acty_login_et_password) EditText et_login_password;
	@InjectView(R.id.acty_register_et_comfirmpassword) EditText et_reg_confirm;
	@InjectView(R.id.acty_register_et_phone) EditText et_reg_phone;
	@InjectView(R.id.acty_register_et_password) EditText et_reg_password;
	@InjectView(R.id.acty_register_et_name) EditText et_reg_name;
	@InjectView(R.id.radioMale) RadioButton maleRadio;
	@InjectView(R.id.radioFemale) RadioButton femaleRadio;
	@InjectView(R.id.acty_register_et_email) EditText et_reg_email;
	@InjectView(R.id.acty_register_et_workplace) EditText et_reg_workplace;
	private PublicerAPI publicApi;
	private EmployerAPI employerAPI;
	Employer employer;
	Publicer publicer;
	WaittingDialog dialog;
	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState, R.layout.acty_login);
		initFlipper();
		publicApi =  new PublicerAPI();
		dialog = new WaittingDialog(this);
	}
	/* 登录 */
	@OnClick(R.id.acty_login_btn_login) public void Login(){
		if (!(AndroidUtils.isNetworkConnected(this)))
		{
			toast("请打开您的网络开关");
			return ;
		}
		if (StringUtils.isEmpty(EditTextUtils.getText(et_login_username)) 
				|| StringUtils.isEmpty(EditTextUtils.getText(et_login_password))
				){
			toast("不能为空");
			return ;
		}
		//普通用户登录，手机判断
		if(StringUtils.isPhone(EditTextUtils.getText(et_login_username)))
		{
		publicApi.Login(EditTextUtils.getText(et_login_username), 
				EditTextUtils.getText(et_login_password), 
				new JsonResponseHandler() {
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						if (dialog == null)
							dialog = new WaittingDialog(Login.this);
						dialog.setText("正在登录");
						dialog.show();
					}
					
					@Override
					public void onOK(Header[] headers, JSONObject obj) {
						// TODO Auto-generated method stub
						try {
							if(obj.getString("code").equals("20000")){
								
							toast("登录成功");
							Log.i(TAG, "登录成功" + obj);
							Intent intent = new Intent(Constants.Action_Publicer_isLogin);
							Login.this.sendBroadcast(intent);
							savePublicerLoginInfo();
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						try {
							if(obj.getString("error_code").equals("40000")){
								toast("密码错误");
								return ;
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
					@Override
					public void onFaild(int errorType, int errorCode) {
						// TODO Auto-generated method stub
						toast("网络不给力，请检查你的网络设置!");
						if(dialog.isShowing())
							dialog.dismiss();
						Login.this.finish();
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
		//用人单位登录，邮箱判断
		if(StringUtils.isEmail(EditTextUtils.getText(et_login_username))){
			if(employerAPI == null)
				employerAPI =  new EmployerAPI();
			employerAPI.Login(EditTextUtils.getText(et_login_username),
					EditTextUtils.getText(et_login_password), 
					new JsonResponseHandler() {
				@Override
				public void onStart() {
					// TODO Auto-generated method stub
					if (dialog == null)
						dialog = new WaittingDialog(Login.this);
					dialog.setText("正在登录");
					dialog.show();
				}
						
				@Override
				public void onOK(Header[] headers, JSONObject obj) {
							// TODO Auto-generated method stub
							try {
								if (obj.getString("code").equals("20000"))
								{
									toast("登录成功");
									Intent intent =  new Intent(Constants.Action_Employer_isLogin);
									Login.this.sendBroadcast(intent);
									saveEmployerLoginInfo();
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								toast("网络不给力，请检查你的网络设置");
								closeActivity();
							}
							try {
								if(obj.getString("error_code").equals("40000")){
									toast("密码错误");
									return ;
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						@Override
						public void onFaild(int errorType, int errorCode) {
							// TODO Auto-generated method stub
							if (dialog.isShowing())
								dialog.dismiss();
							dialog = null;
							toast("登录失败,请检查你的网络设置" );
							closeActivity();
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
	}
	
	/*点击进去普通注册*/
	@OnClick(R.id.acty_login_btn_publicRegist) public void Login_publicRegister(){
		flipper.showNext();
	}
	/*普通注册页面返回上层 */
	@OnClick(R.id.acty_register_btn_cancle) public void Filp_Before() {
		flipper.showPrevious();
	}
	/*用人单位注册 还是使用Activity跳转*/
	@OnClick(R.id.acty_login_btn_employerRegist) public void Login_employerRegister(){
		openActivity(Employer_Register.class);
	}
	
	
	 /*普通注册页面 判断*/
	@OnClick(R.id.acty_register_btn_regist) public void Register(){
		if (!(AndroidUtils.isNetworkConnected(this)))
		{
			toast("请先打开您的网络开关");
			return ;
		}
		if (StringUtils.isEmpty(EditTextUtils.getText(et_reg_phone)))
		{
			toast("手机号码不能为空!");
			return ;
		}
		if (StringUtils.isEmpty(EditTextUtils.getText(et_reg_password)))
		{
			toast("密码不能为空!");
			return ;
		}
		if (StringUtils.isEmpty(EditTextUtils.getText(et_reg_name)))
		{
			toast("名字不能为空!");
			return ;
		}
		if (!EditTextUtils.getText(et_reg_password).equals(EditTextUtils.getTextTrim(et_reg_confirm))){
			toast("两次输入密码不一致");
			return ;
		}
		if (!StringUtils.isNickname(EditTextUtils.getText(et_reg_name))){
			toast("名字请填写您的中文名字");
			return ;
		}
		if (!StringUtils.isPhone(EditTextUtils.getText(et_reg_phone))){
			toast("请填写正确11位手机号码格式");
			return ;
		}
		if (!StringUtils.isPassword(EditTextUtils.getText(et_reg_password))){
			toast("密码请填写8-16位数字或字母");
			return ;
		}
		
		String email = EditTextUtils.getText(et_reg_email).trim();
		if (!(email.equals("")))
		{
			if (!StringUtils.isEmail(EditTextUtils.getText(et_reg_email).trim())){
				toast("请输入正确的邮箱格式!");
				return ;
			}
		}
		//这里是手机号码
		String publicerPhone = EditTextUtils.getText(et_reg_phone);
		
		String password = EditTextUtils.getText(et_reg_password);
		//真名
		String nickname = EditTextUtils.getText(et_reg_name);
		String sex = "";
		boolean maleIsChecked = maleRadio.isChecked();//男性被点击
		if(maleIsChecked) sex = "男";
		boolean femaleIsChecked = femaleRadio.isChecked(); //女性被点击
		if (femaleIsChecked) sex = "女";
		String work_place = EditTextUtils.getText(et_reg_workplace);//工作地点
		publicApi.regist(nickname, publicerPhone, email, password, sex, work_place, new JsonResponseHandler() {
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				if (dialog == null)
					dialog = new WaittingDialog(Login.this);
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
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				flipper.showPrevious();
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				if (dialog.isShowing())
					dialog.dismiss();
				dialog = null;
				toast("网络不给力,注册失败! 请检查您的网络设置" );
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
	
	/**
	 * 保存普通用户登录信息 
	 */
	private void savePublicerLoginInfo() {
		Publicer publicer =  new Publicer();
		AppInfo.setPublicer(getContext(), publicer);
		//保存用户账号密码
		AppInfo.setPublicername(getContext(), et_login_username.getText().toString());
		AppInfo.setPublicerPSW(getContext(), et_login_password.getText().toString());
		Log.i(TAG, "登录保存信息" + AppInfo.getPublicername(getContext()));
		closeActivity();
	}
	/**
	 * 保存用人单位信息
	 */
	private void saveEmployerLoginInfo(){
		Employer employer = new Employer();
		AppInfo.setEmployer(getContext(), employer);
		//保存用户账号密码
		AppInfo.setEmployername(getContext(), et_login_username.getText().toString());
		AppInfo.setEmployerPSW(getContext(), et_login_password.getText().toString());
		Log.i(TAG, "保存用人单位" + AppInfo.getEmployername(getContext()));
		closeActivity();
	}
	
	
	private void initFlipper() {
		flipper.setInAnimation(this, R.anim.push_up_in);
		flipper.setOutAnimation(this, R.anim.push_up_out);
	}
	
}
