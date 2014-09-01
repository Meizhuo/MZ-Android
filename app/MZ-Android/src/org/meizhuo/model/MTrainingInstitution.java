package org.meizhuo.model;

import java.util.ArrayList;
import java.util.List;

public class MTrainingInstitution {

	public static MTrainingInstitution getTestData() {
		MTrainingInstitution ti = new MTrainingInstitution();
		ti.setName("文思特（北京）管理咨询有限公司");
		ti.setDescription("文思特是一家致力于为企业提供全方位运营咨询与培训的服务性机构");
		ti.setLocation("北京市海淀区花园北路14号环星大厦A座828室 ");
		List<String> urls = new ArrayList<String>();
		urls.add("http://www.win-starcn.com/images/logo2.png");
		return ti;
	}

	// 名称、地址、培训的范围及师资情况。
	private String name;
	private String description;
	private String location;
	private List<String> image_urls;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<String> getImage_urls() {
		return image_urls;
	}

	public void setImage_urls(List<String> image_urls) {
		this.image_urls = image_urls;
	}

}
