package com.funshion.bean.sdkmanager;

public class DownloadTaskInfo {

	private String task;
	private int count;
	private int date;

	public DownloadTaskInfo() {
		super();
	}

	public DownloadTaskInfo(String task, int count, int date) {
		super();
		this.task = task;
		this.count = count;
		this.date = date;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "DownloadApkInfo [task=" + task + ", count=" + count + ", date=" + date + "]";
	}

}
