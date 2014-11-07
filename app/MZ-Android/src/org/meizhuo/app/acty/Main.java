package org.meizhuo.app.acty;

import java.io.File;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.meizhuo.adapter.ImagePagerAdapter;
import org.meizhuo.adapter.ImagePagerAdapter.OnItemClickListener;
import org.meizhuo.adapter.ImagePagerAdapter.OnPositionChangeListener;
import org.meizhuo.api.AdvertisementAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.CoreService;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.model.Advertisement;
import org.meizhuo.utils.AndroidUtils;
import org.meizhuo.utils.Constants;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;

public class Main extends BaseActivity {
	private static final String TAG = "Main";
	@InjectView(R.id.autoscrollviewpage)
	org.meizhuo.view.AutoScrollViewPager viewPager;
	@InjectView(R.id.tv_ad_title)
	TextView tv_ad_title;

	private BroadcastReceiver mReceiver = null;
	private BroadcastReceiver loginReceiver = null;
	private BroadcastReceiver logoffReceiver = null;
	// DownloadManager
//	private DownloadManager downloadManager;
//	private SharedPreferences prefs;
//	private static final String DL_ID = "downloadId";

	/** 普通用户第一次登陆 */
	private boolean is_Publicer_Login;
	/** 用人单位第一次登陆 */
	private boolean is_Employer_Login;
	/*** 普通用户重新登陆 */
	private boolean Publicer_reLogin;
	/** 用人单位重新登陆 */
	private boolean Employer_reLogin;
	/** 注销 */
	private boolean logoff;
	/**修改密码成功*/
	private boolean has_change_psw;

	List<Advertisement> ad;

	ImagePagerAdapter adapter_imagepage;

	List<String> ad_list;

	private BroadcastReceiver logoutReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.acty_main);
		setDisplayBackIcon(false);
//		initDownload();
		initLoginReceiver();
		initLogout_Receiver();
		initLogoffReceiver();
		checkPublicerReLogin();
		checkEmployerReLogin();
		checkVersion();
		initReceiver();
		initData();

	}

