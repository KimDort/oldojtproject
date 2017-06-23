package com.icnman.domain;

import java.sql.Date;

public class ProjectVO {
	private int no;
	private String name;
	private String content;
	private Date start;
	private Date end;
	private String order_company;
	private String create_skill;
	private String etc;
	private char isdelete='N';
	
	public char getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(char isdelete) {
		this.isdelete = isdelete;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getOrder_company() {
		return order_company;
	}
	public void setOrder_company(String order_company) {
		this.order_company = order_company;
	}
	public String getCreate_skill() {
		return create_skill;
	}
	public void setCreate_skill(String create_skill) {
		this.create_skill = create_skill;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	
}
