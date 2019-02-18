package com.funshion.bean.sdkmanager;

public class AppInstallFailInfo {

	private String romVer;
	private int sdkVer;
	private String app;
	private int errorCode;
	private int count;
	private int date;

	public AppInstallFailInfo() {
		super();
	}

	public AppInstallFailInfo(String romVer, int sdkVer, String app, int errorCode, int count, int date) {
		super();
		this.romVer = romVer;
		this.sdkVer = sdkVer;
		this.app = app;
		this.errorCode = errorCode;
		this.count = count;
		this.date = date;
	}

	public String getRomVer() {
		return romVer;
	}

	public void setRomVer(String romVer) {
		this.romVer = romVer;
	}

	public int getSdkVer() {
		return sdkVer;
	}

	public void setSdkVer(int sdkVer) {
		this.sdkVer = sdkVer;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "AppInstallFailInfo [romVer=" + romVer + ", sdkVer=" + sdkVer + ", app=" + app + ", errorCode="
				+ errorCode + ", count=" + count + ", date=" + date + "]";
	}

}
