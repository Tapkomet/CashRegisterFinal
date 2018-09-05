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
            The product <br/>
        </h2>
        <c:if test="${not empty sql_error_message}">
            <p class="error">${sql_error_message}</p>
        </c:if>

        <br>
        <br>
        <form action="${pageContext.request.contextPath}/api/manager/editProduct" method="post">
             Code <input type="number" name="code" value="${product.code}" readonly/><br>
             <c:if test="${not empty code_error_message}">
                <p class="error">${code_error_message}</p>
             </c:if>
             Name <input type="text" name="name" value="${product.name}" readonly/><br>
             Sold by Weight: ${product.soldByWeight}<br>
             <input type="hidden" name="soldByWeight" value="${product.soldByWeight}"/>
             Number in stock <input type="number" name="number" value="${product.number}"/><br>
             <c:if test="${not empty number_error_message}">
                <p class="error">${number_error_message}</p>
             </c:if>
             Total weight in stock <input type="number" name="weight" value="${product.weight}"/><br>
             <c:if test="${not empty weight_error_message}">
                <p class="error">${weight_error_message}</p>
             </c:if>
             Price per unit or kilo <input type="number" name="price" value="${product.price}"/><br>
             <c:if test="${not empty price_error_message}">
                <p class="error">${price_error_message}</p>
             </c:if>
             <input type="submit"
        </form>

        <br/>
        <a href="${pageContext.request.contextPath}/index.jsp">index</a>
    </body>
</html>
