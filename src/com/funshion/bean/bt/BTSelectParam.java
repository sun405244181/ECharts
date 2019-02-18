package com.funshion.bean.bt;

public class BTSelectParam {

	private String type;
	private String param;

	public BTSelectParam() {
		super();
	}

	public BTSelectParam(String type, String param) {
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
		return "BTSelectParam [type=" + type + ", param=" + param + "]";
	}

}
