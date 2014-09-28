package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.utils.EditTextUtils;
import org.meizhuo.utils.StringUtils;


import butterknife.InjectView;
import butterknife.OnClick;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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
	@InjectView(R.id.acty_register_et_username) EditText et_reg_username;
	@InjectView(R.id.acty_register_et_password) EditText et_reg_password;
	@InjectView(R.id.acty_register_et_name) EditText et_reg_name;
	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState, R.layout.acty_login);
		initFlipper();
	}
	/* 登录 */
	@OnClick(R.id.acty_login_btn_login) public void Login(){
		if (StringUtils.isEmpty(EditTextUtils.getText(et_login_username)) 
				|| StringUtils.isEmpty(EditTextUtils.getText(et_login_password))
				){
			toast("不能为空");
			return ;
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
		if (StringUtils.isEmpty(EditTextUtils.getText(et_reg_username)) 
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
			toast("请填写您的中文名");
		}
				
	}
	
	
	private void initFlipper() {
		flipper.setInAnimation(this, R.anim.push_up_in);
		flipper.setOutAnimation(this, R.anim.push_up_out);
	}
	
}
