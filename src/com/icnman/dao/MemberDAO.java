package com.icnman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.icnman.domain.ExperienceVO;
import com.icnman.domain.HaveSkillsVO;
import com.icnman.domain.LicenseVO;
import com.icnman.domain.MemberVO;
import com.icnman.tools.DBConn;
import com.icnman.tools.SearchCriteria;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public int exitMember(MemberVO vo){
		conn=DBConn.getConnection();
		String sql="UPDATE EMPLOYEE SET MEMBER_ISEXIT=? WHERE MEMBER_NO=?";
		int success=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, String.valueOf(vo.getMember_isexit()));
			pstmt.setInt(2, vo.getMember_no());
			success=pstmt.executeUpdate();
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
		return success;
	}
	
	public MemberVO read(int mno){
		conn=DBConn.getConnection();
		MemberVO vo=new MemberVO();
		String sql="SELECT * FROM EMPLOYEE WHERE MEMBER_NO = ?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs=pstmt.executeQuery();
			while(rs.next()){
				vo.setMember_no(rs.getInt("MEMBER_NO"));
				vo.setMember_name(rs.getString("MEMBER_NAME"));
				vo.setMember_company(rs.getString("MEMBER_COMPANY"));
				vo.setMember_idnumber(rs.getString("MEMBER_IDNUMBER"));
				vo.setMember_zipcode(rs.getString("MEMBER_ZIPCODE"));
				vo.setMember_address(rs.getString("MEMBER_ADDRESS"));
				vo.setMember_address_detail(rs.getString("MEMBER_ADDRESS_DETAIL"));
				vo.setMember_rank(rs.getString("MEMBER_RANK"));
				vo.setMember_join(rs.getDate("MEMBER_JOIN"));
				vo.setMember_out(rs.getDate("MEMBER_OUT"));
				vo.setMember_isnew(rs.getString("MEMBER_ISNEW"));
				vo.setMember_haveskill(rs.getString("MEMBER_HAVESKILL"));
				vo.setMember_position(rs.getString("MEMBER_POSITION"));
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
		return vo;
	}
	public int totalCount(){
		conn=DBConn.getConnection();
		String sql="SELECT COUNT(*) FROM EMPLOYEE";
		int count=0;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
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
		return count;
	}
	
	public List<MemberVO> list(SearchCriteria cri, String name, String isnew){
		conn=DBConn.getConnection();
		List<MemberVO> list = new ArrayList<>();
		String sql="SELECT * FROM (SELECT ROWNUM AS R,"
					+"MEMBER_NO, MEMBER_NAME, MEMBER_COMPANY, MEMBER_IDNUMBER,"
					+"MEMBER_ZIPCODE, MEMBER_ADDRESS, MEMBER_ADDRESS_DETAIL,"
					+"MEMBER_RANK, MEMBER_JOIN, MEMBER_OUT, MEMBER_ISNEW,"
					+"PROJECT_HISTORY,  MEMBER_HAVESKILL, MEMBER_ISEXIT "
					+"FROM (SELECT MEMBER_NO, MEMBER_NAME, MEMBER_COMPANY, MEMBER_IDNUMBER,"
					+"MEMBER_ZIPCODE, MEMBER_ADDRESS, MEMBER_ADDRESS_DETAIL,"
					+"MEMBER_RANK, MEMBER_JOIN, MEMBER_OUT, MEMBER_ISNEW,"
					+"(SELECT COUNT(*) FROM PROJECT_JOIN WHERE PROJECT_JOIN.MEMBER_NO=EMPLOYEE.MEMBER_NO) AS PROJECT_HISTORY,"
					+" MEMBER_HAVESKILL, MEMBER_ISEXIT "
					+"FROM EMPLOYEE ORDER BY MEMBER_NO DESC"
					+"))WHERE R >= ? AND R <= ? AND MEMBER_ISEXIT = 'N' "
					+"AND MEMBER_NAME LIKE '%' || ? || '%'"
					+"AND MEMBER_ISNEW LIKE '%' || ? || '%' ";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cri.getPage());
			pstmt.setInt(2, cri.getPageEnd());
			pstmt.setString(3, isnew);
			pstmt.setString(4, name);
			rs=pstmt.executeQuery();
			while(rs.next()){
				MemberVO vo= new MemberVO();
				vo.setMember_no(rs.getInt("MEMBER_NO"));
				vo.setMember_name(rs.getString("MEMBER_NAME"));
				vo.setMember_company(rs.getString("MEMBER_COMPANY"));
				vo.setMember_idnumber(rs.getString("MEMBER_IDNUMBER"));
				vo.setMember_zipcode(rs.getString("MEMBER_ZIPCODE"));
				vo.setMember_address(rs.getString("MEMBER_ADDRESS"));
				vo.setMember_address_detail(rs.getString("MEMBER_ADDRESS_DETAIL"));
				vo.setMember_rank(rs.getString("MEMBER_RANK"));
				vo.setMember_join(rs.getDate("MEMBER_JOIN"));
				vo.setMember_out(rs.getDate("MEMBER_OUT"));
				vo.setMember_isnew(rs.getString("MEMBER_ISNEW"));
				vo.setMember_haveskill(rs.getString("MEMBER_HAVESKILL"));
				vo.setMember_projecthistory(rs.getInt("PROJECT_HISTORY"));
				vo.setMember_isexit(rs.getString("MEMBER_ISEXIT").charAt(0));
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
	
	public int maxMember(){
		int result=0;
		conn=DBConn.getConnection();
		String sql="SELECT NVL(MAX(MEMBER_NO),0) FROM EMPLOYEE";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				result=rs.getInt(1);
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
		return result;
	}
	public int updateMember(MemberVO vo, Connection getConn){
		int success=0;
		try {
			getConn.setAutoCommit(false);
			String sql="UPDATE EMPLOYEE SET MEMBER_NAME=?, MEMBER_COMPANY=?, MEMBER_IDNUMBER=?, MEMBER_ZIPCODE=?, MEMBER_ADDRESS=?, "
				+"MEMBER_ADDRESS_DETAIL=?, MEMBER_RANK=?, MEMBER_JOIN=?, MEMBER_OUT=?, MEMBER_ISNEW=?, "
				+"MEMBER_HAVESKILL=?, MEMBER_ISEXIT=?, MEMBER_POSITION=? WHERE MEMBER_NO=?";
			
			pstmt=getConn.prepareStatement(sql);
			pstmt.setString(1, vo.getMember_name());
			pstmt.setString(2, vo.getMember_company());
			pstmt.setString(3, vo.getMember_idnumber());
			pstmt.setString(4, vo.getMember_zipcode());
			pstmt.setString(5, vo.getMember_address());
			pstmt.setString(6, vo.getMember_address_detail());
			pstmt.setString(7, vo.getMember_rank());
			pstmt.setDate(8, vo.getMember_join());
			pstmt.setDate(9, vo.getMember_out());
			pstmt.setString(10, vo.getMember_isnew());
			pstmt.setString(11, vo.getMember_haveskill());
			pstmt.setString(12, String.valueOf(vo.getMember_isexit()));
			pstmt.setString(13, vo.getMember_position());
			pstmt.setInt(14, vo.getMember_no());
			success=pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return success;
	}
	
	public int register(MemberVO vo, Connection getConn){
		int success=0;
		try {
			String sql="INSERT INTO EMPLOYEE VALUES(EMPLOYEESEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt=getConn.prepareStatement(sql);
			pstmt.setString(1, vo.getMember_name());
			pstmt.setString(2, vo.getMember_company());
			pstmt.setString(3, vo.getMember_idnumber());
			pstmt.setString(4, vo.getMember_zipcode());
			pstmt.setString(5, vo.getMember_address());
			pstmt.setString(6, vo.getMember_address_detail());
			pstmt.setString(7, vo.getMember_rank());
			pstmt.setDate(8, vo.getMember_join());
			pstmt.setDate(9, vo.getMember_out());
			pstmt.setString(10, vo.getMember_isnew());
			pstmt.setString(11, vo.getMember_haveskill());	
			pstmt.setString(12, String.valueOf(vo.getMember_isexit()));
			pstmt.setString(13, vo.getMember_position());
			success=pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return success;
	}
	
	public int delete(int mno){
		conn=DBConn.getConnection();
		int success=0;
		String sql="DELETE FROM EMPLOYEE WHERE MEMBER_NO=?";
		try {
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			success=pstmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			// TODO: handle exception
			if(conn!=null)try{conn.rollback();}catch (Exception e2) {}
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
		return success;
	}
}
