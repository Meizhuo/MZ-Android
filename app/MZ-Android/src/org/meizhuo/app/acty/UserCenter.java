package org.meizhuo.app.acty;

import org.apache.http.Header;
import org.json.JSONObject;
import org.meizhuo.api.PublicerAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.model.ErrorCode;
import org.meizhuo.model.Publicer;

import butterknife.InjectView;
import butterknife.OnClick;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 用戶中心
 * 
 * @author Lenovo
 * 
 */
public class UserCenter extends BaseActivity {
	
	private static final String TAG = "UserCenter";
	
	@InjectView(R.id.mz_usercenter_name_tv) TextView name_tv;
	@InjectView(R.id.mz_usercenter_name_et) EditText name_et;
	@InjectView(R.id.mz_usercenter_sex_tv) TextView sex_tv;
	@InjectView(R.id.mz_usercenter_sex_et) EditText sex_et;
	@InjectView(R.id.mz_usercenter_workplace_tv) TextView workplace_tv;
	@InjectView(R.id.mz_usercenter_workplace_et) EditText workplace_et;
	@InjectView(R.id.tv_userinfo_edit_ok) TextView ok_et;
	@InjectView(R.id.tv_userinfo_edit_cancel) TextView cancel_et;
	@InjectView(R.id.tv_userinfo_edit) TextView edit_tv;  // 右上角的编辑信息
	private Publicer publicer;
	
	
	PublicerAPI publicApi;

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState, R.layout.acty_usercenter);
		setAppTitle("用户中心");
		publicApi = new PublicerAPI();
		initData();
		initLayout();

	}
	
	
	
	@OnClick(R.id.tv_userinfo_edit) public void editinfo() {
		change2edit();
		name_et.setText(publicer.getName().toString());
		sex_et.setText(publicer.getGender().toString());
		workplace_et.setText(publicer.getWorkPlace().toString());
		
	}
	
	@OnClick(R.id.tv_userinfo_edit_ok) public void saveinfo() {
		change2normal();
		name_tv.setText(name_et.getText().toString());
		sex_tv.setText(sex_et.getText().toString());
		workplace_tv.setText(workplace_et.getText().toString());
		String nickname = name_et.getText().toString().trim();
		String sex = sex_et.getText().toString().trim();
		String workplace = workplace_et.getText().toString().trim();
		publicApi.updateProfile(nickname,sex,workplace,
				new JsonResponseHandler() {
			
					
					@Override
					public void onOK(Header[] headers, JSONObject obj) {
						// TODO Auto-generated method stub
						toast("更新用户资料成功");
						Log.i(TAG, "更新成功返回:" + obj);
					}
					
					@Override
					public void onFaild(int errorType, int errorCode) {
						// TODO Auto-generated method stub
						toast("更新资料失败" + errorCode);
					}
				});
	}
	@OnClick(R.id.tv_userinfo_edit_cancel) public void cancel() {
		change2normal();
		name_tv.setText(publicer.getName().toString());
		sex_tv.setText(publicer.getGender().toString());
		workplace_tv.setText(publicer.getWorkPlace().toString());
	}
	
	
	protected void initData() {
		String sex = getIntent().getStringExtra("sex");
		String nickname = getIntent().getStringExtra("nickname");
		String work_place = getIntent().getStringExtra("work_place");
		publicer = new Publicer();
		publicer.setName(nickname);
		publicer.setGender(sex);
		publicer.setWorkPlace(work_place);
	}
	
	private void initLayout() {
		name_tv.setText(publicer.getName().toString());
		sex_tv.setText(publicer.getGender().toString());
		workplace_tv.setText(publicer.getWorkPlace().toString());
	}
	
	
	/**切换到编辑界面*/
	private void change2edit() {
		edit_tv.setVisibility(View.GONE);
		ok_et.setVisibility(View.VISIBLE);
		cancel_et.setVisibility(View.VISIBLE);
		
		name_tv.setVisibility(View.GONE);
		name_et.setVisibility(View.VISIBLE);
		
		sex_tv.setVisibility(View.GONE);
		sex_et.setVisibility(View.VISIBLE);
		
		workplace_tv.setVisibility(View.GONE);
		workplace_et.setVisibility(View.VISIBLE);
	}
	/**切换到正常页面*/
	private void change2normal() {
		edit_tv.setVisibility(View.VISIBLE);
		ok_et.setVisibility(View.GONE);
		cancel_et.setVisibility(View.GONE);
		
		name_tv.setVisibility(View.VISIBLE);
		name_et.setVisibility(View.GONE);
		
		sex_tv.setVisibility(View.VISIBLE);
		sex_et.setVisibility(View.GONE);
		
		workplace_tv.setVisibility(View.VISIBLE);
		workplace_et.setVisibility(View.GONE);
		
	}
	

}
