package kr.co.pe.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.pe.service.NoticeService;
import kr.co.pe.serviceimpl.NoticeServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class NoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	/**
	 * 공지사항 리스트
	 * @param request
	 * @param respone
	 * @return
	 */
	@RequestMapping(value = "/notice_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView NoticeDefault(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/Notice/notice_default");
		
		
		return mv;
	}
	
	/**
	 * 공지사항 정보 보기
	 * @param request
	 * @param respone
	 * @return
	 */
	@RequestMapping(value = "/notice_view_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView NoticeViewDefault(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/Notice/notice_view_default");
		
		return mv;
	}
	
	/**
	 * 공지사항 등록 페이지
	 * @param request
	 * @param respone
	 * @return
	 */
	@RequestMapping(value = "/notice_write_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView NoticeWriteDefault(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/Notice/notice_write_default");
		
		return mv;
	}
	
	/**
	 * 공지사항 등록하기
	 * @param request
	 * @param respone
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/notice_write_ok", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView NoticeWriteOk(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/notice_default");
		
		String notice_title = request.getParameter("notice_title");
		notice_title = notice_title.replaceAll("'", "''");
		
		//String contents_check = request.getParameter("contents_check");
				
		//String notice_contents = request.getParameter("notice_contents");
		
		String notice_contents = request.getParameter("notice_contents");
		//notice_contents = notice_contents.replace("\r\n", "<br>");
        //notice_contents = notice_contents.replaceAll(System.getProperty("line.separator"), "<br>");
        //notice_contents = notice_contents.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		
		NoticeService noticeService = new NoticeServiceImpl();
		noticeService.NoticeInsert(notice_title, notice_contents);
		
		return mv;
	}
	
	/**
	 * 공지사항 수정하기
	 * @param request
	 * @param respone
	 * @return
	 */
	@RequestMapping(value = "/notice_modify_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView NoticeModifyDefault(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/Notice/notice_modify_default");
		
		return mv;
	}
	
	/**
	 * 공지사항 수정완료
	 * @param request
	 * @param respone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/notice_modify_ok", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView NoticeModifyOk(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/notice_view_default");
		
		int notice_idx = Integer.parseInt(request.getParameter("notice_idx"));
		String notice_title = request.getParameter("notice_title");
		notice_title = notice_title.replaceAll("'", "''");
		
		//String contents_check = request.getParameter("contents_check");
				
		//String notice_contents = request.getParameter("notice_contents");
		
		String notice_contents = request.getParameter("notice_contents");
		notice_contents = notice_contents.replace("\r\n", "<br>");
        notice_contents = notice_contents.replaceAll(System.getProperty("line.separator"), "<br>");
        notice_contents = notice_contents.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		
		NoticeService noticeService = new NoticeServiceImpl();
		noticeService.NoticeModify(notice_idx, notice_title, notice_contents);
		
		mv.addObject("notice_idx", notice_idx);
		
		return mv;
	}
	
	
	@RequestMapping(value = "/notice_delete_ok", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView NoticeDeleteOk(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/notice_default");
		
		int notice_idx = Integer.parseInt(request.getParameter("notice_idx"));
		
		NoticeService noticeService = new NoticeServiceImpl();
		noticeService.NoticeDelete(notice_idx);
				
		return mv;
	}
	

	
	
	

}
