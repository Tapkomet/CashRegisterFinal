<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Page</title>
</head>
    <body>
        <h2>
            Hello Admin! <br/>
        </h2>

        <br/>
        <a href="${pageContext.request.contextPath}/api/logout">Logout</a>
        <br>
        <a href="${pageContext.request.contextPath}/api/manager/products">ProductList</a>
        <br>
        <a href="${pageContext.request.contextPath}/api/cashier">View as Cashier</a>
        <br>
        <a href="${pageContext.request.contextPath}/api/manager">View as Manager</a>
        <br>
        <a href="${pageContext.request.contextPath}/api/cashier/checks">View all checks</a>
        <br>
        <a href="${pageContext.request.contextPath}/api/admin/users">View users</a>

    </body>
</html>
