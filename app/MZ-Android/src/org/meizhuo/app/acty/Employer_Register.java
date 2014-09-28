package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import butterknife.OnClick;

import android.os.Bundle;

public class Employer_Register extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState, R.layout.frame_employer_register);
	}
	
	@OnClick(R.id.acty_emRegister_btn_cancle) public void back(){
		closeActivity();
	}

}
