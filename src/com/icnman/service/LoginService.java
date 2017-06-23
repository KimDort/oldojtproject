package com.icnman.service;

import com.icnman.dao.LoginDAO;

public class LoginService {
	private LoginDAO dao=new LoginDAO();
	
	public boolean checkingLoing(String id, String pass){
		boolean result=false;
		if(dao.checkLoing(id, pass)==1){
			result=true;
		}
		return result;
	}
}
