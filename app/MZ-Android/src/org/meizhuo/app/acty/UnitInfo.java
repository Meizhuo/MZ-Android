package org.meizhuo.app.acty;

import java.util.ArrayList;
import java.util.List;

import org.meizhuo.adapter.ImagePagerAdapter;
import org.meizhuo.adapter.ImagePagerAdapter.OnItemClickListener;
import org.meizhuo.adapter.UnitinfoAdapter;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import butterknife.InjectView;
import butterknife.OnItemClick;

/**
 * 单位信息列表
 * 
 * @author Jayin
 * 
 */
public class UnitInfo extends BaseActivity {
	@InjectView(R.id.lv) ListView lv;
	@InjectView(R.id.autoscrollviewpage) org.meizhuo.view.AutoScrollViewPager viewPager;
	UnitinfoAdapter adatper_unitinfo;
	ImagePagerAdapter adapter_imagepage;

	List<Integer> imageIdList;

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState, R.layout.acty_unitinfo);
		setAppTitle("单位信息");

		String[] names = new String[] { "基本职能", "职业训练法律法规", "职业训练通知" };
		adatper_unitinfo = new UnitinfoAdapter(this, names);
		lv.setAdapter(adatper_unitinfo);

		viewPager.setInterval(2000);
		viewPager.startAutoScroll();

		imageIdList = new ArrayList<Integer>();
		imageIdList.add(R.drawable.bigbang);
		imageIdList.add(R.drawable.aa_evernote);
		imageIdList.add(R.drawable.hannibal);

		adapter_imagepage = new ImagePagerAdapter(this, imageIdList);
		adapter_imagepage.setOnItemClickListener(new OnItemClickListener() {

			@Override public void onItemClick(int position, View view) {
				// to do some work
				toast("" + position);

			}
		});
		viewPager.setAdapter(adapter_imagepage);

	}


	@OnItemClick(R.id.lv) public void item_click(int position) {
		openActivity(UnitInfoBase.class);
	}
}
