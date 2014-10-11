package org.meizhuo.app.acty;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.meizhuo.api.VersionAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.utils.AndroidUtils;
import org.meizhuo.view.WaittingDialog;

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
	@InjectView(R.id.updatecontent) TextView tv_updatecontent;
	VersionAPI api;
	WaittingDialog diglog;

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState,R.layout.acty_settingabout);
		setAppTitle("关于");
		diglog = new WaittingDialog(this);
		try {
			tv_verison.setText("版本号: "+AndroidUtils.getAppVersionName(this));
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		getUpdateInfo();
	}
	
	private void getUpdateInfo(){
		if (api == null)
			api = new VersionAPI();
		api.getLatestInfo(new JsonResponseHandler() {
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				diglog.setText("正在加载更新信息!");
				diglog.show();
			}
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				try {
					String updateInfo = obj.getString("description");
					tv_updatecontent.setText(updateInfo);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				toast("出错了,请检查你的网络设置!");
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				if (diglog.isShowing())
					diglog.dismiss();
				diglog = null;
			}
		});
	}

}
