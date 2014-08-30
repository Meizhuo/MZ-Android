package org.meizhuo.app;

import org.meizhuo.app.acty.Main;
import org.meizhuo.app.acty.PublicConsult;
import org.meizhuo.app.acty.PublicConsult_Cultivate;

import android.os.Bundle;
import android.os.Handler;

public class AppStart extends BaseActivity {

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_appstart);
		new Handler().postAtTime(new Runnable() {

			@Override public void run() {
				if (!AppStart.this.isFinishing()) {
					openActivity(PublicConsult_Cultivate.class);
					closeActivity();
				}
			}
		}, 500);
	}

}
