<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	int member_idx = 0;
	String member_id = "";
	String member_pwd = "";
	String member_name = "";
	String member_kind = "C";
	int member_level = 0;
	
	if(session.getAttribute("member_idx")!=null){
		member_idx = (Integer)session.getAttribute("member_idx");
		member_id = (String)session.getAttribute("member_id");
		member_name = (String)session.getAttribute("member_name");
		member_kind = (String)session.getAttribute("member_kind");
		member_level = (Integer)session.getAttribute("member_level");
	}
	
	//System.out.println("member_idx="+member_idx);
%>
    
<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">SB Admin <sup>2</sup></div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <a class="nav-link" href="/">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Interface
    </div>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
            aria-expanded="true" aria-controls="collapseTwo">
            <i class="fas fa-fw fa-cog"></i>
            <span>커리큘럼</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">커리큘럼:</h6>
                <a class="collapse-item" href="javascript:send_menu('/testcode_default1.do')">monaco editor</a>
                <a class="collapse-item" href="javascript:send_menu('/testcode_default2.do')">ace editor</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Utilities Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
            aria-expanded="true" aria-controls="collapseUtilities">
            <i class="fas fa-fw fa-wrench"></i>
            <span>게시판</span>
        </a>
        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
            data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Custom Utilities:</h6>
                <a class="collapse-item" href="javascript:send_menu('/board_default.do')">자유게시판</a>
                <a class="collapse-item" href="javascript:send_menu('/notice_default.do')">공지사항</a>
            </div>
        </div>
    </li>
    <!-- Divider -->
    <hr class="sidebar-divider">

	<%if(member_kind.equals("A")){ %>
	    <!-- Heading -->
	    <div class="sidebar-heading">
	        Addons
	    </div>
	
	    <!-- Nav Item - Pages Collapse Menu -->
		<li class="nav-item">
		    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
		        aria-expanded="true" aria-controls="collapsePages">
		        <i class="fas fa-fw fa-folder"></i>
		        <span>관리자 페이지</span>
		    </a>
		    <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
		        <div class="bg-white py-2 collapse-inner rounded">
		            <h6 class="collapse-header">회원 관리:</h6>
		            <a class="collapse-item" href="javascript:send_menu('/admin_member_default.do')">회원 리스트</a>
		            <div class="collapse-divider"></div>
		            <h6 class="collapse-header">공지사항 관리:</h6>
		            <a class="collapse-item" href="javascript:send_menu('/notice_write_default.do')">공지사항 리스트</a>
		            <div class="collapse-divider"></div>
		            <h6 class="collapse-header">자유게시판 관리:</h6>
		            <a class="collapse-item" href="javascript:send_menu('/board_write_default.do')">자유게시판 리스트</a>
		        </div>
		    </div>
		</li>
	
	    <!-- Nav Item - Charts -->
	<!--     <li class="nav-item">
	        <a class="nav-link" href="/jsphome/theme/charts.html">
	            <i class="fas fa-fw fa-chart-area"></i>
	            <span>Charts</span></a>
	    </li> -->
	
	    <!-- Nav Item - Tables -->
	<!--     <li class="nav-item">
	        <a class="nav-link" href="/jsphome/theme/tables.html">
	            <i class="fas fa-fw fa-table"></i>
	            <span>Tables</span></a>
	    </li> -->
	
	    <!-- Divider -->
	    <hr class="sidebar-divider d-none d-md-block">
    <%} %>

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

    <!-- Sidebar Message -->
    <div class="sidebar-card d-none d-lg-flex">
        <img class="sidebar-card-illustration mb-2" src="/jsphome/theme/img/undraw_rocket.svg" alt="...">
        <p class="text-center mb-2"><strong>SB Admin Pro</strong> is packed with premium features, components, and more!</p>
        <a class="btn btn-success btn-sm" href="https://startbootstrap.com/theme/sb-admin-pro">Upgrade to Pro!</a>
    </div>

</ul>
<!-- End of Sidebar -->


<script langauge="javascript">

function send_menu(strUrl){
	var allParams = {};
	
	$.ajax({
		type : "post",
		url : strUrl,
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