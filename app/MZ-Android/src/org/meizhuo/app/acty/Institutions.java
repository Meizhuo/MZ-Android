package org.meizhuo.app.acty;

import java.util.ArrayList;
import java.util.List;

import org.meizhuo.adapter.ImagePagerAdapter;
import org.meizhuo.adapter.UnitinfoAdapter;
import org.meizhuo.adapter.ImagePagerAdapter.OnItemClickListener;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import butterknife.InjectView;
import butterknife.OnItemClick;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/**
 * 机构列表
 * 
 * @author Jayin
 * 
 */
public class Institutions extends BaseActivity {

	@InjectView(R.id.lv) ListView lv;
	@InjectView(R.id.autoscrollviewpage) org.meizhuo.view.AutoScrollViewPager viewPager;
	UnitinfoAdapter adatper_unitinfo;
	ImagePagerAdapter adapter_imagepage;

	List<Integer> imageIdList;

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState, R.layout.acty_institutions);
		setAppTitle("培训机构信息");

		String[] names = new String[] { "文思特（北京）管理咨询有限公司", "培训机构1", "培训机构1" , "培训机构1", "培训机构1"};
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
		openActivity(InstitutionsInfo.class);
	}
}
