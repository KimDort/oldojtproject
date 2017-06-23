package com.icnman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.icnman.tools.DBConn;

public class LoginDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public int checkLoing(String id, String pass){
		int count=0;
		conn=DBConn.getConnection();
		String sql="SELECT count(id) FROM MEMBER WHERE id=? AND password=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
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
				if(pstmt!=null)rs.close();
				if(conn!=null)rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return count;
	}
}
