<%@page import="kr.co.pe.serviceimpl.BoardServiceImpl"%>
<%@page import="kr.co.pe.service.BoardService"%>
<%@page import="kr.co.pe.daoimpl.NoticeDaoImpl"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="kr.co.pe.dao.*"%>
<%@page import="kr.co.pe.common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
	CommonUtil CU = new CommonUtil();
	LocalValue lv = new LocalValue();
	

	int current_page = 0;
	String board_type = "free";
	if(request.getParameter("board_type")!=null){
		board_type = request.getParameter("board_type");
	}

	BoardService BS = new BoardServiceImpl();
	JSONArray board_list = new JSONArray();
	JSONObject board_info = new JSONObject();
	board_list = BS.BoardList(current_page, board_type);
	
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
                    <h1 class="h3 mb-2 text-gray-800">게시판</h1>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">자유게시판</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th style="width:80px;">No.</th>
                                            <th>타이틀</th>
                                            <th style="width:80px;">방문자</th>
                                            <th style="width:120px;">등록일</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th style="width:80px;">No.</th>
                                            <th>타이틀</th>
                                            <th style="width:80px;">방문자</th>
                                            <th style="width:120px;">등록일</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                    
                                    	<%for(int i=0;i<board_list.size();i++){ %>
                                    		<%
                                    			board_info = (JSONObject)board_list.get(i);
                                    			int board_idx = (Integer)board_info.get("board_idx");
                                    			int depth = (Integer)board_info.get("depth");
                                    		%>
	                                    	<tr>
	                                            <td><%=board_list.size()-i %></td>
	                                            <td>
	                                            	<span style="margin-right:<%=10*depth%>px;"></span><a href="javascript:send_view('<%=board_idx %>');"><%=(String)board_info.get("board_title") %></a>
	                                            </td>
	                                            <td><%=(Integer)board_info.get("visit") %></td>
	                                            <td><%=((String)board_info.get("reg_dt")).substring(0, 10) %></td>
	                                        </tr>
                                    	
                                    	<%} %>
                                    

                                    </tbody>
                                </table>
                            </div>
                            
                            <div class="text-center">
                            	<button type="button" class="btn btn-sm btn-outline-info" OnClick="send_write('<%=board_type%>');">등록하기</button>
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
 
 function send_view(board_idx){
	 
	 var allParams = {"board_idx":board_idx};
		
	$.ajax({
		type : "post",
		url : "/board_view_default.do",
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
 
// 게시글 등록하기
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
 
</script>