package kr.co.pe.serviceimpl;

import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.json.simple.JSONObject;

import kr.co.pe.dao.MemberDao;
import kr.co.pe.daoimpl.MemberDaoImpl;
import kr.co.pe.dto.MemberDto;
import kr.co.pe.service.MemberService;

public class MemberServiceImpl implements MemberService{

	private MemberDao memberDao;

	public MemberServiceImpl() {
		memberDao = new MemberDaoImpl();
	}

	@Override
	public void MemberInsert(MemberDto member) throws Exception {
		// TODO Auto-generated method stub
		memberDao.MemberInsert(member);
		
	}

	@Override
	public LinkedHashMap MemberList(int current_page) throws SQLException {
		// TODO Auto-generated method stub
		return memberDao.MemberList(current_page);
	}

	@Override
	public MemberDto MemberInfo(int member_idx) throws SQLException {
		return memberDao.MemberInfo(member_idx);
	}

	@Override
	public LinkedHashMap MemberInfo2(String member_id, String member_pwd) throws SQLException {
		return memberDao.MemberInfo2(member_id, member_pwd);
	}
	
	@Override
	public JSONObject MemberInfo3(String member_id) throws SQLException {
		// TODO Auto-generated method stub
		return memberDao.MemberInfo3(member_id);
	}

	@Override
	public int MemberTotal() throws SQLException {
		return memberDao.MemberTotal();
	}

	@Override
	public void MemberModify(MemberDto memberDto) throws Exception {
		memberDao.MemberModify(memberDto);
	}

	@Override
	public void MemberDelete(int member_idx) throws Exception {
		memberDao.MemberDelete(member_idx);
	}

	@Override
	public int MemberPwdCheck(int member_idx, String member_pwd) throws SQLException {
		return memberDao.MemberPwdCheck(member_idx, member_pwd);
	}

	@Override
	public void PwdUpdate(int member_idx, String member_pwd) throws Exception {
		memberDao.PwdUpdate(member_idx, member_pwd);
	}

	
	
	
	
}
