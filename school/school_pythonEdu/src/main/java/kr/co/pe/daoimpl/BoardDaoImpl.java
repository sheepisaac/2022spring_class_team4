package kr.co.pe.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.pe.common.ConnectionDB;
import kr.co.pe.dao.BoardDao;

public class BoardDaoImpl implements BoardDao {

	@Override
	public void BoardInsert(HashMap<String, String> params) throws Exception {

		int board_idx = Integer.parseInt((String)params.get("board_idx"));
		int member_idx = Integer.parseInt((String)params.get("member_idx"));
		String board_type = (String)params.get("board_type");
		String board_title = (String)params.get("board_title");
		
		int ref = Integer.parseInt((String)params.get("ref"));
		int subref = Integer.parseInt((String)params.get("subref"));
		int depth = Integer.parseInt((String)params.get("depth"));
		int visit = Integer.parseInt((String)params.get("visit"));
		
		String board_contents = (String)params.get("board_contents");
		String file = (String)params.get("file");
		
		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		String sql = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connectionDB.YesConnectionDB();
			sql = "insert into board ("
					+ "board_idx, "
					+ "member_idx, "
					+ "board_type, "
					+ "board_title, "
					+ "ref, "
					+ "subref, "
					+ "depth, "
					+ "visit, "
					+ "board_contents, "
					+ "file, "
					+ "reg_dt, "
					+ "mod_dt) "
					+ "values (?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, now(), now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_idx);
			pstmt.setInt(2, member_idx);
			pstmt.setString(3, board_type);
			pstmt.setString(4, board_title);
			pstmt.setInt(5, ref);
			pstmt.setInt(6, subref);
			pstmt.setInt(7, depth);
			pstmt.setInt(8, visit);
			pstmt.setString(9, board_contents);
			pstmt.setString(10, file);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.close();
		}

	}

	@Override
	public JSONArray BoardList(int current_page, String board_type) throws SQLException {

		//변수 선언
		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		JSONArray board_list = new JSONArray();
		JSONObject board_info = new JSONObject();
		
		int iEndPage = 10;
		int iStartPage = (current_page*iEndPage)-10;
		
		
		try {
			conn = connectionDB.YesConnectionDB();
			sql = "select * from board "
					+ "where board_type='"+board_type+"' order by ref desc, depth desc ";
			if(current_page>0) {
				sql	+= "limit "+iStartPage+", "+iEndPage+" ";
			}
			
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board_info = new JSONObject();
				
				//게시글 정보
				board_info.put("board_idx", new Integer(rs.getInt("board_idx")));
				board_info.put("member_idx", new Integer(rs.getInt("member_idx")));
				board_info.put("board_type", new String(rs.getString("board_type")));
				board_info.put("board_title", new String(rs.getString("board_title")));
				
				board_info.put("ref", new Integer(rs.getInt("ref")));
				board_info.put("subref", new Integer(rs.getInt("subref")));
				board_info.put("depth", new Integer(rs.getInt("depth")));
				board_info.put("visit", new Integer(rs.getInt("visit")));
				
				board_info.put("board_contents", new String(rs.getString("board_contents")));
				board_info.put("file", new String(rs.getString("file")));
				board_info.put("reg_dt", new String(rs.getString("reg_dt")));
				board_info.put("mod_dt", new String(rs.getString("mod_dt")));


				board_list.add(board_info);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		
		return board_list;
	}

	@Override
	public JSONObject BoardInfo(int board_idx) throws SQLException {

		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		JSONObject board_info = new JSONObject();
		
		try {
			conn = connectionDB.YesConnectionDB();
			sql = "select * from board where board_idx = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_idx);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board_info = new JSONObject();
				
				//게시글 정보
				board_info.put("board_idx", new Integer(rs.getInt("board_idx")));
				board_info.put("member_idx", new Integer(rs.getInt("member_idx")));
				board_info.put("board_type", new String(rs.getString("board_type")));
				board_info.put("board_title", new String(rs.getString("board_title")));
				board_info.put("ref", new Integer(rs.getInt("ref")));
				board_info.put("subref", new Integer(rs.getInt("subref")));
				board_info.put("depth", new Integer(rs.getInt("depth")));
				board_info.put("visit", new Integer(rs.getInt("visit")));
				board_info.put("board_contents", new String(rs.getString("board_contents")));
				board_info.put("file", new String(rs.getString("file")));
				board_info.put("reg_dt", new String(rs.getString("reg_dt")));
				board_info.put("mod_dt", new String(rs.getString("mod_dt")));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		
		return board_info;
	}

	@Override
	public int BoardTotal(String board_type) throws SQLException {

		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		int total_count = 0;
		try {
			conn = connectionDB.YesConnectionDB();
			sql = "select count(*) from board "
					+ "where board_type='"+board_type+"' ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				total_count = rs.getInt(1);
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

	@Override
	public void BoardModify(HashMap<String, String> params) throws Exception {

		int board_idx = Integer.parseInt((String)params.get("board_idx"));
		String board_type = (String)params.get("board_type");
		String board_title = (String)params.get("board_title");
		String board_contents = (String)params.get("board_contents");
		String file = (String)params.get("file");
		
		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		String sql = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connectionDB.YesConnectionDB();
			sql = "update board set "
					+ "board_type = ?, "
					+ "board_title = ?, "
					+ "board_contents = ?, "
					+ "file = ?, "
					+ "mod_dt = now() "
					+ "where board_idx=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_type);
			pstmt.setString(2, board_title);
			pstmt.setString(3, board_contents);
			pstmt.setString(4, file);
			pstmt.setInt(5, board_idx);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.close();
		}

	}

	@Override
	public void BoardDelete(int board_idx) throws Exception {

		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		String sql = null;
		
		
		try {
			conn = connectionDB.YesConnectionDB();
			sql = "delete from board where board_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_idx);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.close();
		}

	}
	
	
	
	/**
	 * 게시글 방문자 업데이트
	 * @param board_idx
	 * @throws SQLException
	 */
	public void BoardVisitUpdate(int board_idx) throws SQLException{
		
		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		String sql = null;
		
		
		try {
			conn = connectionDB.YesConnectionDB();
			sql = "update board set visit=visit+1 where board_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_idx);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.close();
		}
	}
	
	/**
	 * 가장 큰 IDX 가져오기
	 * @return
	 * @throws SQLException
	 */
	public int BoardMaxId() throws SQLException{
		
		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		int max_number = 0;
		try {
			conn = connectionDB.YesConnectionDB();
			sql = "select max(board_idx) from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				max_number = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		
		return max_number;
	}

	
	/**
	 * 계층형 게시판 업데이트
	 * @param ref
	 * @param depth
	 * @throws SQLException
	 */
	public void BoardDepthUpdate(int ref, int depth, String board_type) throws SQLException{
		
		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		String sql = null;
		
		
		try {
			conn = connectionDB.YesConnectionDB();
			sql = "update board set depth=? where ref=? and depth > ? and board_type=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, depth+1);
			pstmt.setInt(2, ref);
			pstmt.setInt(3, depth);
			pstmt.setString(4, board_type);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.close();
		}
	}
	

}
