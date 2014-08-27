package org.meizhuo.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class AppStart extends Activity {
 
	@InjectView(R.id.textView1)
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_appstart);
		ButterKnife.inject(this);
		tv.setText("ButterKnife!");
		
	}
}
