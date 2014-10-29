package org.meizhuo.app.acty;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.meizhuo.api.PublicerAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.utils.Constants;
import org.meizhuo.view.WaittingDialog;

import butterknife.InjectView;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserCenter_Publicer_EditInfo extends BaseActivity{
	
	
	@InjectView(R.id.usercenter_editInfo_sex) LinearLayout edit_sex;
	@InjectView(R.id.usercenter_editInfo_sex_tv) TextView edit_sex_tv;
	@InjectView(R.id.usercenter_editInfo_name) TextView edit_name;
	@InjectView(R.id.usercenter_editInfo_workplace) EditText edit_workplace;
	PublicerAPI publicerAPI;
	UCEHandler handler = new UCEHandler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState,R.layout.acty_usercenter_publicer_edit);
		setAppTitle("编辑信息");
		initLayout();
		registerForContextMenu(edit_sex);
	}
	
	@OnClick(R.id.tv_userinfo_edit_save) public void save(){
		String sex = edit_sex_tv.getText().toString();
		String name =  edit_name.getText().toString();
		String work_place = edit_workplace.getText().toString();
		if (publicerAPI == null)
			publicerAPI = new PublicerAPI();
		handler.sendEmptyMessage(Constants.Start);
		final Message msg =  handler.obtainMessage();
		publicerAPI.updateProfile(name, sex, work_place, new JsonResponseHandler() {
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				try {
					if(obj.getString("code").equals("20000"))
					{
						msg.what = Constants.Finish;
						handler.sendMessage(msg);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg.what = Constants.Fail;
					handler.sendMessage(msg);
				}
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				
				msg.what = Constants.Fail;
				handler.sendMessage(msg);
			}
		});
	} 
	
	private void initLayout(){
		String name = getIntent().getStringExtra("name");
		String gender = getIntent().getStringExtra("gender");
		String workplace = getIntent().getStringExtra("workplace");
		edit_name.setText(name);
		edit_sex_tv.setText(gender);
		edit_workplace.setText(workplace);
	}
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		menu.add(0, 1, 0, "男");
		menu.add(0, 2, 0, "女");

		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo itemInfo = (AdapterContextMenuInfo)item.getMenuInfo();
		switch (item.getItemId()) {
		case 1:
			edit_sex_tv.setText("男");
			toast("男");
			break;
		case 2:
			edit_sex_tv.setText("女");
			toast("女");
			break;

		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
	
	class UCEHandler extends Handler{
		WaittingDialog waittingDialog;
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case Constants.Start:
				waittingDialog  = new WaittingDialog(UserCenter_Publicer_EditInfo.this);
				waittingDialog.show();
				break;
			case Constants.Finish:
				
				if(waittingDialog.isShowing())
					waittingDialog.dismiss();
				String sex = edit_sex_tv.getText().toString();
				String name =  edit_name.getText().toString();
				String work_place = edit_workplace.getText().toString();
				Intent backintent = new Intent(UserCenter_Publicer_EditInfo.this,UserCenter_Publicer.class);
				backintent.putExtra("name", name);
				backintent.putExtra("sex", sex);
				backintent.putExtra("work_place", work_place);
				UserCenter_Publicer_EditInfo.this.setResult(1001, backintent);
				UserCenter_Publicer_EditInfo.this.finish();
				break;
			case Constants.Fail:
				if (waittingDialog.isShowing())
					waittingDialog.dismiss();
				toast("保存失败，请检查您的网络");
				break;
			default:
				break;
			}
		}
	}
	


}
