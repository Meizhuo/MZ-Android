package org.meizhuo.api;

import com.loopj.android.http.AsyncHttpResponseHandler;

public class AdvertisementAPI {
	
	public AdvertisementAPI() {
		// TODO Auto-generated constructor stub
	}
	
	public static void getAdList(AsyncHttpResponseHandler responseHandler){
		RestClient.get("/home/ad/current", null, responseHandler);
	}


}
