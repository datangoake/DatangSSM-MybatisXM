<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录页面</title>
<link href="${pageContext.request.contextPath }/css/css.css" rel="stylesheet" type="text/css" />
</head>

<body>

<div id="logo">
<img src="${pageContext.request.contextPath }/images/logo.jpg" alt="大唐电信首页" width="1002" height="392" /></div>
<div class="login" id="middle">
	<form action="DaTangLogin.do" method="post" name="form001"  id="form001">
    <p>
      <label>
      用户名：<input type="text" name="employee_name"/>
    </label></p>
    <p>  密 码：     
      <label>
      <input type="password" name="password"/>
    </label></p>
    <p>角 色：
      <label>
      <select name="role_id">
      	<option>请选择</option>
      	<option value="2">系统管理员</option>
      	<option value="3">主管</option>
      	<option value="4">员工</option>
      </select>
      </label>
    </p>
    <p>
      <label class="left007-bg">
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
      <input name="Submit" type="submit" class="menu1" value="提交" />
      </label>
    </p>
  </form>
</div>
<div id="nutton"><img src="${pageContext.request.contextPath }/images/button.jpg" width="1002" height="17" /></div>
</body>
</html>
