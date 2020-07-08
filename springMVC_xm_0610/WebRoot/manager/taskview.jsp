<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../commons/mytaglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>查看任务</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
</head>

<body><!-- #BeginLibraryItem "/Library/topbanner.lbi" -->
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<div id="logo"><img src="../images/top.jpg" width="1002" height="258" /></div>
<!-- #EndLibraryItem --><table width="1002" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="rightimg"><!-- #BeginLibraryItem "/Library/left.lbi" --><div id="left">
      <p><a href="lgtask.do" target="_self">查看任务</a></p>
      <p><a href="lgtaskput.do" target="_self">制定任务</a></p>
      <p><a href="lgtaskundone.do" target="_self">调整任务</a></p>
      <p><a href="lgtaskdance.do" target="_self">跟踪任务</a></p>
      <p><a href="lgemp.do" target="_self">查看人员</a></p>
      <p><a href="../zhuxiao.do" target="_self">退出系统</a></p>
</div><!-- #EndLibraryItem --><div id="right">
        <p>您现在的位置 &gt;&gt; 查看任务 &gt;&gt; 任务信息</p>
        <h1>任务信息:</h1>
        <form action="lgtaskinfo.do" method="post" name="form1" target="_self" id="form1">
          <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="15%" class="tdcolor">任务名称</td>
              <td width="15%" class="tdcolor">实施人</td>
              <td width="15%" class="tdcolor">开始时间</td>
              <td width="15%" class="tdcolor">结束时间</td>
              <td width="15%" class="tdcolor">任务状态</td>
              <td width="15%" class="tdcolor">计划数目</td>
              <td width="10%" class="tdcolor">&nbsp;</td>
            </tr>
			<c:forEach items="${allTask.data}" var="g">
			<tr>
				<td>${g.task_name}</td>
              <td>${g.mp_emp.employee_name}</td>
              <td><fmt:formatDate value="${g.begin_date }" pattern="yyyy-MM-dd"/></td>
              <td><fmt:formatDate value="${g.end_date }" pattern="yyyy-MM-dd"/></td>
              <td>${g.status }</td>
              <td>${g.task_desc}</td>             	
              <td>
              <input type="hidden" name="taskcount" value="${g.task_desc}"/>
              <input type="radio" name="radiobutton" value="${g.task_id }-${g.task_desc}" checked="checked" />
              </td>
              </tr>
			</c:forEach>
          </table>
          <a style="margin-left: 200px">共${allTask.lastPage }页</a>
			<a href="lgtask.do?PageNo=${allTask.firstPage}&PageSize=${allTask.pageSize}">首页</a>
			<a href="lgtask.do?PageNo=${allTask.previousPage}&PageSize=${allTask.pageSize}">上一页</a>
			<a href="lgtask.do?PageNo=${allTask.nextPage}&PageSize=${allTask.pageSize}">下一页</a>
			<a href="lgtask.do?PageNo=${allTask.lastPage}&PageSize=${allTask.pageSize}">末页</a>
			<a>第${allTask.pageNo }页</a>
			<a>共${allTask.totalRecords }条记录</a>
    <p>
              <label>
              <input name="Submit" type="submit" class="menu3" value="详细信息" />
            </label>
          </p>
      </form>
      </div>
    </td></tr>
</table>
<!-- #BeginLibraryItem "/Library/bottom.lbi" --><div id="bottom"><img src="../images/button.jpg" width="1002" height="17" /></div><!-- #EndLibraryItem --></body>
</html>
