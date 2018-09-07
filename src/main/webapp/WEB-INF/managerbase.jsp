<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Manager Page</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="/css/style.css" type="text/css">
</head>
    <body>
        <div class="container col-lg-12 centre">
        <h2>
            Hello Manager! <br/>
        </h2>

        <br/>
        <ul class="options">
        <li><a href="${pageContext.request.contextPath}/api/logout">Logout</a></li>
        <li><a href="${pageContext.request.contextPath}/api/manager/products">ProductList</a></li>
        </ul>
        </div>
    </body>
</html>
