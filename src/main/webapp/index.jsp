<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cash Register</title>
</head>
    <body>
        <h2>
            Landing page <br/>
        </h2>

        <br/>
        <a href="${pageContext.request.contextPath}/login.jsp">Login</a>
        <br>
        <a href="${pageContext.request.contextPath}/api/logout">Logout</a>
        <br>
        <a href="${pageContext.request.contextPath}/api/exception">Exception</a>
        <br>
        <a href="${pageContext.request.contextPath}/registration.jsp">Registration</a>
        <br>
        <a href="${pageContext.request.contextPath}/api/manager">Manager View</a>
        <br>
        <a href="${pageContext.request.contextPath}/api/admin">Admin View</a>
        <br>
        <a href="${pageContext.request.contextPath}/api/cashier">Cashier View</a>
        <br>


        <c:if test="${not empty index_message}">
            <p>${index_message}</p>
        </c:if>
    </body>
</html>
