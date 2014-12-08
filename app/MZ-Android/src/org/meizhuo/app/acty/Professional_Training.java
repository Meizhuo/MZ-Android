package org.meizhuo.app.acty;

import java.util.ArrayList;
import java.util.List;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.fragment.ProfessionalTrainingBase;
import org.meizhuo.fragment.ProfessionalTrainingAboutNews;
import org.meizhuo.fragment.ProfessionalTrainingNotification;
import org.meizhuo.fragment.ProfessionalTrainingPolicy;

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
public class Professional_Training extends BaseActivity {
	private static final String TAG = "Professional_Training";
	
	@InjectView(R.id.tabs) com.astuetz.PagerSlidingTabStrip mPagerSlidingTabStrip;
	@InjectView(R.id.viewpager) ViewPager mViewPager;
 
	List<Fragment> fragments = new ArrayList<Fragment>();

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState, R.layout.acty_profession_training );
		setAppTitle("职业培训信息");

		fragments.add(new ProfessionalTrainingAboutNews());
		fragments.add(new ProfessionalTrainingBase());
		fragments.add(new ProfessionalTrainingPolicy());
		fragments.add(new ProfessionalTrainingNotification());

		mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),
				fragments));
		mPagerSlidingTabStrip.setViewPager(mViewPager);
		
	}
	

	public class MyPagerAdapter extends FragmentPagerAdapter {

		List<Fragment> fragments;

		private final String[] TITLES = {  "相关新闻","基本职能","法规政策","通知信息" };

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
