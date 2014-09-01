package org.meizhuo.adapter;

import java.util.ArrayList;
import java.util.List;

import org.meizhuo.app.R;
import org.meizhuo.model.MTrainingInstitution;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class InstitutionAdapter extends BaseAdapter {

	private List<MTrainingInstitution> data = new ArrayList<MTrainingInstitution>();
	private Context mContext;

	public InstitutionAdapter(Context context,
			List<MTrainingInstitution> institutions) {
		this.mContext = context;
		this.data = institutions;
	}

	@Override public int getCount() {
		return data.size();
	}

	@Override public Object getItem(int position) {
		return data.get(position);
	}

	@Override public long getItemId(int position) {
		return position;
	}

	@Override public View getView(int position, View convertView,
			ViewGroup parent) {
		ViewHolder h;
		if(convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.lv_institutions, null);
			h = new ViewHolder(convertView);
			convertView.setTag(h);
		}else{
			h  =(ViewHolder)convertView.getTag();
		}
		//TODO 处理UI....
		return convertView;
	}

	static class ViewHolder {
		@InjectView(R.id.tv_institution_name) TextView tv_name;
		@InjectView(R.id.tv_institution_description) TextView tv_description;
		@InjectView(R.id.iv_institution_img) ImageView iv_avater;

		public ViewHolder(View v) {
			ButterKnife.inject(this, v);
		}
	}

}
