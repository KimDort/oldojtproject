package com.icnman.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icnman.domain.MemberVO;
import com.icnman.service.MemberService;
import com.icnman.tools.SearchCriteria;

public class RemoveServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 172538753675884096L;
	private SearchCriteria cri = new SearchCriteria();
	private MemberService memberService = new MemberService();

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
		try {
			MemberVO vo =new MemberVO();
			int mno= Integer.parseInt(req.getParameter("mno"));
			int page = Integer.parseInt(req.getParameter("page"));
			int perPageNum = Integer.parseInt(req.getParameter("perPageNum"));
			vo.setMember_isexit('Y');
			vo.setMember_no(mno);
			
			cri.setPage(page);
			cri.setPerPageNum(perPageNum);
			
			if(memberService.exitMember(vo)==1){
				resp.sendRedirect(req.getContextPath()+"/member/list.do?page="+page+"&perPageNum="+perPageNum);
			}else{
				resp.sendRedirect(req.getContextPath()+"/error/error.do");
			}
			req.setAttribute("cri", cri);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();		
		}
	}
}
