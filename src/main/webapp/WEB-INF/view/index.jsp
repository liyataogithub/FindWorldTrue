<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>发现</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css"
	type="text/css">

<link rel="stylesheet" href="/FindWorld/css/style.css" type="text/css">
<script type="text/javascript" src="/FindWorld/js/jquery.min.js"></script>

</head>

<body>
	<div class="container">
		<div class="login">
			<h1 class="login-heading">
				<strong>欢迎 .</strong> 请 登录 .
			</h1>
			<form method="post">
				<input type="text" name="loginname" id="loginname" placeholder="Username"
					required="required" class="input-txt" /> 
				<input type="password" name="loginpwd" id="loginpwd"
				 placeholder="Password" required="required" class="input-txt" />
				<div class="login-footer">
					<a href="#" class="lnk"> <span class="icon icon--min">ಠ╭╮ಠ</span>
						忘记密码？
					</a>
					<button id="registorBtn" type="submit" class="btn btn--right">登录</button>
				</div>
			</form>
		</div>
	</div>

	<script type="text/javascript" src="/FindWorld/js/index.js" ></script>

</body>
<script type="text/javascript">
$(function() {
	//登录点击事件
	$("#registorBtn").click(function() {
		var registerName = $('#loginname').val();
		var registerpwd = $('#loginpwd').val();
		//查询登录的用户是否存在
		$.ajax({  
		      url:'registerUser.do',// 查询注册的用户是否已经存在
		      data:{  
		    	  registerName : registerName, 
		      },  
		     type:'post',  
		     cache:false,  
		     dataType:'text',  
		     success:function(data) {  
		         if(data =="true" ){  
		        	 $.ajax({
		            	  url:'LoginUser.do',
					      data:{  
					    	  registerName : registerName,  
					    	  registerpwd : registerpwd,
					      },  
					      type : 'post',
						  cache : false,
						  async: false,
						  dataType : 'json',
					      success:function(d) { 
					    	 if(d!=null){ 
					    		 //$("#log").html("登录成功");
					    		 alert("登录成功");
					    		//判断是否支持console
					    		 if (window["console"]) {
					    		 console.log("用户的id是："+d.id+"/t"+d.username);
					    		 }
					    		//存储id，名字
					    		sessionStorage.setItem("userId",d.id);
					    		sessionStorage.setItem("userName",d.username);
					    	    window.location.href = "toHome.do";
					    	     
					    	 }else{
					    		 //$("#log").html("密码错误");
					    		 alert("密码错误");
					    	 }
					     },
					     error:function() { 
					    	 if (window["console"]) {
					    	 console.log('登录异常');  
					    	 }
					     }
		              });
		         }else{  
		        	 //$("#log").html('没有该用户，请注册');  
		        	 alert('没有该用户，请注册');
		         }  
		      },  
		      error : function() {  
		    	  if (window["console"]) {
		           console.log("查询登录异常！");
		    	  }
		      }  
		 });
	});
	
	$("body").keydown(function(e){
		event =  e || event ;
		if(event.keyCode == 13){
			$("#registorBtn").click();
		}
	});
});
</script>

</html>
