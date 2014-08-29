package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import butterknife.ButterKnife;

import android.os.Bundle;

/**
 * 个人(民工)咨询页面
 * 
 * @author Lenovo
 * 
 */
public class PublicConsultChat extends BaseActivity {

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_publiconsult);
		ButterKnife.inject(this);
	}

}
