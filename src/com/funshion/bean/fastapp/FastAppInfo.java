package com.funshion.bean.fastapp;

public class FastAppInfo {

	private String date;
	private int appStartTimes;
	private int userCountApps;
	private int appCounts;

	public FastAppInfo() {
		super();
	}

	public FastAppInfo(String date, int appStartTimes, int userCountApps, int appCounts) {
		super();
		this.date = date;
		this.appStartTimes = appStartTimes;
		this.userCountApps = userCountApps;
		this.appCounts = appCounts;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getAppStartTimes() {
		return appStartTimes;
	}

	public void setAppStartTimes(int appStartTimes) {
		this.appStartTimes = appStartTimes;
	}

	public int getUserCountApps() {
		return userCountApps;
	}

	public void setUserCountApps(int userCountApps) {
		this.userCountApps = userCountApps;
	}

	public int getAppCounts() {
		return appCounts;
	}

	public void setAppCounts(int appCounts) {
		this.appCounts = appCounts;
	}

	@Override
	public String toString() {
		return "FastAppInfo [date=" + date + ", appStartTimes=" + appStartTimes + ", userCountApps=" + userCountApps
				+ ", appCounts=" + appCounts + "]";
	}

}
