<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib prefix="sp"  uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>注册</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <sp:form action="register" modelAttribute="users" method="post">
        	用户名：<sp:input path="userName" /><br/>
        	密码：<sp:input path="userPassword" /><br/>
        	出生日期：<sp:input path="userBirth" /><br/>
        	地址：<sp:input path="userAddress" /><br/>
        	电话：<sp:input path="userPhone" /><br/>
        	当前状态：<sp:input path="userStatus" /><br/>
         <input type="submit" value="注册">
    </sp:form>
  </body>
</html>
