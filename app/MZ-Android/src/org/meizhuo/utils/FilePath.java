package org.meizhuo.utils;

import java.io.File;

import android.os.Environment;

/**
 * 文件路径管理
 * 
 * @author Jason
 *
 */
public class FilePath {
	
	public final static String File_Save_Path = Environment.
			getExternalStorageDirectory().getAbsolutePath() 
			+ File.separator 
			+ "MZ" + File.separator;
	
	/**
	 * SD卡上保存主目录
	 * @return
	 */
	public static String getFileSavePath() {
		return File_Save_Path;
	}
	/**
	 *  图片保存路径
	 * @return
	 */
	public static String getImageFilePath() {
		File f = new File(File_Save_Path + "img" + File.separator);
		if (!f.exists())
			f.mkdirs();
		return f.getAbsolutePath() + File.separator;
	}


}
