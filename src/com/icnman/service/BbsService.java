package com.icnman.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icnman.dao.BbsDAO;
import com.icnman.domain.BbsVO;
import com.icnman.tools.SearchCriteria;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BbsService {
	private BbsDAO dao = new BbsDAO();
	private String savePath = "C:/worktool/fileUpload/";
	private int fileMaxSize = 100 * 1024 * 1024;
	
	public List<BbsVO> bbsList(int contentNum) {
		return dao.bbsList(contentNum);
	}

	public List<BbsVO> bbsList(SearchCriteria cri) {
		return dao.bbsList(cri);
	}

	public int totalBbsList() {
		return dao.totalBbsList();
	}

	public int isReadCount(int id) {
		return dao.isReadCount(id);
	}

	public void download(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String fileName = req.getParameter("fileName");
		String filePath = "C:/worktool/fileUpload/";
		File file = new File(filePath + fileName);

		if (file.exists() && file.isFile()) {
			try {
				req.setCharacterEncoding("UTF-8");
				byte b[] = new byte[4096];

				resp.reset();
				resp.setContentType("application/octet-stream");

				String encoding = new String(fileName.getBytes("UTF-8"), "8859_1");
				resp.setHeader("Content-Disposition", "attachment; filename=" + encoding);

				FileInputStream is = new FileInputStream(file);
				ServletOutputStream sops = resp.getOutputStream();

				int numRead;

				while ((numRead = is.read(b, 0, b.length)) != -1) {
					sops.write(b, 0, numRead);
				}
				sops.flush();
				sops.close();
				is.close();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("에러");
		}
	}

	public BbsVO read(int no) {
		BbsVO vo = dao.read(no);

		return vo;
	}

	public int update(HttpServletRequest req, HttpServletResponse resp, int no) {
		int success=0;
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
		return success;
	}

	public String register(BbsVO vo, HttpServletRequest req) {
		String redirectUrl="";
		MultipartRequest multi=fileUpload(req);
		vo.setTitle(multi.getParameter("title"));
		vo.setContent(multi.getParameter("content").replaceAll("\r\n", "<br>"));
		vo.setWriter(multi.getParameter("writer"));
		vo.setTomember(multi.getParameter("tomember"));
		try {
			if(multi.getFile("file").exists()){
				vo.setFile_oriname(multi.getOriginalFileName("file"));
				vo.setFile_copyname(multi.getFilesystemName("file"));
				vo.setFile_size((int) multi.getFile("file").length());
			}else{
				vo.setFile_oriname("");
				vo.setFile_copyname("");
				vo.setFile_size(0);
			}
			
			if (dao.register(vo) == 1) {
				redirectUrl=req.getContextPath()+"/bbs/list.do?page="+multi.getParameter("page");
				redirectUrl+="&perPageNum="+multi.getParameter("perPageNum");
			}else{
				multi.getFile("file").delete();
				redirectUrl=req.getContextPath()+"/error/error.do";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return redirectUrl;
	}

	public int delete(int no) {
		return dao.delete(no);
	}

	public MultipartRequest fileUpload(HttpServletRequest req) {
		MultipartRequest multi=null;
		try {
			multi = new MultipartRequest(req, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return multi;
	}
}
