<%--
  Created by IntelliJ IDEA.
  User: Tykow
  Date: 21.07.2021
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Home</title>
</head>
<body>
<a href='<c:url value="/logout" />'>Logout</a>
<h1>Welcome
    <c:if test="${pageContext.request.userPrincipal.name != null }">
        ${pageContext.request.userPrincipal.name }
    </c:if></h1>
<br/>
<a href='<c:url value="/user/list" />'>Users List</a>
</body>
</html>
