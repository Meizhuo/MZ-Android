package org.meizhuo.app.acty;

import org.meizhuo.adapter.UnitinfoAdapter;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import android.widget.ListView;
import butterknife.InjectView;
import butterknife.OnItemClick;

/**
 * 基本职能
 * 
 * @author Jayin
 * 
 */
public class UnitInfoBase extends BaseActivity {
	@InjectView(R.id.lv) ListView lv;
	UnitinfoAdapter adatper_unitinfo;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.acty_unitinfobase);
		setAppTitle("基本职能");

		String[] names = new String[] { "中心概况", "职业训练法律法规", "职业训练通知",
				"职业训练法律法规", "职业训练通知", "职业训练法律法规", "职业训练通知" };
		adatper_unitinfo = new UnitinfoAdapter(this, names);
		lv.setAdapter(adatper_unitinfo);

	}

	@OnItemClick(R.id.lv) public void item_click(int position) {
		openActivity(UnitInfoView.class);
	}

}
