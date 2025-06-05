<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Success</title>
    <style>
        /* General Styles */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #e8f5e9;
            margin: 0;
            padding: 50px;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            height: 100vh;
        }
        h1 {
            color: #4caf50;
            font-size: 2.5em;
            margin-bottom: 20px;
            text-align: center;
        }
        p {
            color: #333;
            font-size: 1.2em;
            margin-bottom: 30px;
            text-align: center;
        }
        /* Button Style */
        a {
            text-decoration: none;
            background-color: #1e88e5;
            color: white;
            padding: 15px 30px;
            font-size: 1.1em;
            border-radius: 5px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: all 0.3s ease;
        }
        a:hover {
            background-color: #1565c0;
            box-shadow: 0 6px 10px rgba(0, 0, 0, 0.2);
        }
        /* Add a subtle animation */
        .container {
            text-align: center;
            background-color: white;
            border-radius: 10px;
            padding: 40px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px;
            animation: fadeIn 1s ease-out;
        }
        /* Fade-in animation */
        @keyframes fadeIn {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Registration Successful!</h1>
        <p>Your account has been created successfully.</p>
        <a href="login.jsp">Click here to login</a>
    </div>
</body>
</html>
