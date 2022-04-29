package kr.co.pe.dao;

public interface LoginDao {
	
	public int LoginCheck(String member_id, String member_pwd) throws Exception;
	
}
