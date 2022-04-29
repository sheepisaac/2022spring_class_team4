package kr.co.pe.serviceimpl;

import kr.co.pe.dao.LoginDao;
import kr.co.pe.daoimpl.LoginDaoImpl;
import kr.co.pe.service.LoginService;

public class LoginServiceImpl implements LoginService {
	
	public LoginDao loginDao;
	
	public LoginServiceImpl() {
		super();
		this.loginDao = new LoginDaoImpl();
	}

	@Override
	public int LoginCheck(String member_id, String member_pwd) throws Exception {
		// TODO Auto-generated method stub
		int nflag = 0;
		nflag = loginDao.LoginCheck(member_id, member_pwd);
		
		return nflag;
	}
	
	

}
