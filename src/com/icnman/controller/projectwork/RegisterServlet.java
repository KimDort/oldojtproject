package com.icnman.controller.projectwork;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icnman.domain.ProjectWorkingVO;
import com.icnman.service.ProjectWorkService;

public class RegisterServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3411819906623244872L;
	private ProjectWorkService projectWorkService= new ProjectWorkService();
	private ProjectWorkingVO vo = new ProjectWorkingVO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp){
		int page=Integer.parseInt(req.getParameter("page"));
		int perPageNum=Integer.parseInt(req.getParameter("perPageNum"));
		String location=req.getParameter("loc");
		
		int pno=Integer.parseInt(req.getParameter("pno"));
		int mno=Integer.parseInt(req.getParameter("mno"));
		String position=req.getParameter("position");
		String joinday=req.getParameter("joinday");
		String outday=req.getParameter("outday");
		
		vo.setMno(mno);
		vo.setPno(pno);
		vo.setJoinday(joinday);
		vo.setOutday(outday);
		vo.setPosition(position);
		try {
			if(projectWorkService.register(vo)==0){
				resp.sendRedirect(req.getContextPath()+"/error/error.do");
			}else{
				resp.sendRedirect(req.getContextPath()+"/project/list.do?page="+page+"&perPageNum="+perPageNum+"&loc="+location);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
