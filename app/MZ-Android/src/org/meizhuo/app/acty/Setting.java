package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.utils.DataPool;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 设置
 * 
 * @author Lenovo
 * 
 */
public class Setting extends BaseActivity {

	@InjectView(R.id.setting_notification) LinearLayout newssettings;
	@InjectView(R.id.setting_feedback) LinearLayout feedback;
	@InjectView(R.id.about) LinearLayout about;

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState, R.layout.acty_setting);
		setAppTitle("设置");

	}
	
	@OnClick(R.id.user_login) public void Login() {
		if(checkLoginInfo()){
			Toast.makeText(this, "已经登录,无需重复登录", Toast.LENGTH_SHORT).show();
		}
		else
		{
			openActivity(Login.class);
		}
		closeActivity();
	}

	@OnClick(R.id.about) public void ToAbout() {
		
		openActivity(SettingAbout.class);
	}

	@OnClick(R.id.setting_feedback) public void ToFeedback() {
		openActivity(Feedback.class);
	}
	

    /** 检测登录信息 */
	private boolean checkLoginInfo() {
		DataPool dp = new DataPool(DataPool.SP_Name_Publicer, this);
		if (dp.contains(DataPool.SP_Key_Publicer))
			return true;
		else 
			return false;
	}

}
