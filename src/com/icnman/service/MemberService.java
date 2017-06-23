package com.icnman.service;

import java.sql.Connection;
import java.util.List;

import com.icnman.dao.MemberDAO;
import com.icnman.domain.ExperienceVO;
import com.icnman.domain.LicenseVO;
import com.icnman.domain.MemberVO;
import com.icnman.tools.SearchCriteria;

public class MemberService {
	private MemberDAO dao= new MemberDAO();

	public int maxMember(){
		return dao.maxMember();
	}
	public int register(MemberVO vo, Connection conn){
		return dao.register(vo, conn);
	}
	
	public List<MemberVO> list(SearchCriteria cri, String name, String isnew){
		return dao.list(cri, name, isnew);
	}
	
	public int totalCount(){
		return dao.totalCount();
	}
	
	public MemberVO read(int mno){
		return dao.read(mno);
	}
	public int delete(int mno){
		return dao.delete(mno);
	}
	public int modifyMember(MemberVO vo, Connection conn){
		return dao.updateMember(vo, conn);
	}
	public int exitMember(MemberVO vo){
		return dao.exitMember(vo);
	}
}
