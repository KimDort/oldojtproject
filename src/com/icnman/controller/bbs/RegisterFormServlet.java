package com.icnman.controller.bbs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icnman.service.MemberService;
import com.icnman.tools.SearchCriteria;

public class RegisterFormServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8409157675023027786L;
	
	private MemberService memberService=new MemberService();
	private SearchCriteria cri=new SearchCriteria();
	private RequestDispatcher ds;
	
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
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		ds=req.getRequestDispatcher("/WEB-INF/jsp/bbs/create.jsp");
		cri.setPage(Integer.parseInt(req.getParameter("page")));
		cri.setPerPageNum(Integer.parseInt(req.getParameter("perPageNum")));
		
		//req.setAttribute("memberList", memberService.list());
		req.setAttribute("cri", cri);
		ds.forward(req, resp);
	}
}
