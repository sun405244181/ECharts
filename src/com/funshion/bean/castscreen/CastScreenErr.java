package com.funshion.bean.castscreen;

public class CastScreenErr {

	private String errType;
	private int errCount;
	private int date;

	public CastScreenErr() {
		super();
	}

	public CastScreenErr(String errType, int errCount, int date) {
		super();
		this.errType = errType;
		this.errCount = errCount;
		this.date = date;
	}

	public String getErrType() {
		return errType;
	}

	public void setErrType(String errType) {
		this.errType = errType;
	}

	public int getErrCount() {
		return errCount;
	}

	public void setErrCount(int errCount) {
		this.errCount = errCount;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "CastScreenErr [errType=" + errType + ", errCount=" + errCount + ", date=" + date + "]";
	}
	
	

}
