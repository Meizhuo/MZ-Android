package org.meizhuo.app.acty;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONObject;
import org.meizhuo.adapter.InstitutionCourseAdapter;
import org.meizhuo.api.CourseAPI;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.imple.JsonResponseHandler;
import org.meizhuo.model.Course;
import org.meizhuo.utils.Constants;
import org.meizhuo.view.WaittingDialog;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnItemClick;

/**
 * 某机构详情里面的详细课程列表信息
 * 
 * @author Jayin
 * 
 */
public class CourseDetails extends BaseActivity implements OnRefreshListener , OnScrollListener {

	@InjectView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
	@InjectView(R.id.course_ListView) ListView course_lv;
	InstitutionCourseAdapter adapter;
	List<Course>data;
	String institution_id ;
	String subsidy_id = "";
	String page = "1";
	
	boolean hasMore = true, isloading = false;
	

	@Override protected void onCreate(Bundle savedInstanceState) {
		
	super.onCreate(savedInstanceState, R.layout.acty_institution_course);
		setAppTitle("课程信息");
		initData();
		initLayout();
	}
	
	private void initData(){
		Intent intent =  getIntent();
		institution_id = intent.getStringExtra("institution_id");
		data = new ArrayList<Course>();
		adapter = new InstitutionCourseAdapter(CourseDetails.this, data);
	}
	
	private void initLayout(){
		swipeRefreshLayout.setOnRefreshListener(this);
		
		swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_blue_light,
				android.R.color.holo_blue_bright,
				android.R.color.holo_blue_light);
		course_lv.setAdapter(adapter);
		course_lv.setOnScrollListener(this);
		onRefresh();
		
	}
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		CourseAPI.getCourseList(institution_id, subsidy_id,page,  new JsonResponseHandler() {
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				swipeRefreshLayout.setRefreshing(true);
			}
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				List<Course> list = Course.create_by_jsonarray(obj.toString());
				data.clear();
				data.addAll(list);
				adapter.notifyDataSetChanged();
				page = "1";
				if (list.size() < 10){
					hasMore = false;
				} else {
					hasMore = true;
				}
			}
			
			@Override
			public void onFaild(int errorType, int errorCode) {
				// TODO Auto-generated method stub
				toast("出错了，请检查你的网络设置 ");
				
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
		CourseAPI.getCourseList(institution_id, subsidy_id,page, new JsonResponseHandler() {
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				swipeRefreshLayout.setRefreshing(true);
			}
			
			@Override
			public void onOK(Header[] headers, JSONObject obj) {
				// TODO Auto-generated method stub
				List<Course>course = Course.create_by_jsonarray(obj.toString());
				data.addAll(course);
				adapter.notifyDataSetChanged();
				hasMore = true;
				if (obj.isNull("response")||course.size() <10)
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

	

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		if(swipeRefreshLayout.isRefreshing() || isloading)
			return ;
		if (firstVisibleItem + visibleItemCount >= totalItemCount && totalItemCount != 0 && hasMore){
			isloading = true;
			onLoadMore();
		}
	}
	
	@OnItemClick(R.id.course_ListView) public void item_click(int position){
		String name = data.get(position).getName();
		String start_time = data.get(position).getStart_time();
		String address = data.get(position).getAddress();
		String teacher = data.get(position).getTeacher();
		String introduction = data.get(position).getIntroduction();
		String cost = data.get(position).getCost();
		Intent intent = new Intent(CourseDetails.this, CourseDetails_OneCourse.class);
		intent.putExtra("name", name);
		intent.putExtra("start_time", start_time);
		intent.putExtra("address", address);
		intent.putExtra("teacher", teacher);
		intent.putExtra("introduction", introduction);
		intent.putExtra("cost", cost);
		startActivity(intent);
		
	}
	

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}

	

}
