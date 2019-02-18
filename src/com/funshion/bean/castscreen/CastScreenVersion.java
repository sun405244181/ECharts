package com.funshion.bean.castscreen;

public class CastScreenVersion {

	private String appVersion;

	private String protocalId;

	public CastScreenVersion() {
		super();
	}

	public CastScreenVersion(String appVersion, String protocalId) {
		super();
		this.appVersion = appVersion;
		this.protocalId = protocalId;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getProtocalId() {
		return protocalId;
	}

	public void setProtocalId(String protocalId) {
		this.protocalId = protocalId;
	}

	@Override
	public String toString() {
		return "CastScreenVersion [appVersion=" + appVersion + ", protocalId=" + protocalId + "]";
	}

}
