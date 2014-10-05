package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.utils.AndroidUtils;

import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.InjectView;

/**
 * 设置里面关于页面的Activity！
 * 
 * 
 * @author Jayin
 * 
 */
public class SettingAbout extends BaseActivity {
	@InjectView(R.id.tv_version)TextView tv_verison;

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState,R.layout.acty_settingabout);
		setAppTitle("关于");
		try {
			tv_verison.setText("版本号: "+AndroidUtils.getAppVersionName(this));
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

}
