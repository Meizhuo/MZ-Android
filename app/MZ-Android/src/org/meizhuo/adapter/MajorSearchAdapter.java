package org.meizhuo.adapter;

import java.util.List;

import org.meizhuo.app.R;
import org.meizhuo.model.Subsidy;

import butterknife.ButterKnife;
import butterknife.InjectView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MajorSearchAdapter extends BaseAdapter{
	
	private Context mContext;
	private List<Subsidy>data;

	public MajorSearchAdapter(Context context, List<Subsidy>list) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.data = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return data.get(arg0);
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
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.lv_major_search_lv, null);
			h = new ViewHolder(convertView);
			convertView.setTag(h);
		} else {
			h = (ViewHolder)convertView.getTag();
		}
		h.tv_certificate_type.setText(data.get(position).getCertificate_type());
		h.tv_level.setText(data.get(position).getLevel());
		h.tv_money.setText(data.get(position).getMoney());
		return convertView;
	}
	
	static class ViewHolder{
		@InjectView(R.id.search_tv_certificate_type) TextView tv_certificate_type;
		@InjectView(R.id.search_tv_level) TextView tv_level;
		@InjectView(R.id.search_tv_money) TextView tv_money;
		
		public ViewHolder(View v) {
			// TODO Auto-generated constructor stub
			ButterKnife.inject(this, v);
		}
		
	} 

}
