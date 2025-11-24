<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Quiz</title>
    <link rel="stylesheet" href="css/quiz.css">
</head>

<body>
<div class="container">

    <h2>Category: ${question.category}</h2>
    <h2>Difficulty: ${difficulty}</h2>

    <div class="stats-row">
        <h3>CorrectAnswers: ${correctAnswers}</h3>
        <h3>IncorrectAnswers: ${incorrectAnswers}</h3>
    </div>

    <div class="time-row">
        <div class="stat-group">
            <h3>TotalTime: </h3>
            <div id="totalTime"></div>
        </div>
        <div class="stat-group">
            <h3>Timer: </h3>
            <div id="timer"></div>
        </div>
    </div>

    <h3>${question.question.text}</h3>

    <form action="quiz" method="post">
        <input type="hidden" name="correctAnswer" value="${question.correctAnswer}">
        <input type="hidden" name="timer" id="timerValue">
        <input type="hidden" name="totalTime" id="totalTimeValue">
        <input type="hidden" name="correctAnswers" value="${correctAnswers}">
        <input type="hidden" name="incorrectAnswers" value="${incorrectAnswers}">

        <c:forEach var="answer" items="${answers}">
            <input type="submit" name="answer" value="${answer}">
        </c:forEach>
    </form>

    <form action="quiz" method="post" id="endForm">
        <input type="hidden" name="correctAnswer" value="${question.correctAnswer}">
        <input type="hidden" name="timer" id="endTimerValue">
        <input type="hidden" name="totalTime" id="endTotalTimeValue">
        <input type="hidden" name="correctAnswers" value="${correctAnswers}">
        <input type="hidden" name="incorrectAnswers" value="${incorrectAnswers}">
    </form>

</div>

<script>
    let timer = ${timer};
    let totalTime = ${totalTime};

    function putTimer() {
        const div1 = document.getElementById('timer');
        div1.innerHTML = timer;

        const div2 = document.getElementById('totalTime');
        div2.innerHTML = totalTime;

        if (timer > 0) {
            timer--;
            totalTime++;
            document.getElementById("timerValue").value = timer;
            document.getElementById("totalTimeValue").value = totalTime;
        } else {
            document.getElementById("endTimerValue").value = timer;
            document.getElementById("endTotalTimeValue").value = totalTime;
            document.getElementById("endForm").submit();
        }
    }

    putTimer();
    setInterval(putTimer, 1000);
</script>

</body>
</html>
