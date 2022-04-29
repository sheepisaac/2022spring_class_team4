package kr.co.pe.controller;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.pe.common.CommonUtil;
import kr.co.pe.common.FileUtiles;
import kr.co.pe.common.LocalValue;
import kr.co.pe.service.BoardService;
import kr.co.pe.serviceimpl.BoardServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	/**
	 * 게시판 리스트
	 * @param request
	 * @param respone
	 * @return
	 */
	@RequestMapping(value = "/board_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView BoardDefault(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/Board/board_default");
		
		return mv;
	}
	
	/**
	 * 게시판 정보 보기
	 * @param request
	 * @param respone
	 * @return
	 */
	@RequestMapping(value = "/board_view_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView BoardViewDefault(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/Board/board_view_default");
		
		return mv;
	}
	
	/**
	 * 게시판 등록 페이지
	 * @param request
	 * @param respone
	 * @return
	 */
	@RequestMapping(value = "/board_write_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView BoardWriteDefault(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/Board/board_write_default");
		
		return mv;
	}
	
	/**
	 * 게시판 등록하기
	 * @param request
	 * @param respone
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/board_write_ok", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView BoardWriteOk(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/board_default");
		
		CommonUtil commonUtil = new CommonUtil();
		LocalValue lv = new LocalValue();
		FileUtiles FU = new FileUtiles();
		
		BoardService boardService = new BoardServiceImpl();
		
		int member_idx = 0;
		if(request.getParameter("member_idx")!=null) {
			member_idx = Integer.parseInt(request.getParameter("member_idx"));
		}
		
		int board_idx = boardService.BoardMaxId()+1;
		String board_type = request.getParameter("board_type");
		String board_title = request.getParameter("board_title");
		board_title = board_title.replaceAll("'", "''");
						
		String board_contents = request.getParameter("board_contents");
		/*
		board_contents = request.getParameter("board_contents");
		board_contents = board_contents.replace("\r\n", "<br>");
        board_contents = board_contents.replaceAll(System.getProperty("line.separator"), "<br>");
        board_contents = board_contents.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
        */
		
		int ref = board_idx;
		int subref = 0;
		int depth = 0;
		int visit = 1;
		
		String file = "";
		
		if(board_type.equals("photo")) {
			//파일 받기
			/**************************************************************/
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			MultipartFile photo = (MultipartFile)multipartRequest.getFile("photo");
			
			ServletContext context = request.getSession().getServletContext();
			String uploadPath = context.getRealPath("/")+lv.FILEUPLOAD_ROOT_PATH;
			String strFolder = "board";
			
			if(!photo.getOriginalFilename().isEmpty()) {
				file = FU.setSingleFileUpload(photo, uploadPath, strFolder);
			}else {
				file = "";
			}
			/**************************************************************/
		}

		
		HashMap<String, String> params = new HashMap();
		params.put("board_idx", String.valueOf(board_idx));
		params.put("member_idx", String.valueOf(member_idx));
		params.put("board_type", board_type);
		params.put("board_title", board_title);
		params.put("ref", String.valueOf(ref));
		params.put("subref", String.valueOf(subref));
		params.put("depth", String.valueOf(depth));
		params.put("visit", String.valueOf(visit));
		params.put("board_contents", board_contents);
		params.put("file", file);

		
		boardService.BoardInsert(params);
		
		mv.addObject("board_type", board_type);
		
		return mv;
	}
	
	/**
	 * 게시판 수정하기
	 * @param request
	 * @param respone
	 * @return
	 */
	@RequestMapping(value = "/board_modify_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView BoardModifyDefault(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/Board/board_modify_default"); // 링트타고 들어감. 거쳐감.
		
		return mv;
	}
	
	/**
	 * 게시판 수정완료
	 * @param request
	 * @param respone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/board_modify_ok", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView BoardModifyOk(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/board_view_default");
		
		CommonUtil commonUtil = new CommonUtil();
		LocalValue lv = new LocalValue();
		FileUtiles FU = new FileUtiles();
		
		BoardService boardService = new BoardServiceImpl();
		
		int member_idx = 0;
		if(request.getParameter("member_idx")!=null) {
			member_idx = Integer.parseInt(request.getParameter("member_idx"));
		}
		
		int board_idx = Integer.parseInt(request.getParameter("board_idx"));
		String board_type = request.getParameter("board_type");
		String board_title = request.getParameter("board_title");
		board_title = board_title.replaceAll("'", "''");
						
		String board_contents = request.getParameter("board_contents");
		/*
		board_contents = request.getParameter("board_contents");
		board_contents = board_contents.replace("\r\n", "<br>");
        board_contents = board_contents.replaceAll(System.getProperty("line.separator"), "<br>");
        board_contents = board_contents.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
        */
		
		JSONObject board_info = new JSONObject();
		board_info = boardService.BoardInfo(board_idx);
		
		String file = "";
		
		if(board_type.equals("photo")) {			
			//파일 받기
			/**************************************************************/
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			MultipartFile photo = (MultipartFile)multipartRequest.getFile("photo");
			
			ServletContext context = request.getSession().getServletContext();
			String uploadPath = context.getRealPath("/")+lv.FILEUPLOAD_ROOT_PATH;
			String strFolder = "board";
			
			if(!photo.getOriginalFilename().isEmpty()) {
				file = FU.setSingleFileUpload(photo, uploadPath, strFolder);
			}else {
				file = (String)board_info.get("file");
			}
			/**************************************************************/
		}
		
		HashMap<String, String> params = new HashMap();
		params.put("board_idx", String.valueOf(board_idx));
		params.put("board_type", board_type);
		params.put("board_title", board_title);
		params.put("board_contents", board_contents);
		params.put("file", file);

		
		boardService.BoardModify(params);
		
		mv.addObject("board_idx", board_idx);
		
		return mv;
	}
	
	
	@RequestMapping(value = "/board_delete_ok", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView BoardDeleteOk(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/board_default"); // redirect는 '/board_default'라는 링크가 있어야함.
									// 링크는 데이터를 못 가지지만 얘는 다시 불러주는 거기때문에 데이터 가능.
		CommonUtil CU = new CommonUtil();
		FileUtiles FU = new FileUtiles();
		LocalValue lv = new LocalValue();
		
		int board_idx = Integer.parseInt(request.getParameter("board_idx"));
		String board_type = request.getParameter("board_type");
		
		BoardService boardService = new BoardServiceImpl();
		
		JSONObject board_info = new JSONObject();
		board_info = boardService.BoardInfo(board_idx);
		
		if(board_type.equals("photo")) {
			//파일 받기
			/**************************************************************/
			ServletContext context = request.getSession().getServletContext();
			String uploadPath = context.getRealPath("/")+lv.FILEUPLOAD_ROOT_PATH;
			
			// 상품 이미지 삭제
			FU.delete(uploadPath+"/"+ (String)board_info.get("file"));			
			/**************************************************************/
		}
		
		// 게시글 삭제하기
		boardService.BoardDelete(board_idx);
				
		return mv;
	}
	
	/**
	 * 게시글 답글달기
	 * @param request
	 * @param respone
	 * @return
	 */
	@RequestMapping(value = "/board_reply_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView BoardReplyDefault(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/Board/board_reply_default");
		
		return mv;
	}
	
	/**
	 * 게시글 답글 달기
	 * @param request
	 * @param respone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/board_reply_ok", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView BoardReplyOk(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/board_default");
		
		CommonUtil commonUtil = new CommonUtil();
		LocalValue lv = new LocalValue();
		FileUtiles FU = new FileUtiles();
		
		BoardService boardService = new BoardServiceImpl();
		
		int member_idx = 0;
		if(request.getParameter("member_idx")!=null) {
			member_idx = Integer.parseInt(request.getParameter("member_idx"));
		}
		
		int pre_board_idx = Integer.parseInt(request.getParameter("board_idx"));
		
		int board_idx = boardService.BoardMaxId()+1;
		String board_type = request.getParameter("board_type");
		String board_title = request.getParameter("board_title");
		board_title = board_title.replaceAll("'", "''");
						
		String board_contents = request.getParameter("board_contents");
		/*
		board_contents = request.getParameter("board_contents");
		board_contents = board_contents.replace("\r\n", "<br>");
        board_contents = board_contents.replaceAll(System.getProperty("line.separator"), "<br>");
        board_contents = board_contents.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
        */
		
		int ref = Integer.parseInt(request.getParameter("ref"));
		int subref = Integer.parseInt(request.getParameter("subref"));
		int depth = Integer.parseInt(request.getParameter("depth"));
		int visit = 1;
		
		String file = "";
		
		if(board_type.equals("photo")) {
			//파일 받기
			/**************************************************************/
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			MultipartFile photo = (MultipartFile)multipartRequest.getFile("photo");
			
			ServletContext context = request.getSession().getServletContext();
			String uploadPath = context.getRealPath("/")+lv.FILEUPLOAD_ROOT_PATH;
			String strFolder = "board";
			
			if(!photo.getOriginalFilename().isEmpty()) {
				file = FU.setSingleFileUpload(photo, uploadPath, strFolder);
			}else {
				file = "";
			}
			/**************************************************************/
		}
		
		boardService.BoardDepthUpdate(ref, depth, board_type);
		
		subref += 1;
		depth += 1;

		
		HashMap<String, String> params = new HashMap();
		params.put("board_idx", String.valueOf(board_idx));
		params.put("member_idx", String.valueOf(member_idx));
		params.put("board_type", board_type);
		params.put("board_title", board_title);
		params.put("ref", String.valueOf(ref));
		params.put("subref", String.valueOf(subref));
		params.put("depth", String.valueOf(depth));
		params.put("visit", String.valueOf(visit));
		params.put("board_contents", board_contents);
		params.put("file", file);

		
		boardService.BoardInsert(params);
		
		mv.addObject("board_type", board_type);
		
		return mv;
	}
	

	
	
	

}
