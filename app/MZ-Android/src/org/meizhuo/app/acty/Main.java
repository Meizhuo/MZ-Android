package org.meizhuo.app.acty;

import java.util.ArrayList;
import java.util.List;

import org.meizhuo.adapter.ImagePagerAdapter;
import org.meizhuo.adapter.ImagePagerAdapter.OnItemClickListener;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.CoreService;
import org.meizhuo.app.R;
import org.meizhuo.utils.Constants;

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

	@InjectView(R.id.autoscrollviewpage) org.meizhuo.view.AutoScrollViewPager viewPager;

	ImagePagerAdapter adapter_imagepage;

	List<Integer> imageIdList;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.acty_main);
		setDisplayBackIcon(false);
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

	@OnClick(R.id.btn_unitinfo) public void unitinfo() {
		openActivity(UnitInfo.class);
	}

	@OnClick(R.id.btn_institutions) public void Institutions() {
		openActivity(Institutions.class);
	}

	@OnClick(R.id.btn_institution_consult) public void institution_consult() {
		openActivity(InstitutionConsult.class);
	}

	@OnClick(R.id.btn_public_consult) public void public_consult() {
		openActivity(PublicConsult.class);
	}

	@OnClick(R.id.btn_usercenter) public void usercenter() {
		openActivity(UserCenter.class);
	}

	@OnClick(R.id.btn_setting) public void setting() {
		openActivity(Setting.class);
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
			if(action.equals(Constants.Action_Receive_VersionInfo)){
				Log.i(TAG, "SUCCESSFUL");
				AlertDialog.Builder  builder=new AlertDialog.Builder(Main.this);
				builder.setTitle("发现新版本");
				builder.setMessage( updateInfo);
				
				builder.setPositiveButton("立刻更新 ", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent =  new Intent(Intent.ACTION_VIEW);
						Uri uri = Uri.parse("http://etips.github.io");
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
	
	private void checkVersion() {
		Intent service =  new Intent(getContext(), CoreService.class);
		service.setAction(Constants.Action_checkVersion);
		startService(service);
	}

}
