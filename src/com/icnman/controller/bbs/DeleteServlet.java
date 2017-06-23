package com.icnman.controller.bbs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icnman.service.BbsService;
import com.icnman.tools.SearchCriteria;

public class DeleteServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4128641650932045529L;
	private BbsService bbsService = new BbsService();
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
		int no=Integer.parseInt(req.getParameter("no"));
		String page=req.getParameter("page");
		String perPageNum=req.getParameter("perPageNum");
		if(bbsService.delete(no)==1){
			resp.sendRedirect(req.getContextPath()+"/bbs/list.do?page="+page+"&perPageNum="+perPageNum);
		}		
	}
}
