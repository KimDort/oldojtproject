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

public class ReadServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4283155566551581777L;
	private RequestDispatcher ds;
	private MemberService memberService = new MemberService();
	private ExperienceService experienceService = new ExperienceService();
	private LicenseService licenseService = new LicenseService();
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
			ds=req.getRequestDispatcher("/WEB-INF/jsp/member/profile.jsp");
			int page= Integer.parseInt(req.getParameter("page"));
			int perPageNum=Integer.parseInt(req.getParameter("perPageNum"));
			
			cri.setPage(page);
			cri.setPerPageNum(perPageNum);
			
			int member= Integer.parseInt(req.getParameter("member"));
			
			req.setAttribute("readMember", memberService.read(member));
			req.setAttribute("readCareer", experienceService.read(member));
			req.setAttribute("readLicense", licenseService.read(member));
			req.setAttribute("cri", cri);
			
			ds.forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
