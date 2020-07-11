<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../commons/mytaglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>任务详细信息</title>
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
        <p>您现在的位置 &gt;&gt; 查看任务 &gt;&gt; 任务详细信息</p>
        <h1>任务详细信息:</h1>

          <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="15%" class="tdcolor">任务名称</td>
              <td width="579" colspan="5">${lgtaskinfo.task_name}</td>
            </tr>
            <tr>
              <td class="tdcolor">任务描述</td>
              <td colspan="5">${lgtaskinfo.task_desc}</td>
            </tr>
            <tr>
              <td class="tdcolor">开始时间</td>
              <td width="15%"><fmt:formatDate value="${lgtaskinfo.begin_date }" pattern="yyyy-MM-dd"/></td>
              <td width="20%" class="tdcolor">结束时间</td>
              <td width="50%" colspan="3"><p><fmt:formatDate value="${lgtaskinfo.end_date }" pattern="yyyy-MM-dd"/></p>              </td>
            </tr>
            <tr>
              <td class="tdcolor">实际开始时间</td>
              <td><fmt:formatDate value="${lgtaskinfo.real_begin_date }" pattern="yyyy-MM-dd"/></td>
              <td class="tdcolor">实际结束时间</td>
              <td colspan="3"><fmt:formatDate value="${lgtaskinfo.real_end_date }" pattern="yyyy-MM-dd"/></td>
            </tr>
            <tr>
              <td class="tdcolor">实施人</td>
              <td>${lgtaskinfo.mp_emp.employee_name}</td>
              <td class="tdcolor">任务状态</td>
              <td>${lgtaskinfo.status}</td>
              <td class="tdcolor">计划数目</td>
              <td><%=request.getParameter("count")%></td>
            </tr>
        </table>
	    <form id="form1" name="form1" method="post" action="lgplaninfo.do">
		  <h1>实施计划：</h1>
		  <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="15%" class="tdcolor">计划名称</td>
              <td width="15%" class="tdcolor">完成状态</td>
              <td width="20%" class="tdcolor">是否反馈</td>
              <td class="tdcolor">开始时间</td>
              <td class="tdcolor">结束时间</td>
              <td class="tdcolor">&nbsp;</td>
            </tr>
            <c:forEach items="${lgtask_plan}" var="g">
            	 <tr>
              <td>${g.plan_name }</td>
              <td>${g.status }</td>
              <td>${g.is_feedback }</td>
              <td><fmt:formatDate value="${g.begin_date }" pattern="yyyy-MM-dd"/></td>
              <td><fmt:formatDate value="${g.end_date }" pattern="yyyy-MM-dd"/></td>
              <td><label>
                <input type="radio" checked="checked" name="radiobutton" value="${g.plan_id }-<%=request.getParameter("count")%>" />
              </label></td>
            </tr>
            </c:forEach>
          </table>
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