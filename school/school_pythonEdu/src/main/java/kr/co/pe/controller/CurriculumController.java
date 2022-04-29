package kr.co.pe.controller;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.pe.common.CommonUtil;
import kr.co.pe.common.LocalValue;
import kr.co.pe.dao.CurriculumDao;
import kr.co.pe.daoimpl.CurriculumDaoImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CurriculumController {
	
	private static final Logger logger = LoggerFactory.getLogger(CurriculumController.class);
		
	
	/**
	 * 파이썬 결과를 String으로 변환하여 결과 반환
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	@ResponseBody
	//@RequestMapping(value = "/python_compile_ok", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
	@RequestMapping(value = "/python_compile_ok", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain;charset=utf-8")
	public String CloudCompile(Model model, HttpServletRequest request) throws Exception {

		LocalValue lv = new LocalValue();
		CommonUtil CU = new CommonUtil();
		//CharacterSet CS = new CharacterSet();

		HttpSession session = request.getSession(true);
		String session_id = session.getId();
      
		JSONObject data_temp = new JSONObject();
      
		String code = "";
		if(request.getParameter("code")!=null) {
			code = request.getParameter("code");
		}

		ServletContext context = request.getSession().getServletContext();
		String uploadPath = context.getRealPath("/")+lv.FILEUPLOAD_ROOT_PATH;
		String strFolder = "code";
  
		CurriculumDao CD = new CurriculumDaoImpl();
		String res = CD.CloudCompilerPython(code, uploadPath, strFolder, session_id);
  
		data_temp.put("RES", res);
		data_temp.put("CD", 100);
		//return data_temp.toJSONString();
		return res;
   }
	
	
	
	

	
	
	

}
