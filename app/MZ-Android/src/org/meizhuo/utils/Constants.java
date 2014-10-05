package org.meizhuo.utils;

/**
 * 各种常量
 * @author Jason
 *
 */
public class Constants {
	/**服务Action*/
	/**检测新版本*/
	public static final String Action_checkVersion = "check_version";
	
	/** 获得版本信息*/
	public static final String Action_Receive_VersionInfo = "Receive_VersionInfo"; 
	
	/**http请求状态*/
	public final static int Start = 0;
	public final static int Logining = 1;
	public final static int Downloading = 2;
	public final static int Finish = 3;
	public final static int Fail = 4;
	


}
