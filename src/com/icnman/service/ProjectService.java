package com.icnman.service;

import java.util.List;

import com.icnman.dao.ProjectDAO;
import com.icnman.domain.ProjectVO;
import com.icnman.tools.SearchCriteria;

public class ProjectService {
	private ProjectDAO dao= new ProjectDAO();
	
	public int update(ProjectVO vo){
		return dao.update(vo);
	}
	public ProjectVO read(int no){
		return dao.read(no);
	}
	public List<ProjectVO> list(int countNum){
		return dao.list(countNum);
	}
	
	public List<ProjectVO> list(SearchCriteria cri){
		return dao.list(cri);
	}
	
	public int totalCount(){
		return dao.totalCount();
	}
	
	public int register(ProjectVO vo){
		return dao.register(vo);
	}
}
