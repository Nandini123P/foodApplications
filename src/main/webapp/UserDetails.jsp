<%@ page import="com.tap.foodAppServlet.pojo.user" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Details</title>
    <style>
        :root {
            --primary-color: #ffffff;
            --secondary-color: #e74c3c; /* Dark Red */
            --background-color: #2c3e50; /* Dark Blue/Gray */
            --card-background: #f5f5f5; /* Lighter Gray for cards */
            --link-color: #1e90ff; /* Blue color for links */
            --card-hover-color: #bdc3c7; /* Hover effect for card */
            --border-radius: 8px;
            --box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: var(--background-color);
            color: var(--primary-color);
            line-height: 1.6;
        }

        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 30px;
            background-color: var(--card-background);
            border-radius: var(--border-radius);
            box-shadow: var(--box-shadow);
        }

        h2 {
            text-align: center;
            color: var(--secondary-color);
        }

        .user-details p {
            font-size: 18px;
            margin: 10px 0;
            color: #333;
        }

        .back-button {
            display: block;
            width: 100%;
            padding: 12px;
            margin-top: 20px;
            background-color: var(--secondary-color);
            color: var(--primary-color);
            border: none;
            border-radius: var(--border-radius);
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .back-button:hover {
            background-color: #c0392b;
        }
    </style>
</head>
<body>
<%
    user loggedInUser = (user) session.getAttribute("loggedInUser");
    if (loggedInUser != null) {
%>
    <div class="container">
        <h2>User Details</h2>
        <div class="user-details">
            <p><strong>Name:</strong> <%= loggedInUser.getU_name() %></p>
            <p><strong>Address:</strong> <%= loggedInUser.getAddress() %></p>
            <p><strong>Email:</strong> <%= loggedInUser.getEmail() %></p>
        </div>
        <button class="back-button" onclick="window.history.back();"> Back</button>
    </div>
<%
    } else {
        response.sendRedirect("login.jsp"); // Redirect to login if user is not in session
    }
%>
</body>
</html>
