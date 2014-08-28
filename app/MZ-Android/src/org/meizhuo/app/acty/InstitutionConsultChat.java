package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import butterknife.ButterKnife;
/**
 * 机构咨询页面
 * @author Lenovo
 *
 */
public class InstitutionConsultChat extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_institutionconsultchat);
		ButterKnife.inject(this);
	}
}
