<%@page import="kr.co.pe.dto.MemberDto"%>
<%@page import="kr.co.pe.serviceimpl.MemberServiceImpl"%>
<%@page import="kr.co.pe.service.MemberService"%>
<%@page import="kr.co.pe.daoimpl.NoticeDaoImpl"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="kr.co.pe.dao.*"%>
<%@page import="kr.co.pe.common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
	CommonUtil commonUtil = new CommonUtil();

	//int my_idx = (Integer)session.getAttribute("member_idx");
	int my_idx = (Integer)session.getAttribute("member_idx");
	int member_idx = Integer.parseInt(request.getParameter("his_idx"));

	MemberService memberService = new MemberServiceImpl();
	MemberDto memberDto = new MemberDto();
	memberDto = memberService.MemberInfo(member_idx);
	
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

<!-- Custom styles for this page -->
<!-- <link href="/jsphome/scripts/sb_admin/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet"> -->

<!-- Main Content -->
<div id="content">

	<!-- Begin Page Content -->
	<div class="container-fluid">
	
		<!-- Page Heading -->
		<h1 class="h3 mb-2 text-gray-800">회원 정보 보기</h1>
		
		<!-- DataTales Example -->
		<div class="card shadow mb-4">
		    <div class="card-header py-3">
		        <h6 class="m-0 font-weight-bold text-primary">회원 정보 보기</h6>
		    </div>
		    
		    <div class="card-body">
		        <div class="table-responsive">
		            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
		                <tbody>
		                	<tr>
		                        <td style="width:20%;">회원 고유 넘버</td>
								<td style="width:80%;"><%=memberDto.getMember_idx() %></td>
		   					</tr>
							<tr>
		       					<td style="width:20%;">회원 종류</td>
		     					<td style="width:80%;">
							     	<%
							     		String member_kind = memberDto.getMember_kind();
							     	%>
							     	<%if(member_kind.equals("A")){ %>
							     		관리자
							     	<%} else{ %>
							     		일반 회원
							     	<%} %>
		                        </td>
							</tr>
							<tr>
								<td style="width:20%;">회원 이름</td>
								<td style="width:80%;"><%=memberDto.getMember_name() %></td>
							</tr>
							<tr>
								<td style="width:20%;">회원 아이디</td>
								<td style="width:80%;"><%=memberDto.getMember_id() %></td>
							</tr>
							<tr>
								<td style="width:20%;">회원 비밀번호</td>
								<td style="width:80%;"><%=commonUtil.getDecrypt(memberDto.getMember_pwd()) %></td>
							</tr>
							<tr>
								<td style="width:20%;">회원 연락처</td>
								<td style="width:80%;"><%=commonUtil.phoneNumberHyphenAdd(memberDto.getMember_phone(), "N")  %></td>
							</tr>
							<tr>
								<td style="width:20%;">회원 진도</td>
								<td style="width:80%;"><%=memberDto.getMember_level()  %></td>
							</tr>
		           
						</tbody>
					</table>
				</div>
		
				<div class="text-center">
					<button type="button" class="btn btn-sm btn-outline-info" OnClick="send_list();">리스트</button>
					<button type="button" class="btn btn-sm btn-outline-info" OnClick="send_modify('<%=member_idx%>', '<%=current_page%>');">수정하기</button>
				</div>
		    	
			</div>
		</div>
		<!-- 카드 완료 -->
	
	</div>
	<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<!-- Page level plugins -->
<script src="/jsphome/scripts/sb_admin/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="/jsphome/scripts/sb_admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="/jsphome/scripts/sb_admin/js/demo/datatables-demo.js"></script>


<script language="javascript">
/* 	// 페이지가 처음 실행될 때 무조건 실행됨.
	$(document).ready(function(){
	});
 */
 
 // 회원 정보 수정 페이지 이동
function send_modify(his_idx, current_page){
	var allParams = {
		"his_idx":his_idx,
		"current_page":current_page
	};
	
	$.ajax({
		type : "post",
		url : "/admin_member_modify_default.do",
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
 
 // 메인 홈 이동
function send_list(current_page){
	var allParams = {"current_page": current_page};
	
	$.ajax({
		type : "post",
		url : "/admin_member_default.do",
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