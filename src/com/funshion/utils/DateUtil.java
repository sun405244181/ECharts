package com.funshion.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
		return getDayBefore(now);// now - 1;
	}

	public static int getDayBefore(int day) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyyMMdd").parse(String.valueOf(day));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int time = c.get(Calendar.DATE);
		c.set(Calendar.DATE, time - 1);

		String dayBefore = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
		return Integer.valueOf(dayBefore);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getCurrentTime(null));
	}

}
