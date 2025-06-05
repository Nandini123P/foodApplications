<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Logged Out - Nandi Foods</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f8f9fa;
            text-align: center;
            padding: 100px;
        }
        .message {
            font-size: 24px;
            color: #023e8a;
            margin-bottom: 20px;
        }
        .login-link {
            display: inline-block;
            background-color: #0077b6;
            color: white;
            padding: 10px 20px;
            border-radius: 8px;
            text-decoration: none;
            font-size: 18px;
        }
        .login-link:hover {
            background-color: #0096c7;
        }
    </style>
</head>
<body>
    <div class="message">You have logged out successfully.<br>Thanks for using <strong>Nandi Foods</strong>. Visit again!</div>
    <a class="login-link" href="login.jsp">🔐 Login Here</a>
</body>
</html>