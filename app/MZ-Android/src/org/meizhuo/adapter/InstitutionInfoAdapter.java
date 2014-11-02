package org.meizhuo.adapter;

import java.util.List;

import org.meizhuo.app.R;
import org.meizhuo.model.Institution;

import butterknife.ButterKnife;
import butterknife.InjectView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InstitutionInfoAdapter extends BaseAdapter{
	
	private List<Institution> mData;
	private Context mContext;
	public InstitutionInfoAdapter(Context context, List<Institution>list) {
		// TODO Auto-generated constructor stub
		mData = list;
		mContext=context;
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
		if (convertView == null)
		{
			convertView = LayoutInflater.from(mContext).inflate(R.layout.lv_institution_info_item, null);
			h = new ViewHolder(convertView);
			convertView.setTag(h);
		}else
		{
			h = (ViewHolder)convertView.getTag();
		}
		h.tv_name.setText(mData.get(position).getName().toString());
		
		if(mData.get(position).getAddress() == null || mData.get(position).getAddress().equals("")){
			h.tv_addr.setText("");
		}else{
			h.tv_addr.setText(mData.get(position).getAddress().toString());
		}
		if(mData.get(position).getType() == null||mData.get(position).getType().equals("")){
			h.tv_type.setText("");
		}else{
			h.tv_type.setText(mData.get(position).getType().toString());
		}
		if(mData.get(position).getTraining_scope()==null || mData.get(position).getTraining_scope().equals("")){
			h.tv_description.setText("");
		}else{
			h.tv_description.setText(mData.get(position).getTraining_scope().toString());
		}
		return convertView;
	}
	static class ViewHolder {
		@InjectView(R.id.tv_institution_name) TextView tv_name;
		@InjectView(R.id.tv_institution_addr) TextView tv_addr;
		@InjectView(R.id.tv_institution_type) TextView tv_type;
		@InjectView(R.id.tv_institution_description) TextView tv_description;
		
		public ViewHolder(View v) {
			// TODO Auto-generated constructor stub
			ButterKnife.inject(this, v);
		}
	}

}
