package com.icnman.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icnman.service.BbsService;
import com.icnman.service.ProjectService;

public class MainListServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6696272933233210918L;
	private BbsService bbsService =new BbsService();
	private ProjectService projectService = new ProjectService();
	
	private RequestDispatcher ds;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ds=req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		/*req.setAttribute("bbslist", bbsService.bbsList(5));
		req.setAttribute("project", projectService.list(5));*/
		ds.forward(req, resp);
	}

}
