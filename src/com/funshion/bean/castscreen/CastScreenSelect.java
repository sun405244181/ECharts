package com.funshion.bean.castscreen;

public class CastScreenSelect {

	private String type;
	private String param;

	public CastScreenSelect() {
		super();
	}

	public CastScreenSelect(String type, String param) {
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
