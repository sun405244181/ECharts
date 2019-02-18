package com.funshion.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static int getCurrentTime(String rDate) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String date = df.format(new Date());
		int now = Integer.valueOf(date);
		try {
			int reqDate = Integer.valueOf(rDate);
			if (reqDate > 0)
				return reqDate;
		} catch (Exception ex) {
		}
		return now - 1;
	}

}
