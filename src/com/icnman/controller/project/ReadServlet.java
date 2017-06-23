package com.icnman.controller.project;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icnman.service.ProjectService;
import com.icnman.tools.SearchCriteria;

public class ReadServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6618867424621539861L;
	private RequestDispatcher ds;
	private SearchCriteria cri= new SearchCriteria();
	private ProjectService projectService = new ProjectService();
	
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
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ds=req.getRequestDispatcher("/WEB-INF/jsp/project/read.jsp");
		int page=Integer.parseInt(req.getParameter("page"));
		int perPageNum=Integer.parseInt(req.getParameter("perPageNum"));
		int project_no=Integer.parseInt(req.getParameter("read"));
		String location=req.getParameter("loc");
		
		cri.setPage(page);
		cri.setPerPageNum(perPageNum);
		cri.setLocation(location);
		
		req.setAttribute("cri", cri);
		req.setAttribute("read", projectService.read(project_no));
		ds.forward(req, resp);
	}
}
