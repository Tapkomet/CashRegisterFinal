<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Check List</title>
</head>
    <body>
        <h2>
            List Checks <br/>
        </h2>
        <c:if test="${not empty sql_error_message}">
            <p class="error">${sql_error_message}</p>
        </c:if>
        <table>
        <tr><th>Id</th><th>Total price</th><th>Time</th></tr>
        <c:forEach var="i" items="${checks}">
            <tr><td><a href="check?id=<c:out value='${i.id}' />"> <c:out value="${i.id}"/></a></td>
            <td>${i.totalPrice}</td><td>${i.createTime}</td>
        </c:forEach>
        </table>
        <br>
        <br>
        <a href="${pageContext.request.contextPath}/api/cashier/checks/addPage">Create new check</a>
        <br/>
        <a href="${pageContext.request.contextPath}/index.jsp">index</a>
    </body>
</html>
