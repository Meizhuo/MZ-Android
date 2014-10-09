package org.meizhuo.app.acty;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONObject;
import org.meizhuo.api.SearchAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.model.Subsidy;
import org.meizhuo.model.Subsidy_Kind;
import org.meizhuo.model.Subsidy_Levels;
import org.meizhuo.model.Subsidy_certificateTypes;
import org.meizhuo.utils.Constants;
import org.meizhuo.utils.EditTextUtils;
import org.meizhuo.view.WaittingDialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;

import com.google.gson.Gson;



public class Major_Search extends BaseActivity{
	private static final String[] certificate_Types = {"B(国家职业资格证书、计算机信息高新技术证书、专项职业能力证书)",""};
	private static final String[] kind = {"B1(专项职业能力)","B2","B3","B4","B5","B6",""};
	private static final String[] level = {"B1-1","B1-2","B1-3","B1-4","B2-1(初、中级)","B2-2(高级)","B3-1(初、中级)","B3-2(高级)","B3-2(技师、高级技师)","B4-1(初、中级)","B4-2(高级)","B5-3(技师、高级技师)","B6-1(初、中级)","B6-2(高级)","B6-3(技师、高级技师)",""};
	private static final String TAG  = "Major_Search";

	/**项目类别*/
	@InjectView(R.id.project_sp) Spinner kind_sp;
	/**证书类别*/
	@InjectView(R.id.certificate_sp) Spinner certificate_sp;
	/**等级*/
	@InjectView(R.id.lever_sp) Spinner level_sp;
	
	SearchAPI searchAPI;
	Subsidy_certificateTypes certificateTypes;
	Subsidy_Kind kinds;
	Subsidy_Levels levels;
	MSHandler handler =  new MSHandler();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState,R.layout.acty_major_search);
		setAppTitle("搜索");
		certificateTypes = new Subsidy_certificateTypes();
		kinds = new Subsidy_Kind();
		levels = new Subsidy_Levels();
		initKindAdapter();
		initLevelAdapter();
		initCertificateAdapter();
	}
	
	@OnClick(R.id.write_search_tv) public void write_search() {
		LayoutInflater inflater = LayoutInflater.from(Major_Search.this);
		View dialogView = inflater.inflate(R.layout.write_search, null);
		final EditText write_search_et =(EditText) dialogView.findViewById(R.id.write_search_et);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("输入资格名称搜索");
		builder.setView(dialogView);
		builder.setPositiveButton("搜索", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String input = EditTextUtils.getText(write_search_et);
				String kind = "";
				String level = "";
				String certificate_type = "";
				Intent intent = new Intent(Major_Search.this, Major_Search_lv.class);
				intent.putExtra("title", input);
				intent.putExtra("kind", kind);
				intent.putExtra("level", level);
				intent.putExtra("certificate_type", certificate_type);
				startActivity(intent);
				
			}
		});
		builder.setNegativeButton("取消", null);
		AlertDialog dialog =  builder.create();
		dialog.show();
	}
	
	@OnClick(R.id.btn_search) public void search(){
		
		
		String kind = kinds.getKind();
		String level = levels.getLevel();
		String certificate_type = certificateTypes.getCertificate_type();
		String title = "";
		Intent intent = new Intent(Major_Search.this, Major_Search_lv.class);
		intent.putExtra("kind", kind);
		intent.putExtra("level", level);
		intent.putExtra("certificate_type", certificate_type);
		intent.putExtra("title", title);
		startActivity(intent);
	}
	
	private void initData(){
		if (searchAPI == null)
			searchAPI = new SearchAPI();
		handler.sendEmptyMessage(Constants.Start);
		final Message msg = handler.obtainMessage();
		searchAPI.getCertificateTypes(new JsonResponseHandler() {
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				Log.i(TAG, "证书类别");
				msg.what = Constants.Finish;
				msg.obj = obj;
				msg.arg1 = 1;
				handler.sendMessage(msg);
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				toast("错误码" + errorCode);
			}
		});
	}
	
	private void initKindAdapter() {
		ArrayAdapter<String>adapter;
		adapter = new ArrayAdapter<String>(Major_Search.this,android.R.layout.simple_spinner_item, kind);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		kind_sp.setAdapter(adapter);
		kind_sp.setOnItemSelectedListener(new KindSpinnerListener());
		kind_sp.setVisibility(View.VISIBLE);
	}
	
	private void initLevelAdapter() {
		ArrayAdapter<String>adapter;
		adapter = new ArrayAdapter<String>(Major_Search.this, android.R.layout.simple_spinner_item, level);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		level_sp.setAdapter(adapter);
		level_sp.setOnItemSelectedListener(new levelSpinnerListener());
		level_sp.setVisibility(View.VISIBLE);
	}
	
	private void initCertificateAdapter() {
		ArrayAdapter<String> adapter;
		adapter = new ArrayAdapter<String>(Major_Search.this, android.R.layout.simple_spinner_item, certificate_Types);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		certificate_sp.setAdapter(adapter);
		certificate_sp.setOnItemSelectedListener(new CertificateTypesSpinnerListener());
		certificate_sp.setVisibility(View.VISIBLE);
	}
	
	 class MSHandler extends Handler{
		 WaittingDialog waittingDialog;
		 
		 @Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			 switch (msg.what) {
			case Constants.Start:
				waittingDialog = new WaittingDialog(Major_Search.this);
				waittingDialog.setText("正在加载数据");
				waittingDialog.show();
				break;
			case Constants.Finish:
				if(waittingDialog.isShowing())
				waittingDialog.dismiss();
				waittingDialog = null;
				if (msg.arg1 == 1){
				JSONObject obj = (JSONObject)msg.obj;
				ArrayList<Subsidy_certificateTypes>list = Subsidy_certificateTypes.create_by_jsonObject(obj);
				Log.i(TAG, "证书数据" + list);
				ArrayList<String>lists = new ArrayList<String>();
				for (int i = 0 ;i < list.size();i++){
					lists.add(list.get(i).getCertificate_type());
				}
				
				}
				
			}
		}
	 }
	 
	 class CertificateTypesSpinnerListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			String selectItem = parent.getItemAtPosition(position).toString();
			certificateTypes.setCertificate_type(selectItem);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
		 
	 }
	 /**国家职业资格证书、计算机信息高新技术证书、专项职业能力证书**/
	 class KindSpinnerListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			String selectItem = parent.getItemAtPosition(position).toString();
			kinds.setKind(selectItem);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
		 
	 }
	 class levelSpinnerListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			String selectItem = parent.getItemAtPosition(position).toString();
			levels.setLevel(selectItem);
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		 
	 }

}
