package com.jarvit.helper;

public class Match {
	private String desc;
	private String ground;
	private String date;
	private String time;

	public String toString() {
		String str="";
		str="\nMatch Description- "+desc+"\nGround- "+ground+"\nDate- "+date+"\nTime- "+time;
		return str;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getGround() {
		return ground;
	}

	public void setGround(String ground) {
		this.ground = ground;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
