package com.funshion.bean.romrecovery;

public class RomRecoveryInfo {

	private int dataWipeCount;
	private int cacheWipeCount;
	private int facWipeCount;
	private int forceWipeCount;
	private int date;

	public RomRecoveryInfo() {
		super();
	}

	public int getDataWipeCount() {
		return dataWipeCount;
	}

	public void setDataWipeCount(int dataWipeCount) {
		this.dataWipeCount = dataWipeCount;
	}

	public int getCacheWipeCount() {
		return cacheWipeCount;
	}

	public void setCacheWipeCount(int cacheWipeCount) {
		this.cacheWipeCount = cacheWipeCount;
	}

	public int getFacWipeCount() {
		return facWipeCount;
	}

	public void setFacWipeCount(int facWipeCount) {
		this.facWipeCount = facWipeCount;
	}

	public int getForceWipeCount() {
		return forceWipeCount;
	}

	public void setForceWipeCount(int forceWipeCount) {
		this.forceWipeCount = forceWipeCount;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "RomRecoveryInfo [dataWipeCount=" + dataWipeCount + ", cacheWipeCount=" + cacheWipeCount
				+ ", facWipeCount=" + facWipeCount + ", forceWipeCount=" + forceWipeCount + ", date=" + date + "]";
	}

}
