<%@page import="kr.co.pe.daoimpl.*"%>
<%@page import="kr.co.pe.serviceimpl.*"%>
<%@page import="kr.co.pe.service.*"%>
<%@page import="kr.co.pe.dto.*"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="kr.co.pe.dao.*"%>
<%@page import="kr.co.pe.common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
	int notice_idx = Integer.parseInt(request.getParameter("notice_idx"));
	NoticeDao ND = new NoticeDaoImpl();
	JSONObject notice_info = new JSONObject();
	notice_info = ND.NoticeInfo(notice_idx);
	
	MemberService memberService = new MemberServiceImpl();
	//LinkedHashMap member_info2 = new LinkedHashMap();
	MemberDto member_info = new MemberDto();
	
	int member_idx = 0;
	if(session.getAttribute("member_idx")!=null){
		member_idx = (Integer)session.getAttribute("member_idx");
	}
	
	String my_kind = "C";
	if(member_idx!=0){
		member_info = memberService.MemberInfo(member_idx);
		my_kind = member_info.getMember_kind();
	}
	//System.out.println("member_idx="+member_idx);

%>


<!-- Main Content -->
<div id="content">

     <!-- Begin Page Content -->
     <div class="container-fluid">

         <!-- Page Heading -->
         <h1 class="h3 mb-2 text-gray-800">Tables</h1>
         <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
             For more information about DataTables, please visit the <a target="_blank"
                 href="https://datatables.net">official DataTables documentation</a>.</p>

         <!-- DataTales Example -->
         <div class="card shadow mb-4">
             <div class="card-header py-3">
                 <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
             </div>
             <div class="card-body">
                 <div class="table-responsive">
                     <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                         <tbody>
                         	<tr>
                              <td style="width:20%; font-weight:800;">Title</td>
                              <td style="width:80%;"><%=(String)notice_info.get("notice_title") %></td>
                          </tr>
                         	<tr>
                              <td style="width:20%; font-weight:800;">Contents</td>
                              <td style="width:80%;"><%=(String)notice_info.get("notice_contents") %></td>
                          </tr>
                         </tbody>
                     </table>
                 </div>
                 
                 <div class="text-center">
             		<%if( my_kind.equals("A") ){ %>
						<button type="button" class="btn btn-sm btn-outline-info" OnClick="send_modify('<%=notice_idx%>');">수정하기</button>
						<button type="button" class="btn btn-sm btn-outline-warning" OnClick="send_delete('<%=notice_idx%>');">삭제하기</button>
					<%} %>
             		<button type="button" class="btn btn-sm btn-outline-info" OnClick="send_list();">리스트</button>
             		
             	</div>
                 
             </div><!-- card -->
         </div>

     </div>
     <!-- /.container-fluid -->

</div>
<!-- End of Main Content -->




<script language="javascript">
/* 	// 페이지가 처음 실행될 때 무조건 실행됨.
	$(document).ready(function(){
	});
 */
 
//리스트로 이동
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
 
 
	//리스트로 이동
	function send_modify(notice_idx){
	 var allParams = {"notice_idx":notice_idx};
		
	$.ajax({
		type : "post",
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
	
	
	function send_delete(notice_idx){
	 var allParams = {"notice_idx":notice_idx};
	 
	 var ans = confirm("정말 삭제하시겠습니까?");
	 
	 if(ans){
		$.ajax({
			type : "post",
			url : "/notice_delete_ok.do",
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
 
 
 
 
</script>