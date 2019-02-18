package com.funshion.bean.romrecovery;

public class SelectParam {

	private String type;
	private String param;

	public SelectParam() {
		super();
	}

	public SelectParam(String type, String param) {
		super();
		this.type = type;
		this.param = param;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@Override
	public String toString() {
		return "SelectParam [type=" + type + ", param=" + param + "]";
	}

}
