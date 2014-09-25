package org.meizhuo.api;

import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * 版本接口
 * @author Jason
 *
 */
public class VersionAPI {

	public VersionAPI() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 最新版本信息
	 * @param responseHandler
	 */
	public void getLatestInfo(AsyncHttpResponseHandler responseHandler) {
		RestClient.get("/home/app/getVersionInfo", null, responseHandler);
	}
}
