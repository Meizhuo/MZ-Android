package org.meizhuo.app;

import org.meizhuo.app.acty.Main;

import android.os.Bundle;
import android.os.Handler;

public class AppStart extends BaseActivity {

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_appstart);
		new Handler().postDelayed(new Runnable() {

			@Override public void run() {
				if (!AppStart.this.isFinishing()) {
					openActivity(Main.class);
					closeActivity();
				}
			}
		}, 1500);
	}

}
