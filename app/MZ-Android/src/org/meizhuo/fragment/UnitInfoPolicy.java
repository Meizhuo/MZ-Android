package org.meizhuo.fragment;

import org.meizhuo.adapter.UnitinfoAdapter;
import org.meizhuo.app.R;
import org.meizhuo.app.acty.UnitInfoView;

import butterknife.InjectView;
import butterknife.OnItemClick;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class UnitInfoPolicy extends BaseFragment {
	@InjectView(R.id.lv) ListView lv;
	UnitinfoAdapter adatper_unitinfo;
	@Override public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState,R.layout.fm_unitinfopolicy);
		

		String[] names = new String[] { "中心概况", "职业训练法律法规", "职业训练通知",
				"职业训练法律法规", "职业训练通知", "职业训练法律法规", "职业训练通知" };
		adatper_unitinfo = new UnitinfoAdapter(getActivity(), names);
		lv.setAdapter(adatper_unitinfo);
		return contentView;
	}
	
	@OnItemClick(R.id.lv) public void item_click(int position) {
		openActivity(UnitInfoView.class);
	}

}
