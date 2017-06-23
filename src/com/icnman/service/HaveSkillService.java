package com.icnman.service;

import java.util.List;

import com.icnman.dao.HaveSkillsDAO;
import com.icnman.domain.HaveSkillsVO;

public class HaveSkillService {
	private HaveSkillsDAO dao = new HaveSkillsDAO();
	public int register(List<HaveSkillsVO> list){
		return dao.register(list);
	}
}
