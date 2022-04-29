<%@page import="kr.co.pe.serviceimpl.BoardServiceImpl"%>
<%@page import="kr.co.pe.service.BoardService"%>
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

<%if(session.getAttribute("member_idx") == null || session.getAttribute("member_idx").equals("")) { %>
	<script language="javascript">
		alert("로그인이 필요합니다.\n로그인해 주시기 바랍니다.");
		location.href = "/login_default.do";
	</script>
<%} %>

<%
	CommonUtil commonUtil = new CommonUtil();
	LocalValue lv = new LocalValue();
	
	int member_idx = 0;
	if(session.getAttribute("member_idx")!=null){
		member_idx = (Integer)session.getAttribute("member_idx");
	}
	
	String board_type = "free";
	if(request.getParameter("board_type")!=null){
		board_type = request.getParameter("board_type");
	}
	
	int board_idx = Integer.parseInt(request.getParameter("board_idx"));
	BoardService boardService = new BoardServiceImpl();
	JSONObject board_info = boardService.BoardInfo(board_idx);
	
	String board_title = (String)board_info.get("board_title");
	board_title = board_title.replace("''", "'");
	
	// 방문자 업데이트
	boardService.BoardVisitUpdate(board_idx);

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
	    <h1 class="h3 mb-2 text-gray-800">게시글 보기</h1>
	
	    <!-- DataTales Example -->
	    <div class="card shadow mb-4">
	        <div class="card-header py-3">
	            <h6 class="m-0 font-weight-bold text-primary">게시글 보기</h6>
	        </div>
	        <div class="card-body">
	        
            	<form name="WriteForm" id="WriteForm" method="post" enctype="multipart/form-data">
	            <div class="table-responsive">
	                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	                    <tbody>

	                        <tr>
	                            <td style="width:20%;">게시글 제목</td>
	                            <td style="width:80%;">
	                            	<%=board_title %>
                            	</td>
	                        </tr>
	                        
	                        <tr>
	                            <td style="width:20%;">방문자수</td>
	                            <td style="width:80%;">
	                            	<%=(Integer)board_info.get("visit") %> 명
                            	</td>
	                        </tr>
							
							<%if(board_type.equals("photo")){ %>
		                        <tr>
		                            <td style="width:20%;">등록 파일</td>
		                            <td style="width:80%;">
		                            	<%=lv.FILEUPLOAD_ROOT_PATH %>/<%=(String)board_info.get("file") %>
	                            	</td>
		                        </tr>
							<%}else{ %>
								<input type="hidden" name="photo" value="">
							<%} %>
	                        <tr>
	                            <td style="width:20%;">게시글 내용</td>
	                            <td style="width:80%; height:400px;">
	                            	<%=(String)board_info.get("board_contents") %>
                            	</td>
	                        </tr>

	                    </tbody>
	                </table>
	            </div>
	            <div class="text-center">
	            	<button type="button" class="btn btn-sm btn-outline-info"" OnClick="send_list('<%=board_type%>');">리스트</button>
	            	<button type="button" class="btn btn-sm btn-outline-info"" OnClick="send_write('<%=board_type%>');">작성하기</button>
	            	<button type="button" class="btn btn-sm btn-outline-info"" OnClick="send_reply('<%=board_type%>', '<%=board_idx%>');">답글달기</button>
	            	
	            	<%if(member_idx == (Integer)board_info.get("member_idx") && member_idx!=0){ %>
		            	<button type="button" class="btn btn-sm btn-outline-info"" OnClick="send_modify();">수정하기</button>
		            	<button type="button" class="btn btn-sm btn-outline-warning"" OnClick="send_delete();">삭제하기</button>
	            	<%} %>
	            		
	            </div>
	            				
	            <input type="hidden" name="board_idx" value="<%=board_idx%>">
				<input type="hidden" name="member_idx" value="<%=member_idx%>">
				<input type="hidden" name="board_type" value="<%=board_type%>">
	            </form>
	        </div>
	    </div><!-- 카드 완료 -->
	
	</div>
	<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->


<script langauge="javascript">


	$(document).ready(function(){
		
	})
	

	//회원 정보 수정 페이지 이동
	function send_modify(){
		
		var obj = document.WriteForm;

		//파라미터 직접 입력 방식
		//var allParams = {};
		
		//일반 폼 방식
		//var allParams = jQuery("#WriteForm").serialize();
		
		//파일 전송 방식
		var form = $("#WriteForm")[0];
		var allParams = new FormData(form);

		//var ans = confirm("수정 하시겠습니까?");
		
		$.ajax({
			type : "post",
			enctype: "multipart/form-data",
			url : "/board_modify_default.do",
			timeout : 30000,
			cache : false,
			data : allParams,
			processData : false,
			contentType: false,
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

	

	//회원 정보 수정 페이지 이동
	function send_delete(){
		
		var obj = document.WriteForm;

		//파라미터 직접 입력 방식
		//var allParams = {};
		
		//일반 폼 방식
		//var allParams = jQuery("#WriteForm").serialize();
		
		//파일 전송 방식
		var form = $("#WriteForm")[0];
		var allParams = new FormData(form);
		
		var ans = confirm("정말 삭제하시겠습니까?");
		
		if(ans){
			$.ajax({
				type : "post",
				enctype: "multipart/form-data",
				url : "/board_delete_ok.do",
				timeout : 30000,
				cache : false,
				data : allParams,
				processData : false,
				contentType: false,
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
	function send_list(board_type){

		var allParams = {"board_type":board_type};
		
		$.ajax({
			type : "post",
			url : "/board_default.do",
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
	
	
	function send_write(board_type){

		var allParams = {"board_type":board_type};
		
		$.ajax({
			type : "post",
			url : "/board_write_default.do",
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
	
	
	function send_reply(board_type, board_idx){

		var allParams = {"board_type":board_type, "board_idx":board_idx};
		
		$.ajax({
			type : "post",
			url : "/board_reply_default.do",
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

