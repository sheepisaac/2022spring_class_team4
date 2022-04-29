<%@page import="kr.co.pe.dto.MemberDto"%>
<%@page import="kr.co.pe.serviceimpl.MemberServiceImpl"%>
<%@page import="kr.co.pe.service.MemberService"%>
<%@page import="kr.co.pe.daoimpl.NoticeDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="kr.co.pe.dao.*"%>
<%@page import="kr.co.pe.common.*"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>

<%
	CommonUtil commonUtil = new CommonUtil();

	int my_idx = (Integer)session.getAttribute("member_idx");
	int member_idx = Integer.parseInt(request.getParameter("his_idx"));
	//int my_idx = (Integer)session.getAttribute("my_idx");

	MemberService memberService = new MemberServiceImpl();
	MemberDto memberDto = new MemberDto();
	memberDto = memberService.MemberInfo(member_idx);
	
	//MemberDto myDto = new MemberDto();
	//myDto = memberService.MemberInfo(my_idx);
	//String my_kind = myDto.getMember_kind();
	
	int current_page = 1;
	if(request.getParameter("current_page")!=null){
		current_page = Integer.parseInt(request.getParameter("current_page"));
	}
%>

<style>

	a {
		text-decoration-line:none;
		color:inherit;
	}

	a:hover {
		text-decoration-line:none;
		color:inherit;
	}

</style>

	
<!-- Main Content -->
<div id="content">

	<!-- Begin Page Content -->
	<div class="container-fluid">
	
	    <!-- Page Heading -->
	    <h1 class="h3 mb-2 text-gray-800">회원 정보 수정하기</h1>
	
	    <!-- DataTales Example -->
	    <div class="card shadow mb-4">
	        <div class="card-header py-3">
	            <h6 class="m-0 font-weight-bold text-primary">회원 정보 수정하기</h6>
	        </div>
	        <div class="card-body">
	        
            	<form name="ModifyForm" id="ModifyForm" method="post">
	            <div class="table-responsive">
	                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	                    <tbody>
	                        
	                        <tr>
	                            <td style="width:20%;">회원 종류</td>
	                            <td style="width:80%;">
	                            
	                            	<div class="form-inline">
		                            	<%
		                            		String member_kind = memberDto.getMember_kind();
		                            	%>
	
		                            	<select class="form-control form-control-sm" name="member_kind2" id="member_kind2">
		                            	
			                            	<%if(member_kind.equals("A")){ %>
			                            		<option value="C">일반회원</option>
			                            		<option value="A" selected>관리자</option>
			                            	<%}else{ %>
			                            		<option value="C" selected>일반회원</option>
			                            		<option value="A">관리자</option>
			                            	<%} %>
		                            		
		                            	</select>
		                            	<input type="hidden" name="member_kind" id="member_kind" value="">
	                            	</div>

                            	</td>
	                        </tr>
							
	                        <tr>
	                            <td style="width:20%;">회원 아이디</td>
	                            <td style="width:80%;">
	                            	<div class="form-inline">
		                            	<input type="text" class="form-control form-control-sm " value="<%=memberDto.getMember_id() %>" disabled>
		                            	<input type="hidden" name="member_id" id="member_id" value="<%=memberDto.getMember_id()%>">
	                            	</div>
                            	</td>
	                        </tr>

	                        <tr>
	                            <td style="width:20%;">비밀번호</td>
	                            <td style="width:80%;">
	                            	<div class="form-inline">
		                            	<input type="password" class="form-control form-control-sm" value="<%=commonUtil.getDecrypt(memberDto.getMember_pwd())%>" disabled>
	                            		<input type="hidden" name="member_pwd" id="member_pwd" value="<%=commonUtil.getDecrypt(memberDto.getMember_pwd())%>">
	                            	</div>
                            	</td>
	                        </tr>

	                        <tr>
	                            <td style="width:20%;">회원 이름</td>
	                            <td style="width:80%;">
	                            	<div class="form-inline">
		                            	<input type="text" class="form-control form-control-sm" value="<%=memberDto.getMember_name() %>" disabled>
	                            		<input type="hidden" name="member_name" id="member_name" value="<%=memberDto.getMember_name()%>">
	                            	</div>
                            	</td>
	                        </tr>
	                        
	                        <tr>
	                            <td style="width:20%;">회원 연락처</td>
	                            <td style="width:80%;">
	                            	<div class="form-inline">
		                            	<input type="number" class="form-control form-control-sm" value="<%=memberDto.getMember_phone() %>" disabled>
	                            		<input type="hidden" name="member_phone" id="member_phone" value="<%=memberDto.getMember_phone()%>">
	                            	</div>
                            	</td>
	                        </tr>
	                        
	                        <tr>
	                            <td style="width:20%;">회원 진도</td>
	                            <td style="width:80%;">
	                            	<div class="form-inline">
		                            	<input type="number" class="form-control form-control-sm" name="member_level" id="member_level" value="<%=memberDto.getMember_level() %>" >
	                            	</div>
                            	</td>
	                        </tr>

	                    </tbody>
	                </table>
	            </div>
	            <div class="text-center">
	            	<button type="button" class="btn btn-sm btn-outline-info"" OnClick="send_view('<%=member_idx%>', '<%=current_page%>');">회원정보</button>
	            	<button type="button" class="btn btn-sm btn-outline-info"" OnClick="send_modify();">회원수정</button>
	            </div>
	            
				<input type="hidden" name="member_idx" value="<%=member_idx%>">
				<input type="hidden" name="current_page" value="<%=current_page%>">
	            </form>
	        </div>
	    </div><!-- 카드 완료 -->
	
	</div>
	<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->


<script langauge="javascript">
	
	//회원 정보 수정 페이지 이동
	function send_modify(){
		
		var obj = document.ModifyForm;
		var kind = document.getElementById("member_kind2");
		member_kind.value = kind.options[kind.selectedIndex].value
		
		if(obj.member_kind.value==""){
			alert("회원 종류를 선택해 주세요");
			obj.member_kind.focus();
			return false;
		}

		if(obj.member_level.value==""){
			alert("회원 진도를 입력해 주세요");
			obj.member_level.focus();
			return false;
		}
		
		var ans = confirm("정말 수정 하시겠습니까?");
		
		if(ans){
			
			//파라미터 직접 입력 방식
			//var allParams = {};
			
			//일반 폼 방식
			var allParams = jQuery("#ModifyForm").serialize();
			
			//파일 전송 방식
			//var form = $("#ModifyForm")[0];
			//var allParams = new FormData(form);
			
			$.ajax({
				type : "post",
				//enctype: "multipart/form-data",
				url : "/admin_member_modify_ok.do",
				timeout : 30000,
				cache : false,
				data : allParams,
				datatype : 'html',
				success : function(data) {
					$("#main_contents").html(data);
				},
				error : function(data, status, error) {
					alert("통신데이터 값 : " + data);
					$("#main_contents").html(data);
				}
				
			});
			
			
		}else{
			return false;
		}
		
	}

	
//메인 홈 이동
function send_view(his_idx, current_page){

	var allParams = {
			"his_idx":his_idx,
			"current_page":current_page
	};
	
	$.ajax({
		type : "post",
		url : "/admin_member_view_default.do",
		timeout : 30000,
		cache : false,
		data : allParams,
		datatype : 'html',
		success : function(data) {
			$("#main_contents").html(data);
		},
		error : function(data, status, error) {
			alert("통신데이터 값 : " + data);
			$("#main_contents").html(data);
		}
		
	});
	
	
}
	
</script>