//	private void initDownload() {
//		downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//		prefs = PreferenceManager.getDefaultSharedPreferences(this);
//	}

	private void initData() {

		viewPager.setInterval(3000);
		viewPager.startAutoScroll();

		final Handler h = new Handler() {
			public void handleMessage(android.os.Message msg) {
				switch (msg.what) {
				case Constants.Finish:
					JSONObject obj1 = (JSONObject) msg.obj;
					ad = Advertisement.create_by_jsonarray(obj1.toString());
					adapter_imagepage = new ImagePagerAdapter(Main.this, ad,
							null);

					adapter_imagepage
							.setOnPositionChangeListener(new OnPositionChangeListener() {

								@Override
								public void OnPositionChange(final int position) {
									// TODO Auto-generated method stub
									int realposition = position;
									if (position - 1 < 0) {
										realposition = ad.size() - 1;
									} else {
										realposition--;
									}
									tv_ad_title.setText(ad.get(realposition)
											.getDescription());
								}
							});

					adapter_imagepage
							.setOnItemClickListener(new OnItemClickListener() {

								@Override
								public void onItemClick(int position, View view) {
									// to do some work

									Log.i(TAG, "url位置" + position);
									Intent intent = new Intent(Main.this,
											Main_Advertise.class);
									intent.putExtra("url", ad.get(position)
											.getUrl());
									intent.putExtra("description",
											ad.get(position).getDescription());
									startActivity(intent);

								}
							});
					viewPager.setAdapter(adapter_imagepage);
					break;

				default:
					break;
				}
			}
		};
		final Message msg = h.obtainMessage();
		AdvertisementAPI.getAdList(new JsonResponseHandler() {

			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				try {
					if (obj.getString("code").equals("20000")) {
						// ad =
						// Advertisement.create_by_jsonarray(obj.toString());
						msg.what = Constants.Finish;
						msg.obj = obj;
						h.sendMessage(msg);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				toast("新闻图片加载失败!请检查您的网络设置!");
			}
		});

	}

	@OnClick(R.id.btn_app_back)
	public void back() {
		if (this.findViewById(R.id.tv_app_title).getVisibility() != View.INVISIBLE) {
			return;
		}
	}

	@OnClick(R.id.btn_professional_training)
	public void Professional_Training() {
		openActivity(Professional_Training.class);
	}

	@OnClick(R.id.btn_professional_evaluation)
	public void Professional_Evaluation() {
		openActivity(Professional_Evaluation.class);
	}

	@OnClick(R.id.btn_institution_info)
	public void InstitutionInfo() {
		openActivity(InstitutionInfo.class);
	}

	@OnClick(R.id.btn_major_search)
	public void major_search() {
		if (!AndroidUtils.isNetworkConnected(Main.this)) {
			toast("请先打开您的网络开关");
			return;
		}
		openActivity(Major_Search.class);

	}

	@OnClick(R.id.btn_usercenter)
	public void usercenter() {
		boolean isLogin = is_Publicer_Login || is_Employer_Login
				|| Publicer_reLogin || Employer_reLogin;
		if (!isLogin || logoff) {
			openActivity(Login.class);
			return;
		}
		if (!AndroidUtils.isNetworkConnected(Main.this)) {
			toast("请打开您的网络开关 ");
			return;
		}
		if (is_Publicer_Login || Publicer_reLogin) {
			openActivity(UserCenter_Publicer.class);
		}

		if (is_Employer_Login || Employer_reLogin) {
			openActivity(UserCenter_Employer.class);
		}

	}

	@OnClick(R.id.btn_setting)
	public void setting() {
		boolean isLogin = is_Publicer_Login || is_Employer_Login
				|| Publicer_reLogin || Employer_reLogin;
		Intent it = new Intent(this, Setting.class);
		it.putExtra("isLogin", isLogin);
		startActivity(it);
	}

	private void initLoginReceiver() {
		loginReceiver = new LoginReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constants.Action_Publicer_isLogin);
		filter.addAction(Constants.Action_Employer_isLogin);
		filter.addAction(Constants.Action_Publicer_ReLoginSuccessful);
		filter.addAction(Constants.Action_Employer_ReLoginSuccessful);
		filter.addAction(Constants.Action_Logoff);
		filter.addAction(Constants.Action_Changed_Psw_Success);
		registerReceiver(loginReceiver, filter);
	}

	private void initLogoffReceiver() {
		logoffReceiver = new LogoffReceiver();
		registerReceiver(logoffReceiver, new IntentFilter(
				Constants.Action_Logoff));
	}

	private void initLogout_Receiver() {
		logoutReceiver = new LogoutReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constants.Action_Logout);
		registerReceiver(logoutReceiver, filter);

	}

	private void initReceiver() {
		mReceiver = new AppStartReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constants.Action_Receive_VersionInfo);
		registerReceiver(mReceiver, filter);

	}
	

	class AppStartReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			String updateInfo = intent.getStringExtra("updateInfo");
			String versionname = intent.getStringExtra("version_name");
			final String url = intent.getStringExtra("url");
			if (action.equals(Constants.Action_Receive_VersionInfo)) {
				AlertDialog.Builder builder = new AlertDialog.Builder(Main.this);
				builder.setTitle("发现新版本");
				try {
					builder.setMessage("当前版本:"
							+ AndroidUtils.getAppVersionName(Main.this)
							+ "\n更新版本号:" + versionname + "\n" + updateInfo);
				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				builder.setPositiveButton("立刻更新 ",
						new DialogInterface.OnClickListener() {

							@SuppressLint("NewApi")
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								Intent intent = new Intent(Intent.ACTION_VIEW);
								Uri uri = Uri.parse(url);
								 intent.setData(uri);
								 startActivity(intent);
								
//									DownloadManager.Request request = new DownloadManager.Request(
//											uri);
//									request.setAllowedNetworkTypes(Request.NETWORK_MOBILE
//											| Request.NETWORK_WIFI);
//									request.setAllowedOverRoaming(false);
//									// 设置文件类型
//									MimeTypeMap mimeTypeMap = MimeTypeMap
//											.getSingleton();
//									String mimeString = mimeTypeMap
//											.getMimeTypeFromExtension(MimeTypeMap
//													.getFileExtensionFromUrl(url));
//									request.setMimeType(mimeString);
//									// 在通知栏中显示
//									request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
//									request.setVisibleInDownloadsUi(true);
//									// sdcard的目录下的download文件夹
//									request.setDestinationInExternalPublicDir(
//											"/download/", "meizhuo.apk");
//									request.setTitle("东莞技能培训");
//									long id = downloadManager.enqueue(request);
//									// 保存id
//									prefs.edit().putLong(DL_ID, id).commit();
//								
//
//								registerReceiver(
//										receiver,
//										new IntentFilter(
//												DownloadManager.ACTION_DOWNLOAD_COMPLETE));
							}
						});
				builder.setCancelable(false);
				builder.setOnKeyListener(keylistener);
				AlertDialog dialog = builder.create();
				dialog.show();
			}
		}
	}

	OnKeyListener keylistener = new DialogInterface.OnKeyListener() {

		@Override
		public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				return true;
			} else {
				return false;
			}

		}
	};

