<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Menú</title>
    <link rel="stylesheet" href="css/menu.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Quiz Game</h1>
    </div>

    <div class="start-section">
        <form action="${pageContext.request.contextPath}/login" method="get">
            <button class="start-btn" type="submit">Start Game</button>
        </form>
    </div>

    <div class="scores-section">
        <h2 class="scores-title">Ranking</h2>
        <table>
            <thead>
            <tr>
                <th>User</th>
                <th>Correct Answers</th>
                <th>Total Time</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><strong>${user.username}</strong></td>
                    <td>${user.correctAnswersRecord}</td>
                    <td>${user.totalGameTimeRecord}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>