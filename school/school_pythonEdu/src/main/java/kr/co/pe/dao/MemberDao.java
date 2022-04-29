package kr.co.pe.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.json.simple.JSONObject;

import kr.co.pe.dto.MemberDto;

public interface MemberDao{

	
	public void MemberInsert(MemberDto member) throws Exception;
	public LinkedHashMap MemberList(int current_page) throws SQLException; 
	public MemberDto MemberInfo(int member_idx) throws SQLException;
	public LinkedHashMap MemberInfo2(String member_id, String member_pwd) throws SQLException;
	public JSONObject MemberInfo3(String member_id) throws SQLException;
	public int MemberTotal() throws SQLException;
	public void MemberModify(MemberDto memberDto) throws Exception;
	public void MemberDelete(int member_idx) throws Exception;
	public int MemberPwdCheck(int member_idx, String member_pwd) throws SQLException;
	public void PwdUpdate(int member_idx, String member_pwd) throws Exception;
	
	
}
