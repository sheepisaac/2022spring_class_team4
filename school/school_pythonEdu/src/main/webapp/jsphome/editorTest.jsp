<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editor Test</title>

<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/monaco-editor/0.20.0/min/vs/editor/editor.main.min.css">

</head>
<body>
	<h2>editor test</h2>
	
	
	<iframe id="SaveSomePart" style="display:none"></iframe>
	
	<form name="WriteForm" id="WriteForm" method="post">
		<input type="text" class="form-control form-control-sm" name="testcode_title" id="testcode_title" style="width:300px; max-width:300px;">
	
		<div name="testcode_editor" id="testcode_editor" style="height:300px;">
			<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/monaco-editor/0.26.1/min/vs/loader.min.js"></script> -->
			<textarea name="testcode_contents" id="testcode_contents" style="width:100%;height:100%;display:none;"></textarea>
		</div>
		
		<div>
			<button type="button" class="btn btn-sm" id="sendbtn" OnClick="">보내기</button>
		</div>
	</form>
	
	<p>This is test version.</p>
	<div>
		<textarea name="testcode_result" id="testcode_result" style="width:100%;height:100px;"></textarea>
	</div>
	
	<!-- Jquery CDN -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/2.0.0/FileSaver.min.js"></script>
	
</body>
</html>

<script src="https://cdnjs.cloudflare.com/ajax/libs/monaco-editor/0.26.1/min/vs/loader.min.js"></script>
<script>
var mona = [];
var text3 = "";
require.config({
	paths: {
		'vs': 'https://cdnjs.cloudflare.com/ajax/libs/monaco-editor/0.26.1/min/vs'
	}
});

/* var edito = require(["vs/editor/editor.main"], () => {
	monaco.editor.create(document.getElementById('testcode_editor'), {
		value: `#include <stdio.h>\nvoid main(){\n\tprintf("Hello World!");\n}`,
		language: 'c',
		theme: 'vs-dark',
	});
	
}); */
require(["vs/editor/editor.main"], function() {
	var edito = monaco.editor.create(document.getElementById('testcode_editor'), {
		value: `#test.py\ndef testFunc(a,b):\n\tprint("TEST FUNC")\n\tc = a + b\n\treturn c\n`,
		language: 'python',
		theme: 'vs-dark',
	});
	
	var text2 = "";
	
	function testdata(){ 
		
/* 		var obj = document.WriteForm;
		
		var text = edito.getValue();
		alert(text);
		
		if(obj.testcode_title.value == ""){
			 alert("제목을 입력해 주세요");
			 obj.testcode_title.focus();
			 return false;
		} */
		
		//var allParams = jQuery("#WriteForm").serialize();
		
		//obj.action="/testcode_write_ok.do";
		//obj.submit();
		
		var text = edito.getValue();
		//alert(text);
		text2 = text;
		//alert(text2);
		//console.log(text2);
		text3 = text;
		document.getElementById('testcode_contents').value = text;
		
		SavePartAsFile(document.WriteForm.testcode_contents.value);
		send();
	}
	
	
	document.getElementById('sendbtn').onclick = testdata;
});


function SavePartAsFile(somePart){
	var FileSaver = require('file-saver');
	var blob = new Blob([somePart], { type: "text/plain;charset=utf-8" });
	FileSaver.saveAs(blob, "testcode.txt");
}


function send(){
	var obj = document.WriteForm;
	
	if(obj.testcode_title.value == ""){
	 alert("제목을 입력해 주세요");
	 obj.testcode_title.focus();
	 return false;
	}
	
	
	if(obj.testcode_contents.value == ""){
	 alert("내용을 작성해 주세요");
	 obj.testcode_contents.focus();
	 return false;
	}
	 
	// 일반 폼 방식
	var allParams = jQuery("#WriteForm").serialize();
	
	//mona.getById["testcode_contents"].exec("UPDATE_CONTENTS_FIELD", []);

	obj.action="/testcode_write_ok.do";
	obj.submit();
		
}

</script>