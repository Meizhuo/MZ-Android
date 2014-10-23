package org.meizhuo.api;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class CourseAPI {

	public CourseAPI() {
		// TODO Auto-generated constructor stub
	}
	
	public static void getCourseList(String institution_id, String subsidy_id,
			String page,
			AsyncHttpResponseHandler responseHandler ){
		RequestParams params = new RequestParams();
		if (!(institution_id == null|| institution_id.equals("")))
			params.add("institution_id", institution_id);
		if (!(subsidy_id == null || subsidy_id.equals("")))
			params.add("subsidy_id", subsidy_id);
		if (!(page == null || page.equals("")))
			params.add("page", page);
		RestClient.get("/home/course/lists", params, responseHandler);
	}
	
	

}
