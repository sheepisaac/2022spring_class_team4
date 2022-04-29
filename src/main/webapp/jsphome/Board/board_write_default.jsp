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
	    <h1 class="h3 mb-2 text-gray-800">게시글 등록하기</h1>
	
	    <!-- DataTales Example -->
	    <div class="card shadow mb-4">
	        <div class="card-header py-3">
	            <h6 class="m-0 font-weight-bold text-primary">게시글 등록</h6>
	        </div>
	        <div class="card-body">
	        
            	<form name="WriteForm" id="WriteForm" method="post" enctype="multipart/form-data">
	            <div class="table-responsive">
	                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	                    <tbody>

	                        <tr>
	                            <td style="width:20%;">게시글 제목</td>
	                            <td style="width:80%;">
	                            	<input type="text" class="form-control form-control-sm" name="board_title" id="board_title" style="width:300px;max-width:300px;">
                            	</td>
	                        </tr>
							
							<%if(board_type.equals("photo")){ %>
		                        <tr>
		                            <td style="width:20%;">파일 업로드</td>
		                            <td style="width:80%;">
		                            	<input type="file" name="photo" id="photo" style="width:300px;max-width:300px;">
	                            	</td>
		                        </tr>
							<%}else{ %>
								<input type="hidden" name="photo" value="">
							<%} %>
	                        <tr>
	                            <td style="width:20%;">게시글 내용</td>
	                            <td style="width:80%;">
	                            	
					            	<script type="text/javascript" src="/jsphome/scripts/SE/js/HuskyEZCreator.js" charset="utf-8"></script>
					            	<textarea name="board_contents" id="board_contents" style="width:100%;height:300px;display:none;"></textarea>

                            	</td>
	                        </tr>

	                    </tbody>
	                </table>
	            </div>
	            <div class="text-center">
	            	<button type="button" class="btn btn-sm btn-outline-info"" OnClick="send_list('<%=board_type%>');">리스트</button>
	            	<button type="button" class="btn btn-sm btn-outline-info"" OnClick="send_write();">작성하기</button>
	            </div>
	            
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
	
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors,
	    elPlaceHolder: "board_contents",
	    sSkinURI: "/jsphome/scripts/SE/SmartEditor2Skin.html",
	    fCreator: "createSEditor2"
	});	


	//회원 정보 수정 페이지 이동
	function send_write(){
		
		var obj = document.WriteForm;
		
		if(obj.board_title.value==""){
			alert("게시글 타이틀을 작성해 주세요.");
			obj.board_title.focus();
			return false;
		}
		
		oEditors.getById["board_contents"].exec("UPDATE_CONTENTS_FIELD", []);
		

		//파라미터 직접 입력 방식
		//var allParams = {};
		
		//일반 폼 방식
		//var allParams = jQuery("#WriteForm").serialize();
		
		//파일 전송 방식
		var form = $("#WriteForm")[0];
		var allParams = new FormData(form);

		$.ajax({
			type : "post",
			enctype: "multipart/form-data",
			url : "/board_write_ok.do",
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
	
</script>

