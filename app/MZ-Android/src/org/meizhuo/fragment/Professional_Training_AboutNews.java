package org.meizhuo.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.meizhuo.adapter.Professional_Training_Article_Title_Adapter;
import org.meizhuo.api.ArticleAPI;
import org.meizhuo.app.R;
import org.meizhuo.app.acty.Professional_Article;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.model.DocumentInfo;

import com.google.gson.JsonObject;

import butterknife.InjectView;
import butterknife.OnItemClick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
/**
 * 相关新闻
 * @author Jason
 *
 */
public class Professional_Training_AboutNews extends BaseFragment implements OnRefreshListener, OnScrollListener{
	private static final String TAG = "Professional_Training_AboutNews";

	@InjectView(R.id.lv) ListView lv;
	@InjectView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
	Professional_Training_Article_Title_Adapter adatper_title;
	/**文档列表 其实也包括了内容*/
	List<DocumentInfo>data;
	String category_id = "1",title = "",content = "", page = "1";
	boolean hasMore = true, isloading = false;
	
	
	@Override public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState,R.layout.fm_professional_training_about_news);
		initData();
		initLayout();
		onRefresh();
		return contentView;
	}
	
	private void initData(){
		data = new ArrayList<DocumentInfo>();
		adatper_title = new Professional_Training_Article_Title_Adapter(getActivity(), data);
	}
	
	private void initLayout(){
		swipeRefreshLayout.setOnRefreshListener(this);
		swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_blue_light,
				android.R.color.holo_blue_bright,
				android.R.color.holo_blue_light);
		lv.setAdapter(adatper_title);
		lv.setOnScrollListener(this);
		
	}
	

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		ArticleAPI.getArticleInfo("1", title, content, page, new JsonResponseHandler() {
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				swipeRefreshLayout.setRefreshing(true);
			}
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				List<DocumentInfo>documentInfos = DocumentInfo.create_by_jsonarray(obj.toString());
				data.clear();
				data.addAll(documentInfos);
				adatper_title.notifyDataSetChanged();
				page = "1";
				if (documentInfos.size() < 10) {
					hasMore = false;
				} else {
					hasMore = true;
				}
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
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
		ArticleAPI.getArticleInfo(category_id, title, content, page, new JsonResponseHandler() {
			
			@Override
			public void onStart() {
				swipeRefreshLayout.setRefreshing(true);
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				Log.i(TAG, obj.toString());
				List<DocumentInfo> documentInfos =  DocumentInfo.create_by_jsonarray(obj.toString());
				data.addAll(documentInfos);
				adatper_title.notifyDataSetChanged();
				if (obj.isNull("response") || documentInfos.size() < 10)
					hasMore = false;
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
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
	
	@OnItemClick(R.id.lv) public void item_click(int position) {
		Intent intent =  new Intent(getActivity(), Professional_Article.class);
		intent.putExtra("id", data.get(position).getCategory_id());
		intent.putExtra("id", data.get(position).getId());
		startActivity(intent);
	}
	


	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		if (swipeRefreshLayout.isRefreshing() || isloading)
			return ;
		if (firstVisibleItem + visibleItemCount >= totalItemCount && totalItemCount != 0 && hasMore){
			isloading = true;
			onLoadMore();
		}
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}


	

}
