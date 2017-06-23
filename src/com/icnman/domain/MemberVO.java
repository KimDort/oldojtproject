package com.icnman.domain;

import java.sql.Date;

public class MemberVO {
	private int member_no;
	private String member_name;
	private String member_company;
	private String member_idnumber;
	private String member_zipcode;
	private String member_address;
	private String member_address_detail;
	private String member_rank;
	private Date member_join;
	private Date member_out;
	private String member_isnew;
	private String member_haveskill;
	private String member_position;
	private char member_isexit='N';
	private int member_projecthistory;
	
	public int getMember_projecthistory() {
		return member_projecthistory;
	}
	public void setMember_projecthistory(int member_projecthistory) {
		this.member_projecthistory = member_projecthistory;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_company() {
		return member_company;
	}
	public void setMember_company(String member_company) {
		this.member_company = member_company;
	}
	public String getMember_idnumber() {
		return member_idnumber;
	}
	public void setMember_idnumber(String member_idnumber) {
		this.member_idnumber = member_idnumber;
	}
	public String getMember_zipcode() {
		return member_zipcode;
	}
	public void setMember_zipcode(String member_zipcode) {
		this.member_zipcode = member_zipcode;
	}
	public String getMember_address() {
		return member_address;
	}
	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}
	public String getMember_address_detail() {
		return member_address_detail;
	}
	public void setMember_address_detail(String member_address_detail) {
		this.member_address_detail = member_address_detail;
	}
	public String getMember_rank() {
		return member_rank;
	}
	public void setMember_rank(String member_rank) {
		this.member_rank = member_rank;
	}
	public Date getMember_join() {
		return member_join;
	}
	public void setMember_join(Date member_join) {
		this.member_join = member_join;
	}
	public Date getMember_out() {
		return member_out;
	}
	public void setMember_out(Date member_out) {
		this.member_out = member_out;
	}
	public String getMember_isnew() {
		return member_isnew;
	}
	public void setMember_isnew(String member_isnew) {
		this.member_isnew = member_isnew;
	}
	public String getMember_haveskill() {
		return member_haveskill;
	}
	public void setMember_haveskill(String member_haveskill) {
		this.member_haveskill = member_haveskill;
	}
	public String getMember_position() {
		return member_position;
	}
	public void setMember_position(String member_position) {
		this.member_position = member_position;
	}
	public char getMember_isexit() {
		return member_isexit;
	}
	public void setMember_isexit(char member_isexit) {
		this.member_isexit = member_isexit;
	}
	
}
