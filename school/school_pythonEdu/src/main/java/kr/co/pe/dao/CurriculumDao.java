package kr.co.pe.dao;

import java.sql.SQLException;

public interface CurriculumDao {

	public String CloudCompilerPython(String input, String uploadPath, String strFoler, String session_id) throws SQLException;
}
