package com.icnman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.icnman.domain.ExperienceVO;
import com.icnman.domain.MemberVO;
import com.icnman.tools.DBConn;

public class ExperienceDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public void delete(int mno, Connection getConn){
		String sql = "DELETE FROM CAREER WHERE MEMBER_NO = ?";
		try {
			pstmt=getConn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			pstmt.executeUpdate();				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<ExperienceVO> list(){
		conn=DBConn.getConnection();
		List<ExperienceVO> list = new ArrayList<>();
		String sql="SELECT CAREER_NO, MEMBER_NO, CAREER_PERIOD_START, NVL(CAREER_PERIOD_END,SYSDATE)AS CAREER_PERIOD_END"
				+ ",CAREER_COMPANY, CAREER_RANK, CAREER_POSITION FROM CAREER";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ExperienceVO vo = new ExperienceVO();
				vo.setNo(rs.getInt("CAREER_NO"));
				vo.setMno(rs.getInt("MEMBER_NO"));
				vo.setPeriod_start(rs.getDate("CAREER_PERIOD_START"));
				vo.setPeriod_end(rs.getDate("CAREER_PERIOD_END"));
				vo.setCompany(rs.getString("CAREER_COMPANY"));
				vo.setRank(rs.getString("CAREER_RANK"));
				vo.setPosition(rs.getString("CAREER_POSITION"));
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
	public int maxNumber(){
		conn=DBConn.getConnection();
		int maxNum=0;
		String sql="(SELECT NVL(MAX(CAREER_NO),0)+1 FROM CAREER)";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				maxNum=rs.getInt(1);
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
		return maxNum;
	}
	public List<ExperienceVO> read(int mno){
		List<ExperienceVO> list = new ArrayList<>();
		conn=DBConn.getConnection();
		String sql="SELECT CAREER_NO, MEMBER_NO, CAREER_PERIOD_START, NVL(CAREER_PERIOD_END,SYSDATE)AS CAREER_PERIOD_END"
				+ ",CAREER_COMPANY, CAREER_RANK, CAREER_POSITION FROM CAREER WHERE MEMBER_NO=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ExperienceVO vo = new ExperienceVO();
				vo.setNo(rs.getInt("CAREER_NO"));
				vo.setMno(rs.getInt("MEMBER_NO"));
				vo.setPeriod_start(rs.getDate("CAREER_PERIOD_START"));
				vo.setPeriod_end(rs.getDate("CAREER_PERIOD_END"));
				vo.setCompany(rs.getString("CAREER_COMPANY"));
				vo.setRank(rs.getString("CAREER_RANK"));
				vo.setPosition(rs.getString("CAREER_POSITION"));
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
	public int update(List<ExperienceVO> list, MemberVO vo,Connection getConn){
		int success=0;
		try {		
			String sql="INSERT INTO CAREER VALUES((SELECT NVL(MAX(CAREER_NO),0)+1 FROM CAREER), ?, ?, ?, ?, ?, ?)";
			for(int idx=0;idx<list.size();idx++){
				pstmt=getConn.prepareStatement(sql);
				pstmt.setInt(1, vo.getMember_no());
				pstmt.setDate(2, list.get(idx).getPeriod_start());
				pstmt.setDate(3, list.get(idx).getPeriod_end());
				pstmt.setString(4, list.get(idx).getCompany());
				pstmt.setString(5, list.get(idx).getRank());
				pstmt.setString(6, list.get(idx).getPosition());
				success=pstmt.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return success;
	}
	public int register(List<ExperienceVO> list, Connection getConn){
		int success=0;
		try {		
			String sql="INSERT INTO CAREER VALUES((SELECT NVL(MAX(CAREER_NO),0)+1 FROM CAREER), (EMPLOYEESEQ.CURRVAL), ?, ?, ?, ?, ?)";
			for(int idx=0;idx<list.size();idx++){
				pstmt=getConn.prepareStatement(sql);
				pstmt.setDate(1, list.get(idx).getPeriod_start());
				pstmt.setDate(2, list.get(idx).getPeriod_end());
				pstmt.setString(3, list.get(idx).getCompany());
				pstmt.setString(4, list.get(idx).getRank());
				pstmt.setString(5, list.get(idx).getPosition());
				success=pstmt.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return success;
	}
}
