package com.icnman.service;

import java.util.List;

import com.icnman.dao.EducationDAO;
import com.icnman.domain.EducationVO;

public class EducationService {
	private EducationDAO dao = new EducationDAO();
	
	public int register(List<EducationVO> list){
		return dao.register(list);
	}
}
