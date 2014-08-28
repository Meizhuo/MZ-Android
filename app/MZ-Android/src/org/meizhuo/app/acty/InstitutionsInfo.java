package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import butterknife.ButterKnife;
/**
 * 某机构的信息
 * @author Lenovo
 *
 */
public class InstitutionsInfo extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_institutionsinfo);
		ButterKnife.inject(this);
	}
}
