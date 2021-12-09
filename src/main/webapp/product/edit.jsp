<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12/9/2021
  Time: 10:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h1>Edit</h1>
    <form method="post">
        <input type="text" name="name" value="${product.getName()}">
        <input type="text" name="price" value="${product.getPrice()}">
        <input type="text" name="quantity" value="${product.getQuantity()}">
        <input type="text" name="price" value="${product.getColor()}">
        <input type="text" name="description" value="${product.getDescription()}">
        <input type="text" name="category" value="${product.getCategory()}">
        <button>Edit</button>
    </form>
</center>
</body>
</html>
