package kr.co.pe.serviceimpl;

import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.pe.dao.NoticeDao;
import kr.co.pe.daoimpl.NoticeDaoImpl;
import kr.co.pe.service.NoticeService;

public class NoticeServiceImpl implements NoticeService {
	
	NoticeDao noticeDao = new NoticeDaoImpl();

	@Override
	public void NoticeInsert(String notice_title, String notice_contents) throws Exception {
		noticeDao.NoticeInsert(notice_title, notice_contents);
	}

	@Override
	public JSONArray NoticeList(int current_page) throws SQLException {
		// TODO Auto-generated method stub
		return noticeDao.NoticeList(current_page);
	}

	@Override
	public JSONObject NoticeInfo(int notice_idx) throws SQLException {
		// TODO Auto-generated method stub
		return noticeDao.NoticeInfo(notice_idx);
	}

	@Override
	public int NoticeTotal() throws SQLException {
		// TODO Auto-generated method stub
		return noticeDao.NoticeTotal();
	}

	@Override
	public void NoticeModify(int notice_idx, String notice_title, String notice_contents) throws Exception {
		// TODO Auto-generated method stub
		noticeDao.NoticeModify(notice_idx, notice_title, notice_contents);
	}

	@Override
	public void NoticeDelete(int notice_idx) throws Exception {
		// TODO Auto-generated method stub
		noticeDao.NoticeDelete(notice_idx);
	}

}
