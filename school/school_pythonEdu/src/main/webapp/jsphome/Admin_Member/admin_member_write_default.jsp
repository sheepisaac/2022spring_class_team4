<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="kr.co.pe.dao.*"%>
<%@page import="kr.co.pe.common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>회원가입</title>

    <!-- Custom fonts for this template-->
    <link href="/jsphome/scripts/sb_admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/jsphome/scripts/sb_admin/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                            </div>
                            <form class="user" name="WriteForm" method="post">
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" name="member_name" id="member_name"
                                            placeholder="Name">
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="number" class="form-control form-control-user" name="member_phone" id="member_phone"
                                            placeholder="Phone Number">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="email" class="form-control form-control-user" name="member_id" id="member_id"
                                        placeholder="Email Address">
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="password" class="form-control form-control-user"
                                            name="member_pwd" id="member_pwd" placeholder="Password">
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="password" class="form-control form-control-user"
                                            name="member_pwd2" id="member_pwd2" placeholder="Repeat Password">
                                    </div>
                                </div>
                                <a href="javascript:send_write();" class="btn btn-primary btn-user btn-block">
                                    Register Account
                                </a>
                                <!-- <hr>
                                <a href="/index.do" class="btn btn-google btn-user btn-block">
                                    <i class="fab fa-google fa-fw"></i> Register with Google
                                </a>
                                <a href="/index.do" class="btn btn-facebook btn-user btn-block">
                                    <i class="fab fa-facebook-f fa-fw"></i> Register with Facebook
                                </a> -->
                            </form>
                            <hr>
                            <!-- <div class="text-center">
                                <a class="small" href="#">Forgot Password?</a>
                            </div> -->
                            <div class="text-center">
                                <a class="small" href="/login_default.do">Already have an account? Login!</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="/jsphome/scripts/sb_admin/vendor/jquery/jquery.min.js"></script>
    <script src="/jsphome/scripts/sb_admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/jsphome/scripts/sb_admin/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/jsphome/scripts/sb_admin/js/sb-admin-2.min.js"></script>

</body>

</html>

<script language="javascript">
/* 	// 페이지가 처음 실행될 때 무조건 실행됨.
	$(document).ready(function(){
	});
 */
 
 // 회원 가입하기
 function send_write(){
	 var obj = document.WriteForm;
	 
	 if(obj.member_name.value==""){
		 alert("회원 이름을 입력해주세요.");
		 obj.member_name.focus();
		 return false;
	 }
	 if(obj.member_phone.value==""){
		 alert("회원 전화번호를 입력해주세요.");
		 obj.member_phone.focus();
		 return false;
	 }
	 if(obj.member_id.value==""){
		 alert("회원 이메일을 입력해주세요.");
		 obj.member_id.focus();
		 return false;
	 }
	 if(obj.member_pwd.value==""){
		 alert("회원 비밀번호를 입력해주세요.");
		 obj.member_pwd.focus();
		 return false;
	 }
	 if(obj.member_pwd2.value==""){
		 alert("회원 비밀번호 확인을 입력해주세요.");
		 obj.member_pwd2.focus();
		 return false;
	 }
	 if(obj.member_pwd.value != obj.member_pwd2.value){
		 alert("비밀번화와 비밀번호 확인이 같지 않습니다.");
		 obj.member_pwd2.focus();
		 return false;
	 }
	 
	 
	 obj.action="/member_write_ok.do";
	 obj.submit();
 }
 
</script>