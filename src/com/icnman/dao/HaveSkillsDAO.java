package com.icnman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.icnman.domain.HaveSkillsVO;
import com.icnman.tools.DBConn;

public class HaveSkillsDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public int register(List<HaveSkillsVO> list){
		int success=0;
		conn=DBConn.getConnection();
		String sql="INSERT INTO HAVESKILL VALUES((SELECT IFNULL(MAX(NO),0)+1 FROM HAVESKILL as h),?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			for(int i=0;i<list.size();i++){
				pstmt.setInt(1, list.get(i).getMno());
				pstmt.setString(2, list.get(i).getName());
				pstmt.setString(3, list.get(i).getSkilllevel());
				success=pstmt.executeUpdate();
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
		return success;
	}
}
