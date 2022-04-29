package kr.co.pe.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.pe.common.CommonUtil;
import kr.co.pe.dao.MemberDao;
import kr.co.pe.daoimpl.MemberDaoImpl;
import kr.co.pe.dto.MemberDto;

@Controller
public class AdminMemberController {
	
	@RequestMapping(value = "/admin_member_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView AdminMemberDefault(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/Admin_Member/admin_member_default");
		return mv;
	}
	
	
	@RequestMapping(value = "/admin_member_write_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView AdminMemberWriteDefault(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/Admin_Member/admin_member_write_default");
		return mv;
	}
	
	
	@RequestMapping(value = "/admin_member_write_ok", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView AdminMemberWriteOk(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin_member_default");
		
		CommonUtil CU = new CommonUtil();
		
		String member_kind = "C";
		String member_name = request.getParameter("member_name");
		String member_phone = request.getParameter("member_phone");
		String member_id = request.getParameter("member_id");
		String member_pwd = request.getParameter("member_pwd");
		int member_level = 0;
		
		
		MemberDto memberDto = new MemberDto();
		memberDto.setMember_kind(member_kind);
		memberDto.setMember_id(member_id);
		memberDto.setMember_pwd(CU.getEncrypt(member_pwd));
		memberDto.setMember_name(member_name);
		memberDto.setMember_phone(member_phone);
		memberDto.setMember_level(member_level);
		
		
		MemberDao MD = new MemberDaoImpl();
		MD.MemberInsert(memberDto);
		
		return mv;
		
	}
	
	
	
	/*
	 * @RequestMapping(value = "/member_write_ok2", method = {RequestMethod.GET,
	 * RequestMethod.POST}) public ModelAndView AdminMemberWriteOk2(HttpServletRequest
	 * request, HttpServletResponse response) { ModelAndView mv = new
	 * ModelAndView("redirect:member_default");
	 * 
	 * MemberDto memberDto = new MemberDto();
	 * 
	 * // 데이터 오브젝트 만들기 memberDto.setMember_id("test0");
	 * memberDto.setMember_pwd("1234"); memberDto.setMember_kind("A");
	 * memberDto.setMember_name("홍길동0"); memberDto.setMember_phone("01022223333");
	 * memberDto.setReg_dt("1900-01-01"); memberDto.setMod_dt("1900-01-01");
	 * 
	 * myMemberDao.insertMember(memberDto);
	 * 
	 * return mv;
	 * 
	 * }
	 */
	
	/**
	 * 회원 정보 보기
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/admin_member_view_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView AdminMemberViewDefault(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/Admin_Member/admin_member_view_default");
		return mv;
	}
	
	/**
	 * 회원 정보 수정 페이지
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/admin_member_modify_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView AdminMemberModifyDefault(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/Admin_Member/admin_member_modify_default");
		return mv;
	}
	
	/**
	 * 회원 정보 수정 완료
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin_member_modify_ok", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView AdminMemberModifyOk(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin_member_default");
		
		CommonUtil CU = new CommonUtil();
		
		int member_idx = Integer.parseInt(request.getParameter("member_idx"));
		String member_kind = request.getParameter("member_kind");
		String member_id = request.getParameter("member_id");
		String member_pwd = request.getParameter("member_pwd");
		String member_name = request.getParameter("member_name");
		String member_phone = request.getParameter("member_phone");
		int member_level = Integer.parseInt(request.getParameter("member_level"));
		
		
		MemberDto memberDto = new MemberDto();
		memberDto.setMember_idx(member_idx);
		memberDto.setMember_kind(member_kind);
		memberDto.setMember_id(member_id);
		memberDto.setMember_pwd( CU.getEncrypt(member_pwd) );
		memberDto.setMember_name(member_name);
		memberDto.setMember_phone(member_phone);
		memberDto.setMember_level(member_level);
		
		MemberDao MD = new MemberDaoImpl();
		MD.MemberModify(memberDto);
		
		return mv;
		
	}
	
	
	
	
	
	
	
}
