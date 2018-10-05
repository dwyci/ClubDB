<%-- 
    Document   : Purchases
    Created on : Nov 14, 2017, 9:14:25 PM
    Author     : dallas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Purchases</title>
    </head>
    <body>
        <h1>Member Purchases</h1>
        <h2>${m.memid}</h2>
         <h2>${m.firstnm}  ${m.lastnm}</h2>
         <table border ="1">
             <tr>
                 <th>Purchase Dt</th>
                 <th>Purchase Type</th>
                 <th>Trans Cd</th>
                 <th>Trans Desc</th>
                 <th>Amount</th>
                                 
             </tr>
             <c:forEach var="p" items="${pur}">
                 <tr>
                     <td aling="left">${p.purchdt}</td>
                     <td aling="left">${p.purchtype}</td>
                     <td aling="left">${p.transtcd}</td>
                     <td aling="left">${p.transdesc}</td>
                     <td aling="left">${p.amount}</td>
                 </tr>
                 
                 
             </c:forEach>
             
         </table>
         <br>
         <a href="MemberScreen.jsp">Back to Member Screen
              </a>
               
    </body>
</html>
