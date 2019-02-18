package com.funshion.bean.sdkmanager;

public class SDKManagerVersion {

	private String romVer;
	private String sdkVer;
	private int count;
	private int date;

	public SDKManagerVersion() {
		super();
	}

	public SDKManagerVersion(String romVer, String sdkVer, int count, int date) {
		super();
		this.romVer = romVer;
		this.sdkVer = sdkVer;
		this.count = count;
		this.date = date;
	}

	public String getRomVer() {
		return romVer;
	}

	public void setRomVer(String romVer) {
		this.romVer = romVer;
	}

	public String getSdkVer() {
		return sdkVer;
	}

	public void setSdkVer(String sdkVer) {
		this.sdkVer = sdkVer;
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
		return "SDKManagerVersions [romVer=" + romVer + ", sdkVer=" + sdkVer + ", count=" + count + ", date=" + date
				+ "]";
	}

}
