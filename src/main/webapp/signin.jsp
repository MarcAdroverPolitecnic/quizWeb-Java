<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>Sign In</title>
    <link rel="stylesheet" href="css/signin.css">
</head>

<body>

<div class="signin-box">
    <h2>Sign In</h2> <form action="signin" method="post">
    <div class="input-field">
        <input type="text" name="username" placeholder="Username" required>
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

</body>
</html>