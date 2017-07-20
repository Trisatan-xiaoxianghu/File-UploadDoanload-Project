<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
	<body>
		<table border="1" align="center">
			<tr>
			<th>number</th>
			<th>filename</th>
			<th>action</th>
			</tr>
			<c:forEach  var="en" items="${requestScope.fileNames}" varStatus="vs" >
				<tr>
					<td>${vs.count}</td>
					<td>${en.value}</td>
					<td>
						<c:url var="url" value="fileServlet">
						<c:param name="method" value="down"></c:param>
						<c:param name="fileName" value="${en.key}"></c:param>
						</c:url>
					<a href="${url }">download</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>