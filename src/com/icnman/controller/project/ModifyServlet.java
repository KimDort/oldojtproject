package com.icnman.controller.project;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icnman.domain.ProjectVO;
import com.icnman.service.ProjectService;
import com.icnman.tools.SearchCriteria;

public class ModifyServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -210890102565404943L;
	private SearchCriteria cri= new SearchCriteria();
	private ProjectVO vo= new ProjectVO();
	private ProjectService projectService = new ProjectService();

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
		req.setCharacterEncoding("UTF-8");
		int page=Integer.parseInt(req.getParameter("page"));
		int perPageNum=Integer.parseInt(req.getParameter("perPageNum"));
		int project_no=Integer.parseInt(req.getParameter("no"));
		String location=req.getParameter("loc");
		
		cri.setPage(page);
		cri.setPerPageNum(perPageNum);
		cri.setLocation(location);
		
		String name=req.getParameter("name");
		String contnet=req.getParameter("content");
		Date start=Date.valueOf(req.getParameter("startday"));
		Date end=Date.valueOf(req.getParameter("endday"));
		String order_company=req.getParameter("ordercompany");
		String create_skill=req.getParameter("createskill");
		String etc=req.getParameter("etc");
		
		vo.setNo(project_no);
		vo.setName(name);
		vo.setContent(contnet);
		vo.setStart(start);
		vo.setEnd(end);
		vo.setOrder_company(order_company);
		vo.setCreate_skill(create_skill);
		vo.setEtc(etc);
		
		if(projectService.update(vo)!=1){
			resp.sendRedirect(req.getContextPath()+"/error/error.do");
		}else{
			resp.sendRedirect(req.getContextPath()+"/project/list.do?page="+page+"&perPageNum="+perPageNum+"&loc="+location);
		}
	}
}
