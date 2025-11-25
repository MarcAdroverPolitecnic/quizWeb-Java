<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="css/login.css">
</head>

<body>

<div class="login-box">
    <h2>Login</h2>

    <form action="login" method="post">
        <div class="input-field">
            <input type="text" name="username" placeholder="User" required>
        </div>

        <div class="input-field">
            <input type="password" name="password" placeholder="Password" required>
        </div>

        <button class="btn" type="submit">submit</button>
    </form>
    <c:if test="${not empty(error)}">
        <p>${error}</p>
    </c:if>
</div>
<div class="login-box">
    <p>You dont have a user?</p>

    <form action="signin" method="get">
        <button class="btn" type="submit">Sign in</button>
    </form>
</div>

</body>
</html>