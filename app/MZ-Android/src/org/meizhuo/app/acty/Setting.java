package org.meizhuo.app.acty;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.meizhuo.api.PublicerAPI;
import org.meizhuo.api.VersionAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.utils.AndroidUtils;
import org.meizhuo.utils.Constants;
import org.meizhuo.utils.DataPool;
import org.meizhuo.view.WaittingDialog;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
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

	@InjectView(R.id.setting_feedback) LinearLayout feedback;
	@InjectView(R.id.about) LinearLayout about;
	@InjectView(R.id.user_login) LinearLayout login;
	@InjectView(R.id.logout) LinearLayout logout;
	PublicerAPI publicerApi;
	UpdateHandler handler = new UpdateHandler();
	 BroadcastReceiver isLoginReceiver;
	

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState, R.layout.acty_setting);
		setAppTitle("设置");
		publicerApi = new PublicerAPI();
		openReceiver();
 	}
	
	private void openReceiver(){
		isLoginReceiver = new isLoginReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constants.Action_Employer_isLogin);//用人单位登录
		filter.addAction(Constants.Action_Publicer_isLogin);
		registerReceiver(isLoginReceiver, filter);
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
	
	@OnClick(R.id.settings_update_software) public void update(){
		if(!AndroidUtils.isNetworkConnected(Setting.this))
		{
			toast("请打开您的网络开关！");
			return ;
		}
		VersionAPI api = new VersionAPI();
		handler.sendEmptyMessage(Constants.Start);
		final Message msg = handler.obtainMessage();
		api.getLatestInfo(new JsonResponseHandler() {
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				try {
				
					msg.what = Constants.Finish;
					msg.obj = obj ;
					handler.sendMessage(msg);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					msg.what = Constants.Fail;
					handler.sendMessage(msg);
				}
			
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				
			}
		});
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
						String response = obj.getString("response");
						toast(response);
					
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
	
	class UpdateHandler extends Handler{
		WaittingDialog dialog;
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case Constants.Start:
					dialog =  new WaittingDialog(Setting.this);
					dialog.setText("正在加载更新信息!");
					dialog.show();
				break;
				
			case Constants.Finish:
				if (dialog.isShowing())
					dialog.dismiss();
				dialog = null;
				JSONObject obj1 = (JSONObject) msg.obj;
				try {
					int versioncode = Integer.parseInt(obj1
							.getString("version_code"));
					String versionname = obj1.getString("version_name");
					int currentVersion = AndroidUtils
							.getAppVersionCode(getApplicationContext());
					final String url = obj1.getString("url");
					String updateInfo = obj1.getString("description");
					if(currentVersion == versioncode)
					{
						toast("已经是最新版本，无需更新");
						return ;
					}
					AlertDialog.Builder builder =  new AlertDialog.Builder(Setting.this);
					builder.setTitle("更新提示");
					builder.setMessage("当前版本:" + AndroidUtils.getAppVersionName(Setting.this) + "\n更新版本号:" + versionname + "\n" + updateInfo);
					builder.setPositiveButton("立刻更新", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Intent intent =  new Intent(Intent.ACTION_VIEW);
							Uri uri = Uri.parse(url);
							intent.setData(uri);
							startActivity(intent);
						}
					});
					builder.setNegativeButton("稍后更新", null);
					AlertDialog alertdialog = builder.create();
					alertdialog.show();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		}
	}
	
	class isLoginReceiver extends BroadcastReceiver {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			toast("执行了");
			if (intent.getAction().equals(Constants.Action_Employer_isLogin) ||intent.getAction().equals(Constants.Action_Publicer_isLogin)){
				login.setVisibility(View.INVISIBLE);
				logout.setVisibility(View.VISIBLE);
			}
		}
	}
	
	

}
