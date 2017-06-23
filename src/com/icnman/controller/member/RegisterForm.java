package com.icnman.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icnman.tools.DBConn;
import com.icnman.tools.SearchCriteria;

public class RegisterForm extends HttpServlet{
	/**
	 * 
	 */
	private RequestDispatcher ds;
	private static final long serialVersionUID = 5985193525562234907L;
	private SearchCriteria cri=new SearchCriteria();

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
			ds=req.getRequestDispatcher("/WEB-INF/jsp/member/create.jsp");
			int page=Integer.parseInt(req.getParameter("page"));
			int perPageNum=Integer.parseInt(req.getParameter("perPageNum"));
			cri.setPage(page);
			cri.setPerPageNum(perPageNum);
			req.setAttribute("cri", cri);			
			req.setCharacterEncoding("UTF-8");
			ds.forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
