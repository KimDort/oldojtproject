package com.icnman.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.icnman.dao.ProjectWorkDAO;
import com.icnman.domain.ProjectWorkingVO;
import com.icnman.tools.SearchCriteria;

public class ProjectWorkService {
	private ProjectWorkDAO dao = new ProjectWorkDAO();
	public int register(ProjectWorkingVO vo){
		return dao.register(vo);
	}
	
	public List<ProjectWorkingVO> list(SearchCriteria cri){
		List<ProjectWorkingVO> list= dao.list(cri);
		Map<String, ProjectWorkingVO> map = new HashMap<>();
		for(int idx=0;idx<list.size();idx++){
			map.put(list.get(idx).getName(), list.get(idx));
		}
		return dao.list(cri);
	}
	
	public int totalCount(){
		return dao.totalCount();
	}
}
