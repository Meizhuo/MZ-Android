package org.meizhuo.app.acty;

import java.util.ArrayList;
import java.util.List;

import org.meizhuo.adapter.ImagePagerAdapter;
import org.meizhuo.adapter.ImagePagerAdapter.OnItemClickListener;
import org.meizhuo.adapter.PublicConsultAdapter;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.view.AutoScrollViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * 个人(民工)咨询列表
 * 
 * @author Lenovo
 * 
 */
public class PublicConsult extends BaseActivity {

	@InjectView(R.id.tv_title) TextView tv_title;
	@InjectView(R.id.lv_publicconsult) ListView lv;
	@InjectView(R.id.autoscrollviewpage) AutoScrollViewPager viewPager;
	PublicConsultAdapter adapter_publicconsult;
	ImagePagerAdapter adapter_imagepage;
	
	List<Integer> imageIdList;
	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_publiconsult);
		ButterKnife.inject(this);
		tv_title.setText("公众咨询");
		
		String[] names =  new String[] { "培训咨询","机构咨询","政策咨询" };
		adapter_publicconsult =  new PublicConsultAdapter(this, names);
		lv.setAdapter(adapter_publicconsult);
		
		viewPager.setInterval(2000);
		viewPager.startAutoScroll();
		
		imageIdList =  new ArrayList<Integer>();
		imageIdList.add(R.drawable.bigbang);
		imageIdList.add(R.drawable.aa_evernote);
		imageIdList.add(R.drawable.hannibal);
		
		adapter_imagepage = new ImagePagerAdapter(this, imageIdList);
		adapter_imagepage.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(int position, View view) {
				// TODO Auto-generated method stub
				toast("" + position);
			}
		});
		viewPager.setAdapter(adapter_imagepage);
	}
	
	
	@OnClick(R.id.btn_title_back) public void back() {
		closeActivity();
	}
	
	@OnItemClick(R.id.lv_publicconsult) public void item_click(int position)
	{
		switch (position) {
		case 0:
			 openActivity(PublicConsult_Cultivate.class);
			break;
		case 1:
			 openActivity(PublicConsult_Org.class);

			break;
			
		case 2:
			 openActivity(PublicConsult_Policy.class);

			break;
		}
	}
}
