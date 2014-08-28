package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import butterknife.ButterKnife;
/**
 * 单位咨询——政策
 * @author Lenovo
 *
 */
public class UnitInfoPolicy extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_unitinfopolicy);
		ButterKnife.inject(this);
	}
}
