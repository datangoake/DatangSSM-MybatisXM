<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../commons/mytaglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>我的任务</title>
<link href="${pageContext.request.contextPath }/css/css.css" rel="stylesheet" type="text/css" />

</head>

<body>

<div id="logo"><img src="${pageContext.request.contextPath }/images/top.jpg" width="1002" height="258" /></div>
<!-- #EndLibraryItem --><table width="1002" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="rightimg">

<div id="left">
      <p><a href="../renwu.do" target="_self" >计划管理</a></p>
      <p><a href="../jiazairenwu.do" target="_self">查询计划</a></p>
      <p><a href="../renwu.do" target="_self">我的任务</a></p>
      <p ><a href="../zhuxiao.do" target="_self">退出系统</a></p>
</div><!-- #EndLibraryItem --><div id="right">
        <p>您现在的位置 &gt;&gt; 我的任务 &gt;&gt; 任务信息</p>
        <h1>任务信息:</h1>
        <form id="form1" name="form1" method="post" action="../renwuById.do">
          <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="15%" class="tdcolor">任务名称</td>
              <td width="15%" class="tdcolor">制定人</td>
              <td width="15%" class="tdcolor">开始时间</td>
              <td width="15%" class="tdcolor">结束时间</td>
              <td width="15%" class="tdcolor">任务状态</td>
              <td width="15%" class="tdcolor">计划数目</td>
              <td width="10%" class="tdcolor">&nbsp;</td>
            </tr>
            <c:forEach var="rw" items="${renwuAll.data}">
            <tr>
              <td>${rw.task_name}</td>
              <td>${rw.as_emp.employee_name}</td>
              <td><fmt:formatDate value="${rw.begin_date}" pattern="yyyy-MM-dd"/></td>
              <td><fmt:formatDate value="${rw.end_date}" pattern="yyyy-MM-dd"/></td>
              <td>${rw.status}</td>
              <td>${rw.task_desc}</td>
              <td><input type="radio" name="jihua_id" value="${rw.task_id}" checked="checked" /></td>
            </tr>
            </c:forEach>
          </table>
          <a style="margin-left: 200px">共${renwuAll.lastPage }页</a>
			<a href="../renwu.do?PageNo=${renwuAll.firstPage}&PageSize=${renwuAll.pageSize}">首页</a>
			<a href="../renwu.do?PageNo=${renwuAll.previousPage}&PageSize=${renwuAll.pageSize}">上一页</a>
			<a href="../renwu.do?PageNo=${renwuAll.nextPage}&PageSize=${renwuAll.pageSize}">下一页</a>
			<a href="../renwu.do?PageNo=${renwuAll.lastPage}&PageSize=${renwuAll.pageSize}">末页</a>
			<a>第${renwuAll.pageNo }页</a>
			<a>共${renwuAll.totalRecords }条记录</a>
    <p>
              <label>
              <input name="Submit" type="submit" class="menu3" value="计划管理" />
            </label>
          </p>
      </form>
      </div>
    </td></tr>
</table>
<!-- #BeginLibraryItem "/Library/bottom.lbi" --><div id="bottom"><img src="../images/button.jpg" width="1002" height="17" /></div><!-- #EndLibraryItem --></body>
</html>

