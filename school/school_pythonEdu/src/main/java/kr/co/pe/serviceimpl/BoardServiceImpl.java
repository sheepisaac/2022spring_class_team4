package kr.co.pe.serviceimpl;

import java.sql.SQLException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.pe.dao.BoardDao;
import kr.co.pe.daoimpl.BoardDaoImpl;
import kr.co.pe.service.BoardService;

public class BoardServiceImpl implements BoardService {

	@Override
	public void BoardInsert(HashMap<String, String> params) throws Exception {
		BoardDao boardDao = new BoardDaoImpl();
		boardDao.BoardInsert(params);
	}

	@Override
	public JSONArray BoardList(int current_page, String board_type) throws SQLException {
		BoardDao boardDao = new BoardDaoImpl();
		return boardDao.BoardList(current_page, board_type);
	}

	@Override
	public JSONObject BoardInfo(int board_idx) throws SQLException {
		BoardDao boardDao = new BoardDaoImpl();
		return boardDao.BoardInfo(board_idx);
	}

	@Override
	public int BoardTotal(String board_type) throws SQLException {
		BoardDao boardDao = new BoardDaoImpl();
		return boardDao.BoardTotal(board_type);
	}

	@Override
	public void BoardModify(HashMap<String, String> params) throws Exception {
		BoardDao boardDao = new BoardDaoImpl();
		boardDao.BoardModify(params);
	}

	@Override
	public void BoardDelete(int board_idx) throws Exception {
		BoardDao boardDao = new BoardDaoImpl();
		boardDao.BoardDelete(board_idx);
	}

	@Override
	public void BoardVisitUpdate(int board_idx) throws SQLException {
		BoardDao boardDao = new BoardDaoImpl();
		boardDao.BoardVisitUpdate(board_idx);
	}

	@Override
	public int BoardMaxId() throws SQLException {
		BoardDao boardDao = new BoardDaoImpl();
		return boardDao.BoardMaxId();
	}

	@Override
	public void BoardDepthUpdate(int ref, int depth, String board_type) throws SQLException {
		BoardDao boardDao = new BoardDaoImpl();
		boardDao.BoardDepthUpdate(ref, depth, board_type);
	}

}
