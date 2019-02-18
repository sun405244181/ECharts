package com.funshion.bean.sdkmanager;

public class SDKManagerBoot {

	private String romVer;
	private int tvCount;
	private String appName;
	private String appVer;
	private int appCount;
	private int date;
	private int ok;

	public SDKManagerBoot() {
		super();
	}

	public SDKManagerBoot(String romVer, int tvCount, String appName, String appVer, int appCount, int date, int ok) {
		super();
		this.romVer = romVer;
		this.tvCount = tvCount;
		this.appName = appName;
		this.appVer = appVer;
		this.appCount = appCount;
		this.date = date;
		this.ok = ok;
	}

	public String getRomVer() {
		return romVer;
	}

	public void setRomVer(String romVer) {
		this.romVer = romVer;
	}

	public int getTvCount() {
		return tvCount;
	}

	public void setTvCount(int tvCount) {
		this.tvCount = tvCount;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppVer() {
		return appVer;
	}

	public void setAppVer(String appVer) {
		this.appVer = appVer;
	}

	public int getAppCount() {
		return appCount;
	}

	public void setAppCount(int appCount) {
		this.appCount = appCount;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getOk() {
		return ok;
	}

	public void setOk(int ok) {
		this.ok = ok;
	}

	@Override
	public String toString() {
		return "SDKManagerBoot [romVer=" + romVer + ", tvCount=" + tvCount + ", appName=" + appName + ", appVer="
				+ appVer + ", appCount=" + appCount + ", date=" + date + ", ok=" + ok + "]";
	}

}
