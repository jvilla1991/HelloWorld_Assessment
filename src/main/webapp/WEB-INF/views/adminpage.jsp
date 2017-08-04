<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/2/2017
  Time: 10:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>

<h1>Admin Page</h1>

<a href="/">Jump to User Registration</a>

<table border="1">
    <%--This row entitles each colum for clarity--%>
    <tr>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Address1</td>
        <td>Address2</td>
        <td>City</td>
        <td>State</td>
        <td>Zip</td>
        <td>Country</td>
        <td>Date</td>
    </tr>
        <%--This loop parses through the arraylist that is sent from the homecontroller and populates a table--%>
        <%--with the correct values for each entity within the registraton table--%>
    <c:forEach var="u" items="${uList}">
        <tr>
            <td>${u.firstname}</td>
            <td>${u.lastname}</td>
            <td>${u.address1}</td>
            <td>${u.address2}</td>
            <td>${u.city}</td>
            <td>${u.state}</td>
            <td>${u.zip}</td>
            <td>${u.country}</td>
            <td>${u.date}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
