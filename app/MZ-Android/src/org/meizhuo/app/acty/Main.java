package org.meizhuo.app.acty;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONObject;
import org.meizhuo.adapter.ImagePagerAdapter;
import org.meizhuo.adapter.ImagePagerAdapter.OnItemClickListener;
import org.meizhuo.api.PublicerAPI;
import org.meizhuo.app.AppInfo;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.CoreService;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.utils.AndroidUtils;
import org.meizhuo.utils.Constants;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Optional;

public class Main extends BaseActivity {
	private static final String TAG = "Main";
	
	private BroadcastReceiver mReceiver = null;
	private BroadcastReceiver loginReceiver = null;
	/**普通用户第一次登陆*/
	private boolean is_Publicer_Login;
	/**用人单位第一次登陆*/
	private boolean is_Employer_Login;
	/***普通用户重新登陆*/
	private boolean Publicer_reLogin;
	/**用人单位重新登陆*/
	private boolean Employer_reLogin;
	/*
	 	@Optional @OnClick(R.id.btn_app_back) public void back() {
		if (this.findViewById(R.id.tv_app_title).getVisibility() != View.INVISIBLE) {
			closeActivity();
		}
	}
	 */

	@InjectView(R.id.autoscrollviewpage) org.meizhuo.view.AutoScrollViewPager viewPager;

	ImagePagerAdapter adapter_imagepage;

	List<Integer> imageIdList;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.acty_main);
		setDisplayBackIcon(false);
		initLoginReceiver();
		checkPublicerReLogin();
		checkEmployerReLogin();
		checkVersion();
		initReceiver();
		
		
		viewPager.setInterval(2000);
		viewPager.startAutoScroll();

		imageIdList = new ArrayList<Integer>();
		
		
		imageIdList.add(R.drawable.bigbang);
		imageIdList.add(R.drawable.aa_evernote);
		imageIdList.add(R.drawable.hannibal);

		adapter_imagepage = new ImagePagerAdapter(this, imageIdList);
		adapter_imagepage.setOnItemClickListener(new OnItemClickListener() {

			@Override public void onItemClick(int position, View view) {
				// to do some work
				toast("" + position);

			}
		});
		viewPager.setAdapter(adapter_imagepage);
	}
	
	
	@OnClick(R.id.btn_app_back) public void back(){
		if (this.findViewById(R.id.tv_app_title).getVisibility() != View.INVISIBLE) {
			return ;
		}
	}

	@OnClick(R.id.btn_professional_training) public void Professional_Training() {
		openActivity(Professional_Training.class);
	}

	@OnClick(R.id.btn_professional_evaluation) public void Professional_Evaluation() {
		openActivity(Professional_Evaluation.class);
	}

	@OnClick(R.id.btn_institution_info) public void InstitutionInfo() {
		openActivity(InstitutionInfo.class);
	}

	@OnClick(R.id.btn_major_search) public void major_search() {
		openActivity(Major_Search.class);
		
	}

	@OnClick(R.id.btn_usercenter) public void usercenter() {
		boolean isLogin = is_Publicer_Login || is_Employer_Login || Publicer_reLogin || Employer_reLogin;
		if(!isLogin){
			toast("请先到设置模块进行登录");
			return;
		}
		if (!AndroidUtils.isNetworkConnected(Main.this)){
			toast("请打开您的网络开关 ");
			return;
		}
		if(is_Publicer_Login ||Publicer_reLogin ){
			openActivity(UserCenter_Publicer.class);
		}
		
		if (is_Employer_Login || Employer_reLogin) {
			openActivity(UserCenter_Employer.class);
		}
		
	}

	@OnClick(R.id.btn_setting) public void setting() {
		boolean isLogin = is_Publicer_Login || is_Employer_Login;
		Intent it = new Intent(this, Setting.class);
		it.putExtra("isLogin", isLogin);
		startActivity(it);
	}
	private void initLoginReceiver(){
		loginReceiver =  new LoginReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constants.Action_Publicer_isLogin);;
		filter.addAction(Constants.Action_Employer_isLogin);
		filter.addAction(Constants.Action_Publicer_ReLoginSuccessful);
		filter.addAction(Constants.Action_Employer_ReLoginSuccessful);
		registerReceiver(loginReceiver, filter);
	}
	
	private void initReceiver() {
		mReceiver =  new AppStartReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constants.Action_Receive_VersionInfo);
		registerReceiver(mReceiver, filter);
		
	}
	
	class  AppStartReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action =  intent.getAction();
			String updateInfo = intent.getStringExtra("updateInfo");
			String versionname = intent.getStringExtra("version_name");
			final String url = intent.getStringExtra("url");
			if(action.equals(Constants.Action_Receive_VersionInfo)){
				Log.i(TAG, "SUCCESSFUL");
				AlertDialog.Builder  builder=new AlertDialog.Builder(Main.this);
				builder.setTitle("发现新版本");
				try {
					builder.setMessage("当前版本:" + AndroidUtils.getAppVersionName(Main.this) + "\n更新版本号:"+ versionname + "\n" +  updateInfo);
				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				builder.setPositiveButton("立刻更新 ", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent =  new Intent(Intent.ACTION_VIEW);
						Uri uri = Uri.parse(url);
						intent.setData(uri);
						startActivity(intent);
					}
				});
				builder.setCancelable(false);
				builder.setOnKeyListener(keylistener);
				AlertDialog dialog =  builder.create();
				dialog.show();
			}
		}
	}
	
	OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
		
		@Override
		public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			if (keyCode == KeyEvent.KEYCODE_BACK)
			{
				return true;
			}else{
				return false;
			}
		
		}
	};
	
	
	
	
	
	class LoginReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if(action.equals(Constants.Action_Publicer_isLogin)){
				is_Publicer_Login = true;
			}
			if(action.equals(Constants.Action_Employer_isLogin)){
				is_Employer_Login = true;
			}
			if(action.equals(Constants.Action_Publicer_ReLoginSuccessful)){
				Log.i(TAG, "普通用户重新登录");
				Publicer_reLogin = true;
			}
			if(action.equals(Constants.Action_Employer_ReLoginSuccessful)){
				Log.i(TAG, "用人单位重新登录");
				Employer_reLogin = true;
			}
		}
		
	}
	
	private void checkVersion() {
		Intent service =  new Intent(getContext(), CoreService.class);
		service.setAction(Constants.Action_checkVersion);
		startService(service);
	}
	
	private void  checkPublicerReLogin(){
		Intent service =  new Intent(getContext(), CoreService.class);
		service.setAction(Constants.Action_Publicer_To_ReLogin);
		startService(service);
	}
	
	private void checkEmployerReLogin(){
		Intent service =  new Intent(getContext(), CoreService.class);
		service.setAction(Constants.Action_Employer_To_Relogin);
		startService(service);
	}
	


}
