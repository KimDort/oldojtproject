package com.icnman.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icnman.domain.MemberVO;
import com.icnman.service.ExperienceService;
import com.icnman.service.LicenseService;
import com.icnman.service.MemberService;

import net.sf.json.JSONObject;

public class UserInfoServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7913870575810512747L;
	private MemberService memberService = new MemberService();
	private ExperienceService experienceService = new ExperienceService();
	private LicenseService licenseService = new LicenseService();

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
		int no=Integer.parseInt(req.getParameter("no"));
		try {
			JSONObject obj=new JSONObject();
			MemberVO vo = new MemberVO();
			vo=memberService.read(no);
			
			
			obj.put("name", vo.getMember_name());
			/*obj.put("userCareer", experienceService.thisCareerCreate(experienceService.read(no)));
			obj.put("userLicense", licenseService.read(no));*/
			resp.setContentType("application/x-json; charset=UTF-8");
			resp.getWriter().print(obj);
			resp.getWriter().flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
