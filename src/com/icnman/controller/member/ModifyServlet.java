package com.icnman.controller.member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icnman.domain.ExperienceVO;
import com.icnman.domain.LicenseVO;
import com.icnman.domain.MemberVO;
import com.icnman.service.ExperienceService;
import com.icnman.service.LicenseService;
import com.icnman.service.MemberService;
import com.icnman.tools.DBConn;

public class ModifyServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7108450363515057049L;
	private MemberService memberService= new MemberService();
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
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			req.setCharacterEncoding("UTF-8");
			String page=req.getParameter("page");
			String perPageNum=req.getParameter("perPageNum");
			/* 멤버 파라메터 받기 */
			MemberVO vo= new MemberVO();
			List<ExperienceVO> reqCareerList = new ArrayList<>();
			List<LicenseVO> reqLicenseList = new ArrayList<>();
			vo=createMember(req);
			reqCareerList=createCareerList(req, vo);
			reqLicenseList=createLicenseList(req, vo);
			System.out.println("요청 경력 파라메터 : "+reqCareerList.size());
			System.out.println("요청 자격증 파라메터 : "+reqLicenseList.size());
			
			Connection conn=DBConn.getConnection();
			if(memberService.modifyMember(vo, conn) < 0 ){
				conn.rollback();			
			}else{
				experienceService.delete(vo.getMember_no(), conn);
				licenseService.delete(vo.getMember_no(), conn);
				if(experienceService.update(reqCareerList, vo, conn)<0){
					conn.rollback();
				}else if(licenseService.update(reqLicenseList, vo,conn)<0){
					conn.rollback();
				}else{
					conn.commit();
					conn.setAutoCommit(true);
					conn.close();
					resp.sendRedirect(req.getContextPath()+"/member/list.do?page="+page+"&perPageNum="+perPageNum);
				}
			}	
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			errorSendUrl(req, resp);
		}
	}
	public void errorSendUrl(HttpServletRequest req, HttpServletResponse resp) {
		try {
			resp.sendRedirect(req.getContextPath() + "/error/error.do");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<ExperienceVO> createCareerList(HttpServletRequest req, MemberVO vo) {
		/* 경력 파라메터 받기 */
		List<ExperienceVO> experienceList = new ArrayList<>();
		if(req.getParameterValues("period_start")==null || req.getParameter("period_start")==null){	
			ExperienceVO experienceVO = new ExperienceVO();
			experienceVO.setPeriod_start(vo.getMember_join());
			experienceVO.setCompany(vo.getMember_company());
			experienceVO.setRank(vo.getMember_rank());
			experienceVO.setPosition(vo.getMember_position());
			experienceList.add(experienceVO);
		} else {
			String[] period_start = req.getParameterValues("period_start");
			String[] period_end = req.getParameterValues("period_end");
			String[] period_company = req.getParameterValues("period_company");
			String[] period_rank = req.getParameterValues("period_rank");
			String[] period_position = req.getParameterValues("period_position");
			
			ExperienceVO experienceVO = new ExperienceVO();
			experienceVO.setPeriod_start(vo.getMember_join());
			experienceVO.setCompany(vo.getMember_company());
			experienceVO.setRank(vo.getMember_rank());
			experienceVO.setPosition(vo.getMember_position());
			experienceList.add(experienceVO);
			
			for (int idx = 0; idx < period_start.length; idx++) {
				ExperienceVO newAddVO = new ExperienceVO();
				newAddVO.setPeriod_start(Date.valueOf(period_start[idx]));
				newAddVO.setPeriod_end(Date.valueOf(period_end[idx]));
				newAddVO.setCompany(period_company[idx]);
				newAddVO.setRank(period_rank[idx]);
				newAddVO.setPosition(period_position[idx]);
				experienceList.add(newAddVO);
			}
		}
		
		return experienceList;
	}

	public List<LicenseVO> createLicenseList(HttpServletRequest req, MemberVO vo) {
		/* 자격증 파라메터 받기 */
		List<LicenseVO> licenseList = new ArrayList<>();
		if (req.getParameterValues("license_name") != null || req.getParameter("license_name")!=null) {
			String[] license_name = req.getParameterValues("license_name");
			String[] license_level = req.getParameterValues("license_level");
			String[] license_getDate = req.getParameterValues("license_date");
			String[] license_publisher = req.getParameterValues("license_publisher");
			for (int idx = 0; idx < license_name.length; idx++) {
				LicenseVO licenseVO = new LicenseVO();
				licenseVO.setMno(vo.getMember_no());
				licenseVO.setName(license_name[idx]);
				licenseVO.setLevel(license_level[idx]);
				licenseVO.setGetdate(Date.valueOf(license_getDate[idx]));
				licenseVO.setPublisher(license_publisher[idx]);
				licenseList.add(licenseVO);
			}
		}
		return licenseList;
	}

	public MemberVO createMember(HttpServletRequest req) {
		/* 멤버 파라메터 받기 */
		MemberVO vo = new MemberVO();
		int mno=Integer.parseInt(req.getParameter("mno"));
		String name = req.getParameter("name");
		String company = req.getParameter("company");
		String rank = req.getParameter("rank");
		String joinCompany = req.getParameter("joincompany");
		String isnew = req.getParameter("isnew");
		String haveskills = req.getParameter("haveskills");
		String idNumber = req.getParameter("idnumber1") + "-" + req.getParameter("idnumber2");
		String zipcode = req.getParameter("zipcode");
		String address = req.getParameter("address");
		String address_detail = req.getParameter("address_detail");
		String position = req.getParameter("position");

		/* 사원 정보 셋팅 */
		vo.setMember_no(mno);
		vo.setMember_name(name);
		vo.setMember_company(company);
		vo.setMember_rank(rank);
		vo.setMember_join(Date.valueOf(joinCompany));
		vo.setMember_isnew(isnew);
		vo.setMember_haveskill(haveskills);
		vo.setMember_idnumber(idNumber);
		vo.setMember_zipcode(zipcode);
		vo.setMember_address(address);
		vo.setMember_address_detail(address_detail);
		vo.setMember_position(position);

		return vo;
	}
}
