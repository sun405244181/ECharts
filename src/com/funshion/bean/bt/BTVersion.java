package com.funshion.bean.bt;

public class BTVersion {

	private String btVer;
	private int count;
	private int date;

	public BTVersion() {
		super();
	}

	public BTVersion(String btVer, int count, int date) {
		super();
		this.btVer = btVer;
		this.count = count;
		this.date = date;
	}

	public String getBtVer() {
		return btVer;
	}

	public void setBtVer(String btVer) {
		this.btVer = btVer;
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
		return "BTVersion [btVer=" + btVer + ", count=" + count + ", date=" + date + "]";
	}

}
