package com.icnman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.icnman.domain.BbsVO;
import com.icnman.tools.SearchCriteria;
import com.icnman.tools.DBConn;

public class BbsDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int isReadCount(int id){
		int count=0;
		conn=DBConn.getConnection();
		String sql="SELECT COUNT(*) FROM BBS WHERE TOMEMBER=? AND isRead='N'";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
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
	public BbsVO read(int no){
		BbsVO vo=new BbsVO();
		conn=DBConn.getConnection();
		String sql = "SELECT bb.no, bb.title, bb.content, bb.name as tomember, member.name as writer," 
						+"bb.regdate, bb.file_oriname, bb.file_copyname,"
						+"bb.file_size, bb.isRead FROM MEMBER," 
						+"(SELECT BBS.no, BBS.title, BBS.content, BBS.tomember, BBS.writer, BBS.regdate, "
						+"BBS.file_oriname, BBS.file_copyname, BBS.file_size, BBS.isRead, member.name"
						+" FROM BBS, MEMBER WHERE BBS.tomember = member.no)"
						+"as bb where bb.writer=member.no AND bb.no = ?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			while(rs.next()){
				vo.setNo(rs.getInt("bb.no"));
				vo.setTitle(rs.getString("bb.title"));
				vo.setContent(rs.getString("bb.content"));
				vo.setWriter(rs.getString("writer"));
				vo.setTomember(rs.getString("tomember"));
				vo.setRegdate(rs.getString("bb.regdate"));
				vo.setFile_oriname(rs.getString("bb.file_oriname"));
				vo.setFile_copyname(rs.getString("bb.file_copyname"));
				vo.setFile_size(rs.getInt("bb.file_size"));
				vo.setIsread(rs.getString("bb.isRead").charAt(0));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
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
	public List<BbsVO> bbsList(int contentNum) {
		List<BbsVO> list = new ArrayList<>();
		conn = DBConn.getConnection();
		String sql = "SELECT * FROM BBS ORDER BY NO DESC LIMIT ?";
		try {
			list = new ArrayList<>();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, contentNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BbsVO vo=new BbsVO();
				vo.setNo(rs.getInt("no"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setRegdate(rs.getString("regdate"));
				list.add(vo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
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

	public List<BbsVO> bbsList(SearchCriteria cri) {
		List<BbsVO> list = new ArrayList<>();
		conn = DBConn.getConnection();
		String sql = "SELECT * FROM BBS INNER JOIN MEMBER ON BBS.WRITER=MEMBER.NO "
				+ "WHERE BBS.NO > 0 ORDER BY BBS.NO DESC LIMIT ?, ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cri.getPageStart());
			pstmt.setInt(2, cri.getPerPageNum());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BbsVO vo=new BbsVO();
				vo.setNo(rs.getInt("bbs.no"));
				vo.setTitle(rs.getString("bbs.title"));
				vo.setContent(rs.getString("bbs.content"));
				vo.setWriter(rs.getString("member.name"));
				vo.setTomember(rs.getString("bbs.tomember"));
				vo.setRegdate(rs.getString("bbs.regdate"));
				vo.setIsread(rs.getString("bbs.isread").charAt(0));
				list.add(vo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println(e2);
			}
		}
		return list;
	}

	public int register(BbsVO vo) {
		int success = 0;
		conn = DBConn.getConnection();
		String sql = "INSERT INTO BBS VALUES((SELECT IFNULL(MAX(NO),0)+1 FROM BBS AS NOTICE), ?, ?, ?, ?, NOW(), ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.setString(4, vo.getTomember());
			pstmt.setString(5, vo.getFile_oriname());
			pstmt.setString(6, vo.getFile_copyname());
			pstmt.setInt(7, vo.getFile_size());
			pstmt.setString(8, String.valueOf(vo.getIsread()));
			success = pstmt.executeUpdate();
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
	
	public int totalBbsList(){
		int totalCount=0;
		conn=DBConn.getConnection();
		String sql="SELECT COUNT(*) FROM BBS";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				totalCount=rs.getInt(1);
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
		return totalCount;
	}
	
	public int update(int no){
		int success = 0;
		conn = DBConn.getConnection();
		String sql="UPDATE SET BBS TITLE=?, CONTENT=?, TOMEMBER=?";
		try {
			pstmt=conn.prepareStatement(sql);
			success=pstmt.executeUpdate();
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
		return success;
	}
	
	public int delete(int no){
		int success = 0;
		conn = DBConn.getConnection();
		String sql="DELETE FROM BBS WHERE NO=? ";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			success=pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
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
