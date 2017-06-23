package com.icnman.controller.bbs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icnman.service.BbsService;
import com.icnman.tools.SearchCriteria;

public class ModifyServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7207962385521286532L;
	private RequestDispatcher ds;
	private BbsService bbsService = new BbsService();
	private SearchCriteria cri = new SearchCriteria();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			doProcess(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			doProcess(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp)throws Exception{
		ds=req.getRequestDispatcher("/WEB-INF/jsp/bbs/modify.jsp");
		ds.forward(req, resp);
	}
}
