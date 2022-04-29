package kr.co.pe.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.pe.dto.MemberDto;

public interface BoardService{

	
	public void BoardInsert(HashMap<String, String> params) throws Exception;
	public JSONArray BoardList(int current_page, String board_type) throws SQLException; 
	public JSONObject BoardInfo(int board_idx) throws SQLException;
	public int BoardTotal(String board_type) throws SQLException;
	public void BoardModify(HashMap<String, String> params) throws Exception;
	public void BoardDelete(int board_idx) throws Exception;
	public void BoardVisitUpdate(int board_idx) throws SQLException;
	public int BoardMaxId() throws SQLException;
	public void BoardDepthUpdate(int ref, int depth, String board_type) throws SQLException;

	
	
}
