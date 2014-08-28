package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import butterknife.ButterKnife;
/**
 * 设置
 * @author Lenovo
 *
 */
public class Setting extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_setting);
		ButterKnife.inject(this);
	}
}
