<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Car List</title>
</head>
    <body>
        <h2>
            List Products <br/>
        </h2>
        <table>
        <tr><th>Name</th><th>Group</th></tr>
        <c:forEach var="i" items="${products}">
            <tr><td>${i.code}<c:out value="${i.name}"/></td><td>${i.price}</td>
        </c:forEach>
        </table>
        <br>
        <br>
        <%=request.getAttribute("products")%>
        <br>
        <form action="${pageContext.request.contextPath}/api/manager/addProduct'/>" method="post">
             Code <input type="number" name="code"/><br>
             Name <input type="text" name="name"/><br>
             Sold by Weight <input type="checkbox" name="soldByWeight"/><br>
             Number in stock <input type="number" name="number"/><br>
             Total weight in stock <input type="number" name="weight"/><br>
             <input type="submit"/>
        </form>

        <br/>
        <a href="${pageContext.request.contextPath}/index.jsp">index</a>
    </body>
</html>
