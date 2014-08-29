package org.meizhuo.app.acty;

import org.meizhuo.adapter.UnitinfoAdapter;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;
/**
 * 单位信息列表
 * @author Jayin
 *
 */
public class UnitInfo extends BaseActivity {
	@InjectView(R.id.tv_title) TextView tv_title; 
	@InjectView(R.id.lv_unitinfo) ListView lv;
	
	UnitinfoAdapter adatper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_unitinfo);
		ButterKnife.inject(this);
		
		tv_title.setText("单位信息");
		
		String[] names = new String[]{"基本职能","职业训练法律法规","职业训练通知"};
		adatper = new UnitinfoAdapter(this, names);
		lv.setAdapter(adatper);
	}
	
	@OnClick(R.id.btn_title_back)
	public void back(){
		closeActivity();
	}
	
	@OnItemClick(R.id.lv_unitinfo)
	public void item_click(int position){
		toast(""+position);
	}
}
