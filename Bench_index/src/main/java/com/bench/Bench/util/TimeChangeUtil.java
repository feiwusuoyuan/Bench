package com.bench.Bench.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bench.bean.S3Article;
import com.bench.bean.S3Comment;

public class TimeChangeUtil {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 文章转化
	 */
	public static List<S3Article> change(List<S3Article> list) {
		Date date = new Date(System.currentTimeMillis());
		String end = sdf.format(date);
		for (S3Article art : list) {
			String start = sdf.format(art.getSendtime());
			String s = TimeSumUtil.sum(start, end);
			String ss[] = s.split(":");
			String day = ss[0];
			String hour = ss[1];
			String min = ss[2];
			if (Integer.valueOf(ss[0]) > 0 && Integer.valueOf(ss[0]) < 7) {
				art.setTimeChange(Integer.valueOf(day) + "天前");
			} else if (Integer.valueOf(ss[0]) > 7) {
				art.setTimeChange(String.valueOf(art.getSendtime()));
			} else if (Integer.valueOf(ss[0]) == 0) {
				if (Integer.valueOf(ss[1]) > 0) {
					art.setTimeChange(Integer.valueOf(hour) + "小时前");
				} else if (Integer.valueOf(ss[1]) == 0) {
					if (Integer.valueOf(ss[2]) > 0) {
						art.setTimeChange(Integer.valueOf(min) + "分钟前");
					} else if (Integer.valueOf(ss[2]) == 0) {
						art.setTimeChange("刚刚");
					}

				}
			}
		}
		return list;

	}
	
	public static List<S3Comment> change1(List<S3Comment> list) {
		Date date = new Date(System.currentTimeMillis());
		String end = sdf.format(date);
		for (S3Comment com : list) {
			String start = sdf.format(com.getRegtime());
			String s = TimeSumUtil.sum(start, end);
			String ss[] = s.split(":");
			String day = ss[0];
			String hour = ss[1];
			String min = ss[2];
			if (Integer.valueOf(ss[0]) > 0 && Integer.valueOf(ss[0]) < 7) {
				com.setTimeChange(Integer.valueOf(day) + "天前");
			} else if (Integer.valueOf(ss[0]) > 7) {
				com.setTimeChange(String.valueOf(com.getRegtime()));
			} else if (Integer.valueOf(ss[0]) == 0) {
				if (Integer.valueOf(ss[1]) > 0) {
					com.setTimeChange(Integer.valueOf(hour) + "小时前");
				} else if (Integer.valueOf(ss[1]) == 0) {
					if (Integer.valueOf(ss[2]) > 0) {
						com.setTimeChange(Integer.valueOf(min) + "分钟前");
					} else if (Integer.valueOf(ss[2]) == 0) {
						com.setTimeChange("刚刚");
					}

				}
			}
		}
		return list;

	}
	
	public static S3Article change(S3Article art) {
		Date date = new Date(System.currentTimeMillis());
		String end = sdf.format(date);
			String start = sdf.format(art.getSendtime());
			String s = TimeSumUtil.sum(start, end);
			String ss[] = s.split(":");
			String day = ss[0];
			String hour = ss[1];
			String min = ss[2];
			if (Integer.valueOf(ss[0]) > 0 && Integer.valueOf(ss[0]) < 7) {
				art.setTimeChange(Integer.valueOf(day) + "天前");
			} else if (Integer.valueOf(ss[0]) > 7) {
				art.setTimeChange(String.valueOf(art.getSendtime()));
			} else if (Integer.valueOf(ss[0]) == 0) {
				if (Integer.valueOf(ss[1]) > 0) {
					art.setTimeChange(Integer.valueOf(hour) + "小时前");
				} else if (Integer.valueOf(ss[1]) == 0) {
					if (Integer.valueOf(ss[2]) > 0) {
						art.setTimeChange(Integer.valueOf(min) + "分钟前");
					} else if (Integer.valueOf(ss[2]) == 0) {
						art.setTimeChange("刚刚");
					}

				}
			}
		return art;

	}

}
