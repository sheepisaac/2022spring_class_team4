<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	int member_idx = (Integer)session.getAttribute("member_idx");
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
<link href="//cdnjs.cloudflare.com/ajax/libs/monaco-editor/0.20.0/min/vs/editor/editor.main.min.css" rel="stylesheet">

<!-- Main Content -->
<div id="content">

	<!-- Begin Page Content -->
	<div class="container-fluid">
	
		<!-- Page Heading -->
		<h1 class="h3 mb-2 text-gray-800">모나코 에디터 test</h1>
		
		<!-- DataTales Example -->
		<div class="card shadow mb-4">
		    <div class="card-header py-3">
		        <h6 class="m-0 font-weight-bold text-primary">모나코 에디터</h6>
		    </div>
		    
		    <div class="card-body">
		        <form name="WriteForm" id="WriteForm" method="post">
	                <input type="text" class="form-control form-control-sm" name="testcode_title" id="testcode_title" style="width:300px; max-width:300px;">
				
					<div name="testcode_editor" id="testcode_editor" style="height:300px;">
						<textarea name="testcode_contents" id="testcode_contents" style="width:100%;height:100%;display:none;"></textarea>
					</div>
					
					<div>
						<button type="button" class="btn btn-sm btn-outline-info" OnClick="test();">보내기</button>
					</div>
	            </form>
            
            	<p>This is test version.</p>
				<div>
					<textarea name="testcode_result" id="testcode_result" style="width:100%;height:100px;"></textarea>
				</div>
		    	
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
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/2.0.0/FileSaver.min.js"></script>


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
		alert("button click");	
		var text = edito.getValue();
		//alert(text);
		text2 = text;
		//alert(text2);
		//console.log(text2);
		text3 = text;
		document.getElementById('testcode_contents').value = text;
		
		//SavePartAsFile(document.WriteForm.testcode_contents.value);
		send();
	}
	
	
	//document.getElementById('sendbtn').onclick = testdata;
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

function test(){
	alert("document.WriteForm.testcode_contents="+document.WriteForm.testcode_contents.value);
}

</script>