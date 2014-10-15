package org.meizhuo.app;

import org.meizhuo.app.acty.Main;
import org.meizhuo.utils.Constants;
import org.meizhuo.utils.DataPool;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

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
