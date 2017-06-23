package com.icnman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.icnman.domain.LicenseVO;
import com.icnman.domain.MemberVO;
import com.icnman.tools.DBConn;

public class LicenseDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	public void delete(int mno, Connection getConn){
		String sql="DELETE FROM LICENSE WHERE MEMBER_NO=?";
		try {
			pstmt=getConn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			pstmt.executeUpdate();			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public List<LicenseVO> read(int mno){
		List<LicenseVO> list = new ArrayList<>();
		conn=DBConn.getConnection();
		String sql="SELECT * FROM LICENSE WHERE MEMBER_NO=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs=pstmt.executeQuery();
			while(rs.next()){
				LicenseVO vo = new LicenseVO();
				vo.setNo(rs.getInt("LICENSE_NO"));
				vo.setMno(rs.getInt("MEMBER_NO"));
				vo.setName(rs.getString("LICENSE_NAME"));
				vo.setLevel(rs.getString("LICENSE_LEVEL"));
				vo.setGetdate(rs.getDate("LICENSE_GETDATE"));
				vo.setPublisher(rs.getString("LICENSE_PUBLISHER"));
				list.add(vo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return list;
	}
	
	public int register(List<LicenseVO> list, Connection getConn){
		int success=0;
		String sql="INSERT INTO LICENSE VALUES((SELECT NVL(MAX(LICENSE_NO),0)+1 FROM LICENSE),"
				+ "(EMPLOYEESEQ.CURRVAL), ?, ?, ?, ?)";
		try {
			for(int i=0;i<list.size();i++){
				pstmt=getConn.prepareStatement(sql);
				pstmt.setString(1, list.get(i).getName());
				pstmt.setString(2, list.get(i).getLevel());
				pstmt.setDate(3, list.get(i).getGetdate());
				pstmt.setString(4, list.get(i).getPublisher());
				success=pstmt.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return success;
	}
	public int update(List<LicenseVO> list, MemberVO vo, Connection getConn){
		int success=0;
		String sql="INSERT INTO LICENSE VALUES((SELECT NVL(MAX(LICENSE_NO),0)+1 FROM LICENSE),"
				+ "?, ?, ?, ?, ?)";
		try {
			for(int i=0;i<list.size();i++){
				pstmt=getConn.prepareStatement(sql);
				pstmt.setInt(1, vo.getMember_no());
				pstmt.setString(2, list.get(i).getName());
				pstmt.setString(3, list.get(i).getLevel());
				pstmt.setDate(4, list.get(i).getGetdate());
				pstmt.setString(5, list.get(i).getPublisher());
				success=pstmt.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return success;
	}
}
