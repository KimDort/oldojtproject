<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
	$(document).ready(function() {
		var result="${request.getAttribute('login')}";
		$("#signin").css("margin-top", (($(window).height() / 2) - $("#signin").height()));
		$("#signin").css("margin-left", ($(window).width() / 2) - $("#signin").width());
			
		$(window).resize(function() {
			$("#signin").css("margin-top",$(window).height() / 2);
			$("#signin").css("margin-left", ($(window).width() / 2) - $("#signin").width());
		});
	});
</script>
<style type="text/css">
body{
	background: #E4E4E4;		
}
#signin {
	border: 1px solid #E4E4E4;
	height: 150px;
	padding-top: 35px;
	border-radius: 10px;
  	-moz-border-radius: 10px;
  	-webkit-border-radius: 10px;
  	background-color: #F4F4F4;
}
.btn {
	height: 70px;
}
</style>
<script>
	function login(){
		$("#frm").submit();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section class="body-sec">
		<div class="container">
			<div class="row">
				<div class="col-md-4" id="signin">
				<form action="${pageContext.request.contextPath }/login.do" method="post" id="frm">
					<div style="float: right;" class="col-sm-3">
						<input type="button" value="Sign In" class="btn btn-default" onclick="login()">
				</div>
				<div class="col-sm-7" style="width: 75%">
					<label style="float: left; width: 20%;">ID:</label>
					<div class="col-sm-7" style="width: 80%; ">
						<input type="text" class="form-control" style="float: right;" name='id'>
					</div>
					<label style="float: left; width: 20%; padding-top:15px;">PASS:</label>
					<div class="col-sm-7" style="width: 80%;padding-top:5px;">
						<input type="password" class="form-control" style="float: right;" name="password">
					</div>
				</div>
				</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>