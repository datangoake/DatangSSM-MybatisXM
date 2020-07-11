<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="sp"  uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>分配人员</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
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
        <p>您现在的位置 &gt;&gt; 分配人员</p>
        <h1>用户详细信息:</h1>
      
		<sp:form id="form0" name="form1" method="post" action="myupdate.do" >
		
        <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
        
          <tr>
            <td class="tdcolor">用户名称</td>
            <td>${all.employee_name}&nbsp;</td>
          </tr>
          <tr>
            <td width="15%" class="tdcolor">真实姓名</td>
            <td>${all.real_name}&nbsp;</td>
          </tr>
          <tr>
            <td class="tdcolor">行业角色</td>
            <td>${all.role.role_NAME}&nbsp;</td>
          </tr>
          <tr>
            <td class="tdcolor">性&nbsp;&nbsp;&nbsp;别</td>
            <td>${all.sex}</td>
          </tr>
          <tr>
            <td class="tdcolor">入职时间</td>
            <td>${all.enrolldate}&nbsp;</td>
          </tr>
          <tr>
            <td class="tdcolor">职位信息</td>
            <td>${all.duty}&nbsp;</td>
          </tr>
          <tr>
            <td class="tdcolor">出生年月</td>
            <td>${all.birthday}&nbsp;</td>
          </tr>
          <tr>
            <td class="tdcolor">学历信息</td>
            <td>${all.education}&nbsp;</td>
          </tr>
          <tr>
            <td class="tdcolor">专业信息</td>
            <td>${all.major}&nbsp;</td>
          </tr>
          <tr>
            <td class="tdcolor">行业经验</td>
            <td>${all.experience}&nbsp;</td>
          </tr>
          <tr>
         <td class="tdcolor">上级主管</td>
								<td><label> <select name="managerid">
									<c:forEach items="${empall }" var ="m">
									<option value="${m.employee_ID }">${m.real_name }</option>
									</c:forEach>
									
								</select></label>
								</td>
          </tr>
        </table>
        <p>&nbsp;</p>

          <p>
            <input name="Submit" type="reset" class="menu2" value="取消" />
            <label>
            <input name="Submit2" type="submit" class="menu1" value="提交" />
            </label>
          </p>
     </sp:form>
    </div>    </td></tr>
</table>
<!-- #BeginLibraryItem "/Library/bottom.lbi" --><div id="bottom"><img src="../images/button.jpg" width="1002" height="17" /></div><!-- #EndLibraryItem --></body>
</html>
