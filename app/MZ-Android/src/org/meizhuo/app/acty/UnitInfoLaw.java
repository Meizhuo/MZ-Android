package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import butterknife.ButterKnife;
/**
 * 单位咨询——法律法规
 * @author Lenovo
 *
 */
public class UnitInfoLaw extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_unitinfolaw);
		ButterKnife.inject(this);
	}
}
