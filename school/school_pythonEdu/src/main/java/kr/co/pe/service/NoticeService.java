package kr.co.pe.service;

import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface NoticeService {
	
	public void NoticeInsert(String notice_title, String notice_contents) throws Exception;
	public JSONArray NoticeList(int current_page) throws SQLException; 
	public JSONObject NoticeInfo(int notice_idx) throws SQLException;
	public int NoticeTotal() throws SQLException;
	public void NoticeModify(int notice_idx, String notice_title, String notice_contents) throws Exception;
	public void NoticeDelete(int notice_idx) throws Exception;
	
}
