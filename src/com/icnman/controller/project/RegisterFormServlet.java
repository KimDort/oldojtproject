package com.icnman.controller.project;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icnman.tools.SearchCriteria;

public class RegisterFormServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6460260323978052896L;
	private RequestDispatcher ds;
	private SearchCriteria cri= new SearchCriteria();
	
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
			ds=req.getRequestDispatcher("/WEB-INF/jsp/project/create.jsp");
			int page=Integer.parseInt(req.getParameter("page"));
			int perPageNum=Integer.parseInt(req.getParameter("perPageNum"));
			String location=req.getParameter("loc");
			
			cri.setPage(page);
			cri.setPerPageNum(perPageNum);
			cri.setLocation(location);
			
			req.setAttribute("cri", cri);
			ds.forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
