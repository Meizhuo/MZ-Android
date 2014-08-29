package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
	@InjectView(R.id.tv_title)
	TextView tv_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_setting);
		ButterKnife.inject(this);
		tv_title.setText("设置");
		
	}
	@OnClick(R.id.about)
	public void ToAbout(){
		openActivity(SettingAbout.class);
	}
	
	@OnClick(R.id.setting_feedback)
	public void ToFeedback()
	{
		openActivity(Feedback.class);
	}
	
	@OnClick(R.id.btn_title_back)
	public void back() {
		closeActivity();
	}

}
