package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class Main extends BaseActivity {
	
	@InjectView(R.id.ic_title_back) ImageView iv_title_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_main);
		ButterKnife.inject(this);
		iv_title_back.setVisibility(View.INVISIBLE);
	}
	
	@OnClick(R.id.btn_unitinfo)
	public void unitinfo(){
		openActivity(UnitInfo.class);
	}
	
	@OnClick(R.id.btn_institutions)
	public void Institutions(){
		openActivity(Institutions.class);
	}
	
	@OnClick(R.id.btn_institution_consult)
	public void institution_consult(){
		openActivity(InstitutionConsult.class);
	}
	
	@OnClick(R.id.btn_public_consult)
	public void public_consult(){
		openActivity(PublicConsult.class);
	}
	
	
	@OnClick(R.id.btn_usercenter)
	public void usercenter(){
		openActivity(UserCenter.class);
	}
	
	@OnClick(R.id.btn_setting)
	public void setting(){
		openActivity(Setting.class);
	}
	
	

}
