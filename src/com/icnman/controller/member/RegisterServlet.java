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

public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -377588282971103104L;
	private MemberService memberService = new MemberService();
	private ExperienceService experienceService = new ExperienceService();
	private LicenseService licenseService = new LicenseService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	public void doProcess(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("UTF-8");
			String page = req.getParameter("page");
			String perPageNum = req.getParameter("perPageNum");

			List<ExperienceVO> experienceList = new ArrayList<>();
			List<LicenseVO> licenseList = new ArrayList<>();
			MemberVO vo = new MemberVO();
			
			vo = createMember(req);	
			licenseList = createLicenseList(req);
			experienceList = createCareerList(req, vo);
			
			Connection conn = DBConn.getConnection();
			conn.setAutoCommit(false);
			
			if (memberService.register(vo, conn) <= 0) {
				System.out.println("사원 정보 입력 롤백");
				conn.rollback();
			} else {
				if (experienceList.size() > 0) {
					System.out.println("경력 입력 들어옴");
					if (experienceService.register(experienceList, conn) <= 0) {
						System.out.println("경력 입력 롤백");
						conn.rollback();
					} 
				} else if (licenseList.size() > 0) {
					System.out.println("자격증 입력 들어옴");
					if (licenseService.register(licenseList, conn) <= 0) {
						System.out.println("자격증 입력 롤백");
						conn.rollback();
					}
				}
				conn.commit();
				conn.setAutoCommit(true);
				conn.close();
				resp.sendRedirect(req.getContextPath() + "/member/list.do?page=" + page + "&perPageNum=" + perPageNum);
			}

		} catch (Exception e) {
			e.printStackTrace();
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
			System.out.println("경력 파라메터 없음 : 기본 정보 입력");		
			ExperienceVO experienceVO = new ExperienceVO();
			experienceVO.setPeriod_start(vo.getMember_join());
			experienceVO.setCompany(vo.getMember_company());
			experienceVO.setRank(vo.getMember_rank());
			experienceVO.setPosition(vo.getMember_position());
			experienceList.add(experienceVO);
		} else {
			System.out.println("경력 파라메터 있음 ");
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

	public List<LicenseVO> createLicenseList(HttpServletRequest req) {
		/* 자격증 파라메터 받기 */
		List<LicenseVO> licenseList = new ArrayList<>();
		if (req.getParameterValues("license_name") != null || req.getParameter("license_name")!=null) {
			String[] license_name = req.getParameterValues("license_name");
			String[] license_level = req.getParameterValues("license_level");
			String[] license_getDate = req.getParameterValues("license_date");
			String[] license_publisher = req.getParameterValues("license_publisher");
			for (int idx = 0; idx < license_name.length; idx++) {
				LicenseVO licenseVO = new LicenseVO();
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
