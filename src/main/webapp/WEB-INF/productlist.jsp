<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product List</title>
</head>
    <body>
        <h2>
            List Products <br/>
        </h2>
        <table>
        <tr><th>Code</th><th>Name</th><th>Price</th><th>IsSoldByWeight</th>
        <th>Total number</th><th>Total weight</th><th>User</th><th></th></tr>
        <c:forEach var="i" items="${products}">
            <tr><td><a href="product?id=<c:out value='${i.code}' />"> <c:out value="${i.code}"/></a></td>
            <td>${i.name}</td><td>${i.price}</td>
            <td>${i.soldByWeight}</td><td>${i.number}</td><td>${i.weight}</td><td>${i.manager.id}</td>
            <td>
            <form action="${pageContext.request.contextPath}/api/manager/deleteProduct?id=${i.code}"
             method="post">
            <input type="submit" value="Delete"/>
            </form>
            </td>
        </c:forEach>
        </table>
        <br>
        <br>
        <form action="${pageContext.request.contextPath}/api/manager/addProduct" method="post">
             Code <input type="number" name="code"/><br>
             Name <input type="text" name="name"/><br>
             Sold by Weight <input type="checkbox" name="soldByWeight"/><br>
             Number in stock <input type="number" name="number"/><br>
             Total weight in stock <input type="number" name="weight"/><br>
             Price per unit or kilo <input type="number" name="price"/><br>
             <input type="submit"/>
        </form>

        <br/>
        <a href="${pageContext.request.contextPath}/index.jsp">index</a>
    </body>
</html>
