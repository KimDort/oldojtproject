package com.icnman.controller.project;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icnman.service.ProjectService;
import com.icnman.tools.SearchCriteria;
import com.icnman.tools.PageMaker;

public class MainListServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7327564132407655345L;
	private RequestDispatcher ds;
	private SearchCriteria cri= new SearchCriteria();
	private PageMaker pageMaker=new PageMaker();
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
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp){
		try {
			ds=req.getRequestDispatcher("/WEB-INF/jsp/project/list.jsp");
			int page=Integer.parseInt(req.getParameter("page"));
			int perPageNum=Integer.parseInt(req.getParameter("perPageNum"));
			String location=req.getParameter("loc");
			
			cri.setPage(page);
			cri.setPerPageNum(perPageNum);
			cri.setLocation(location);
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(projectService.totalCount());
			
			req.setAttribute("list", projectService.list(cri));
			req.setAttribute("cri", cri);
			req.setAttribute("pageMaker", pageMaker);
			ds.forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
