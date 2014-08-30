package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 设置里面关于页面的Activity！
 * 
 * @author Jason
 * 
 */
public class SettingAbout extends BaseActivity {


	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_settingabout);
		ButterKnife.inject(this);

	}
	@OnClick(R.id.btn_title_back) public void back() {
		closeActivity();
	}

}
