package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import butterknife.ButterKnife;

import android.os.Bundle;
/**
 * 培训机构列表
 * @author Lenovo
 *
 */
public class InstitutionConsult extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_institutionconsult);
		ButterKnife.inject(this);
	}
}
