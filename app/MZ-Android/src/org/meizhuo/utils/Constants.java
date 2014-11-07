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
	/**普通用户第一次登录*/
	public static final String Action_Publicer_isLogin = "Publicer_isLogin";
	/**用人单位第一次登录*/
	public static final String Action_Employer_isLogin = "Employer_isLogin";
	/**普通用户再次去登录*/
	public static final String Action_Publicer_To_ReLogin = "Publicer_To_ReLogin ";
	/***用人单位再次去登录*/
	public static final String Action_Employer_To_Relogin = "Employer_To_Relogin";
	/**普通用户再次登录成功*/
	public static final String Action_Publicer_ReLoginSuccessful = "Publicer_ReLoginSuccessful" ;
	/***用人单位再次登录成功*/
	public static final String Action_Employer_ReLoginSuccessful = "Employer_ReLoginSuccessful" ;
	/***普通用户再次登录失败*/
	public static final String Action_Publicer_ReLoginFaild = "Publicer_ReLoginFaild" ;
	/***用人单位再次登录失败*/
	public static final String Action_Employer_ReloginFaild = "Employer_ReloginFaild" ;
	/***退出*/
	public static final String Action_Logout = "Logout";
	/**注销*/
	public static final String Action_Logoff = "Logoff";
	/**修改密码成功*/
	public static final String Action_Changed_Psw_Success = "Changed_Psw_Success";


	
	/**http请求状态*/
	public final static int Start = 0;
	public final static int Logining = 1;
	public final static int Downloading = 2;
	public final static int Finish = 3;
	public final static int Fail = 4;
	


}
