<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tap.foodAppServlet.pojo.user" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Confirmation</title>

    <!-- Font Awesome for icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet" />

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #f0f4f8, #d9e2ec);
            color: #1b1b1b;
            display: flex;
            flex-direction: column;
            align-items: center;
            min-height: 100vh;
            padding-top: 80px;
        }

        .navbar {
            position: fixed;
            top: 0;
            width: 100%;
            background: #1e90ff;
            color: white;
            padding: 15px 40px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 4px 12px rgba(30,144,255,0.5);
            z-index: 1000;
        }

        .logo {
            font-size: 1.9rem;
            font-weight: 700;
            color: #fff;
        }

        /* Profile container styles */
        .profile-container {
            display: flex;
            align-items: center;
            gap: 18px;
            
        }

        .icon-link {
            color: #fff;
            font-size: 1.6rem;
            transition: color 0.3s ease, transform 0.3s ease;
        }

        .icon-link:hover {
            color: #ffd700;
            transform: scale(1.2);
        }

        .username {
            color: #f0f0f0;
            font-weight: 600;
            font-size: 1.4rem;
            padding-left: 12px;
            border-left: 1.5px solid rgba(255,255,255,0.4);
            user-select: none;
        }

        .container {
            background: #fff;
            max-width: 450px;
            width: 80%;
            border-radius: 60px;
            box-shadow: 0 12px 25px rgba(30,144,255,0.15);
            padding: 30px 35px;
            margin-top: 80px;
            text-align: center;
            
        }

        h1 {
            color: #1e90ff;
            margin-bottom: 20px;
            
        }

        p {
            font-size: 1.3rem;
            color: #333;
            margin-bottom: 10px;
        }

        .home-btn {
            background: #1e90ff;
            color: #fff;
            border: none;
            border-radius: 25px;
            padding: 12px 30px;
            font-size: 1rem;
            font-weight: 600;
            cursor: pointer;
            box-shadow: 0 6px 15px rgba(30,144,255,0.35);
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
            margin-top: 20px;
        }

        .home-btn:hover {
            background: #176fca;
            box-shadow: 0 8px 22px rgba(23,111,202,0.55);
        }
                .back-btn {
            background: #1e90ff;
            color: #fff;
            border: none;
            border-radius: 25px;
            padding: 12px 30px;
            font-size: 1rem;
            font-weight: 600;
            cursor: pointer;
            box-shadow: 0 6px 15px rgba(30,144,255,0.35);
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
            margin-top: 20px;
        }

        .back-btn:hover {
            background: #176fca;
            box-shadow: 0 8px 22px rgba(23,111,202,0.55);
        }
        


  .btn:hover {
    background-color: #0056b3;

        .dark-mode {
            background: linear-gradient(135deg, #121212, #1e1e1e);
            color: #f5f5f5;
        }

        .dark-mode .navbar {
            background: #333;
        }

        .dark-mode .logo {
            color: #fff;
        }

        .dark-mode .container {
            background: #1c1c1c;
            box-shadow: 0 12px 25px rgba(0, 0, 0, 0.5);
        }

        .dark-mode .home-btn {
            background: #1e90ff;
        }

        .dark-mode .icon-link {
            color: #fff;
        }

        .dark-mode .username {
            color: #f5f5f5;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<div class="navbar">
    <div class="logo">Nandi Food</div>

    <div class="profile-container">
        <a href="home.jsp" class="icon-link" title="Home"><i class="fas fa-home"></i></a>
        <a href="cart.jsp" class="icon-link" title="Cart"><i class="fas fa-shopping-cart"></i></a>
        <a href="profile.jsp" class="icon-link" title="User Profile"><i class="fas fa-user"></i></a>
        
            <div class="username">
                <%
                    user loggedInUser = (user) session.getAttribute("loggedInUser");
                    if (loggedInUser == null) {
                        response.sendRedirect("login.jsp");
                        return;
                    }
                %>
                 Welcome 👋, <%= loggedInUser.getU_name() %>
            </div>
        
    </div>
</div>

<div class="container">
    <h1>Order Confirmed 🎉</h1>
    <p>Thank you for your order!</p>
    <p>Your delicious food is on its way.</p>
    <p>We hope you enjoy your meal 🍽️</p>
  <div style="display: flex; flex-direction: column; align-items: center;">
    <a href="home.jsp" style="margin-bottom: 10px;">
        <button class="home-btn">Go to Home</button>
    </a>
    <a href="logout.jsp">
        <button class="back-btn">Logout</button>
    </a>
</div>
</div>

<!-- Dark Mode Toggle -->
<button onclick="toggleDarkMode()" id="darkModeToggle" style="position: fixed; bottom: 20px; right: 20px; background: #222; color: #fff; border: none; padding: 10px 20px; border-radius: 25px; cursor: pointer; z-index: 1001;">🌙 Dark Mode</button>

<script>
    function toggleDarkMode() {
        document.body.classList.toggle("dark-mode");
        const isDark = document.body.classList.contains("dark-mode");
        document.getElementById("darkModeToggle").textContent = isDark ? "☀️ Light Mode" : "🌙 Dark Mode";
    }
</script>

</body>
</html>
