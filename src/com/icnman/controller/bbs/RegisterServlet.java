package com.icnman.controller.bbs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icnman.domain.BbsVO;
import com.icnman.service.BbsService;

public class RegisterServlet extends HttpServlet{

	/**
	 * 
	 */
	private BbsService bbsService = new BbsService();
	private static final long serialVersionUID = -4320824744839015333L;
	
	private BbsVO vo = new BbsVO();
	
	
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
			resp.sendRedirect(bbsService.register(vo, req));
	}
}
