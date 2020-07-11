<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>

<body>
	<h1>财务明细</h1>
	组合查询
	<div>
		<form action="selectpage.do" method="post" name="form">
			 编号： <input type="text" name="search_tallyid" 
			 value="${dto.search_tallyid eq 0?'': dto.search_tallyid}" /><br/>
			 消费类型：<input type="text" name="search_type" value="${dto.search_type }" />
			 消费金额： <input type="text" name="search_price1"
				value="${dto.search_price1 eq 0 ?'': dto.search_price1}" /> 至 <input
				type="text" name="search_price2"
				value="${dto.search_price2 eq 0 ? '':dto.search_price2}" /> <br/>
				消费时间： <input type="text" name="search_payDate1"
				value='<fmt:formatDate value="${dto.search_payDate1}" pattern="yyyy-MM-dd" />' />
			            至 
			   <input type="text" name="search_payDate2" value='<fmt:formatDate 
			   value="${dto.search_payDate2}" pattern="yyyy-MM-dd" />' /><br/>
			<input type="submit" value="查询" />
		</form>
	</div>
	<table border="1">
		<tr>
			<td>编号</td>
			<td>消费类型</td>
			<td>消费金额</td>
			<td>消费时间</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${pb.data}" var="tally">
			<td>${tally.tallyid }</td>
			<td>${tally.type }</td>
			<td>${tally.price }</td>
			<td><fmt:formatDate value="${tally.payDate}"
					pattern="yyyy-MM-dd hh:mm:ss" /></td>1\5
			<td><a
				href="${pageContext.request.contextPath }page.do?tallyid=${tally.tallyid}">修改</a>
			</td>
			<tr />
		</c:forEach>
	</table>
	<a href="selectpage.do?pageNo=${pb.firstPage}&pageSize=${pb.pageSize}">首页</a>
	<a href="selectpage.do?pageNo=${pb.previousPage}&pageSize=${pb.pageSize}">上一页</a>
	<a href="selectpage.do?pageNo=${pb.nextPage}&pageSize=${pb.pageSize}">下一页</a>
	<a href="selectpage.do?pageNo=${pb.lastPage}&pageSize=${pb.pageSize}">末页</a>
	<a>共${pb.totalRecords }条记录</a>
	<a>共${pb.lastPage }页</a>
	<a>第${pb.pageNo }页</a>
	<a>每页显示${pb.pageSize }条记录</a>
</body>
</html>
