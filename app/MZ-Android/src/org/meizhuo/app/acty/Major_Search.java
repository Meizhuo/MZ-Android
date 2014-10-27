package org.meizhuo.app.acty;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.meizhuo.adapter.CertificateTypesAdapter;
import org.meizhuo.adapter.KindAdapter;
import org.meizhuo.adapter.LevelAdapter;
import org.meizhuo.api.SearchAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
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
import butterknife.InjectView;
import butterknife.OnClick;




public class Major_Search extends BaseActivity{
	
	private static final String TAG  = "Major_Search";
	
	private static final String[] Certificate_Types = {"B(国家职业资格证书、计算机信息高新技术证书、专项职业能力证书)",""};
	
	List<Subsidy_certificateTypes>_certificateTypes;
	List<Subsidy_Kind> _kinds;
	List<Subsidy_Levels> _levels;
	
	
	CertificateTypesAdapter certificateTypesAdapter;
	KindAdapter kindAdapter;
	LevelAdapter levelAdapter;

	
	String certificate_Types_Selected_Item = "";
	String kind_Selected_Item = "";
	String level_Selected_Item = "";

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
		_certificateTypes =  new ArrayList<Subsidy_certificateTypes>();
		_kinds = new ArrayList<Subsidy_Kind>();
		_levels =  new ArrayList<Subsidy_Levels>();
		initData();
		
		
	}
	
	@OnClick(R.id.btn_search_content) public void write_search() {
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
		handler.sendEmptyMessage(Constants.Start);
		final Message msg = handler.obtainMessage();
		SearchAPI.getCertificateTypes(new JsonResponseHandler() {
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				try {
					if(obj.getString("code").equals("20000")){
						_certificateTypes = Subsidy_certificateTypes.create_by_jsonarray(obj.toString());
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				msg.what = Constants.Fail;
				handler.sendMessage(msg);
			}
		});
		
		SearchAPI.getkinds(new JsonResponseHandler() {
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				try {
					if(obj.getString("code").equals("20000")){
						_kinds = Subsidy_Kind.create_by_jsonarray(obj.toString());
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
			}
		});
		
		SearchAPI.getLevels(new JsonResponseHandler() {
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				try {
					if(obj.getString("code").equals("20000")){
						_levels = Subsidy_Levels.create_by_jsonarray(obj.toString());
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
	
	private void initCertificateAdapter() {
		 certificateTypesAdapter = new CertificateTypesAdapter(Major_Search.this, _certificateTypes);
//		ArrayAdapter<String>adapter;
//		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Certificate_Types);
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		certificate_sp.setAdapter(certificateTypesAdapter);
		certificate_sp.setOnItemSelectedListener(new CertificateTypesSpinnerListener());
		certificate_sp.setVisibility(View.VISIBLE);
		
	}
	
	private void initKindAdapter() {
		 kindAdapter = new KindAdapter(Major_Search.this, _kinds);
		kind_sp.setAdapter(kindAdapter);
		kind_sp.setOnItemSelectedListener(new KindSpinnerListener());
		kind_sp.setVisibility(View.VISIBLE);
	}
	 
	private void initLevelAdapter() {
		 levelAdapter = new LevelAdapter(Major_Search.this, _levels);
		
		
				level_sp.setAdapter(levelAdapter);
				level_sp.setOnItemSelectedListener(new levelSpinnerListener());
				level_sp.setVisibility(View.VISIBLE);
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
				initCertificateAdapter();
				initKindAdapter();
				initLevelAdapter();
				
				}
				
			}
		}
	 /**国家职业资格证书、计算机信息高新技术证书、专项职业能力证书**/
	 class CertificateTypesSpinnerListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			String selectItem = parent.getItemAtPosition(position).toString();
//			String selectedItem=((Subsidy_Kind)kindAdapter.getItem(position)).getKind();
//			String selectedItem=((Subsidy_certificateTypes)certificateTypesAdapter.getItem(position)).getCertificate_type();
			certificateTypes.setCertificate_type(selectItem);
		}

		@Override  
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	 }
	 /**  项目类别      **/
	 class KindSpinnerListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			String selectedItem=((Subsidy_Kind)kindAdapter.getItem(position)).getKind();
			kinds.setKind(kind_Selected_Item);
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
			String selectItem = ((Subsidy_Levels)levelAdapter.getItem(position)).getLevel();
		levels.setLevel(selectItem);
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		 
	 }
	 

}
