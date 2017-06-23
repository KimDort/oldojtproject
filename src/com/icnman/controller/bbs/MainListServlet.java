package com.icnman.controller.bbs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icnman.service.BbsService;
import com.icnman.tools.SearchCriteria;
import com.icnman.tools.PageMaker;

public class MainListServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -714272748880116918L;
	
	private BbsService bbsService=new BbsService();
	private SearchCriteria cri= new SearchCriteria();
	private PageMaker pageMaker=new PageMaker();
	private RequestDispatcher ds;
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
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		int page=Integer.parseInt(req.getParameter("page"));
		int perPageNum=Integer.parseInt(req.getParameter("perPageNum"));
		ds=req.getRequestDispatcher("/WEB-INF/jsp/bbs/list.jsp");
		
		cri.setPage(page);
		cri.setPerPageNum(perPageNum);
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(bbsService.totalBbsList());
		
		req.setAttribute("list", bbsService.bbsList(cri));
		req.setAttribute("cri", cri);
		req.setAttribute("pageMaker", pageMaker);
		ds.forward(req, resp);
	}
}
