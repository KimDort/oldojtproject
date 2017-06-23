package com.icnman.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icnman.service.ExperienceService;
import com.icnman.service.MemberService;
import com.icnman.tools.PageMaker;
import com.icnman.tools.SearchCriteria;

public class MainListServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2433030506741183157L;
	private RequestDispatcher ds;
	private MemberService memberService=new MemberService();
	private ExperienceService experienceService = new ExperienceService();
	private SearchCriteria cri= new SearchCriteria();
	private PageMaker pageMaker = new PageMaker();
	
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
			String name=req.getParameter("name");
			int page =Integer.parseInt(req.getParameter("page"));
			int perPageNum=Integer.parseInt(req.getParameter("perPageNum"));
			String isnew= "All".equals(req.getParameter("isnew")) ? "":req.getParameter("isnew");
			cri.setPage(page);
			cri.setPerPageNum(perPageNum);
				
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(memberService.totalCount());
			
			req.setAttribute("list", memberService.list(cri, name, isnew));
			req.setAttribute("cri", cri);
			req.setAttribute("pageMaker", pageMaker);
			req.setAttribute("career", experienceService.list());
			ds=req.getRequestDispatcher("/WEB-INF/jsp/member/list.jsp");
			ds.forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
