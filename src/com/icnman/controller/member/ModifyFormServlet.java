package com.icnman.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icnman.service.ExperienceService;
import com.icnman.service.LicenseService;
import com.icnman.service.MemberService;
import com.icnman.tools.SearchCriteria;

public class ModifyFormServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -12936026482654291L;
	private RequestDispatcher ds;
	private MemberService memberService = new MemberService();
	private ExperienceService experienceService = new ExperienceService();
	private LicenseService licenseService = new LicenseService();
	private SearchCriteria cri = new SearchCriteria();
	
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
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ds=req.getRequestDispatcher("/WEB-INF/jsp/member/modify.jsp");
		int mno = Integer.parseInt(req.getParameter("mno"));
		int page = Integer.parseInt(req.getParameter("page"));
		int perPageNum = Integer.parseInt(req.getParameter("perPageNum"));
		
		cri.setPage(page);
		cri.setPerPageNum(perPageNum);
		req.setAttribute("cri", cri);
		req.setAttribute("member", memberService.read(mno));
		req.setAttribute("career", experienceService.read(mno));
		req.setAttribute("license", licenseService.read(mno));
		
		ds.forward(req, resp);
	}
}
