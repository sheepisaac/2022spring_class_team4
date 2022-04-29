package kr.co.pe.daoimpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import kr.co.pe.common.FileUtiles;
import kr.co.pe.common.LocalValue;
import kr.co.pe.dao.CurriculumDao;

public class CurriculumDaoImpl implements CurriculumDao {
	
	/**
	 * 
	 * @param input
	 * @param uploadPath
	 * @param strFoler
	 * @param session_id
	 * @return
	 * @throws SQLException
	 */
	public String CloudCompilerPython(String input, String uploadPath, String strFoler, String session_id) throws SQLException{
		FileUtiles FU = new FileUtiles();
		LocalValue lv = new LocalValue();
		
		String result = "Failed";
	      
		try{

			String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String strYear = new SimpleDateFormat("yyyy").format(new Date());
 
			Calendar c = Calendar.getInstance();
			strYear = String.valueOf( (c.get(Calendar.YEAR)) );
 
			/* 저장 폴더 구하기 */
			String uploadFoler = strFoler+"/"+strYear;
 
			//최종 위치
			String final_path = uploadPath+"/"+uploadFoler;
 
			//현재 디렉토리가 없으면 새로 생성한다.
			if(!FU.isExists(final_path)){
				FU.createDirectory(final_path);
			}
 
			/* 디렉토리와 파일 풀네임 */
			String final_filename = final_path+"/"+session_id+"_"+now+".py";
 
			/* 파이썬 위치 */
			String python_path = "C:/ProgramData/Anaconda3/envs/school_pythonedu/python.exe";
			//String python_path = "E:\\ProgramData\\Anaconda3\\envs\\tensorflow";
 
			//String prg = "import sys\nprint int(sys.argv[1])+int(sys.argv[2])\n";
			String prg = input;
			//BufferedWriter out = new BufferedWriter(new FileWriter("test2.py"));
			BufferedWriter out = new BufferedWriter(new FileWriter(final_filename));
			out.write(prg);
			out.close();

			ProcessBuilder pb = new ProcessBuilder(lv.PYTHON_PATH, final_filename);
			Process p = pb.start();

			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader in_err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			//int ret = new Integer(in.readLine()).intValue();
			//String res = in.readLine();

			String res = "";
			String temp;

			// 출력값 읽어오기
			in.mark(1);
			if (in.read() != -1) {
				in.reset();
				//in = new BufferedReader(new InputStreamReader(p.getInputStream()));
				System.out.println("OUTPUT");
				while ((temp = in.readLine()) != null) {
					//System.out.println(temp);
					res += temp;
					res += "\n";
				}
				// 에러시 (출력값 없을시)
			} else {
				res = null;
			}

			// 에러시에 에러내용 가져오기
			in_err.mark(1);
			if (in_err.read() != -1) {
				in_err.reset();
				System.out.println("ERROR");

				// initialize
				res = "";
				temp = "";
				while ((temp = in_err.readLine()) != null) {
					res += temp;
					res += "\n";
				}
			}

			System.out.println(res);
			result = res;
		} catch (Exception e) {
			System.out.println(e);
		}

		return result;
	}

}
