package com.funshion.bean.fastapp;

public class FastAppPredictRatio {

	private String date;
	private double appOpenTimesRatio;
	private double targetUserRatio;
	private double targetUserAppRatio;

	public FastAppPredictRatio() {
		super();
	}

	public FastAppPredictRatio(String date, double appOpenTimesRatio, double targetUserRatio,
			double targetUserAppRatio) {
		super();
		this.date = date;
		this.appOpenTimesRatio = appOpenTimesRatio;
		this.targetUserRatio = targetUserRatio;
		this.targetUserAppRatio = targetUserAppRatio;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getAppOpenTimesRatio() {
		return appOpenTimesRatio;
	}

	public void setAppOpenTimesRatio(double appOpenTimesRatio) {
		this.appOpenTimesRatio = appOpenTimesRatio;
	}

	public double getTargetUserRatio() {
		return targetUserRatio;
	}

	public void setTargetUserRatio(double targetUserRatio) {
		this.targetUserRatio = targetUserRatio;
	}

	public double getTargetUserAppRatio() {
		return targetUserAppRatio;
	}

	public void setTargetUserAppRatio(double targetUserAppRatio) {
		this.targetUserAppRatio = targetUserAppRatio;
	}

	@Override
	public String toString() {
		return "FastAppPredictRatio [date=" + date + ", appOpenTimesRatio=" + appOpenTimesRatio + ", targetUserRatio="
				+ targetUserRatio + ", targetUserAppRatio=" + targetUserAppRatio + "]";
	}

}
