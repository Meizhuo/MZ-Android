package org.meizhuo.app.acty;

import java.util.List;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 单位信息列表
 * 
 * @author Jayin
 * 
 */
public class UnitInfoView extends BaseActivity {
	@InjectView(R.id.tv_title) TextView tv_title;
	@InjectView(R.id.tv_atricle_title) TextView tv_atricle_title;
	@InjectView(R.id.tv_atricle_content) TextView tv_atricle_content;



	List<Integer> imageIdList;

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState, R.layout.acty_unitinfoview);
		tv_title.setText("详情");
		
		tv_atricle_title.setText("基本职能");
		tv_atricle_content.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动  
		tv_atricle_content.setText(Html.fromHtml("<p>　　广东省职业技能鉴定指导中心是广东省人力资源和社会保障厅直属事业单位。负责全省职业技能鉴定工作的组织、协调和指导，按照国家制定的职业技能鉴定规划、政策、标准和有关规定开展鉴定工作；组织编写地方职业标准，承担国家题库广东地方分库运行工作；开发鉴定题库及相关教材资料，" +
		"提供鉴定教材和相关技术支持服务；受行政委托核发国家职业资格证书，换发中英文版国家职业资格证书；审核鉴定站（所）申报资格，对鉴定站（所）实施年检。</p" +
		"<p>1997年至2012年底，全省共组织1664万人参加职业技能鉴定，1288万人取得国家职业资格证书。1998年至2012年全省组织的鉴定人数连续15年居全国各省之首。为提高我省技能人才素质，促进就业与再就业，降低企业人力资源开发成本，规范劳动力市场管理发挥了积极作用。至2012年底，全省共有鉴定机构517个，考评员34589人，可供考生申报鉴定的工种达737个。</p>"
		+"　　<p><strong>广东省职业技能鉴定指导中心业务联系电话（12333劳动保障咨询电话）</strong>中心开设全省鉴定服务热线4008863313供申报职业技能鉴定人员查询鉴定信息、考试成绩和咨询有关业务，参加鉴定人员可通过中心网站www.gdosta.org.cn了解鉴定有关政策、信息及查询证书真伪，或到广州市惠福东路546号广东就业服务大厦直接申报鉴定和进行咨询。</p>"
	));      
	}

	@OnClick(R.id.btn_title_back) public void back() {
		closeActivity();
	}

	
}
