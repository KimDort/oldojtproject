package com.icnman.domain;

public class ProjectWorkingVO extends ProjectVO{
	private int no;
	private int mno;
	private int pno;
	private String position;
	private String joinday;
	private String outday;
	private String member;
	
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
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
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getJoinday() {
		return joinday;
	}
	public void setJoinday(String joinday) {
		this.joinday = joinday;
	}
	public String getOutday() {
		return outday;
	}
	public void setOutday(String outday) {
		this.outday = outday;
	}
	
}
