<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	int member_idx = (Integer)session.getAttribute("member_idx");
%>

<style type="text/css" media="screen">
    #editor { 
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
    }
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
		<h1 class="h3 mb-2 text-gray-800">에이스 에디터 test</h1>
		
		<!-- DataTales Example -->
		<div class="card shadow mb-4">
		    <div class="card-header py-3">
		        <h6 class="m-0 font-weight-bold text-primary">에이스 에디터</h6>
		    </div>
		    
		    <div class="card-body">
		        <form name="WriteForm" id="WriteForm" method="post">
					<input type="text" class="form-control form-control-sm" name="testcode_title2" id="testcode_title2" style="width:300px; max-width:300px;">
					<br>
					
					<input type="text" class="form-control form-control-lg" name="testcode_contents2" id="testcode_contents2" style="width:300px; max-width:300px;display:none;">
					<div name="testcode_editor2" id="testcode_editor2" style="height:300px;">
					</div>
				</form>
				
				<br>
				<div>
					<button type="button" class="btn btn-sm btn-outline-info" id="sendbtn" OnClick="test2();">보내기</button>
				</div>
				<p>This is test version.</p>
				<table class="table table-bordered"  width="100%" cellspacing="0">
					<tr id="testcode_result"></tr>
				</table>
				<!-- <div name="testcode_result" id="testcode_result" style=""> -->
					<!-- <textarea name="testcode_result" id="testcode_result" style="width:100%;height:100px;"></textarea> -->
				<!-- </div> -->
		    	
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

<!-- Jquery CDN -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>


<script src="/jsphome/scripts/ACEeditor/src/ace.js" type="text/javascript" charset="utf-8"></script>
<script language="javascript">
var editor = ace.edit("testcode_editor2");
//var editor = getCodeEditor("testcode_editor2", "ace/mode/javascript");
editor.setTheme("ace/theme/twilight");
editor.session.setMode("ace/mode/python");
editor.setValue("print('hello world')", -1);

function send(){
	var obj = document.WriteForm;
	var code = editor.getValue();
	obj.testcode_contents2.value = code;
	
	if(obj.testcode_title2.value == ""){
	 alert("제목을 입력해 주세요");
	 obj.testcode_title2.focus();
	 return false;
	}
	
	
	if(obj.testcode_contents2.value == ""){
	 alert("내용을 작성해 주세요");
	 obj.testcode_contents2.focus();
	 return false;
	}
	 
	// 일반 폼 방식
	//var allParams = jQuery("#WriteForm").serialize();
	
	//mona.getById["testcode_contents"].exec("UPDATE_CONTENTS_FIELD", []);

	//obj.action="/testcode_write_ok.do";
	//obj.submit();
		
}

function text(){
	var obj = document.WriteForm;
	
	/* if(obj.testcode_title2.value == ""){
		alert("제목을 입력해 주세요");
		obj.testcode_title2.focus();
		return false;
	} */
	var title = "test.txt";
	
	var allParams = {
		"testcode_title": title,
		"testcode_contents": editor.getValue()
	};
	
	//var allParams = jQuery("#WriteForm").serialize();
	
	//-------------------
/* 	set fso = CreateObject("Scripting.FileSystemObject");
	set s = fso.CreateTextFile("/resources/file/"+title, True);
	
	s.writeline(editor.getValue());
	s.Close(); */
	//-------------------
	
	
	$.ajax({
		type : "post",
		//enctype: "multipart/form-data",
		url : "/testcode_write_ok.do",
		timeout : 30000,
		cache : false,
		data : allParams,
		datatype : 'html',
		success : function(data) {
			alert("통신데이터 값 : " + data);
			$("#testcode_result").html(data);
		},
		error : function(data, status, error) {
			alert("통신데이터 값 : " + data);
			$("#main_contents").html(data);
		}
		
	});
	/* obj.action="/testcode_write_ok.do";
	obj.submit(); */
	
}

function test2(){
	
	var allParams = {
		"code": editor.getValue()
	};
	
	$.ajax({
		type : "post",
		//enctype: "multipart/form-data",
		url : "/python_compile_ok.do",
		timeout : 30000,
		cache : false,
		data : allParams,
		datatype : 'html',
		success : function(data) {
			console.log(data);
			var str = '<td>'+data+'</td>';
			$("#testcode_result").html(str);
		},
		error : function(data, status, error) {
			alert("통신데이터 값 : " + data);
			$("#main_contents").html(data);
		}
		
	});
	
}

</script>