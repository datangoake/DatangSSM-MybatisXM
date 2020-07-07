<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../commons/mytaglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>新建计划</title>
<link href="${pageContext.request.contextPath }/css/css.css" rel="stylesheet" type="text/css" />
</head>

<body><!-- #BeginLibraryItem "/Library/topbanner.lbi" -->

<div id="logo"><img src="${pageContext.request.contextPath }/images/top.jpg" width="1002" height="258" /></div>
<!-- #EndLibraryItem --><table width="1002" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="rightimg"><!-- #BeginLibraryItem "/Library/left2.lbi" -->

<div id="left">
      <p><a href="renwu.do" target="_self" >计划管理</a></p>
      <p><a href="jiazairenwu.do" target="_self">查询计划</a></p>
      <p><a href="renwu.do" target="_self">我的任务</a></p>
      <p ><a href="zhuxiao.do" target="_self">退出系统</a></p>
</div><!-- #EndLibraryItem --><div id="right">
        <p>您现在的位置 &gt;&gt; 计划管理 &gt;&gt; 新建计划${taskById.task_id}</p>
                <h1>输入新计划信息</h1>
                <spf:form action="Insertjihua.do?task_id=${taskById.task_id}" modelAttribute="plan" method="post">
          <p>计划名称：
            <label>
            <spf:input path="plan_name"/>
          </label></p>
          <p>计划描述：
            <label>
            <spf:input path="plan_desc"/>
            </label>
          </p>
            <p>开始时间：
              <label>
              	<spf:input path="begin_date"/>
              </label>
             <span class="marginleft1">结束时间：
              <label>
              	<spf:input path="end_date"/>
              </label></span>
          </p>
          <p>计划状态：未完成</p>
          <p>是否反馈：未反馈 </p>
          <p>
              <label>
              <input name="Submit" type="reset" class="menu2" value="重置" />
              </label>
           &nbsp; 
              <label>
              <input name="Submit2" type="submit" class="menu1" value="提交" />
              </label>
           </p>
      </spf:form>
      </div>
    </td></tr>
</table>
<!-- #BeginLibraryItem "/Library/bottom.lbi" --><div id="bottom"><img src="../images/button.jpg" width="1002" height="17" /></div><!-- #EndLibraryItem --></body>
</html>
