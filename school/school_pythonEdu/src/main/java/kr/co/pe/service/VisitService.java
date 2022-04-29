package kr.co.pe.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;

public interface VisitService {
	public JSONArray VisitList(String type, String sdt, String edt) throws SQLException;
	public int VisitTotal() throws SQLException;
	public void VisitInsert(int member_idx, String type, int fidx, String page, String ip, String browser) throws SQLException;
	public void VisitReg(HttpServletRequest request) throws Exception;
	public JSONArray VisitList2(int current_page) throws SQLException;
}
