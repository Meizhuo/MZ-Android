package org.meizhuo.app.acty;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONObject;
import org.meizhuo.adapter.InstitutionInfoAdapter;
import org.meizhuo.api.InstitutionAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.model.Institution;

import butterknife.InjectView;
import butterknife.OnItemClick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class InstitutionInfo_Search_lv extends BaseActivity implements OnRefreshListener , OnScrollListener{
	
	@InjectView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
	@InjectView(R.id.lv_institutionInfo) ListView lv_institution;
	List<Institution>data;
	InstitutionInfoAdapter adapte_lv;
	String status = "";
	String name = "";
	String type = "";
	String page = "1";
	boolean hasMore = true, isloading = false;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState,R.layout.lv_institution_search);
		setAppTitle("机构列表");
		initData();
		initLayout();
	}
	
	private void initData(){
		Intent intent =  getIntent();
		name  = intent.getStringExtra("name");
		data =  new ArrayList<Institution>();
		adapte_lv = new InstitutionInfoAdapter(InstitutionInfo_Search_lv.this, data);
	}
	
	private void initLayout(){
		swipeRefreshLayout.setOnRefreshListener(this);
		swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright, 
				android.R.color.holo_blue_light, 
				android.R.color.holo_blue_bright, 
				android.R.color.holo_blue_light);
		lv_institution.setAdapter(adapte_lv);
		lv_institution.setOnScrollListener(this);
		onRefresh();
	}
	

	@Override
	public void onRefresh() {
		isloading = true;
		InstitutionAPI.getInstitutionInfo(status, name, type, "1", new JsonResponseHandler() {
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				swipeRefreshLayout.setRefreshing(true);
			}
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				List<Institution> institution = Institution.create_by_jsonarray(obj.toString());
				data.clear();
				data.addAll(institution);
				adapte_lv.notifyDataSetChanged();
				if (institution.size() < 10) {
					hasMore = false;
				} else {
					hasMore = true;
				}
				if (institution.size() == 0){
					toast("该项无查询结果");
				}
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				toast("出错了，请检查你的网络");
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
		int i = Integer.parseInt(page);
		i+=1;
		page = String.valueOf(i);
		InstitutionAPI.getInstitutionInfo(status, name, type, page, new JsonResponseHandler() {
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				swipeRefreshLayout.setRefreshing(true);
				
			}
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				List<Institution>institutions = Institution.create_by_jsonarray(obj.toString());
				data.addAll(institutions);
				adapte_lv.notifyDataSetChanged();
				hasMore = true;
				if(obj.isNull("response") || institutions.size() < 10)
				{
					hasMore = false;
					toast("已经到达底部");
				}
					
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
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
		if (firstVisibleItem + visibleItemCount >= totalItemCount && totalItemCount !=0 && hasMore){
			isloading = true;
			onLoadMore();
		}
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}
	
	@OnItemClick(R.id.lv_institutionInfo) public void item_click(int position){
		Intent intent = new Intent(this,InstitutionInfo_Details.class);
		intent.putExtra("uid", data.get(position).getUid());
		startActivity(intent);
	}


}