//	private BroadcastReceiver receiver = new BroadcastReceiver() {
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			// 这里可以取得下载的id，这样就可以知道哪个文件下载完成了。适用与多个下载任务的监听
//			Log.v(TAG,
//					""
//							+ intent.getLongExtra(
//									DownloadManager.EXTRA_DOWNLOAD_ID, 0));
//			queryDownloadStatus();
//			if(intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)){
//				Intent i = new Intent(Intent.ACTION_VIEW);
//				String apkFilePath = new StringBuilder(Environment.getExternalStorageDirectory().getAbsolutePath())
//                .append(File.separator).append("/download/").append(File.separator)
//                .append("meizhuo.apk").toString();
//				 File file = new File(apkFilePath);
//			        if (file != null && file.length() > 0 && file.exists() && file.isFile()) {
//			            i.setDataAndType(Uri.parse("file://" + apkFilePath), "application/vnd.android.package-archive");
//			            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			           Main.this.startActivity(i);
//			        }
//				
//			}
//		}
//	};

	/** 登录接收器 */
	class LoginReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if (action.equals(Constants.Action_Publicer_isLogin)) {
				is_Publicer_Login = true;
				logoff = false;
			}
			if (action.equals(Constants.Action_Employer_isLogin)) {
				is_Employer_Login = true;
				logoff = false;
			}
			if (action.equals(Constants.Action_Publicer_ReLoginSuccessful)) {
				Publicer_reLogin = true;
				logoff = false;
			}
			if (action.equals(Constants.Action_Employer_ReLoginSuccessful)) {
				Employer_reLogin = true;
				logoff = false;
			}
			if (action.equals(Constants.Action_Logoff)) {
				Publicer_reLogin = false;
				is_Employer_Login = false;
				is_Publicer_Login = false;
				Employer_reLogin = false;
			}
			if (action.equals(Constants.Action_Changed_Psw_Success))
			{
				Publicer_reLogin = false;
				is_Employer_Login = false;
				is_Publicer_Login = false;
				Employer_reLogin = false;
			}
		}

	}

	/** 退出接收器 */
	class LogoutReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if (action.equals(Constants.Action_Logout)) {
				Main.this.finish();
			}
		}
	}

	class LogoffReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if (action.equals(Constants.Action_Logoff)) {
				logoff = true;
				is_Employer_Login = false;
				is_Publicer_Login = false;
			}

		}
	}

	private void checkVersion() {
		Intent service = new Intent(getContext(), CoreService.class);
		service.setAction(Constants.Action_checkVersion);
		startService(service);
	}

	private void checkPublicerReLogin() {
		Intent service = new Intent(getContext(), CoreService.class);
		service.setAction(Constants.Action_Publicer_To_ReLogin);
		startService(service);
	}

	private void checkEmployerReLogin() {
		Intent service = new Intent(getContext(), CoreService.class);
		service.setAction(Constants.Action_Employer_To_Relogin);
		startService(service);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		viewPager.stopAutoScroll();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		viewPager.startAutoScroll();
	}

//	@SuppressLint("NewApi")
//	private void queryDownloadStatus() {
//		DownloadManager.Query query = new DownloadManager.Query();
//		query.setFilterById(prefs.getLong(DL_ID, 0));
//		Cursor c = downloadManager.query(query);
//		if (c.moveToFirst()) {
//			int status = c.getInt(c
//					.getColumnIndex(DownloadManager.COLUMN_STATUS));
//			switch (status) {
//			case DownloadManager.STATUS_PAUSED:
//				Log.v(TAG, "STATUS_PAUSED");
//			case DownloadManager.STATUS_PENDING:
//				Log.v(TAG, "STATUS_PENDING");
//			case DownloadManager.STATUS_RUNNING:
//				// 正在下载，不做任何事情
//				Log.v(TAG, "STATUS_RUNNING");
//				break;
//			case DownloadManager.STATUS_SUCCESSFUL:
//				// 完成
//				Log.v(TAG, "下载完成");
//			
//				break;
//			case DownloadManager.STATUS_FAILED:
//				// 清除已下载的内容，重新下载
//				Log.v(TAG, "STATUS_FAILED");
//				downloadManager.remove(prefs.getLong(DL_ID, 0));
//				prefs.edit().clear().commit();
//				break;
//			}
//		}
//	}

}
