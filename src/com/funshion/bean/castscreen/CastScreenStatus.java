package com.funshion.bean.castscreen;

public class CastScreenStatus {

	private int sucessCount;
	private int failCount;
	private int date;

	public CastScreenStatus() {
		super();
	}

	public CastScreenStatus(int sucessCount, int failCount, int date) {
		super();
		this.sucessCount = sucessCount;
		this.failCount = failCount;
		this.date = date;
	}

	public int getSucessCount() {
		return sucessCount;
	}

	public void setSucessCount(int sucessCount) {
		this.sucessCount = sucessCount;
	}

	public int getFailCount() {
		return failCount;
	}

	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "CastScreenStatus [sucessCount=" + sucessCount + ", failCount=" + failCount + ", date=" + date + "]";
	}

}
