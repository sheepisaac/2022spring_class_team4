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
	
	int member_idx = (Integer)session.getAttribute("member_idx");
	System.out.println("notice_modify member_idx="+member_idx);

	int notice_idx = Integer.parseInt(request.getParameter("notice_idx"));
	NoticeDao ND = new NoticeDaoImpl();
	JSONObject notice_info = new JSONObject();
	notice_info = ND.NoticeInfo(notice_idx);

	String notice_title = (String)notice_info.get("notice_title");
	notice_title = notice_title.replaceAll("''", "'");
	
	//String contents_check1 = (String)notice_info.get("contents_check");
	
	String notice_contents = (String)notice_info.get("notice_contents");
/* 	if(contents_check1.equals("N")){
		notice_contents = notice_contents.replaceAll("<br>", "\r\n");
		notice_contents = notice_contents.replaceAll("''", "'");
	} */
	

	MemberService memberService = new MemberServiceImpl();
	MemberDto memberDto = new MemberDto();
	memberDto = memberService.MemberInfo(member_idx);
	
	//String contents_check = (String)notice_info.get("contents_check");
	//String contents_check = "Y";
/* 	if(request.getParameter("contents_check")!=null){
		contents_check = request.getParameter("contents_check");
	} */
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
                    <h1 class="h3 mb-2 text-gray-800">공지사항 수정하기</h1>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">공지사항 수정하기</h6>
                        </div>
                        <div class="card-body">
                        	<form name="WriteForm" id="WriteForm" method="post">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <tbody>
                                        <tr>
                                            <td style="width:20%; vertical-align:middle;">공지사항 제목</td>
                                            <td style="width:80%;">
	                                            <input type="text" class="form-control form-control-sm" name="notice_title" id="notice_title" style="width:300px; max-width:300px;" value="<%=notice_title%>">
                                            </td>
                                        </tr>
                                        
                                        <tr>
                                            <td style="width:20%; vertical-align:middle;">공지사항 내용</td>
                                            <td style="width:80%;">
                                            	<script type="text/javascript" src="/jsphome/scripts/SE/js/HuskyEZCreator.js" charset="utf-8"></script>
	            								<textarea name="notice_contents" id="notice_contents" style="width:100%;height:300px;display:none;"><%=notice_contents %></textarea>
                                            </td>
                                        </tr>
                                    
                                    </tbody>
                                </table>
                            </div>
                        	
                        	<div class="text-center">
                        		<button type="button" class="btn btn-sm btn-outline-info" OnClick="send_list();">리스트</button>
                        		<button type="button" class="btn btn-sm btn-outline-info" OnClick="send_modify();">수정하기</button>
                        	</div>
                        	
                        	<input type="hidden" name="notice_idx" value="<%=notice_idx %>">
                        	</form>
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
$(document).ready(function(){
	
})

var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "notice_contents",
    sSkinURI: "/jsphome/scripts/SE/SmartEditor2Skin.html",
    fCreator: "createSEditor2"
});


// 에디터 체크 함수
function send_check(a, notice_idx){
	
	// 파라미터 직접 입력 방식
	var allParams = {"notice_idx":notice_idx};
	
	// 일반 폼 방식
	//var allParams = jQuery("#WriteForm").serialize();
	
	// 파일 정송 방식
	//var form = $("#ModifyForm")[0];
	//var params = new FormData(form);
	 
	$.ajax({
		type : "post",
		//enctype: "multipart/form-data",
		url : "/notice_modify_default.do",
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
 
 // 공지사항 수정 완료
 function send_modify(){
	 var obj = document.WriteForm;
	 
	 if(obj.notice_title.value == ""){
		 alert("공지사항 제목을 입력해 주세요");
		 obj.notice_title.focus();
		 return false;
	 }
	 
	 
	 
	 oEditors.getById["notice_contents"].exec("UPDATE_CONTENTS_FIELD", []);
	 
	// 파라미터 직접 입력 방식
	//var allParams = {};
	
	// 일반 폼 방식
	var allParams = jQuery("#WriteForm").serialize();
	
	// 파일 정송 방식
	//var form = $("#ModifyForm")[0];
	//var params = new FormData(form);
	
	var ans = confirm("정말 수정하시겠습니까?");
	if(ans){
		$.ajax({
			type : "post",
			//enctype: "multipart/form-data",
			url : "/notice_modify_ok.do",
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
	} else{
		return false;
	}
	 
	 
	
 }
 
 // 리스트로 이동
 function send_list(){
	 var allParams = {};
		
	$.ajax({
		type : "post",
		url : "/notice_default.do",
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