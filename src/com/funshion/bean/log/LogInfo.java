package com.funshion.bean.log;

public class LogInfo {

	private String romVer;
	private String chipType;
	private String tag;
	private String title;
	private String process;
	private String subject;
	private String content;
	private int count;
	private int status;
	private int date;

	public LogInfo() {
		super();
	}

	public LogInfo(String romVer, String chipType, String tag, String title, String process, String subject,
			String content, int count, int status, int date) {
		super();
		this.romVer = romVer;
		this.chipType = chipType;
		this.tag = tag;
		this.title = title;
		this.process = process;
		this.subject = subject;
		this.content = content;
		this.count = count;
		this.status = status;
		this.date = date;
	}

	public String getRomVer() {
		return romVer;
	}

	public void setRomVer(String romVer) {
		this.romVer = romVer;
	}

	public String getChipType() {
		return chipType;
	}

	public void setChipType(String chipType) {
		this.chipType = chipType;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "LogInfo [romVer=" + romVer + ", chipType=" + chipType + ", tag=" + tag + ", title=" + title
				+ ", process=" + process + ", subject=" + subject + ", content=" + content + ", count=" + count
				+ ", status=" + status + ", date=" + date + "]";
	}

}
