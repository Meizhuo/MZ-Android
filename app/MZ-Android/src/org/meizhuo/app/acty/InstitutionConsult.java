package org.meizhuo.app.acty;

import java.util.ArrayList;
import java.util.List;

import org.meizhuo.adapter.ImagePagerAdapter;
import org.meizhuo.adapter.InstitutionConsultAdapter;
import org.meizhuo.adapter.ImagePagerAdapter.OnItemClickListener;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.view.AutoScrollViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

/**
 * 培训机构列表
 * 
 * @author Lenovo
 * 
 */
public class InstitutionConsult extends BaseActivity {
	
	@InjectView(R.id.tv_title) TextView tv_title;
	@InjectView(R.id.lv_institutionconsult) ListView lv;
	@InjectView(R.id.autoscrollviewpage) AutoScrollViewPager viewPager;
	InstitutionConsultAdapter adapter_institutionconsult;
	ImagePagerAdapter adapter_imagepage;

	List<Integer> imageIdList;
	
	
	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_institutionconsult);
		ButterKnife.inject(this);
		tv_title.setText("培训机构服务");
		
		String[] names =  new String[] { "师资培训咨询","职业培训教材规划咨询","职业技能培训咨询" };
		adapter_institutionconsult = new InstitutionConsultAdapter(this, names);
        lv.setAdapter(adapter_institutionconsult);
		
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
	
	
	@OnItemClick(R.id.lv_institutionconsult) public void item_click(int position)
	{
		switch (position) {
		case 0:
			 openActivity(InstitutionConsult_Teacher.class);
			break;
		case 1:
			 openActivity(InstitutionConsult_Textbook.class);

			break;
			
		case 2:
			 openActivity(InstitutionConsult_Skill.class);

			break;
		}
	}
}
