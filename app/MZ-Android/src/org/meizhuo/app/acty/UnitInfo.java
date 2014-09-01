package org.meizhuo.app.acty;

import java.util.ArrayList;
import java.util.List;

import org.meizhuo.adapter.ImagePagerAdapter;
import org.meizhuo.adapter.UnitinfoAdapter;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.fragment.UnitInfoBase;
import org.meizhuo.fragment.UnitInfoLastest;
import org.meizhuo.fragment.UnitInfoPolicy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import butterknife.InjectView;

/**
 * 单位信息列表
 * 
 * @author Jayin
 * 
 */
public class UnitInfo extends BaseActivity {
	@InjectView(R.id.tabs) com.astuetz.PagerSlidingTabStrip mPagerSlidingTabStrip;
	@InjectView(R.id.viewpager) ViewPager mViewPager;
	UnitinfoAdapter adatper_unitinfo;
	ImagePagerAdapter adapter_imagepage;

	List<Fragment> fragments = new ArrayList<Fragment>();

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState, R.layout.acty_unitinfo);
		setAppTitle("单位信息");

		fragments.add(new UnitInfoLastest());
		fragments.add(new UnitInfoBase());
		fragments.add(new UnitInfoPolicy());

		mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),
				fragments));
		mPagerSlidingTabStrip.setViewPager(mViewPager);
	}

	public class MyPagerAdapter extends FragmentPagerAdapter {

		List<Fragment> fragments;

		private final String[] TITLES = {  "通知" ,"基本职能", "法律法规"};

		public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;
		}

		@Override public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override public int getCount() {
			return TITLES.length;
		}

		@Override public Fragment getItem(int position) {
			return fragments.get(position);
		}

	}

}
