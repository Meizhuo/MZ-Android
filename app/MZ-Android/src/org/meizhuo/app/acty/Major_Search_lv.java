package org.meizhuo.app.acty;

import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;
import org.meizhuo.adapter.MajorSearchAdapter;
import org.meizhuo.api.SearchAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.model.Subsidy;
import org.meizhuo.utils.Constants;
import org.meizhuo.view.WaittingDialog;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.DialerFilter;
import android.widget.ListView;
import butterknife.InjectView;

import com.google.gson.Gson;

public class Major_Search_lv extends BaseActivity{
	 private static final String TAG = "Major_Search_lv";
	 @InjectView(R.id.search_ListView) ListView search_lv;
	SearchAPI searchAPI;
	Subsidy subsidy;
	MajorSearchAdapter adapter;
	MSHandler handler = new MSHandler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState, R.layout.lv_major_search);
		setAppTitle("查询列表");
		initData();
	}
	
	private void initData(){
		if(searchAPI == null)
			searchAPI = new SearchAPI();
		String kind = getIntent().getStringExtra("kind");
		String level =  getIntent().getStringExtra("level");
		String certificate_type = getIntent().getStringExtra("certificate_type");
		handler.sendEmptyMessage(Constants.Start);
		final Message msg =  handler.obtainMessage();
		searchAPI.getSubsidy(kind, level, certificate_type, new JsonResponseHandler() {
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				msg.what = Constants.Finish;
				msg.obj = obj;
				handler.sendMessage(msg);
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				toast("" + errorCode);
			}
		});
	}
	/***
	 * 	if (subsidy == null)
					subsidy =new Subsidy();
				List<Subsidy> subsidy = Subsidy.create_by_jsonarray(obj.toString());
				Log.i(TAG, "返回的补贴项目" + subsidy);
	 * @author Jason
	 *
	 */
	
	class MSHandler extends Handler {
		WaittingDialog waittingDialog;
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case Constants.Start:
				waittingDialog =  new WaittingDialog(Major_Search_lv.this);
				waittingDialog.show();
				
				break;
			case Constants.Finish: 
				if(waittingDialog.isShowing())
					waittingDialog.dismiss();
				waittingDialog = null;
				JSONObject obj = (JSONObject)msg.obj;
				if (subsidy == null)
					subsidy =new Subsidy();
				List<Subsidy> subsidy = Subsidy.create_by_jsonarray(obj.toString());
				Log.i(TAG, "返回的补贴项目" + subsidy);
				adapter =  new MajorSearchAdapter(Major_Search_lv.this, subsidy);
				search_lv.setAdapter(adapter);
			}
		}
	}
	
	

}
