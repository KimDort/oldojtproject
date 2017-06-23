package com.icnman.controller.projectwork;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icnman.service.ProjectWorkService;
import com.icnman.tools.PageMaker;
import com.icnman.tools.SearchCriteria;

public class MainListServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -487666478933349045L;
	private SearchCriteria cri = new SearchCriteria();
	private ProjectWorkService projectWorkService = new ProjectWorkService();
	private RequestDispatcher ds;
	private PageMaker pageMaker=new PageMaker();
	
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
		String location = req.getParameter("loc");
		
		cri.setPage(page);
		cri.setPerPageNum(perPageNum);
		cri.setLocation(location);
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(projectWorkService.totalCount());
		try {
			ds=req.getRequestDispatcher("/WEB-INF/jsp/project_working/list.jsp");
			req.setAttribute("list", projectWorkService.list(cri));
			req.setAttribute("cri", cri);
			req.setAttribute("pageMaker", pageMaker);
			ds.forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
