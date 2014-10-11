package org.meizhuo.app.acty;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
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
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.DialerFilter;
import android.widget.ListView;
import butterknife.InjectView;

import com.google.gson.Gson;

public class Major_Search_lv extends BaseActivity implements OnRefreshListener, OnScrollListener{
	 private static final String TAG = "Major_Search_lv";
	 @InjectView(R.id.search_ListView) ListView search_lv;
	 @InjectView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
	SearchAPI searchAPI;
	Subsidy subsidy;
	MajorSearchAdapter adapter;
	String kind = null,  level = null , certificate_type = null,title = null;
	List<Subsidy> data;
	String page = "1";
	String maxPage = "3";
	boolean hasMore = true, isloading = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState, R.layout.lv_major_search);
		setAppTitle("查询列表");
		initData();
		initLayout();
	}
	
	private void initData(){
		data =  new ArrayList<Subsidy>();
		adapter = new MajorSearchAdapter(this, data);
	}
	
	private void initLayout() {
		swipeRefreshLayout.setOnRefreshListener(this);
		swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_blue_light,
				android.R.color.holo_blue_bright,
				android.R.color.holo_blue_light);
		search_lv.setAdapter(adapter);
		search_lv.setOnScrollListener(this);
		onRefresh();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == android.R.id.home){
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		isloading = true;
			if(!(getIntent().getStringExtra("kind")==null && getIntent().getStringExtra("kind").equals(""))){
				kind = getIntent().getStringExtra("kind");
			}else{
				kind = "";
			}
			if(!(getIntent().getStringExtra("level")==null && getIntent().getStringExtra("level").equals(""))){
				level = getIntent().getStringExtra("level");
			}else{
				level = "";
			}
			if(!(getIntent().getStringExtra("certificate_type")==null && getIntent().getStringExtra("certificate_type").equals(""))){
				certificate_type = getIntent().getStringExtra("certificate_type");
			}else{
				certificate_type = "";
			}
			if(!(getIntent().getStringExtra("title")==null && getIntent().getStringExtra("title").equals(""))){
				title = getIntent().getStringExtra("title");
			}else{
				title = "";
			}
		if(searchAPI == null)
			searchAPI = new SearchAPI();
		searchAPI.getSubsidy(certificate_type, title, kind, level, "1", new JsonResponseHandler() {
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				swipeRefreshLayout.setRefreshing(true);
			}
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				Log.i(TAG, obj.toString());
				if (subsidy == null)
					subsidy =new Subsidy();
				List<Subsidy> subsidy = Subsidy.create_by_jsonarray(obj.toString());
				data.clear();
				data.addAll(subsidy);
				adapter.notifyDataSetChanged();
				page = "1";
				if (subsidy.size() < 10) {
					hasMore = false;
				} else {
					hasMore = true;
				}
				if (subsidy.size() == 0){
					toast("该项查询无结果!");
				}
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				Log.e(TAG, "无法获取数据" + errorCode);
				toast("出错了，请检查你的网络设置!");
			}
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				swipeRefreshLayout.setRefreshing(false);
				isloading = false;
			}
		});
		
		
	}
	
	private void onLoadMore(){
		searchAPI = new SearchAPI();
		int i = Integer.parseInt(page);
		i+=1;
		page = String.valueOf(i);
		searchAPI.getSubsidy(certificate_type, title, kind, level, page, new JsonResponseHandler() {
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				swipeRefreshLayout.setRefreshing(true);
			}
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				List<Subsidy>subsidy = Subsidy.create_by_jsonarray(obj.toString());
				data.addAll(subsidy);
				adapter.notifyDataSetChanged();
				hasMore = true;
				if(obj.isNull("response") || subsidy.size() < 10)
				{
					hasMore = false;
					toast("已经到达底部");
				}
					
			}
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				Log.i(TAG, "出错鸟");
				toast("出错了");
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				swipeRefreshLayout.setRefreshing(false);
				isloading = false;
			}
		});
		
	}
	
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		if (swipeRefreshLayout.isRefreshing() || isloading)
		return ;
		if(firstVisibleItem + visibleItemCount >= totalItemCount && totalItemCount != 0 && hasMore){
			isloading = true;
			onLoadMore();
		
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}


	
	
	

}
