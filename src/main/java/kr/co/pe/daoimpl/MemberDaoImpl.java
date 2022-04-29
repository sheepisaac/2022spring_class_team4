package kr.co.pe.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.json.simple.JSONObject;

import kr.co.pe.common.CommonUtil;
import kr.co.pe.common.ConnectionDB;
import kr.co.pe.dao.MemberDao;
import kr.co.pe.dto.MemberDto;


public class MemberDaoImpl implements MemberDao{
	
	
	/**
	 * 회원 정보 입력하기
	 * @param params
	 * @throws Exception
	 */
	@Override
	public void MemberInsert(MemberDto member_dto) throws Exception{
		
		//변수 받아서 변수 처리
		String member_id = member_dto.getMember_id();
		String member_pwd = member_dto.getMember_pwd();
		String member_name = member_dto.getMember_name();
		String member_kind = member_dto.getMember_kind();
		String member_phone = member_dto.getMember_phone();
		int member_level = member_dto.getMember_level();


		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = connectionDB.YesConnectionDB();
			
			sql = "insert into member("
					+ "member_kind, "
					+ "member_id, "
					+ "member_pwd, "
					+ "member_name, "
					+ "member_phone, "
					+ "member_level, "
					+ "reg_dt, "
					+ "mod_dt ) values( "
					+ "?, ?, ?, ?, ?, "
					+ "?, "
					+ "now(), now() )";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_kind);
			pstmt.setString(2, member_id);
			pstmt.setString(3, member_pwd);
			pstmt.setString(4, member_name);
			pstmt.setString(5, member_phone);
			pstmt.setInt(6, member_level);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.close();
		}

	}
	
	
	/**
	 * 회원 리스트 전체 가져오기
	 * @return
	 * @throws SQLException
	 */
	@Override
	public LinkedHashMap MemberList(int current_page) throws SQLException{
		
		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		int iEndPage = 10;
		int iStartPage = (current_page*iEndPage)-10;

		LinkedHashMap member_info = new LinkedHashMap();
		LinkedHashMap member_list = new LinkedHashMap();
		
		ArrayList<LinkedHashMap> member_list2 = new ArrayList<LinkedHashMap>();
		
		
		try {
			conn = connectionDB.YesConnectionDB();
			sql = "select * from member "
					+ "order by member_idx desc limit "+iStartPage+", "+iEndPage+" ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member_info.put("member_idx", rs.getInt("member_idx"));
				member_info.put("member_id", new String( rs.getString("member_id") ));
				member_info.put("member_pwd", new String( rs.getString("member_pwd") ));
				member_info.put("member_name", new String( rs.getString("member_name") ));
				member_info.put("member_kind", new String( rs.getString("member_kind") ));
				member_info.put("member_phone", new String( rs.getString("member_phone") ));
				member_info.put("member_level", rs.getInt("member_level"));
				member_info.put("reg_dt", new String( rs.getString("reg_dt") ));
				member_info.put("mod_dt", new String( rs.getString("mod_dt") ));
				
				member_list.put( String.valueOf((rs.getInt("member_idx"))), new LinkedHashMap(member_info) );
				member_list2.add(member_info);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		
		
		return member_list;
	}
	
	
	/**
	 * 회원 정보 가져오기
	 * @param member_idx
	 * @return
	 * @throws SQLException
	 */
	@Override
	public MemberDto MemberInfo(int member_idx) throws SQLException{
		
		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		MemberDto member_info = new MemberDto();
		
		if(member_idx!=0) {
			try {
				conn = connectionDB.YesConnectionDB();
				sql = "select * from member where member_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, member_idx);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					member_info.setMember_idx(rs.getInt("member_idx"));
					member_info.setMember_id(new String( rs.getString("member_id") ));
					member_info.setMember_pwd(new String( rs.getString("member_pwd") ));
					member_info.setMember_name(new String( rs.getString("member_name") ));
					member_info.setMember_kind(new String( rs.getString("member_kind") ));
					member_info.setMember_phone(new String( rs.getString("member_phone") ));
					member_info.setMember_level(rs.getInt("member_level"));
					member_info.setReg_dt(new String( rs.getString("reg_dt") ));
					member_info.setMod_dt(new String( rs.getString("mod_dt") ));
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				rs.close();
				pstmt.close();
				conn.close();
			}
		}	
		
		return member_info;
	}

	/**
	 * 회원 정보(회원 아이디와 회원 비밀번호 이용)
	 * @param member_id
	 * @param member_pwd
	 * @return
	 * @throws SQLException
	 */
	@Override
	public LinkedHashMap MemberInfo2(String member_id, String member_pwd) throws SQLException{
		
		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		LinkedHashMap member_info = new LinkedHashMap();
		
		try {
			conn = connectionDB.YesConnectionDB();
			sql = "select * from member where member_id=? and member_pwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			pstmt.setString(2, member_pwd);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member_info.put("member_idx", rs.getInt("member_idx"));
				member_info.put("member_id", new String( rs.getString("member_id") ));
				member_info.put("member_pwd", new String( rs.getString("member_pwd") ));
				member_info.put("member_name", new String( rs.getString("member_name") ));
				member_info.put("member_kind", new String( rs.getString("member_kind") ));
				member_info.put("member_phone", new String( rs.getString("member_phone") ));
				member_info.put("member_level", rs.getInt("member_level"));
				member_info.put("reg_dt", new String( rs.getString("reg_dt") ));
				member_info.put("mod_dt", new String( rs.getString("mod_dt") ));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		
		
		return member_info;
	}
	
	
	/**
	 * 회원 정보 보기(회원 아이디로)
	 * @param member_id
	 * @return
	 * @throws SQLException
	 */
	@Override
	public JSONObject MemberInfo3(String member_id) throws SQLException{
		
		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		JSONObject member_info = new JSONObject();
		
		try {
			conn = connectionDB.YesConnectionDB();
			sql = "select * from member where member_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member_info.put("member_idx", rs.getInt("member_idx"));
				member_info.put("member_id", new String( rs.getString("member_id") ));
				member_info.put("member_pwd", new String( rs.getString("member_pwd") ));
				member_info.put("member_kind", new String( rs.getString("member_kind") ));
				member_info.put("member_name", new String( rs.getString("member_name") ));
				member_info.put("member_phone", new String( rs.getString("member_phone") ));
				member_info.put("member_level", rs.getInt("member_level"));
				member_info.put("reg_dt", new String( rs.getString("reg_dt") ));
				member_info.put("mod_dt", new String( rs.getString("mod_dt") ));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		
		
		return member_info;
	}
	
		
	/**
	 * 회원 전체 개수 가져오기
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int MemberTotal() throws SQLException{

		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		int total_count = 0;
		
		try {
			conn = connectionDB.YesConnectionDB();
			sql = "select count(*) as total_count from member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				total_count = rs.getInt(1);
//				total_count = rs.getInt("total_count");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}

		
		return total_count;
	}

	
	/**
	 * 회원 정보 수정
	 * @param params
	 * @throws Exception
	 */
	@Override
	public void MemberModify(MemberDto memberDto) throws Exception{
		
		//변수 받아서 변수 처리
		int member_idx = memberDto.getMember_idx();
		String member_id = memberDto.getMember_id();
		String member_pwd = memberDto.getMember_pwd();
		String member_name = memberDto.getMember_name();
		String member_kind = memberDto.getMember_kind();
		String member_phone = memberDto.getMember_phone();
		int member_level = memberDto.getMember_level();


		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = connectionDB.YesConnectionDB();
			
			sql = "update member set "
					+ "member_kind = ?, "
					+ "member_id = ?, "
					+ "member_pwd = ?, "
					+ "member_name = ?, "
					+ "member_phone = ?, "
					+ "member_level = ?, "
					+ "mod_dt = now() "
					+ "where member_idx = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_kind);
			pstmt.setString(2, member_id);
			pstmt.setString(3, member_pwd);
			pstmt.setString(4, member_name);
			pstmt.setString(5, member_phone);
			pstmt.setInt(6, member_level);
			pstmt.setInt(7, member_idx);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.close();
		}

	}

	
	/**
	 * 회원 삭제
	 * @param member_idx
	 * @throws Exception
	 */
	@Override
	public void MemberDelete(int member_idx) throws Exception{
		
		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = connectionDB.YesConnectionDB();
			
			sql = "delete from member where member_idx = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_idx);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.close();
		}

	}

	
	/**
	 * 회원 비밀번호 체크
	 * @param member_idx
	 * @param member_pwd
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int MemberPwdCheck(int member_idx, String member_pwd) throws SQLException{

		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		CommonUtil commonUtil = new CommonUtil();
		member_pwd = commonUtil.getEncrypt(member_pwd);
		
		int nflag = 0;
		
		try {
			conn = connectionDB.YesConnectionDB();
			sql = "select count(*) as total_count from member "
					+ "where member_idx=? and member_pwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_idx);
			pstmt.setString(2, member_pwd);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				nflag = rs.getInt(1);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		
		return nflag;
	}


	/**
	 * 회원 비밀번호 수정
	 * @param member_idx
	 * @param member_pwd
	 * @throws Exception
	 */
	@Override
	public void PwdUpdate(int member_idx, String member_pwd) throws Exception{

		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = connectionDB.YesConnectionDB();
			
			sql = "update member set "
					+ "member_pwd = ? "
					+ "where member_idx = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_pwd);
			pstmt.setInt(2, member_idx);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			pstmt.close();
			conn.close();
		}

	}
	
	
}
