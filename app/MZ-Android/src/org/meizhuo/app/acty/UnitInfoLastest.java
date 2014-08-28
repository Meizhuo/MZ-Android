package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import butterknife.ButterKnife;
/**
 * 单位信息最新通知
 * @author Lenovo
 *
 */
public class UnitInfoLastest extends BaseActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_unitinfolastest);
		ButterKnife.inject(this);
	}
}
