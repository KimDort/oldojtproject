package com.icnman.controller.bbs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icnman.service.BbsService;
import com.icnman.service.MemberService;
import com.icnman.tools.SearchCriteria;

public class ModifyFormServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7207962385521286532L;
	private RequestDispatcher ds;
	private BbsService bbsService = new BbsService();
	private MemberService memberService=new MemberService();
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
		int no=Integer.parseInt(req.getParameter("no"));
		int page=Integer.parseInt(req.getParameter("page"));
		int perPageNum=Integer.parseInt(req.getParameter("perPageNum"));
		
		cri.setPage(page);
		cri.setPerPageNum(perPageNum);
		
		req.setAttribute("cri", cri);
		req.setAttribute("read", bbsService.read(no));
		//req.setAttribute("member", memberService.list());
		
		ds.forward(req, resp);
	}
}
