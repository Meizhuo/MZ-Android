package org.meizhuo.app.acty;

import java.util.ArrayList;
import java.util.List;

import org.meizhuo.adapter.ImagePagerAdapter;
import org.meizhuo.adapter.ImagePagerAdapter.OnItemClickListener;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;

public class Main extends BaseActivity {
	@InjectView(R.id.ic_title_back) ImageView iv_title_back;
	@InjectView(R.id.tv_title) TextView tv_title;

	@InjectView(R.id.autoscrollviewpage) org.meizhuo.view.AutoScrollViewPager viewPager;

	ImagePagerAdapter adapter_imagepage;

	List<Integer> imageIdList;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.acty_main);
		iv_title_back.setVisibility(View.INVISIBLE);
		tv_title.setText(getResources().getString(R.string.app_name));
		
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

	@OnClick(R.id.btn_unitinfo) public void unitinfo() {
		openActivity(UnitInfo.class);
	}

	@OnClick(R.id.btn_institutions) public void Institutions() {
		openActivity(Institutions.class);
	}

	@OnClick(R.id.btn_institution_consult) public void institution_consult() {
		openActivity(InstitutionConsult.class);
	}

	@OnClick(R.id.btn_public_consult) public void public_consult() {
		openActivity(PublicConsult.class);
	}

	@OnClick(R.id.btn_usercenter) public void usercenter() {
		openActivity(UserCenter.class);
	}

	@OnClick(R.id.btn_setting) public void setting() {
		openActivity(Setting.class);
	}

}
