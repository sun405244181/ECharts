package com.funshion.bean.sdkmanager;

public class AppDownloadInstallInfo {

	private int id;
	private String romVer;
	private String sdkVer;
	private String app;
	private String appName;
	private String appVer;
	private int downloadStart;
	private int downloadSucess;
	private int installSucess;
	private int date;

	public AppDownloadInstallInfo() {
	}

	public AppDownloadInstallInfo(int id, String romVer, String sdkVer, String app, String appName, String appVer,
			int downloadStart, int downloadSucess, int installSucess, int date) {
		super();
		this.id = id;
		this.romVer = romVer;
		this.sdkVer = sdkVer;
		this.app = app;
		this.appName = appName;
		this.appVer = appVer;
		this.downloadStart = downloadStart;
		this.downloadSucess = downloadSucess;
		this.installSucess = installSucess;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
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

	public int getDownloadStart() {
		return downloadStart;
	}

	public void setDownloadStart(int downloadStart) {
		this.downloadStart = downloadStart;
	}

	public int getDownloadSucess() {
		return downloadSucess;
	}

	public void setDownloadSucess(int downloadSucess) {
		this.downloadSucess = downloadSucess;
	}

	public int getInstallSucess() {
		return installSucess;
	}

	public void setInstallSucess(int installSucess) {
		this.installSucess = installSucess;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "AppInstallInfo [id=" + id + ", romVer=" + romVer + ", sdkVer=" + sdkVer + ", app=" + app + ", appName="
				+ appName + ", appVer=" + appVer + ", downloadStart=" + downloadStart + ", downloadSucess="
				+ downloadSucess + ", installSucess=" + installSucess + ", date=" + date + "]";
	}
}
