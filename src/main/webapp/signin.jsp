<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>Sign In</title>
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: #f0f2f5;
            font-family: Arial, sans-serif;
        }

        .signin-box {
            /* Hecho un poco más ancho que el login-box para acomodar más campos si fuera necesario,
               aunque con 4 campos no es estrictamente necesario, mantiene el mismo tamaño para consistencia. */
            width: 330px;
            padding: 30px 24px;
            background: #fff;
            border-radius: 12px;
            border: 1px solid #ddd;
            box-shadow: 0 4px 18px rgba(0, 0, 0, 0.1);
        }

        .signin-box h2 {
            text-align: center;
            margin-bottom: 25px;
        }

        .input-field {
            margin-bottom: 15px;
        }

        .input-field input {
            width: 100%;
            padding: 12px 14px;
            border: 1px solid #ccc;
            border-radius: 8px;
            outline: none;
            font-size: 14px;
            transition: 0.2s;
            background: #e8f0fe;
        }

        .btn {
            width: 100%;
            padding: 12px;
            border: none;
            background: #4f76f6;
            color: white;
            font-size: 15px;
            border-radius: 8px;
            cursor: pointer;
            transition: 0.2s;
        }

        .btn:hover {
            background: #3e63d5;
        }
    </style>
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

    <button class="btn" type="submit">Registrarse</button>
</form>
</div>

</body>
</html>