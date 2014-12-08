package org.meizhuo.app.acty;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.meizhuo.api.PublicerAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.model.Publicer;
import org.meizhuo.utils.Constants;
import org.meizhuo.view.WaittingDialog;
import butterknife.InjectView;
import butterknife.OnClick;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 用戶中心
 * 
 * @author Lenovo
 * 
 */
public class UserCenterPublicer extends BaseActivity {
	
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
	
	/**
	 * 修改密码
	 */
	@OnClick(R.id.mz_usercenter_changepsw) public void ToChangePsw(){
		LayoutInflater inflater = LayoutInflater.from(UserCenterPublicer.this);
		View dialogView = inflater.inflate(R.layout.dialog_change_psw, null);
		final EditText et_change_oldpsw =(EditText) dialogView.findViewById(R.id.et_change_oldpsw);
		final EditText et_change_newpsw =(EditText) dialogView.findViewById(R.id.et_change_newpsw);
		final EditText et_confirm_newpsw=(EditText)dialogView.findViewById(R.id.et_change_confirmnewpsw);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("修改密码");
		builder.setView(dialogView);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if((et_change_oldpsw.getText().toString()) == null || (et_change_oldpsw.getText().toString()).equals(""))
				{
					toast("旧密码不能为空!");
					return ;
				}
				if((et_change_newpsw.getText().toString()) == null || (et_change_newpsw.getText().toString()).equals(""))
				{
					toast("新密码不能为空!");
					return ;
				}
				if((et_change_oldpsw.getText().toString()) == null || (et_change_oldpsw.getText().toString()).equals(""))
				{
					toast("旧密码不能为空!");
					return ;
				}
				if(!((et_confirm_newpsw.getText().toString()).equals(et_change_newpsw.getText().toString()))){
					toast("两次新密码输入不一致,修改失败!");
					return ;
				}
				PublicerAPI.change_psw(et_change_oldpsw.getText().toString(), et_change_newpsw.getText().toString(), new JsonResponseHandler() {
					
					@Override
					public void onOK(Header[] headers, JSONObject obj) {
						try {
							if(obj.getString("code").equals("20000")){
								toast("修改成功");
								sendBroadcast(new Intent(Constants.Action_Changed_Psw_Success));
								openActivity(Login.class);
							}
							if(obj.getString("error_code").equals("40000")){
								String message = obj.getString("msg");
								toast(message);
							}
							
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
					
					@Override
					public void onFaild(int errorType, int errorCode) {
						
					}
				});
			}
		});
		builder.setNegativeButton("取消", null);
		AlertDialog dialog =  builder.create();
		dialog.show();
	}
	
	@OnClick(R.id.tv_userinfo_publicer_edit) public void EditInfo() {
		String name = publicer.getName();
		String gender = publicer.getGender();
		String workplace = publicer.getWorkPlace();
		Intent intent =  new Intent(UserCenterPublicer.this, UserCenterPublicerEditInfo.class);
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
				Log.i(TAG, obj.toString());
				try {
					String sex = obj.getString("sex");
					String nickname = obj.getString("nickname");
					String work_place = obj.getString("work_place");
					
					if (!sex.equals("") && !nickname.equals("") ){
						publicer.setGender(sex);
						publicer.setName(nickname);
						publicer.setWorkPlace(work_place);
						msg.what = Constants.Finish;
					}
					handler.sendMessage(msg);
				} catch (JSONException e) {
					e.printStackTrace();
					msg.what =  Constants.Fail;
					handler.sendMessage(msg);
				}
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				toast("获取数据失败" + errorCode);
				msg.what = Constants.Fail;
				handler.sendMessage(msg);
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
			switch (msg.what) {
			case Constants.Start:
				dialog =  new WaittingDialog(UserCenterPublicer.this);
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
				if (dialog.isShowing()){
					dialog.dismiss();
				}
				toast("网络不给力,请检查你的网络设置");
				UserCenterPublicer.this.finish();
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
		if (requestCode == 1000 && resultCode == 1001){
			String name = data.getStringExtra("name");
			String sex = data.getStringExtra("sex");
			String workplace = data.getStringExtra("work_place");
			name_tv.setText(name);
			sex_tv.setText(sex);
			workplace_tv.setText(workplace);
		}
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			openActivity(Main.class);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	
	

}
