package org.meizhuo.app.acty;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.meizhuo.adapter.ImagePagerAdapter;
import org.meizhuo.adapter.InstitutionInfoAdapter;
import org.meizhuo.adapter.ImagePagerAdapter.OnItemClickListener;
import org.meizhuo.adapter.ImagePagerAdapter.OnPositionChangeListener;
import org.meizhuo.api.AdvertisementAPI;
import org.meizhuo.api.InstitutionAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.model.Advertisement;
import org.meizhuo.model.Institution;
import org.meizhuo.utils.Constants;
import org.meizhuo.utils.EditTextUtils;
import org.meizhuo.view.AutoScrollViewPager;
 
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * 培训机构列表
 * 
 * @author Lenovo
 * 
 */
public class InstitutionInfo extends BaseActivity implements OnRefreshListener, OnScrollListener{
	private static final String TAG = "InstitutionInfo";
	@InjectView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
	@InjectView(R.id.lv_institutionInfo) ListView lv;
	@InjectView(R.id.autoscrollviewpage) AutoScrollViewPager viewPager;
	@InjectView(R.id.tv_app_title) TextView appTitle;
	@InjectView(R.id.iv_app_icon_back) ImageView backicon;
	@InjectView(R.id.tv_institution_search) TextView tv_institution_search;
	@InjectView(R.id.tv_ad_title) TextView tv_ad_title;
	InstitutionInfoAdapter adapter_lv;
	ImagePagerAdapter adapter_imagepage;
	List<Institution>data;
//	List<Advertisement>imageIdList;
	List<Advertisement>ad;
	
//	List<String> ad_list;
//	List<ImageView>imageIdList;
	String name = "";
	String type = "";
	String page = "1";
	boolean hasMore = true, isloading = false;
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState,R.layout.acty_institutioninfo);
		setAppTitle("培训机构信息");
		initData();
		initLayout();
	}
	
	private void initData(){
		data = new ArrayList<Institution>();
		adapter_lv = new InstitutionInfoAdapter(this, data);
	}
	
	private void initLayout(){

		viewPager.setInterval(3000);
		viewPager.startAutoScroll();
		
		final Handler h = new Handler(){
			public void handleMessage(android.os.Message msg) {
				switch (msg.what) {
				case Constants.Finish:
					JSONObject obj1 = (JSONObject)msg.obj;
					ad = Advertisement.create_by_jsonarray(obj1.toString());
					adapter_imagepage = new ImagePagerAdapter(InstitutionInfo.this, ad, null);
					
					adapter_imagepage.setOnPositionChangeListener(new OnPositionChangeListener() {
						
						
						
						@Override
						public void OnPositionChange(final int position) {
							// TODO Auto-generated method stub
							int realposition = position;
							if(position-1<0){
								realposition=ad.size()-1;
							}else{
								realposition--;
							}
							tv_ad_title.setText(ad.get(realposition).getDescription());
						}
					});
					
					adapter_imagepage.setOnItemClickListener(new OnItemClickListener() {

						@Override public void onItemClick(int position, View view) {
							// TODO Auto-generated method stub
							Intent intent =  new Intent(InstitutionInfo.this, Main_Advertise.class);
							intent.putExtra("url", ad.get(position).getUrl());
							intent.putExtra("description", ad.get(position).getDescription());
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
					if(obj.getString("code").equals("20000")){
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
				
			}
		});
		

		
		swipeRefreshLayout.setOnRefreshListener(this);
		swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright, 
				android.R.color.holo_blue_light, 
				android.R.color.holo_blue_bright, 
				android.R.color.holo_blue_light);
		lv.setAdapter(adapter_lv);
		lv.setOnScrollListener(this);
		onRefresh();
		
	}
	/**
	 @InjectView(R.id.tv_institution_search) TextView tv_institution_search;
	 */
	@OnClick(R.id.tv_institution_search) public void search_institution(){
		LayoutInflater inflater = LayoutInflater.from(InstitutionInfo.this);
		View dialogView = inflater.inflate(R.layout.dialog_institution_search, null);
		final EditText et_search_institution =(EditText) dialogView.findViewById(R.id.et_search_institution);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("输入机构名称搜索");
		builder.setView(dialogView);
		builder.setPositiveButton("搜索", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String input = EditTextUtils.getText(et_search_institution);
				Intent intent = new Intent(InstitutionInfo.this, InstitutionInfo_Search_lv.class);
				intent.putExtra("name", input);
				startActivity(intent);
				
			}
		});
		builder.setNegativeButton("取消", null);
		AlertDialog dialog =  builder.create();
		dialog.show();
	}
	



	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		isloading = true;
		InstitutionAPI.getInstitutionInfo( name, type, "1", new JsonResponseHandler() {
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				swipeRefreshLayout.setRefreshing(true);
			}
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				Log.i(TAG, obj.toString());
				List<Institution> institution = Institution.create_by_jsonarray(obj.toString());
				data.clear();
				data.addAll(institution);
				adapter_lv.notifyDataSetChanged();
				if (institution.size() < 10) {
					hasMore = false;
				} else {
					hasMore = true;
				}
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				Log.i(TAG, "无法获取数据"+errorCode);
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
		InstitutionAPI.getInstitutionInfo( name, type, page, new JsonResponseHandler() {
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				swipeRefreshLayout.setRefreshing(true);
				
			}
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				Log.i(TAG, "" + obj);
				List<Institution>institutions = Institution.create_by_jsonarray(obj.toString());
				data.addAll(institutions);
				adapter_lv.notifyDataSetChanged();
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
				Log.i(TAG, "出错了" + errorCode);
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
			return;
		if (firstVisibleItem + visibleItemCount >= totalItemCount && totalItemCount != 0 && hasMore){
			isloading = true;
			onLoadMore();
		
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}
	
	@OnItemClick(R.id.lv_institutionInfo) public void item_click(int position) {
		Intent intent = new Intent(this,InstitutionInfo_Details.class);
		intent.putExtra("uid", data.get(position).getUid());
		startActivity(intent);
	}

}
