package com.icnman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.icnman.domain.EducationVO;
import com.icnman.tools.DBConn;

public class EducationDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public int register(List<EducationVO> list){
		int success=0;
		conn=DBConn.getConnection();
		String sql="INSERT INTO EDUCATION VALUES((SELECT IFNULL(MAX(NO),0)+1 FROM EDUCATION as e),?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			for(int i=0;i<list.size();i++){
				pstmt.setInt(1, list.get(i).getMno());
				pstmt.setString(2, list.get(i).getEdu_time());
				pstmt.setString(3, list.get(i).getEdu_name());
				pstmt.setString(4, list.get(i).getEdu_major());
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
