package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import android.widget.RelativeLayout;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 设置
 * 
 * @author Lenovo
 * 
 */
public class Setting extends BaseActivity {

	@InjectView(R.id.setting_notification) RelativeLayout newssettings;
	@InjectView(R.id.setting_feedback) RelativeLayout feedback;
	@InjectView(R.id.about) RelativeLayout about;

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState, R.layout.acty_setting);
		setAppTitle("设置");

	}

	@OnClick(R.id.about) public void ToAbout() {
		openActivity(SettingAbout.class);
	}

	@OnClick(R.id.setting_feedback) public void ToFeedback() {
		openActivity(Feedback.class);
	}

}
