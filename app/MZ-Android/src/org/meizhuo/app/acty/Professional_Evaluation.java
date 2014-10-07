package org.meizhuo.app.acty;

import java.util.ArrayList;
import java.util.List;

import org.meizhuo.adapter.ImagePagerAdapter;
import org.meizhuo.adapter.InstitutionAdapter;
import org.meizhuo.adapter.ImagePagerAdapter.OnItemClickListener;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.app.acty.Professional_Training.MyPagerAdapter;
import org.meizhuo.fragment.Professional_Technical_Evaluation_Examination;
import org.meizhuo.fragment.Professional_Technical_Evaluation_Guide;
import org.meizhuo.fragment.Professional_Technical_Evaluation_Rule;
import org.meizhuo.fragment.Professional_Training_AboutNews;
import org.meizhuo.fragment.Professional_Training_Base;
import org.meizhuo.fragment.Professional_Training_Policy;
import org.meizhuo.model.MTrainingInstitution;

import butterknife.InjectView;
import butterknife.OnItemClick;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;

/**
 * 机构列表
 * 
 * @author Jayin
 * 
 */
public class Professional_Evaluation extends BaseActivity {
	private static final String TAG = "Professional_Evaluation";
	
	@InjectView(R.id.tabs) com.astuetz.PagerSlidingTabStrip mPagerSlidingTabStrip;
	@InjectView(R.id.viewpager) ViewPager mViewPager;
	List<Fragment> fragments = new ArrayList<Fragment>();

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState, R.layout.acty_profession_training );
		setAppTitle("职业技术鉴定");

		fragments.add(new Professional_Technical_Evaluation_Rule());
		fragments.add(new Professional_Technical_Evaluation_Guide());
		fragments.add(new Professional_Technical_Evaluation_Examination());

		mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),
				fragments));
		mPagerSlidingTabStrip.setViewPager(mViewPager);
		
	}
	

	public class MyPagerAdapter extends FragmentPagerAdapter {

		List<Fragment> fragments;

		private final String[] TITLES = {  "政策法规","办事指南","全国统考" };

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
