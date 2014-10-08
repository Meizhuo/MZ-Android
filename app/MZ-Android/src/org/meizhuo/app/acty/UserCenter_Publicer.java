package org.meizhuo.app.acty;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.meizhuo.api.PublicerAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.model.ErrorCode;
import org.meizhuo.model.Publicer;
import org.meizhuo.utils.Constants;
import org.meizhuo.view.WaittingDialog;

import com.google.gson.JsonNull;

import butterknife.InjectView;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 用戶中心
 * 
 * @author Lenovo
 * 
 */
public class UserCenter_Publicer extends BaseActivity {
	
	private static final String TAG = "UserCenter_Publicer";
	
	@InjectView(R.id.mz_usercenter_name_tv) TextView name_tv;
	@InjectView(R.id.mz_usercenter_sex_tv) TextView sex_tv;
	@InjectView(R.id.mz_usercenter_workplace_tv) TextView workplace_tv;
	@InjectView(R.id.tv_userinfo_publicer_edit) TextView edit_tv;  // 右上角的编辑信息
	private Publicer publicer;
	private PublicerAPI publicApi;
	private UCHandler handler =  new UCHandler();
	

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState, R.layout.acty_usercenter_publicer);
		setAppTitle("用户中心");
		publicer =  new Publicer();
		initData();
		
	}
	
	@OnClick(R.id.tv_userinfo_publicer_edit) public void EditInfo() {
		String name = publicer.getName();
		String gender = publicer.getGender();
		String workplace = publicer.getWorkPlace();
		Intent intent =  new Intent(UserCenter_Publicer.this, UserCenter_Publicer_EditInfo.class);
		intent.putExtra("name", name);
		intent.putExtra("gender", gender);
		intent.putExtra("workplace", workplace);
		startActivityForResult(intent, 1000);
	}
	
	
	private void initData() {
		if (publicApi == null)
			publicApi =  new PublicerAPI();
		handler.sendEmptyMessage(Constants.Start);
		final Message msg = handler.obtainMessage();
		publicApi.getProfile(new JsonResponseHandler() {
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				Log.i(TAG, obj.toString());
				try {
					String sex = obj.getString("sex");
					String nickname = obj.getString("nickname");
					String work_place = obj.getString("work_place");
					
					if (!sex.equals("") && !nickname.equals("") && !work_place.equals("")){
						publicer.setGender(sex);
						publicer.setName(nickname);
						publicer.setWorkPlace(work_place);
						msg.what = Constants.Finish;
					}
					handler.sendMessage(msg);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg.what =  Constants.Fail;
					handler.sendMessage(msg);
				}
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				toast("获取数据失败" + errorCode);
			}
		});
		
	}
	
	
	
	private void initLayout() {
		name_tv.setText(publicer.getName().toString());
		sex_tv.setText(publicer.getGender().toString());
		workplace_tv.setText(publicer.getWorkPlace().toString());
	}
	
	class UCHandler extends Handler {
		WaittingDialog dialog;
		
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case Constants.Start:
				dialog =  new WaittingDialog(UserCenter_Publicer.this);
				dialog.show();
				break;
			case Constants.Finish:
				if (!dialog.isShowing()){
					dialog = null;
					break;
				}
				initLayout();
				dialog.dismiss();
				dialog = null;
				break;
			case Constants.Fail:
				if (!dialog.isShowing()){
					dialog = null;
					break;
				}
				toast("网络不给力,请检查你的网络设置");
				break;

			}
		}
		
	}
	/**
	 * @InjectView(R.id.mz_usercenter_name_tv) TextView name_tv;
	@InjectView(R.id.mz_usercenter_sex_tv) TextView sex_tv;
	@InjectView(R.id.mz_usercenter_workplace_tv) TextView workplace_tv;
	 */
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == 1000 && resultCode == 1001){
			String name = data.getStringExtra("name");
			String sex = data.getStringExtra("sex");
			String workplace = data.getStringExtra("work_place");
			name_tv.setText(name);
			sex_tv.setText(sex);
			workplace_tv.setText(workplace);
		}
		
	}

	
	

}
