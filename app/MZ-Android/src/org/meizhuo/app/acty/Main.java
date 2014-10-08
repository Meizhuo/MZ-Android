package org.meizhuo.app.acty;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.meizhuo.adapter.ImagePagerAdapter;
import org.meizhuo.adapter.ImagePagerAdapter.OnItemClickListener;
import org.meizhuo.api.PublicerAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.CoreService;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.utils.Constants;

import com.google.gson.JsonObject;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import butterknife.InjectView;
import butterknife.OnClick;

public class Main extends BaseActivity {
	private static final String TAG = "Appstart";
	
	private BroadcastReceiver mReceiver = null;
	private BroadcastReceiver loginReceiver = null;
	private boolean is_Publicer_Login;
	private boolean is_Employer_Login;

	@InjectView(R.id.autoscrollviewpage) org.meizhuo.view.AutoScrollViewPager viewPager;

	ImagePagerAdapter adapter_imagepage;

	List<Integer> imageIdList;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.acty_main);
		setDisplayBackIcon(false);
		checkVersion();
		initReceiver();
		initLoginReceiver();
		
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
		boolean isLogin = is_Publicer_Login || is_Employer_Login;
		if(!isLogin){
			toast("请先到设置模块进行登录");
		}
		if(is_Publicer_Login){
			openActivity(UserCenter_Publicer.class);
		}
		
		if (is_Employer_Login) {
			openActivity(UserCenter_Employer.class);
		}
		
	}

	@OnClick(R.id.btn_setting) public void setting() {
		openActivity(Setting.class);
	}
	private void initLoginReceiver(){
		loginReceiver =  new LoginReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constants.Action_Publicer_isLogin);;
		filter.addAction(Constants.Action_Employer_isLogin);
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
			final String url = intent.getStringExtra("url");
			if(action.equals(Constants.Action_Receive_VersionInfo)){
				Log.i(TAG, "SUCCESSFUL");
				AlertDialog.Builder  builder=new AlertDialog.Builder(Main.this);
				builder.setTitle("发现新版本");
				builder.setMessage(updateInfo);
				
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
				builder.setNegativeButton("稍后更新", null);
				AlertDialog dialog =  builder.create();
				dialog.show();
			}
		}
	}
	
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
		}
		
	}
	
	private void checkVersion() {
		Intent service =  new Intent(getContext(), CoreService.class);
		service.setAction(Constants.Action_checkVersion);
		startService(service);
	}

}
