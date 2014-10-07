package org.meizhuo.app.acty;

import java.util.List;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import butterknife.InjectView;

/**
 * 某机构的信息
 * 
 * @author Jayin
 * 
 */
public class InstitutionsInfo_details extends BaseActivity {

	@InjectView(R.id.tv_atricle_title) TextView tv_atricle_title;
	@InjectView(R.id.tv_atricle_content) TextView tv_atricle_content;

	List<Integer> imageIdList;

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState, R.layout.acty_institutioninfo_details);
		setAppTitle("详情");
		
		tv_atricle_title.setText("文思特（北京）管理咨询有限公司");
		tv_atricle_content.setMovementMethod(ScrollingMovementMethod
				.getInstance());// 滚动
		tv_atricle_content
				.setText(Html
						.fromHtml("<p>　　文思特（北京）管理咨询有限公司致力于为企业提供先进生产运营方式的咨询与培训服务。推广先进管理方式，打造理论与实践的联接桥梁是我们的重要使命。我们的主要的业务领域包括以下方面：</p>"
								+ "<p>先进制造技术：敏捷制造、精益生产、六西格玛</p>"
								+ "<p>运营战略及员工能力：运营战略、员工能力</p>"
								+ "<p>   我们的咨询师全部来自于世界知名公司，接受到过良好的教育，有着丰富的实际工作经验，并在咨询行业工作多年。为了时刻保持为顾客提供高价值服务的能力，公司建立了良好的培训教育机制，通过系统的培训不断提升咨询师为企业提供问题解决方案的能力。</p>"));
		
		
		
		
		
	
	}
}
