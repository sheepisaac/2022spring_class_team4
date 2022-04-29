package kr.co.pe.serviceimpl;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;

import kr.co.pe.dao.VisitDao;
import kr.co.pe.daoimpl.VisitDaoImpl;
import kr.co.pe.service.VisitService;

public class VisitServiceImpl implements VisitService {

	@Override
	public JSONArray VisitList(String type, String sdt, String edt) throws SQLException {
		VisitDao visitDao = new VisitDaoImpl();
		return visitDao.VisitList(type, sdt, edt);
	}

	@Override
	public int VisitTotal() throws SQLException {
		VisitDao visitDao = new VisitDaoImpl();
		return visitDao.VisitTotal();
	}

	@Override
	public void VisitInsert(int member_idx, String type, int fidx, String page, String ip, String browser)
			throws SQLException {
		VisitDao visitDao = new VisitDaoImpl();
		visitDao.VisitInsert(member_idx, type, fidx, page, ip, browser);
	}

	@Override
	public void VisitReg(HttpServletRequest request) throws Exception {
		VisitDao visitDao = new VisitDaoImpl();
		visitDao.VisitReg(request);
	}

	@Override
	public JSONArray VisitList2(int current_page) throws SQLException {
		VisitDao visitDao = new VisitDaoImpl();
		return visitDao.VisitList2(current_page);
	}

}
