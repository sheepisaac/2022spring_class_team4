<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editor Test</title>
<style type="text/css" media="screen">
    #editor { 
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
    }
</style>
</head>
<body>
	<h2>editor test</h2>
		
	<form name="WriteForm" id="WriteForm" method="post">
		<input type="text" class="form-control form-control-sm" name="testcode_title2" id="testcode_title2" style="width:300px; max-width:300px;">
		<br><br>
		
		<div name="testcode_editor2" id="testcode_editor2" style="height:300px;">
			<textarea name="testcode_contents2" id="testcode_contents2" style="width:100%;height:100%;display:none;"></textarea>
		</div>
		<br>
		
	</form>
	<div>
		<button type="button" class="btn btn-sm" id="sendbtn" onclick="text();">보내기</button>
	</div>
	<p>This is test version.</p>
	<div>
		<textarea name="testcode_result" id="testcode_result" style="width:100%;height:100px;"></textarea>
	</div>
	
	<!-- Jquery CDN -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.4.14/ace.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>


<script>
var editor = ace.edit("testcode_editor2");
editor.setTheme("ace/theme/monokai");
editor.session.setMode("ace/mode/python");
editor.setValue("#test\nprint("+"\"Hello World!\""+")", -1);

function send(){
	var obj = document.WriteForm;
	
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
	
	if(obj.testcode_title2.value == ""){
		alert("제목을 입력해 주세요");
		obj.testcode_title2.focus();
		return false;
	}
	var title = obj.testcode_title2.value;
	
	var allParams = {
		"testcode_title": title,
		"testcode_contents": editor.getValue()
	};
	
	obj.action="/testcode_write_ok.do";
	obj.submit();
	
}

</script>