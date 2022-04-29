package kr.co.pe.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	/**
	 * 사용자 페이지 1
	 * @param request
	 * @param respone
	 * @return
	 */
	@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView Default1(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/index");
		return mv;
		
	}
	
	/**
	 * 사용자 페이지 2
	 * @param request
	 * @param respone
	 * @return
	 */
	@RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView Default2(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/index");
		return mv;
		
	}
	
	
	@RequestMapping(value = "/main", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView Default3(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/main");
		return mv;
		
	}
	

	

}
