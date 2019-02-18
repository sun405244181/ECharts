package com.funshion.bean.bt;

public class BTStatusInfo {

	private int total;
	private int ui;
	private int pair;
	private int callback;
	private int date;

	public BTStatusInfo() {
		super();
	}

	public BTStatusInfo(int total, int ui, int pair, int callback, int date) {
		super();
		this.total = total;
		this.ui = ui;
		this.pair = pair;
		this.callback = callback;
		this.date = date;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getUi() {
		return ui;
	}

	public void setUi(int ui) {
		this.ui = ui;
	}

	public int getPair() {
		return pair;
	}

	public void setPair(int pair) {
		this.pair = pair;
	}

	public int getCallback() {
		return callback;
	}

	public void setCallback(int callback) {
		this.callback = callback;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "BTStatusInfo [total=" + total + ", ui=" + ui + ", pair=" + pair + ", callback=" + callback + ", date="
				+ date + "]";
	}

}
