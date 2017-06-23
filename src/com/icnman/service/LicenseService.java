package com.icnman.service;

import java.sql.Connection;
import java.util.List;

import com.icnman.dao.LicenseDAO;
import com.icnman.domain.LicenseVO;
import com.icnman.domain.MemberVO;

public class LicenseService {
	private LicenseDAO dao = new LicenseDAO();
	
	public int register(List<LicenseVO> list, Connection getConn){
		return dao.register(list, getConn);
	}
	
	public List<LicenseVO> read(int mno){
		return dao.read(mno);
	}
	
	public void delete(int mno, Connection conn){
		dao.delete(mno, conn);
	}

	public int update(List<LicenseVO> reqLicenseList, MemberVO vo, Connection conn) {
		// TODO Auto-generated method stub
		return dao.update(reqLicenseList, vo, conn);
	}
}
