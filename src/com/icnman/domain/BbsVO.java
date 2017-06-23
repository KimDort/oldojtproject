package com.icnman.domain;

public class BbsVO {
	private int no;
	private String title;
	private String content;
	private String writer;
	private String tomember;
	private String regdate;
	private String file_oriname;
	private String file_copyname;
	private int file_size;
	private char isread='N';
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getFile_oriname() {
		return file_oriname;
	}
	public void setFile_oriname(String file_oriname) {
		this.file_oriname = file_oriname;
	}
	public String getFile_copyname() {
		return file_copyname;
	}
	public void setFile_copyname(String file_copyname) {
		this.file_copyname = file_copyname;
	}
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	public char getIsread() {
		return isread;
	}
	public void setIsread(char isread) {
		this.isread = isread;
	}
	public String getTomember() {
		return tomember;
	}
	public void setTomember(String tomember) {
		this.tomember = tomember;
	}
	
}
