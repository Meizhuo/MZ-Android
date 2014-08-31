package org.meizhuo.adapter;

import java.util.Arrays;
import java.util.List;

import org.meizhuo.app.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InstitutionConsultAdapter extends BaseAdapter{
	
	private List<String> mData;
	private Context mContext;
	public InstitutionConsultAdapter(Context context, String[] names) {
		// TODO Auto-generated constructor stub
		mData = Arrays.asList(names);
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.lv_publicconsult, null);
			h = new ViewHolder(convertView);
			convertView.setTag(h);
		}else
		{
			h = (ViewHolder)convertView.getTag();
		}
		h.tv_name.setText(mData.get(position));
		return convertView;
	}
	static class ViewHolder {
		@InjectView(R.id.tv_name) TextView tv_name;
		
		public ViewHolder(View v) {
			// TODO Auto-generated constructor stub
			ButterKnife.inject(this, v);
		}
	}

}
