package com.funshion.bean.bt;

public class BTPairInfo {

	private int sucess;
	private int fail;
	private int date;

	public BTPairInfo() {
		super();
	}

	public BTPairInfo(int sucess, int fail, int date) {
		super();
		this.sucess = sucess;
		this.fail = fail;
		this.date = date;
	}

	public int getSucess() {
		return sucess;
	}

	public void setSucess(int sucess) {
		this.sucess = sucess;
	}

	public int getFail() {
		return fail;
	}

	public void setFail(int fail) {
		this.fail = fail;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "BTPairInfo [sucess=" + sucess + ", fail=" + fail + ", date=" + date + "]";
	}

}
