<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../commons/mytaglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>制定任务</title>
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
        <p>您现在的位置 &gt;&gt; 制定任务 &gt;&gt; 新建任务</p>
        <h1>输入新任务信息</h1>
        
        <form id="form1" name="form1" method="post" action="lgtaskinsert.do">
          <p>任务名称：<label>
          <input name="task_name" type="text" size="50" />
          </label></p>
          <p>任务描述：
            <label>
            <textarea name="task_desc"></textarea>
            </label>
          </p>
            <p>开始时间：
              <label>
                <input name="begin_date" type="text" size="16" />
              </label>
             <span class="marginleft1">结束时间：
              <label>
                <input name="end_date" type="text" size="16" />
              </label></span>
          </p>
          <p>实施人员：
            <label>
              <select name="select">              
                <c:forEach items="${selectTask}" var="g">
                 <option value="${g. employee_ID}">${g. employee_name}</option>
                </c:forEach>
              </select>
            </label>
            <span class="marginleft">任务状态：
             未实施
            </span></p>
            <p>
              <label>
              <input name="Submit" type="reset" class="menu2" value="重置" />
              </label>
           &nbsp; 
              <label>
              <input name="Submit2" type="submit" class="menu1" value="提交" />
              </label>
           </p>
      </form>
      </div>
    </td></tr>
</table>
<!-- #BeginLibraryItem "/Library/bottom.lbi" --><div id="bottom"><img src="../images/button.jpg" width="1002" height="17" /></div><!-- #EndLibraryItem --></body>
</html>
