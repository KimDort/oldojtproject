package com.icnman.service;

import java.sql.Connection;
import java.util.List;

import com.icnman.dao.ExperienceDAO;
import com.icnman.domain.ExperienceVO;
import com.icnman.domain.MemberVO;

public class ExperienceService {
	private ExperienceDAO dao = new ExperienceDAO();
	public int register(List<ExperienceVO> list, Connection getConn){
		return dao.register(list, getConn);
	}
	
	public List<ExperienceVO> read(int mno){
		return dao.read(mno);
	}
	
	public List<ExperienceVO> list(){
		return dao.list();
	}
	
	public void delete(int mno, Connection conn){
		dao.delete(mno, conn);
	}
	
	public int maxNumber(){
		return dao.maxNumber();
	}

	public int update(List<ExperienceVO> reqCareerList, MemberVO vo, Connection conn) {
		// TODO Auto-generated method stub
		return dao.update(reqCareerList, vo, conn);
	}
}
