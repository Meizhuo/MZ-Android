package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
/**
 * 设置
 * @author Lenovo
 *
 */
public class Setting extends BaseActivity {
	
	@InjectView(R.id.setting_notification) RelativeLayout newssettings;
	@InjectView(R.id.setting_feedback) RelativeLayout feedback;
	@InjectView(R.id.about) RelativeLayout about;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_setting);
		ButterKnife.inject(this);
	}
	@OnClick(R.id.about)
	public void ToAbout(){
		openActivity(SettingAbout.class);
	}
}
