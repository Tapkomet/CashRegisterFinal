<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product List</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="/css/style.css" type="text/css">

     <script src="/scripts/jquery-3.3.1.min.js"></script>
        <script>
         $(window).on('load', function () {
         var productsNumber = 1;

            $('#prodtable').on('click','.addbutton',function(){
                 var self = $(this).closest("tr");
                 var code = self.find(".code").text();
                 var name = self.find(".name").text();
                 var price = self.find(".price").text();
                 var soldByWeight = self.find(".soldByWeight").text();
                 var number = self.find(".number").text();
                 var weight = self.find(".weight").text();
                 var manager = self.find(".manager").text();
                 var numberToAdd = self.find(".numberInput").find("input").val();
                 var weightToAdd = self.find(".weightInput").find("input").val();

                 var productToAdd = "";

                 productToAdd += "Code: "+code+" <input type='hidden' name='code"+productsNumber+
                 "' value='"+code+"'/>"
                 productToAdd += "Name: "+name+" <input type='hidden' name='name"+productsNumber+
                 "' value='"+name+"'/>"
                 productToAdd += "Sold by weight "+soldByWeight+" <input type='hidden' name='soldByWeight"+productsNumber+
                 "' value='"+soldByWeight+"'/>"

                 var priceToAdd;
                 if(soldByWeight=="true") {
                    priceToAdd = price * (weightToAdd / 1000);
                    productToAdd += "Weight sold: "+weightToAdd+" <input type='hidden' name='weightToAdd"+productsNumber+
                    "' value='"+weightToAdd+"'/>"
                 }
                 else{
                    priceToAdd = price * numberToAdd;
                    productToAdd += "Number sold: "+numberToAdd+" <input type='hidden' name='numberToAdd"+productsNumber+
                    "' value='"+numberToAdd+"'/>"
                 }

                 productToAdd += "Total price: "+priceToAdd+" <input type='hidden' name='priceToAdd"+productsNumber+
                 "' value='"+priceToAdd+"'/>"

                 productToAdd += "Manager: "+manager+" <input type='hidden' name='manager"+productsNumber+
                 "' value='"+manager+"'/><br>"

                 $(".addToCheck").prepend(productToAdd);
                 productsNumber++;
             });

         });
         </script>
</head>
    <body>
        <h2>
            List Products <br/>
        </h2>
        <button id="btn1">Append text</button>
        <c:if test="${not empty sql_error_message}">
            <p class="error">${sql_error_message}</p>
        </c:if>
        <table id="prodtable">
        <tr><th>Code</th><th>Name</th><th>Price</th><th>IsSoldByWeight</th>
        <th>Total number</th><th>Total weight</th><th>Manager</th>
        <th>Number to add</th><th>Weight to add</th><th></th></tr>
        <c:forEach var="i" items="${products}">
            <tr><td class="code"><a href="product?id=<c:out value='${i.code}'/>"><c:out value="${i.code}"/></a></td>
            <td class="name">${i.name}</td><td class="price">${i.price}</td>
            <td class="soldByWeight">${i.soldByWeight}</td><td class="number">${i.number}</td>
            <td class="weight">${i.weight}</td><td class="manager">${i.manager.id}</td>
            <td class="numberInput"><input type="number"></td>
            <td class="weightInput"><input type="number"></td>
            <td>
            <button class="addbutton" id="btn1">Add to check</button>
            </td>
        </c:forEach>
        </table>
            <br>
            <p>Add check:</p> <br>

            <form class="addToCheck" action="${pageContext.request.contextPath}/api/cashier/checks/add" method="post">
            <input type="submit" value="Close check"/>
            </form>
        <br>

        <a href="${pageContext.request.contextPath}/index.jsp">index</a>
    </body>
</html>
