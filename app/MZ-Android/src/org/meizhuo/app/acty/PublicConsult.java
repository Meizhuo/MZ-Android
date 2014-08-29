package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import butterknife.ButterKnife;

/**
 * 个人(民工)咨询列表
 * 
 * @author Lenovo
 * 
 */
public class PublicConsult extends BaseActivity {

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_publiconsult);
		ButterKnife.inject(this);
	}
}
