<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12/9/2021
  Time: 9:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/products?action=create"> create</a><br>
<form>
    <input type="text" name="name" placeholder="Enter name you want find?">
    <button> find</button>
</form>
<c:forEach items="${products}" var="pt">
    <h1>${pt.id},${pt.name},${pt.price},${pt.quantity},${pt.color},${pt.description},${pt.category}</h1>
    <td><a href="/products?action=edit&id=${pt.getId()}"> Edit</a></td>
    <a onclick="return confirm('Are you sure?')"
       href="/products?action=delete&id=${pt.id}">delete</a>
</c:forEach>
</body>
</html>
