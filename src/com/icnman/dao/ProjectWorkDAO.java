package com.icnman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.icnman.domain.ProjectWorkingVO;
import com.icnman.tools.DBConn;
import com.icnman.tools.SearchCriteria;

public class ProjectWorkDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public int register(ProjectWorkingVO vo){
		int success=0;
		conn=DBConn.getConnection();
		String sql="INSERT INTO projectwork VALUES((SELECT IFNULL(MAX(NO),0)+1 as max FROM projectwork as pw), ?, ?, ?, ?, ?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMno());
			pstmt.setInt(2, vo.getPno());
			pstmt.setString(3, vo.getPosition());
			pstmt.setString(4, vo.getJoinday());
			pstmt.setString(5, vo.getOutday());
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
	
	public List<ProjectWorkingVO> list(SearchCriteria cri){
		List<ProjectWorkingVO> list=new ArrayList<>();
		conn=DBConn.getConnection();
		String sql="select  *,(select name from project where project.no=projectwork.pno)as name, "
				+ "(select name from member where member.no=projectwork.mno) as member "
				+ "from projectwork where no > 0 order by no desc limit ?, ?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cri.getPageStart());
			pstmt.setInt(2, cri.getPerPageNum());
			rs=pstmt.executeQuery();
			while(rs.next()){
				ProjectWorkingVO vo = new ProjectWorkingVO();
				vo.setNo(rs.getInt("no"));
				vo.setMno(rs.getInt("mno"));
				vo.setPno(rs.getInt("pno"));
				vo.setPosition(rs.getString("position"));
				vo.setJoinday(rs.getString("joinday"));
				vo.setOutday(rs.getString("outday"));
				vo.setName(rs.getString("name"));
				vo.setMember(rs.getString("member"));
				list.add(vo);
			}
		} catch (Exception e) {
			// TODO: handle exception
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
	public int totalCount(){
		int count=0;
		conn=DBConn.getConnection();
		String sql = "SELECT COUNT(*) FROM PROJECTWORK";
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
