package kr.co.pe.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.pe.common.CommonMail;
import kr.co.pe.common.CommonUtil;
import kr.co.pe.common.LocalValue;
import kr.co.pe.service.LoginService;
import kr.co.pe.service.MemberService;
import kr.co.pe.serviceimpl.LoginServiceImpl;
import kr.co.pe.serviceimpl.MemberServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * 로그인 페이지
	 * @param request
	 * @param respone
	 * @return
	 */
	@RequestMapping(value = "/login_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView LoginDefault(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/Login/login_default");
		return mv;
		
	}
	
	
	/**
	 * 로그인 완료
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/login_ok", method = {RequestMethod.GET, RequestMethod.POST})
	public void LoginOk(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// html을 만들기 위해
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
			
		// 세션을 만들기 위해
		HttpSession session = request.getSession();
			
		// 로그인 완료
		String member_id = request.getParameter("member_id");
		String member_pwd = request.getParameter("member_pwd");
		
		CommonUtil commonUtil = new CommonUtil();
		member_pwd = commonUtil.getEncrypt(member_pwd);
		
		int nflag = 0;
			
		try {
			LoginService LS = new LoginServiceImpl();
			
			nflag = LS.LoginCheck(member_id, member_pwd);
			if(nflag == 2) {
				MemberService memberService = new MemberServiceImpl();
				LinkedHashMap member_info = new LinkedHashMap();
				member_info = memberService.MemberInfo2(member_id, member_pwd);
				
				System.out.println("로그인이 되었습니다.");
				session.setAttribute("member_idx", (Integer)member_info.get("member_idx"));
				session.setAttribute("member_id", member_id);
				session.setAttribute("member_pwd", member_pwd);
				session.setAttribute("member_kind", (String)member_info.get("member_kind"));
				session.setAttribute("member_name", (String)member_info.get("member_name"));
				session.setAttribute("member_phone", (String)member_info.get("member_phone"));
				session.setAttribute("member_level", (Integer)member_info.get("member_level"));
				
				
				response.sendRedirect("/");
				
			}else if(nflag == 1){
				System.out.println("로그인 실패 : 비밀번호가 틀립니다.");
				out.println("<script>");
				out.println("alert('비밀번호가 틀립니다. 확인해 주세요.');");
				out.println("location.href='/login_default.do'");
				out.println("</script>");
				
			}else {
				System.out.println("로그인 실패 : 회원 아이디가 틀립니다.");
				out.println("<script>");
				out.println("alert('아이디가 틀립니다. 확인해 주세요.');");
				out.println("location.href='/login_default.do'");
				out.println("</script>");
				//response.sendRedirect("/megait/login_default.do");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			//response.sendRedirect("/megait/Error/error.jsp");
		}
		
	
	}
	
	/**
	 * 로그아웃
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout_ok", method = {RequestMethod.GET, RequestMethod.POST})
	public void LogoutOk(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// html을 만들기 위해
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
			
		// 세션을 만들기 위해
		HttpSession session = request.getSession();
			
		try {
			System.out.println("로그아웃되었습니다.");
			
			session.invalidate();	// invalidate는 모든걸 (초기화) 날려버림.		
			out.println("<script>");
			out.println("alert('회원님 로그아웃되었습니다.');");
			out.println("location.href='/'");
			out.println("</script>");
						
		} catch (Exception e) {
			e.printStackTrace();
			//response.sendRedirect("/megait/Error/error.jsp");
		}
		
	
	}
	
	
	/**
	 * 회원 비밀번호 찾기
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/pwd_find_ok", method = {RequestMethod.GET, RequestMethod.POST})
	public void PwdFindOk(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LocalValue lv = new LocalValue();
		CommonUtil CU = new CommonUtil();
		CommonMail CM = new CommonMail();
		
		// html을 만들기 위해
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
					
		// 세션을 만들기 위해
		HttpSession session = request.getSession();
		
		String member_id = request.getParameter("member_id");
		MemberService memberService = new MemberServiceImpl();
		JSONObject member_info = new JSONObject();
		member_info = memberService.MemberInfo3(member_id);
					
		try {
			
			if(member_info.size() > 0) {
				
				System.out.println("== 새로운 비밀번호를 이메일로 보냈습니다. ==");
				
				String new_pwd = String.valueOf(CU.RandomNum6());
				String new_pwd_temp = CU.getEncrypt(new_pwd); // 임시 비밀번호 암호화
				memberService.PwdUpdate((Integer)member_info.get("member_idx"), new_pwd_temp);
				String email_contents = "";
				email_contents += "안녕하세요. 메가샵 쇼핑몰입니다.\n";
				email_contents += "회원님의 임시 비밀번호는 "+new_pwd+" 입니다.\n";
				email_contents += "로그인을 하시고 필히 변경하여 주세요.";
				
				
				HashMap<String, String> params = new HashMap();
				params.put("from_email", lv.G_MAIL_ID);
				params.put("from_name", (String)member_info.get("member_name"));
				params.put("to_email", (String)member_info.get("member_email"));
				params.put("subject", (String)member_info.get("member_name")+"님 임시 비밀번호 전달");
				params.put("email_contents", email_contents);
				CM.SendMail(params);
				
				out.println("<script>");
				out.println("alert('비밀번호를 회원님의 이메일로 보냈습니다.');");
				out.println("location.href='/login_default.do'");
				out.println("</script>");
			} else {
				
				
				System.out.println("== 현재 회원 아이디가 존재하지 않습니다. ==");
				
				out.println("<script>");
				out.println("alert('현재 회원 아이디가 존재하지 않습니다.');");
				out.println("location.href='/login_default.do'");
				out.println("</script>");
			}
			
			
		} catch (Exception e) {
			System.out.println("비밀번호 변경 에러 : "+e);
			e.printStackTrace();
			
			out.println("<script>");
			out.println("alert('비밀번호찾기에서 에러가 발생하였습니다.');");
			out.println("location.href='/'");
			out.println("</script>");
		}
		
	
	}
	
	
	/**
	 * 회원 아이디 찾기 페이지
	 * @param request
	 * @param respone
	 * @return
	 */
	@RequestMapping(value = "/id_find_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView IdFindDefault(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/frontend/Login/id_find_default");
		return mv;	
	}
	
	/**
	 * 회원 아이디 찾기 완료
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/id_find_ok", method = {RequestMethod.GET, RequestMethod.POST})
	public void IdFindOk(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LocalValue lv = new LocalValue();
		CommonUtil CU = new CommonUtil();
		CommonMail CM = new CommonMail();
		
		// html을 만들기 위해
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
					
		// 세션을 만들기 위해
		HttpSession session = request.getSession();
		
		String member_name = request.getParameter("member_name");
		String member_email = request.getParameter("member_email");
		
		MemberService memberService = new MemberServiceImpl();
		JSONObject member_info = new JSONObject();
		//member_info = memberService.MemberInfo4(member_name, member_email);
					
		try {
			
			if(member_info.size() > 0) {
				
				System.out.println("== 회원 아이디를 보냈습니다. ==");
				
				String email_contents = "";
				email_contents += "안녕하세요. 메가샵 쇼핑몰입니다.\n";
				email_contents += "회원님의 회원 ID는 "+(String)member_info.get("member_id")+" 입니다.\n";
				email_contents += "온전히 서비스를 이용하시려면 로그인을 하시기 바랍니다.";
				
				
				HashMap<String, String> params = new HashMap();
				params.put("from_email", lv.G_MAIL_ID);
				params.put("from_name", (String)member_info.get("member_name"));
				params.put("to_email", (String)member_info.get("member_email"));
				params.put("subject", (String)member_info.get("member_name")+"님 회원 ID 전달");
				params.put("email_contents", email_contents);
				CM.SendMail(params);
				
				out.println("<script>");
				//out.println("alert('회원님의 ID를 입력된 이메일로 보냈습니다.');");
				out.println("alert('회원님의 ID는 "+(String)member_info.get("member_id")+" 입니다.');");
				out.println("location.href='/login_default.do'");
				out.println("</script>");
			} else {
				
				
				System.out.println("== 현재 회원이 존재하지 않습니다. ==");
				
				out.println("<script>");
				out.println("alert('현재 회원이 존재하지 않습니다.');");
				out.println("location.href='/login_default.do'");
				out.println("</script>");
			}
			
			
		} catch (Exception e) {
			System.out.println("회원 ID 찾기 에러 : "+e);
			e.printStackTrace();
			
			out.println("<script>");
			out.println("alert('회원 아이디찾기에서 에러가 발생하였습니다.');");
			out.println("location.href='/'");
			out.println("</script>");
		}
		
	
	}
	
	
	
	

	
	
	

}
