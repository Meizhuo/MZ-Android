package org.meizhuo.app;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.meizhuo.api.EmployerAPI;
import org.meizhuo.api.PublicerAPI;
import org.meizhuo.api.VersionAPI;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.utils.AndroidUtils;
import org.meizhuo.utils.Constants;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class CoreService extends Service {
	private static final String TAG = " CoreService";
	private BroadcastReceiver isLoginReceiver = null;
	private Context context;
	private boolean isPublicerLogin;
	private boolean isEmployerLogin;
	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		openReceiver();
	}
	
	private void openReceiver(){
		isLoginReceiver = new isLoginReceiver();
		IntentFilter filter =  new IntentFilter();
		filter.addAction(Constants.Action_Employer_isLogin);
		filter.addAction(Constants.Action_Publicer_isLogin);
		registerReceiver(isLoginReceiver, filter);
	}
	
	
	
	
	
	

	
	//任务分发
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		if (intent != null){
			String action = intent.getAction();
			Runnable runnable = null;
			if (Constants.Action_checkVersion.equals(action)){
				checkVersion(); //版本控制
			} 
			if (Constants.Action_Publicer_To_ReLogin.equals(action)){
				Log.i(TAG, "拿到了普通登录的Action");
				publicerRelogin();
			} 
			if (Constants.Action_Employer_To_Relogin.equals(action)){
				Log.i(TAG, "拿到了用人单位的Action");
				employerRelogin();
			}
//		new Thread(runnable).start();
		}
		
		return Service.START_STICKY;
	}
	
	private void checkVersion(){
		VersionAPI api = new VersionAPI();
		api.getLatestInfo(new JsonResponseHandler() {
			@Override
			public void onOK(Header[] headers, JSONObject obj)  {
				// TODO Auto-generated method stub
				try {
					System.out.println(obj.toString());
					int versioncode = Integer.parseInt(obj
							.getString("version_code"));
					String versionname = obj.getString("version_name");
					int currentVersion = AndroidUtils
							.getAppVersionCode(getApplicationContext());
					String url = obj.getString("url");
					String updateInfo = obj.getString("description");
					if (versioncode > currentVersion) {
						Intent intent = new Intent(Constants.Action_Receive_VersionInfo);
						intent.putExtra("version_code", versioncode);
						intent.putExtra("version_name", versionname);
						intent.putExtra("updateInfo", updateInfo);
						intent.putExtra("url", url);
						sendBroadcast(intent);
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NameNotFoundException e) {
					e.printStackTrace();
				}
			
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	
	
	class isLoginReceiver extends BroadcastReceiver{
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			
		
			if(intent.getAction().equals(Constants.Action_Publicer_isLogin))
			{
				isPublicerLogin = true;
			}
				
			
			if (intent.getAction().equals(Constants.Action_Employer_isLogin))
			{
				isEmployerLogin = true;
			}
			
		}
	}
	
	private void publicerRelogin(){
		String account = AppInfo.getPublicername(getApplicationContext());
		String psw = AppInfo.getPublicerPSW(getApplicationContext());
		if(account == null || account.equals("") || psw == null || psw.equals(""))
		{
			return ;
		}else{
			PublicerAPI api = new PublicerAPI();
			api.Login(account, psw, new JsonResponseHandler() {
				
				@Override
				public void onOK(Header[] headers, JSONObject obj) {
					// TODO Auto-generated method stub
					Log.i(TAG, "" + obj);
					sendBroadcast(new Intent(Constants.Action_Publicer_ReLoginSuccessful));
				}
				
				@Override
				public void onFaild(int errorType, int errorCode) {
					// TODO Auto-generated method stub
				}
			});
		}
	}
	
	private void employerRelogin(){
		String account = AppInfo.getEmployername(getApplicationContext());
		String psw = AppInfo.getEmployerPSW(getApplicationContext());
		if (account == null || account.equals("") || psw == null || psw.equals(""))
		{
			return ;
		} else {
			EmployerAPI api = new EmployerAPI();
			api.Login(account, psw, new JsonResponseHandler() {
				
				@Override
				public void onOK(Header[] headers, JSONObject obj) {
					// TODO Auto-generated method stub
					Log.i(TAG, "" + obj);
					sendBroadcast(new Intent(Constants.Action_Employer_ReLoginSuccessful));
				}
				
				@Override
				public void onFaild(int errorType, int errorCode) {
					// TODO Auto-generated method stub
					
				}
			});
		}
	}
	

}
