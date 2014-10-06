package org.meizhuo.app;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.meizhuo.model.Publicer;
import org.meizhuo.utils.DataPool;
import org.meizhuo.utils.FilePath;
import org.meizhuo.utils.JsonUtils;

import android.content.Context;


/**
 * 应用程序配置 ： 用于保存用户相关信息及设置
 * @author Jason
 *
 */

public class AppInfo {
	/** 保存普通 
	 * 用户账号 */
	public static boolean setPublicername(Context context, String username) {
		DataPool dp = new DataPool(DataPool.SP_Name_Publicer, context);
		return dp.put(DataPool.SP_Key_Publicer_Name, username);
	}

	/** 获得普通 用户账号 */
	public static String getPublicername(Context context) {
		DataPool dp = new DataPool(DataPool.SP_Name_Publicer, context);
		return (String) dp.get(DataPool.SP_Key_Publicer_Name);
	}

	/** 保存普通 用户密码 */
	public static boolean setPublicerPSW(Context context, String userpsw) {
		DataPool dp = new DataPool(DataPool.SP_Name_Publicer, context);
		return dp.put(DataPool.SP_Key_Publicer_PSW, userpsw);
	}

	/** 获得普通 用户密码 */
	public static String getPublicerPSW(Context context) {
		DataPool dp = new DataPool(DataPool.SP_Name_Publicer, context);
		return (String) dp.get(DataPool.SP_Key_Publicer_PSW);
	}
	
	/**
	 * 获取普通 用户信息
	 * @param context
	 * @return
	 */
	public static Publicer getPublicer(Context context) {
		DataPool dp = new DataPool(DataPool.SP_Name_Publicer, context);
		return (Publicer) dp.get(DataPool.SP_Key_Publicer);
	}
	
	/**
	 * Create by vector
	 * @param json
	 * @param context
	 * @return
	 * @throws IOException
	 */
	public static boolean setUser(String json, Context context) throws IOException {
		
		final DataPool dp = new DataPool(DataPool.SP_Name_Publicer, context);
		
		if (JsonUtils.isOK(json)) {
			List<Publicer>listPublicer = Publicer.create_by_jsonarray(json);
			if (listPublicer != null) {
				dp.remove(DataPool.SP_Key_Publicer);
				return dp.put(DataPool.SP_Key_Publicer, listPublicer.get(0));
			}
		}else {
		}
		return false;
	}
	
	/**
	 * created by jason
	 * @param context   
	 * @param publicer
	 * @return
	 */
	public static boolean setUser(Context context, Publicer publicer) {
		DataPool dp = new DataPool(DataPool.SP_Name_Publicer, context);
		dp.remove(DataPool.SP_Key_Publicer);
		return dp.put(DataPool.SP_Name_Publicer, publicer);
	}
	
	/**
	 * 获得自定义的主页背景图片
	 * @param context
	 * @return
	 */
	public static String getBackgroundPath(Context context) {
		//路径 
		String	path = FilePath.getImageFilePath() + "main_backgroud.jpg";
		File file = new File(path);
		if (file.exists()) {
			return path;
		} else {
			return null;
		}
		
	}

}