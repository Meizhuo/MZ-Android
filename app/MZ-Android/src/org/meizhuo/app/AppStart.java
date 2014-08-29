package org.meizhuo.app;

import org.meizhuo.app.acty.Feedback;
import org.meizhuo.app.acty.Main;
import org.meizhuo.app.acty.Setting;
import org.meizhuo.app.acty.SettingAbout;

import android.os.Bundle;
import android.os.Handler;

public class AppStart extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_appstart);
		new Handler().postAtTime(new Runnable() {

			@Override
			public void run() {
				if (!AppStart.this.isFinishing()) {
					openActivity(Feedback.class);
					closeActivity();
				}
			}
		}, 500);
	}

}
