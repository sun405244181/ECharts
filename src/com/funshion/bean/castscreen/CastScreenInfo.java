package com.funshion.bean.castscreen;

public class CastScreenInfo {

	private int airplayCount;
	private int dlnaCount;
	private int mirrorcastCount;
	private int date;

	public CastScreenInfo() {
		super();
	}

	public CastScreenInfo(int airplayCount, int dlnaCount, int mirrorcastCount, int date) {
		super();
		this.airplayCount = airplayCount;
		this.dlnaCount = dlnaCount;
		this.mirrorcastCount = mirrorcastCount;
		this.date = date;
	}

	public int getAirplayCount() {
		return airplayCount;
	}

	public void setAirplayCount(int airplayCount) {
		this.airplayCount = airplayCount;
	}

	public int getDlnaCount() {
		return dlnaCount;
	}

	public void setDlnaCount(int dlnaCount) {
		this.dlnaCount = dlnaCount;
	}

	public int getMirrorcastCount() {
		return mirrorcastCount;
	}

	public void setMirrorcastCount(int mirrorcastCount) {
		this.mirrorcastCount = mirrorcastCount;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "CastScreenInfo [airplayCount=" + airplayCount + ", dlnaCount=" + dlnaCount + ", mirrorcastCount="
				+ mirrorcastCount + ", date=" + date + "]";
	}

}
