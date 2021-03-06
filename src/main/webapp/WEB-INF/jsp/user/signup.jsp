<%--
  Created by IntelliJ IDEA.
  User: Tykow
  Date: 21.07.2021
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Signup</title>
</head>
<body>
<spring:url value="/user/register" var="registerURL" />
<form:form action="${registerURL}" modelAttribute="userForm" method="post" >
    <label>Username: </label>
    <form:input path="username" type="text" />
    <form:errors path="username" />
    <br/>
    <label>Password: </label>
    <form:password path="password" />
    <form:errors path="password" />
    <br/>
    <label>Confirm Password: </label>
    <form:password path="confirmPassword" />
    <form:errors path="confirmPassword" />
    <br/>
    <button type="submit">Sign up</button>

</form:form>

</body>
</html>
