package org.meizhuo.adapter;

import java.util.List;

import org.meizhuo.app.R;
import org.meizhuo.model.Course;

import butterknife.ButterKnife;
import butterknife.InjectView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InstitutionCourseAdapter extends BaseAdapter{

	Context mContext;
	List<Course> mData;
	
	public InstitutionCourseAdapter(Context context, List<Course>list) {
		// TODO Auto-generated constructor stub
		mContext = context;
		mData = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder h;
		if (convertView == null){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.lv_instition_course_details, null);
			h = new ViewHolder(convertView);
			convertView.setTag(h);
		}else{
			h = (ViewHolder)convertView.getTag();
		}
		h.course_name.setText(mData.get(position).getName().toString());
		h.course_time.setText(mData.get(position).getStart_time().toString());
		h.course_subsidy.setText(mData.get(position).getCost().toString());
		h.course_address.setText(mData.get(position).getAddress().toString());
		return convertView;
	}
	
	static class ViewHolder{
		
		@InjectView(R.id.tv_institution_course_name) TextView course_name;
		@InjectView(R.id.tv_institution_course_time) TextView course_time;
		@InjectView(R.id.tv_institution_course_subsidy) TextView course_subsidy;
		@InjectView(R.id.tv_institution_course_address) TextView course_address;

		public ViewHolder(View v) {
			// TODO Auto-generated constructor stub
			ButterKnife.inject(this, v);
		}
		
	}

}
