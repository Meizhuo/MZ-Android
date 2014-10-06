package org.meizhuo.app.acty;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.meizhuo.api.PublicerAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
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
	PublicerAPI publicerApi;

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState, R.layout.acty_setting);
		setAppTitle("设置");
		publicerApi = new PublicerAPI();

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
	
	@OnClick(R.id.logout) public void logout() {
		publicerApi.logout(new JsonResponseHandler() {
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				try {
					int code = Integer.parseInt(obj.getString("code"));
					String response = obj.getString("response");
					toast(code + response);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				toast("成功登出");
			}
		});
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
