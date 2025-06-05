<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.FoodApp.Model.CartItem" %>
<%@ page import="com.tap.foodAppServlet.pojo.user" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Cart 🛒</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet" />

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(to right, #e0f7fa, #ffffff);
            color: #1b1b1b;
            min-height: 100vh;
        }

        .navbar {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            background: #1e90ff;
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
            letter-spacing: 1.3px;
            user-select: none;
            text-shadow: 0 1px 5px rgba(0,0,0,0.3);
        }

        .profile-container {
            display: flex;
            align-items: center;
            gap: 18px;
            font-size: 1.3rem;
            
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
            font-size: 1rem;
            padding-left: 12px;
            border-left: 1.5px solid rgba(255,255,255,0.4);
            user-select: none;
        }

        h1 {
            margin-top: 100px;
            text-align: center;
            font-size: 2rem;
            color: #1e90ff;
        }

        .card-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 25px;
            padding: 20px;
        }

        .cart-card {
            background: #ffffff;
            border-radius: 18px;
            padding: 20px;
            width: 270px;
            box-shadow: 0 12px 30px rgba(0, 0, 0, 0.1);
            transition: all 0.3s ease-in-out;
            position: relative;
            overflow: hidden;
        }

        .cart-card:hover {
            transform: translateY(-6px) scale(1.02);
            box-shadow: 0 16px 35px rgba(0, 0, 0, 0.2);
        }

        .cart-image {
            width: 100%;
            height: 150px;
            object-fit: cover;
            border-radius: 10px;
            margin: 10px 0;
        }

        .quantity-btn, .delete-btn {
            padding: 6px 14px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            margin: 5px 4px;
            font-weight: bold;
        }

        .quantity-btn {
            background-color: #ffa500;
            color: white;
        }

        .quantity-btn:hover {
            background-color: #e69500;
        }

        .delete-btn {
            background-color: #dc3545;
            color: white;
        }

        .delete-btn:hover {
            background-color: #b51b32;
        }

        .action-row {
            display: flex;
            justify-content: space-between;
            margin-top: 10px;
        }

        .honor-box {
            max-width: 600px;
            margin: 40px auto;
            padding: 30px;
            background: #ffffff;
            border: 2px solid #1e90ff;
            border-radius: 20px;
            box-shadow: 0 0 20px rgba(30,144,255,0.2);
            text-align: center;
        }

        .honor-box h2 {
            font-size: 24px;
            color: #1e90ff;
        }

        .honor-buttons-row {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            gap: 15px;
            margin-top: 20px;
        }

        .action-btn {
            padding: 12px 25px;
            border: none;
            border-radius: 25px;
            font-weight: bold;
            cursor: pointer;
            font-size: 15px;
            transition: 0.3s ease;
        }

        .add-more-btn {
            background-color: #28a745;
            color: white;
        }

        .order-now-btn {
            background-color: #f39c12;
            color: white;
        }

        .delete-cart-btn {
            background-color: #dc3545;
            color: white;
        }

        .action-btn:hover {
            opacity: 0.9;
            transform: translateY(-2px);
        }

        @media (max-width: 500px) {
            .navbar {
                padding: 12px 20px;
            }
        }

        /* DARK MODE STYLES */
        .dark-mode {
            background: #121212;
            color: #ffffff;
        }

        .dark-mode .navbar {
            background: #1c1c1c;
            box-shadow: 0 4px 12px rgba(255,255,255,0.2);
        }

        .dark-mode .logo,
        .dark-mode .username,
        .dark-mode .icon-link {
            color: #fff;
        }

        .dark-mode .cart-card,
        .dark-mode .honor-box {
            background: #1e1e1e;
            color: #ffffff;
            border-color: #444;
            box-shadow: 0 0 20px rgba(255,255,255,0.1);
        }

        .dark-mode .badge {
            color: #fff;
        }

        .dark-mode .action-btn.add-more-btn {
            background-color: #1f7a34;
        }

        .dark-mode .action-btn.order-now-btn {
            background-color: #c87f0a;
        }

        .dark-mode .action-btn.delete-cart-btn {
            background-color: #8e1e2c;
        }

        .dark-mode .rating {
            color: #ffeb3b;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<div class="navbar">
    <div class="logo">Nandi Food</div>
    <div class="profile-container">
        <a href="home.jsp" class="icon-link" title="Home"><i class="fas fa-home"></i></a>
        <a href="profile.jsp" class="icon-link" title="User Profile"><i class="fas fa-user"></i></a>
            <div>
                <% 
                    user loggedInUser = (user) session.getAttribute("loggedInUser");
                    if (loggedInUser == null) {
                        response.sendRedirect("login.jsp");
                        return;
                    } 
                %>
                 Welcome 👋, <%= loggedInUser.getU_name() %>
            </div>
        
        <button class="mode-toggle" style="background-color: black;" onclick="toggleMode()">🌙</button>
    </div>
</div>

<h1>Your Cart 🛒</h1>


<%
    Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
    if (cart == null || cart.isEmpty()) {
%>
    <h2 style="text-align:center;">🛒 Your cart is empty.</h2>
<% } else {
    double grandTotal = 0;
%>
<div class="card-container">
<% for (CartItem item : cart.values()) {
       double itemTotal = item.getPrice() * item.getQuantity();
       grandTotal += itemTotal;
%>
    <form method="post">
        <div class="cart-card">
            <h3><%= item.getName() %></h3>
            <div>
                <% 
                    String category = item.getCategory();
                    if (category != null && !category.trim().isEmpty()) {
                        String catColor = category.equalsIgnoreCase("Veg") ? "#28a745" : "#dc3545";
                %>
                    <span class="badge" style="background-color:<%= catColor %>;"><%= category %></span>
                <% } %>

                <% String tag = item.getTag();
                   if (tag != null && !tag.trim().isEmpty()) { %>
                    <span class="badge" style="background-color:#ff9800;"><%= tag %></span>
                <% } %>
            </div>
            <img class="cart-image" src="CartImageServlet?menuID=<%= item.getMenuID() %>" alt="Item Image" />
            <p style="font-size:14px; margin: 5px 0;">Juicy grilled with cheese and spices.</p>
            <div class="rating">⭐⭐⭐⭐☆</div>
            <p>Price: ₹<%= item.getPrice() %></p>
            <p>Total: ₹<%= itemTotal %></p>

            <input type="hidden" name="itemId" value="<%= item.getMenuID() %>">
            <div>
                <button type="submit" formaction="UpdateCartServlet" name="action" value="decrease" class="quantity-btn">-</button>
                <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1" style="width: 40px; text-align: center;">
                <button type="submit" formaction="UpdateCartServlet" name="action" value="increase" class="quantity-btn">+</button>
                <button type="submit" formaction="DeleteCartItemServlet" class="delete-btn">🗑️</button>
            </div>
        </div>
    </form>
<% } %>
</div>

<div class="honor-box">
    <h2>🫶 Your Efforts Are Honored! 🫶</h2>
    <p>We're proud to serve you. Make your next move below!</p>
    
    <div style="font-size: 20px; font-weight: bold;">Grand Total: ₹<%= grandTotal %></div>

    <div class="honor-buttons-row">
        <form action="GetRestaurant" method="get">
            <button type="submit" class="action-btn add-more-btn">➕ Add More Items</button>
        </form>
        <form action="OrderSummary.jsp" method="post">
            <button type="submit" class="action-btn order-now-btn">✅ Order Now</button>
        </form>
        <form action="ClearCartServlet" method="post">
            <button type="submit" class="action-btn delete-cart-btn">🗑 Clear Cart</button>
        </form>
    </div>
</div>
<% } %>

<script>
    function toggleMode() {
        document.body.classList.toggle('dark-mode');
        localStorage.setItem("mode", document.body.classList.contains('dark-mode') ? 'dark' : 'light');
    }

    window.onload = function() {
        if (localStorage.getItem("mode") === "dark") {
            document.body.classList.add('dark-mode');
        }
    };
</script>
<button onclick="window.history.back()" style="position: fixed; bottom: 20px; left: 20px; padding: 10px 16px; background-color: #1e90ff; color: white; border: none; border-radius: 8px; cursor: pointer; font-size: 14px; z-index: 1000;">🔙 Back</button>


</body>
</html>
