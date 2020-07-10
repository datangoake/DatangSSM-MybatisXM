<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>用户管理</title> 
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.12.0.js" ></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="jquery-1.8.2.js"></script>
<script type="text/javascript">
    
</script>

</head>

<body><!-- #BeginLibraryItem "/Library/topbanner.lbi" -->
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<div id="logo"><img src="../images/top.jpg" width="1002" height="258" /></div>
<!-- #EndLibraryItem --><table width="1002" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="rightimg"><!-- #BeginLibraryItem "/Library/left3.lbi" -->
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<div id="left2">
      <p><a href="queryall.do" target="_self" >用户管理</a></p>
      <p><a href="All.do" target="_self">员工管理</a></p>
      <p><a href="update.do" target="_self">分配人员</a></p>
      <p ><a href="../login.jsp" target="_self">退出系统</a></p>
</div><!-- #EndLibraryItem --><div id="right">
        <p>您现在的位置 &gt;&gt; 员工管理 &gt;&gt; 员工列表</p>
       <h1>用户详细信息:</h1>
	  
	    <form id="form1" name="form1" method="post" action="delete.do">
          <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="15%" class="tdcolor">用户名称</td>
              <td class="tdcolor">用户密码</td>
              <td class="tdcolor">用户角色</td>
              <td class="tdcolor">真实姓名</td>
              <td class="tdcolor">入职时间</td>
              <td class="tdcolor">职位信息</td>
              <td class="tdcolor">&nbsp;</td>
            </tr>
            <c:forEach items="${empadmin.data}" var="all" >
            <tr>
            <td>${all.employee_name}</td>
            <td>${all.password}</td>
            <td>${all.role.role_DESC}</td>
            <td>${all.real_name}</td>
            <td><fmt:formatDate value="${all.enrolldate}" pattern="yyyy-MM-dd" /></td>
            <td>${all.duty}</td>
            <td><label> <input type="radio" name="ID" value="${all.employee_ID}"  id="dio"/> </label></td>
            </tr>
            
            </c:forEach>
                 </table>
	      <p id="myP">
            <label>
            <input name="Submit" type="submit" class="menu3" value="删除人员"  id="btn"/>
            </label>
            <label></label>
	      </p>
        
                         <center>
					<a>共${empadmin.lastPage }页</a>
						<a
							href="${pageContext.request.contextPath }/admin/All.do?pageNo1=${empadmin.firstPage}&pageSize1=${empadmin.pageSize}">首页</a>
						<a
							href="${pageContext.request.contextPath }/admin/All.do?pageNo1=${empadmin.previousPage}&pageSize1=${empadmin.pageSize}">上一页</a>
						<a
							href="${pageContext.request.contextPath }/admin/All.do?pageNo1=${empadmin.nextPage}&pageSize1=${empadmin.pageSize}">下一页</a>
						<a 
						    href="${pageContext.request.contextPath }/admin/All.do?pageNo1=${empadmin.lastPage}&pageSize1=${empadmin.pageSize}">末页</a>
						<a>第${empadmin.pageNo }页</a>
						<a>共${empadmin.totalRecords }条记录</a>
						<a>共${empadmin.lastPage }页</a>
						</center>
						</form>
	    <p>&nbsp;</p>
</div></td></tr>

</table>
<!-- #BeginLibraryItem "/Library/bottom.lbi" --><div id="bottom"><img src="../images/button.jpg" width="1002" height="17" /></div><!-- #EndLibraryItem --></body>
</html>
