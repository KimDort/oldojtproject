package com.icnman.domain;

import java.sql.Date;

public class LicenseVO {
	private int no;
	private int mno;
	private String name;
	private Date getdate;
	private String level;
	private String publisher;
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getGetdate() {
		return getdate;
	}
	public void setGetdate(Date getdate) {
		this.getdate = getdate;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	
}
