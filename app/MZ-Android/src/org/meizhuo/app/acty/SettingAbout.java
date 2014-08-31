package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;

/**
 * 设置里面关于页面的Activity！
 * 
 * @author Jason
 * 
 */
public class SettingAbout extends BaseActivity {


	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState,R.layout.acty_settingabout);
		setAppTitle("关于");

	}

}
