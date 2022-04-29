package kr.co.pe.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.pe.common.ConnectionDB;
import kr.co.pe.dao.NoticeDao;

public class NoticeDaoImpl implements NoticeDao {

	@Override
	public void NoticeInsert(String notice_title, String notice_contents) throws Exception {

		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = connectionDB.YesConnectionDB();
			
			sql = "insert into notice("
					+ "notice_title, "
					+ "notice_contents, "
					+ "reg_dt, "
					+ "mod_dt ) values( "
					+ "?, ?, "
					+ "now(), now() )";
						
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice_title);
			pstmt.setString(2, notice_contents);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.close();
		}
		
	}

	@Override
	public JSONArray NoticeList(int current_page) throws SQLException {
		
		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		int iEndPage = 10;
		int iStartPage = (current_page*iEndPage)-10;

		JSONObject notice_info = new JSONObject();
		JSONArray notice_list = new JSONArray();
				
		
		try {
			conn = connectionDB.YesConnectionDB();
			sql = "select * from notice "
					+ "order by notice_idx desc ";
					
			if(current_page!=0) {
				sql += "limit "+iStartPage+", "+iEndPage+" ";
			}
					
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				notice_info = new JSONObject();
				notice_info.put("notice_idx", rs.getInt("notice_idx"));
				notice_info.put("notice_title", new String( rs.getString("notice_title") ));
				notice_info.put("notice_contents", new String( rs.getString("notice_contents") ));
				notice_info.put("reg_dt", new String( rs.getString("reg_dt") ));
				notice_info.put("mod_dt", new String( rs.getString("mod_dt") ));
				
				notice_list.add(notice_info);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		
		
		return notice_list;
	}

	@Override
	public JSONObject NoticeInfo(int notice_idx) throws SQLException {
		
		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		JSONObject notice_info = new JSONObject();
		
		try {
			conn = connectionDB.YesConnectionDB();
			sql = "select * from notice where notice_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_idx);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				notice_info.put("notice_idx", rs.getInt("notice_idx"));
				notice_info.put("notice_title", new String( rs.getString("notice_title") ));
				notice_info.put("notice_contents", new String( rs.getString("notice_contents") ));

				notice_info.put("reg_dt", new String( rs.getString("reg_dt") ));
				notice_info.put("mod_dt", new String( rs.getString("mod_dt") ));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		
		
		return notice_info;
	}

	@Override
	public int NoticeTotal() throws SQLException {

		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		int total_count = 0;
		
		try {
			conn = connectionDB.YesConnectionDB();
			sql = "select count(*) as total_count from notice";
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
	 * 공지사항 수정하기
	 */
	@Override
	public void NoticeModify(int notice_idx, String notice_title, String notice_contents) throws Exception {

		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = connectionDB.YesConnectionDB();
			
			sql = "update notice set "
					+ "notice_title = ?, "
					+ "notice_contents = ?, "
					+ "mod_dt = now() "
					+ "where notice_idx = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice_title);
			pstmt.setString(2, notice_contents);
			pstmt.setInt(3, notice_idx);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.close();
		}
		
	}

	@Override
	public void NoticeDelete(int notice_idx) throws Exception {
		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = connectionDB.YesConnectionDB();
			
			sql = "delete from notice where notice_idx=? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_idx);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.close();
		}
		
	}

}
