package kr.co.pe.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.python.core.PyFunction;
//import org.python.core.PyInteger;
//import org.python.core.PyObject;
//import org.python.util.PythonInterpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.pe.common.CommonUtil;
import kr.co.pe.common.FileUtiles;
import kr.co.pe.common.LocalValue;
import kr.co.pe.daoimpl.testcodeDaoimpl;

@Controller
public class testcodeController {
	
	private static final Logger logger = LoggerFactory.getLogger(testcodeController.class);
	//private static PythonInterpreter intPre;
	
	@RequestMapping(value = "/testcode_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView testcodeDefault(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/main");
		return mv;
	}
	
	@RequestMapping(value = "/testcode_default1", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView testcodeDefault1(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/Curriculum/editorTest");
		return mv;
	}
	@RequestMapping(value = "/testcode_default11", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView testcodeDefault11(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/editorTest");
		return mv;
	}
	
	@RequestMapping(value = "/testcode_default2", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView testcodeDefault2(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/Curriculum/editorTest2");
		return mv;
	}
	@RequestMapping(value = "/testcode_default22", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView testcodeDefault22(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/editorTest2");
		return mv;
	}
	
	@RequestMapping(value = "/testcode_write_ok", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView testcodeWriteOk(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:testcode_default");
		
		LocalValue lv = new LocalValue();
		CommonUtil CU = new CommonUtil();
		FileUtiles FU = new FileUtiles();

		testcodeDaoimpl test = new testcodeDaoimpl();
		
		String testcode_title =  request.getParameter("testcode_title");
//		testcode_title = testcode_title.replaceAll("'", "''");
		

		String testcode_contents = request.getParameter("testcode_contents");
//		testcode_contents = testcode_contents.replace("\r\n", "<br>");
//		testcode_contents = testcode_contents.replaceAll(System.getProperty("line.separator"), "<br>");
//		testcode_contents = testcode_contents.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		
		//String testcode_result = "";
		
		test.testcodeInsert(testcode_title, testcode_contents);
		
		return mv;
		
	}
	
	
	/*
	 * @RequestMapping(value = "/testcode_run_ok", method = {RequestMethod.GET,
	 * RequestMethod.POST}) public String testcodeRunOk(HttpServletRequest request,
	 * HttpServletResponse respone) throws Exception { intPre = new
	 * PythonInterpreter(); intPre.exec(request.getParameter("testcode_contents"));
	 * 
	 * PyFunction pyFunction = (PyFunction) intPre.get("testFunc",
	 * PyFunction.class); int a=10, b=20; PyObject pyobj = pyFunction.__call__(new
	 * PyInteger(a), new PyInteger(b)); System.out.println(pyobj.toString());
	 * 
	 * return pyobj.toString();
	 * 
	 * }
	 */

}
