package com.icnman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.icnman.domain.ProjectVO;
import com.icnman.tools.SearchCriteria;
import com.icnman.tools.DBConn;

public class ProjectDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public int update(ProjectVO vo){
		int success=0;
		conn=DBConn.getConnection();
		String sql="UPDATE PROJECT SET PROJECT_NAME=?, PROJECT_CONTENT=?, PROJECT_START=?, PROJECT_END=?, "
				+ "PROJECT_ORDER_COMPANY=?, PROJECT_CREATE_SKILL=?, PROJECT_ETC=? WHERE PROJECT_NO=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());
			pstmt.setDate(3, vo.getStart());
			pstmt.setDate(4, vo.getEnd());
			pstmt.setString(5, vo.getOrder_company());
			pstmt.setString(6, vo.getCreate_skill());
			pstmt.setString(7, vo.getEtc());
			pstmt.setInt(8, vo.getNo());
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
	
	public int register(ProjectVO vo){
		int success=0;
		conn=DBConn.getConnection();
		String sql="INSERT INTO PROJECT VALUES((SELECT NVL(MAX(PROJECT_NO),0)+1 FROM PROJECT)"
				+ ", ?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());
			pstmt.setDate(3, vo.getStart());
			pstmt.setDate(4, vo.getEnd());
			pstmt.setString(5, vo.getOrder_company());
			pstmt.setString(6, vo.getCreate_skill());
			pstmt.setString(7, vo.getEtc());
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
	public List<ProjectVO> list(int countNum){
		List<ProjectVO> list=new ArrayList<ProjectVO>();
		conn=DBConn.getConnection();
		String sql="SELECT NO, NAME, MINMEMBER, MAXMEMBER, STARTDAY, ENDDAY, JOINCOMPANY, SIZE,"
				+"(SELECT COUNT(*) FROM PROJECTWORK, PROJECT WHERE PROJECTWORK.PNO=PROJECT.NO) AS INMEMBER "
				+"FROM PROJECT WHERE NO > 0 LIMIT ?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, countNum);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ProjectVO vo=new ProjectVO();
				vo.setNo(rs.getInt("PROJECT_NO"));
				vo.setName(rs.getString("PROJECT_NAME"));
				vo.setContent(rs.getString("PROJECT_CONTENT"));
				vo.setStart(rs.getDate("PROJECT_START"));
				vo.setEnd(rs.getDate("PROJECT_END"));
				vo.setOrder_company(rs.getString("PROJECT_ORDER_COMPANY"));
				vo.setCreate_skill(rs.getString("PROJECT_CREATE_SKILL"));
				vo.setEtc(rs.getString("PROJECT_ETC"));
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
	
	public List<ProjectVO> list(SearchCriteria cri){
		List<ProjectVO> list = new ArrayList<>();
		conn=DBConn.getConnection();
		String sql="SELECT PROJECT_NO, PROJECT_NAME, PROJECT_CONTENT, PROJECT_START, PROJECT_END, "
					+"PROJECT_ORDER_COMPANY, PROJECT_CREATE_SKILL, PROJECT_ETC, PROJECT_ISDELETE FROM "
					+"(SELECT ROWNUM AS R, PROJECT_NO, PROJECT_NAME, PROJECT_CONTENT, PROJECT_START, "
					+"PROJECT_END, PROJECT_ORDER_COMPANY, PROJECT_CREATE_SKILL, PROJECT_ETC, PROJECT_ISDELETE FROM "
					+"(SELECT PROJECT_NO, PROJECT_NAME, PROJECT_CONTENT, PROJECT_START, PROJECT_END, "
					+"PROJECT_ORDER_COMPANY, PROJECT_CREATE_SKILL, PROJECT_ETC, PROJECT_ISDELETE FROM PROJECT ORDER BY PROJECT_NO DESC))"
					+"WHERE PROJECT_NO > 0 AND PROJECT_ISDELETE != 'Y' AND R >=? AND R <= ?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cri.getPageStart());
			pstmt.setInt(2, cri.getPerPageNum());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ProjectVO vo=new ProjectVO();
				vo.setNo(rs.getInt("PROJECT_NO"));
				vo.setName(rs.getString("PROJECT_NAME"));
				vo.setContent(rs.getString("PROJECT_CONTENT"));
				vo.setStart(rs.getDate("PROJECT_START"));
				vo.setEnd(rs.getDate("PROJECT_END"));
				vo.setOrder_company(rs.getString("PROJECT_ORDER_COMPANY"));
				vo.setCreate_skill(rs.getString("PROJECT_CREATE_SKILL"));
				vo.setEtc(rs.getString("PROJECT_ETC"));
				vo.setIsdelete(rs.getString("PROJECT_ISDELETE").charAt(0));
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
	
	public ProjectVO read(int no){
		conn=DBConn.getConnection();
		String sql="SELECT * FROM PROJECT WHERE PROJECT_NO=?";
		ProjectVO vo = new ProjectVO();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			while(rs.next()){
				vo.setNo(rs.getInt("PROJECT_NO"));
				vo.setName(rs.getString("PROJECT_NAME"));
				vo.setContent(rs.getString("PROJECT_CONTENT"));
				vo.setStart(rs.getDate("PROJECT_START"));
				vo.setEnd(rs.getDate("PROJECT_END"));
				vo.setOrder_company(rs.getString("PROJECT_ORDER_COMPANY"));
				vo.setCreate_skill(rs.getString("PROJECT_CREATE_SKILL"));
				vo.setEtc(rs.getString("PROJECT_ETC"));
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
		int count=0;
		conn=DBConn.getConnection();
		String sql="SELECT COUNT(*) FROM PROJECT";
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
}
