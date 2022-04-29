<%@page import="kr.co.pe.daoimpl.*"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="kr.co.pe.dao.*"%>
<%@page import="kr.co.pe.common.*"%>
<%@page import="kr.co.pe.dto.*"%>
<%@page import="kr.co.pe.service.*"%>
<%@page import="kr.co.pe.serviceimpl.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.LinkedHashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
    
<%
	int current_page = 1;
	if(request.getParameter("current_page")!=null){
		current_page = Integer.parseInt(request.getParameter("current_page"));
	}
	
	MemberService memberDAO = new MemberServiceImpl();
	
	LinkedHashMap member_list = new LinkedHashMap();
	LinkedHashMap member_info2 = new LinkedHashMap();
	
	member_list = memberDAO.MemberList(current_page);
	
	//int my_idx = (Integer)session.getAttribute("my_idx");
	
	int member_idx;
	String member_kind = null;
	String member_id = null;
	String member_pwd = null;
	String member_name = null;
	String member_phone = null;
	int member_level;
	String reg_dt = null;
	String mod_dt = null;
	
	int total_count;
	total_count = member_list.size();
	
	int total_page = (int)Math.ceil(total_count/(10*1d));
	
	MemberDto member_info = new MemberDto();

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
<link href="/jsphome/scripts/sb_admin/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

<!-- Main Content -->
<div id="content">

	<!-- Begin Page Content -->
	<div class="container-fluid">
	
	    <!-- Page Heading -->
		<h1 class="h3 mb-2 text-gray-800">회원 정보 관리자 페이지</h1>
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
		                        <th>Name</th>
		                        <th>Id</th>
		                        <th>Phone</th>
		                        <th>Kind</th>
		                        <th>Level</th>
		                        <th>reg_dt</th>
		                        <th>mod_dt</th>
	                    	</tr>
	                	</thead>
		                <tfoot>
		                    <tr>
		                        <th>No.</th>
		                        <th>Name</th>
		                        <th>Id</th>
		                        <th>Phone</th>
		                        <th>Kind</th>
		                        <th>Level</th>
		                        <th>reg_dt</th>
		                        <th>mod_dt</th>
		                    </tr>
		                </tfoot>
		                
	                	<tbody>
	                		<%
								Iterator iter = member_list.keySet().iterator();
								String str_idx = null;
							%>
					
							<%
								int cursor = 0;
								while(iter.hasNext()){
									str_idx = (String)iter.next();
									member_info2 = (LinkedHashMap)member_list.get(str_idx);
									
									member_idx = (Integer)member_info2.get("member_idx");
									member_id = (String)member_info2.get("member_id");
									member_pwd = (String)member_info2.get("member_pwd");
									member_kind = (String)member_info2.get("member_kind");
									member_name = (String)member_info2.get("member_name");
									member_level = (Integer)member_info2.get("member_level");
									member_phone = (String)member_info2.get("member_phone");

									reg_dt = (String)member_info2.get("reg_dt");
									mod_dt = (String)member_info2.get("mod_dt");
							%>
							<tr>
								
								<td><%=total_count-(((current_page-1)*10)+(cursor+1))+1%></td>
								<td>
									<a href="javascript:send_view('<%=member_idx%>', '<%=current_page%>');"><%=member_name %></a>
								</td>
								<td><%=member_id %></td>
								<td><%=member_phone %></td>
								<td>
									<%if(member_kind.equals("A")){ %>
										관리자(<%=member_kind %>)
									<%}else{ %>
										일반회원(<%=member_kind %>)
									<%} %>
								</td>
								<td><%=member_phone %></td>
								<td><%=reg_dt %></td>
								<td><%=mod_dt %></td>
							</tr>
							<%
								cursor++;
								}
							%>
	                	
	                	
	                	
	               
						</tbody>
					</table>
				</div>
	        </div>
	    </div>
	
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
 
 function send_view(member_idx, current_page){
	 
	var allParams = {
		"member_idx": member_idx,
		"current_page": current_page,
	};
		
	$.ajax({
		type : "post",
		url : "/member_view_default.do",
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