package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import butterknife.ButterKnife;
/**
 * 单位信息列表
 * @author Jayin
 *
 */
public class UnitInfo extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_unitinfo);
		ButterKnife.inject(this);
	}
}
