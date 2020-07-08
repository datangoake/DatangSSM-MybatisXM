<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../commons/mytaglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>查询计划</title>
<link href="${pageContext.request.contextPath }/css/css.css" rel="stylesheet" type="text/css" />
</head>

<body><!-- #BeginLibraryItem "/Library/topbanner.lbi" -->

<div id="logo"><img src="${pageContext.request.contextPath }/images/top.jpg" width="1002" height="258" /></div>
<!-- #EndLibraryItem --><table width="1002" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="rightimg"><!-- #BeginLibraryItem "/Library/left2.lbi" -->

<div id="left">
      <p><a href="../renwu.do" target="_self" >计划管理</a></p>
      <p><a href="../jiazairenwu.do" target="_self">查询计划</a></p>
      <p><a href="../renwu.do" target="_self">我的任务</a></p>
      <p ><a href="../zhuxiao.do" target="_self">退出系统</a></p>
</div><!-- #EndLibraryItem --><div id="right">
        <p>您现在的位置 &gt;&gt; 查询计划 &gt;&gt; 查询</p>
        <spf:form method="post"  action="zuhe_select.do">
        <input type="hidden" name="flag" value="1"/>
        
          <h1>查询信息:</h1>
          <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="15%" class="tdcolor">计划名称</td>
              <td width="579"><input type="text" name="plan_name" value="${plan_dto.plan_name}"/></td>
            </tr>
            <tr>
              <td class="tdcolor">所属任务</td>
              <td>
              <select name="select"><option/>请选择</option>
              		<c:forEach  items="${renuw_all}" var="g">
              			<option value="${g }">${g }</option>
              		</c:forEach>
              </select>
          	</td>
            </tr>
            
            <tr>
              <td class="tdcolor">开始时间</td>
              <td>从
                <input name="beanFore" type="text" size="15" value="${plan_dto.begin_date_befor }"/>
              到
              <input name="beanAfter" type="text" size="15" value="${plan_dto.begin_date_after }"/>              </td>
            </tr>
            <tr>
              <td class="tdcolor">结束时间</td>
              <td>从
                <input name="endFore" type="text" size="15" value="${plan_dto.end_date_befor}"/>
到
<input name="endAfter" type="text" size="15" value="${plan_dto.end_date_after}"/></td>
            </tr>
            <tr>
              <td class="tdcolor">是否反馈</td>
              <td><label>
                <input name="is_feedback" type="text" size="15" value="${plan_dto.feedback_info}"/>
              </label></td>
            </tr>
          </table>
          <p>
            <input type="submit" class="menu3" value="查询计划" />
          </p>
         </spf:form>
		  
		  		  <h1>计划信息：</h1>
		  <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="15%" class="tdcolor">计划名称</td>
              <td width="15%" class="tdcolor">所属任务</td>
              <td class="tdcolor">开始时间</td>
              <td class="tdcolor">结束时间</td>
              <td class="tdcolor">计划状态</td>
              <td class="tdcolor">是否反馈</td>
            </tr>
            <c:forEach var="plan" items="${page.data}">
            <tr>
              <td><a href="../jihua_selectById.do?jihuaId=${plan.plan_id}" target="_self">${plan.plan_name}</a></td>
              <td>${plan.task.task_name}</td>
              <td><fmt:formatDate value="${plan.begin_date}" pattern="yyyy-MM-dd"/></td>
              <td><fmt:formatDate value="${plan.end_date}" pattern="yyyy-MM-dd"/></td>
              <td>${plan.status}</td>
              <td><label>${plan.is_feedback}</label></td>
            </tr>
        	</c:forEach>
          </table>
        	<a>共${page.lastPage }页</a>
			<a href="zuhe_select2.do?PageNo=${page.firstPage}&PageSize=${page.pageSize}">首页</a>
			<a href="zuhe_select2.do?PageNo=${page.previousPage}&PageSize=${page.pageSize}">上一页</a>
			<a href="zuhe_select2.do?PageNo=${page.nextPage}&PageSize=${page.pageSize}">下一页</a>
			<a href="zuhe_select2.do?PageNo=${page.lastPage}&PageSize=${page.pageSize}">末页</a>
			<a>第${page.pageNo }页</a>
			<a>共${page.totalRecords }条记录</a>
      </div>  
      </td>
  </tr>
</table>
<!-- #BeginLibraryItem "/Library/bottom.lbi" --><div id="bottom"><img src="../images/button.jpg" width="1002" height="17" /></div><!-- #EndLibraryItem --></body>
</html>

