package kr.co.pe.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.co.pe.common.ConnectionDB;

public class testcodeDaoimpl {
	
	public void testcodeInsert(String testcode_title, String testcode_contents) throws Exception {

		Connection conn = null;
		ConnectionDB connectionDB = new ConnectionDB();
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = connectionDB.YesConnectionDB();
			
			sql = "insert into testcode("
					+ "testcode_title, "
					+ "testcode_contents, "
					+ "reg_dt, "
					+ "mod_dt ) values( "
					+ "?, ?, "
					+ "now(), now() )";
						
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, testcode_title);
			pstmt.setString(2, testcode_contents);
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
