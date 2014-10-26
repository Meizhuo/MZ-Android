package org.meizhuo.adapter;

import java.util.List;

import org.meizhuo.app.R;
import org.meizhuo.model.Subsidy_certificateTypes;

import butterknife.ButterKnife;
import butterknife.InjectView;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CertificateTypesAdapter extends BaseAdapter {
	
	private List<Subsidy_certificateTypes> mList;
	private Context mContext;

	public CertificateTypesAdapter(Context context, List<Subsidy_certificateTypes>list) {
		// TODO Auto-generated constructor stub
		mContext = context;
		mList = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
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
		if(convertView == null) 
		{
			convertView = LayoutInflater.from(mContext).inflate(R.layout.tv_spinner, null);
			h =  new ViewHolder(convertView);
			convertView.setTag(h);
		}else{
			h = (ViewHolder)convertView.getTag();
		}
//		h.tv_spinner.setText(mList.get(position).getCertificate_type());
		h.tv_spinner.setText("B(国家职业资格证书、计算机信息高新技术证书、专项职业能力证书)");
		return convertView;
	}
	
	static class ViewHolder {
		
		@InjectView(R.id.tv_spinner) TextView tv_spinner;
		
		public ViewHolder(View v) {
			// TODO Auto-generated constructor stub
			ButterKnife.inject(this, v);
		}
	}
	
	

}
