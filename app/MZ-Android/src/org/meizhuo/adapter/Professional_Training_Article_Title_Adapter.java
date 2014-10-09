package org.meizhuo.adapter;

import java.util.Arrays;
import java.util.List;

import org.meizhuo.app.R;
import org.meizhuo.model.DocumentInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class Professional_Training_Article_Title_Adapter extends BaseAdapter {

	private List<DocumentInfo> mData;
	private Context mContext;

	public Professional_Training_Article_Title_Adapter(Context context, List<DocumentInfo> list) {
		mData = list;
		mContext = context;
	}

	@Override public int getCount() {
		return mData.size();
	}

	@Override public Object getItem(int position) {
		return mData.get(position);
	}

	@Override public long getItemId(int position) {
		return position;
	}

	@Override public View getView(int position, View convertView,
			ViewGroup parent) {
		ViewHolder h;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.lv_professional_training_article_item, null);
			h = new ViewHolder(convertView);
			convertView.setTag(h);
		} else {
			h = (ViewHolder) convertView.getTag();
		}
		h.tv_name.setText(mData.get(position).getTitle().toString());
		return convertView;
	}

	static class ViewHolder {
		@InjectView(R.id.tv_name) TextView tv_name;

		public ViewHolder(View v) {
			ButterKnife.inject(this, v);
		}
	}
}
