package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * 用戶中心
 * 
 * @author Lenovo
 * 
 */
public class UserCenter extends BaseActivity {
	
	@InjectView(R.id.tv_title)
	TextView tv_title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_usercenter);
		ButterKnife.inject(this);
		tv_title.setText("用户中心");
	}

	@OnClick(R.id.btn_title_back)
	public void back() {
		closeActivity();
	}
}
