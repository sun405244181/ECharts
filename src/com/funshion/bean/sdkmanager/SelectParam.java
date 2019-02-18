package com.funshion.bean.sdkmanager;

public class SelectParam {
	
	private String param;
	
	public SelectParam() {
		super();
	}

	public SelectParam(String param) {
		super();
		this.param = param;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@Override
	public String toString() {
		return "SelectParam [param=" + param + "]";
	}

}
