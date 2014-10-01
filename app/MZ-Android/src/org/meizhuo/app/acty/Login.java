package org.meizhuo.app.acty;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.meizhuo.api.PublicerAPI;
import org.meizhuo.app.AppInfo;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.model.ErrorCode;
import org.meizhuo.model.Publicer;
import org.meizhuo.utils.EditTextUtils;
import org.meizhuo.utils.JsonUtils;
import org.meizhuo.utils.StringUtils;


import butterknife.InjectView;
import butterknife.OnClick;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ViewFlipper;

/**
 * 登录注册页面
 * @author Jason
 *
 */
public class Login extends BaseActivity{
	
	/*
	 * 
		btn_login = _getView(R.id.acty_login_btn_login);
		btn_regitst = _getView(R.id.acty_login_btn_regist);
		btn_ok = _getView(R.id.acty_register_btn_regist);
		btn_cancle = _getView(R.id.acty_register_btn_cancle);
	 */
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
	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState, R.layout.acty_login);
		initFlipper();
		publicApi =  new PublicerAPI();
	}
	/* 登录 */
	@OnClick(R.id.acty_login_btn_login) public void Login(){
		if (StringUtils.isEmpty(EditTextUtils.getText(et_login_username)) 
				|| StringUtils.isEmpty(EditTextUtils.getText(et_login_password))
				){
			toast("不能为空");
			return ;
		}
		publicApi.Login(EditTextUtils.getText(et_login_password), 
				EditTextUtils.getText(et_login_password), 
				new JsonResponseHandler() {
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
					}
					
					@Override
					public void onOK(Header[] headers, JSONObject obj) {
						// TODO Auto-generated method stub
						toast("登录成功");
						saveLoginInfo(obj.toString());
					}
					
					@Override
					public void onFaild(int errorType, int errorCode) {
						// TODO Auto-generated method stub
						toast(ErrorCode.errorList.get(errorCode));
					}
				});
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
		if (StringUtils.isEmpty(EditTextUtils.getText(et_reg_phone)) 
				|| StringUtils.isEmpty(EditTextUtils.getText(et_reg_password))
				|| StringUtils.isEmpty(EditTextUtils.getText(et_reg_confirm))
				|| StringUtils.isEmpty(EditTextUtils.getText(et_reg_name))
				){
			toast("输入不能为空!");
			return ;
		}
		if (!EditTextUtils.getText(et_reg_password).equals(EditTextUtils.getTextTrim(et_reg_confirm))){
			toast("输入密码不一致");
			return ;
		}
		if (!StringUtils.isNickname(EditTextUtils.getText(et_reg_name))){
			toast("请填写您的中文名字");
		}
		if (!StringUtils.isPhoneName(EditTextUtils.getText(et_reg_phone))){
			toast("请填写11位手机号码");
		}
	
		//这里是手机号码
		String publicerPhone = EditTextUtils.getText(et_reg_phone);
		String email = EditTextUtils.getText(et_reg_email);
		String password = EditTextUtils.getText(et_reg_password);
		//真名
		String nickname = EditTextUtils.getText(et_reg_name);
		String sex = null;
		boolean maleIsChecked = maleRadio.isChecked();//男性被点击
		if(maleIsChecked) sex = "男";
		boolean femaleIsChecked = femaleRadio.isChecked(); //女性被点击
		if (femaleIsChecked) sex = "女";
		String work_place = EditTextUtils.getText(et_reg_workplace);//工作地点
		publicApi.regist(nickname, publicerPhone, email, password, sex, work_place, new JsonResponseHandler() {
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				toast("注册成功!");
				flipper.showPrevious();
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				toast("注册失败" + ErrorCode.errorList.get(errorCode));
			}
		});
				
	}
	
	/**
	 * 保存登录信息 
	 * @param json
	 */
	private void saveLoginInfo(String json) {
		int id = JsonUtils.getInt(json, "id");
		PublicerAPI api =  new PublicerAPI();
		api.getProfile(new JsonResponseHandler() {
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				try {
					Publicer  publicer = Publicer.create_by_json(obj.getJSONObject("publicer").toString());
					AppInfo.setUser(getContext(), publicer);
					//保存账号密码
					AppInfo.setPublicername(getContext(), et_login_username.getText().toString());
					AppInfo.setPublicerPSW(getContext(), et_login_password.getText().toString());
					openActivity(Main.class);
					closeActivity();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					toast("网络异常，解析错误");
				}
			
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				toast(ErrorCode.errorList.get(errorCode));
			}
		});
	}
	
	
	private void initFlipper() {
		flipper.setInAnimation(this, R.anim.push_up_in);
		flipper.setOutAnimation(this, R.anim.push_up_out);
	}
	
}
