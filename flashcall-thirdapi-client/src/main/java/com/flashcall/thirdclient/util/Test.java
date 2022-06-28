package com.flashcall.thirdclient.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Test {

	public static void main(String[] args) throws java.lang.Exception {
		// 1625082558000
		String s = "1625082558000";
		String date = stampToTime(s);

		System.out.println(date);

	}

	/* //时间戳转换日期 */
	public static String stampToTime(String stamp) {
		String sd = "";
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+0"));
		sd = sdf.format(new Date(Long.parseLong(stamp))); // 时间戳转换日期
		return sd;
	}
}
