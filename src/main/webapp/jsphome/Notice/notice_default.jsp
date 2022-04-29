<%@page import="kr.co.pe.daoimpl.*"%>
<%@page import="kr.co.pe.dto.*"%>
<%@page import="kr.co.pe.service.*"%>
<%@page import="kr.co.pe.serviceimpl.*"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="kr.co.pe.dao.*"%>
<%@page import="kr.co.pe.common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 
<%
	CommonUtil commonUtil = new CommonUtil();

	NoticeDao ND = new NoticeDaoImpl();
	JSONArray notice_list = new JSONArray();
	JSONObject notice_info = new JSONObject();
	notice_list = ND.NoticeList(0);
	
	MemberService memberService = new MemberServiceImpl();
	//LinkedHashMap member_info2 = new LinkedHashMap();
	MemberDto my_info = new MemberDto();
	
	int member_idx = 0;
	if(session.getAttribute("member_idx")!=null){
		member_idx = (Integer)session.getAttribute("member_idx");
	}
	//int my_idx = (Integer)session.getAttribute("member_idx");
	//my_info = memberService.MemberInfo(my_idx);
	//String my_kind = my_info.getMember_kind();
	//System.out.println("member_idx="+member_idx);
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
<link href="/jsphome/theme/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

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
                                    <thead>
                                        <tr>
                                            <th>No.</th>
                                            <th>Title</th>
                                            <th>Contents</th>
                                            <th>작성자</th>
                                            <th>reg_dt</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>No.</th>
                                            <th>Title</th>
                                            <th>Contents</th>
                                            <th>작성자</th>
                                            <th>reg_dt</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                    
                                    	<%for(int i=0;i<notice_list.size();i++){ %>
                                    		<%
                                    			notice_info = (JSONObject)notice_list.get(i);
                                    			int notice_idx = (Integer)notice_info.get("notice_idx");
                                    			String reg_dt = (String)notice_info.get("reg_dt");
                                    		%>
	                                    	<tr>
	                                            <td><%=notice_list.size()-i %></td>
	                                            <td>
	                                            	<a href="javascript:send_view('<%=notice_idx %>');"><%=(String)notice_info.get("notice_title") %></a>
	                                            </td>
	                                            <td><%=(String)notice_info.get("notice_contents") %></td>
	                                            <td>관리자</td>
	                                            <td><%=reg_dt.substring(0, 11) %></td>
	                                        </tr>
                                    	
                                    	<%} %>
                                    

                                    </tbody>
                                </table>
                            </div>
                            
<%--                             <%if(member_kind.equals("A")){ %>
	                            <div class="text-center">
	                            	<button type="button" class="btn btn-sm btn-outline-info" OnClick="send_write();">등록하기</button>
	                            </div>
                            <%} %> --%>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<!-- Page level plugins -->
<script src="/jsphome/theme/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="/jsphome/theme/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="/jsphome/theme/js/demo/datatables-demo.js"></script>


<script language="javascript">
/* 	// 페이지가 처음 실행될 때 무조건 실행됨.
	$(document).ready(function(){
	});
 */
 
 function send_view(notice_idx){
	 
	var allParams = {
		"notice_idx":notice_idx,
	};
		
	$.ajax({
		type : "post",
		url : "/notice_view_default.do",
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
 
 
function send_write(){
	 
	 var allParams = {};
		
	$.ajax({
		type : "post",
		url : "/notice_write_default.do",
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