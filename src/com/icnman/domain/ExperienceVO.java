package com.icnman.domain;

import java.sql.Date;

public class ExperienceVO {
	private int no;
	private int mno;
	private Date period_start;
	private Date period_end;
	private String company;
	private String rank;
	private String position;
	
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Date getPeriod_start() {
		return period_start;
	}
	public void setPeriod_start(Date period_start) {
		this.period_start = period_start;
	}
	public Date getPeriod_end() {
		return period_end;
	}
	public void setPeriod_end(Date period_end) {
		this.period_end = period_end;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
}
