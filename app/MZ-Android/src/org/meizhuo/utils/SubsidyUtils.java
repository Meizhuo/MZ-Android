package org.meizhuo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.meizhuo.model.Subsidy_Levels;

/**
 * 用于处理专业查询部分的字符串
 * @author Jason
 *
 */

public class SubsidyUtils {
	
	
	public static List<Subsidy_Levels>getSubsidy_LevelsB1(List<Subsidy_Levels>list){
		 List<Subsidy_Levels>result = new ArrayList<Subsidy_Levels>();
		 for(int i= 0 ; i <list.size();i++) {
			 Pattern pattern = Pattern.compile("^B1.*$");
			 Matcher matcher = pattern.matcher(list.get(i).getLevel());
			 if(matcher.find()){
				 result.add(list.get(i));
			 }
		 }
		 return result;
	}
	
	/**
	 * 匹配B2开头的字符串
	 * @param list
	 * @return
	 */
	public static List<Subsidy_Levels>getSubsidy_LevelsB2 (List<Subsidy_Levels>list){
		 List<Subsidy_Levels>result = new ArrayList<Subsidy_Levels>();
		 for(int i= 0 ; i <list.size();i++) {
			 Pattern pattern = Pattern.compile("^B2.*$");
			 Matcher matcher = pattern.matcher(list.get(i).getLevel());
			 if(matcher.find()){
				 result.add(list.get(i));
			 }
		 }
		 return result;
	}
	

	public static List<Subsidy_Levels>getSubsidy_LevelsB3(List<Subsidy_Levels>list){
		 List<Subsidy_Levels>result = new ArrayList<Subsidy_Levels>();
		 for(int i= 0 ; i <list.size();i++) {
			 Pattern pattern = Pattern.compile("^B3.*$");
			 Matcher matcher = pattern.matcher(list.get(i).getLevel());
			 if(matcher.find()){
				 result.add(list.get(i));
			 }
		 }
		 return result;
	}
	
	

	public static List<Subsidy_Levels>getSubsidy_LevelsB4(List<Subsidy_Levels>list){
		 List<Subsidy_Levels>result = new ArrayList<Subsidy_Levels>();
		 for(int i= 0 ; i <list.size();i++) {
			 Pattern pattern = Pattern.compile("^B4.*$");
			 Matcher matcher = pattern.matcher(list.get(i).getLevel());
			 if(matcher.find()){
				 result.add(list.get(i));
			 }
		 }
		 return result;
	}
	

	public static List<Subsidy_Levels>getSubsidy_LevelsB5(List<Subsidy_Levels>list){
		 List<Subsidy_Levels>result = new ArrayList<Subsidy_Levels>();
		 for(int i= 0 ; i <list.size();i++) {
			 Pattern pattern = Pattern.compile("^B5.*$");
			 Matcher matcher = pattern.matcher(list.get(i).getLevel());
			 if(matcher.find()){
				 result.add(list.get(i));
			 }
		 }
		 return result;
	}
	

	public static List<Subsidy_Levels>getSubsidy_LevelsB6(List<Subsidy_Levels>list){
		 List<Subsidy_Levels>result = new ArrayList<Subsidy_Levels>();
		 for(int i= 0 ; i <list.size();i++) {
			 Pattern pattern = Pattern.compile("^B6.*$");
			 Matcher matcher = pattern.matcher(list.get(i).getLevel());
			 if(matcher.find()){
				 result.add(list.get(i));
			 }
		 }
		 return result;
	}
	
	
	


}
