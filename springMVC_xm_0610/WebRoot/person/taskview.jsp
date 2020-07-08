<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../commons/mytaglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>计划信息</title>
<link href="${pageContext.request.contextPath }/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.2.js"></script>
	<script type="text/javascript">
		$(function(){
			$("select").change(function(){
				$(this).attr("selected","true");
				window.location=$(this).val();
			});
		});
	</script>
</head>

<body><!-- #BeginLibraryItem "/Library/topbanner.lbi" -->
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
        <p>您现在的位置 &gt;&gt; 计划管理 &gt;&gt; 计划信息</p>
        <form id="form1" name="form1" method="post" action="../jihua_delete.do?task_id=${taskById.task_id}">
		<h1>任务详细信息:</h1>
          
          <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="15%" class="tdcolor">任务名称</td>
              <td width="579" colspan="3">${taskById.task_name}</td>
            </tr>
            <tr>
              <td class="tdcolor">任务描述</td>
              <td colspan="3">${taskById.task_desc}</td>
            </tr>
            <tr>
              <td class="tdcolor">开始时间</td>
              <td width="579"><fmt:formatDate value="${taskById.begin_date}" pattern="yyyy-MM-dd"/></td>
              <td width="579" class="tdcolor">结束时间</td>
              <td width="579"><p><fmt:formatDate value="${taskById.end_date}" pattern="yyyy-MM-dd"/></p></td>
            </tr>
            
            <tr>
              <td class="tdcolor">实施人</td>
              <td>${taskById.mp_emp.real_name}</td>
              <td class="tdcolor">任务状态</td>
              <td>
                  <select name="select">
                    <option value="${taskById.status}">${taskById.status}</option>
                    <option value="../StatsSelected.do?statusValue=1&jihua_id=${taskById.task_id}">未实施</option>
                    <option value="../StatsSelected.do?statusValue=2&jihua_id=${taskById.task_id}">实施中</option>
                    <option value="../StatsSelected.do?statusValue=3&jihua_id=${taskById.task_id}">已完成</option>
                  </select></td>
            </tr>
        </table>

			<a href="../xinjian.do">新建</a>
		  <h1>计划信息：</h1>
		  <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="15%" class="tdcolor">计划名称</td>
              <td width="15%" class="tdcolor">完成状态</td>
              <td class="tdcolor">是否反馈</td>
              <td class="tdcolor">结束时间</td>
              <td class="tdcolor">结束时间</td>
              <td class="tdcolor">&nbsp;</td>
            </tr>
            <c:forEach var="jh" items="${planAll}">
            <tr>
              <td><a href="../jihua_selectById.do?jihuaId=${jh.plan_id}" target="_self">${jh.plan_name}</a></td>
              <td>${jh.status}</td>
              <td>${jh.is_feedback}</td>
              <td><fmt:formatDate value="${jh.begin_date}" pattern="yyyy-MM-dd"/></td>
              <td><fmt:formatDate value="${jh.end_date}" pattern="yyyy-MM-dd"/></td>
              <td><input type="checkbox" name="jihua_id" value="${jh.plan_id}"/></td>
            </tr>
            </c:forEach>
          </table>
		  <p>

		 		<input name="Submit2" type="submit" class="menu2" value="删除" />
		 
		    
          </p>
           </form>
              
    </div>    </td></tr>
</table>
<!-- #BeginLibraryItem "/Library/bottom.lbi" --><div id="bottom"><img src="../images/button.jpg" width="1002" height="17" /></div><!-- #EndLibraryItem --></body>
</html>
