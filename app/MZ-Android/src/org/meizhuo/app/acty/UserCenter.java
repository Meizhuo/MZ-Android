package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import butterknife.ButterKnife;

/**
 * 用戶中心
 * 
 * @author Lenovo
 * 
 */
public class UserCenter extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_usercenter);
		ButterKnife.inject(this);
	}
}
