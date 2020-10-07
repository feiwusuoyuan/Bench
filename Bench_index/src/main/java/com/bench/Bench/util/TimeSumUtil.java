package com.bench.Bench.util;

import java.text.SimpleDateFormat;

public class TimeSumUtil {
	//两个string必须为simplydataformat转型后的时间
	public static String sum(String start,String end) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long nd=1000*24*60*60;//一天的毫秒数
		long nh=1000*60*60;//一小时的毫秒数
		long nm=1000*60;//一分钟的毫秒数
		try {
			long diff=sdf.parse(end).getTime()-sdf.parse(start).getTime();
			long day=diff/nd;
			long hour=diff%nd/nh;
			long min=diff%nd%nh/nm;
			return day+":"+hour+":"+min;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
