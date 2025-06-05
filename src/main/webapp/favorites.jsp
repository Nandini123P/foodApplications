<%@ page import="java.util.List" %>
<%@ page import="com.FoodApp.Model.RestaurantPojo" %>
<%@ page import="com.FoodApp.Impl.FavoriteDAOImpl" %>
<%@ page import="com.tap.foodAppServlet.pojo.user" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.FoodApp.Servlet.showMenu" %>
<%
    user loggedInUser = (user) session.getAttribute("loggedInUser");
    if (loggedInUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }  

    List<RestaurantPojo> favoriteRestaurants = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oct_jdbc", "root", "root");
        FavoriteDAOImpl dao = new FavoriteDAOImpl(conn);
        favoriteRestaurants = dao.getFavoritesByUserId(loggedInUser.getU_id());
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Your Favorite Restaurants</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #f0f4f8, #d9e2ec);
            color: #1b1b1b;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding-top: 80px;
        }

        .navbar {
            position: fixed;
            top: 0;
            left: 0;
            
            width: 100%;
            background: #1e90ff;
            padding: 5px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 4px 12px rgba(30,144,255,0.5);
            z-index: 1000;
            flex-wrap: wrap;
        }

        .logo {
            font-size: 2rem;
            font-weight: bold;
            color: #fff;
            letter-spacing: 1.5px;
            user-select: none;
            text-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
        }

        .profile-container {
            display: flex;
            align-items: center;
            gap: 10px;
            font-size: 1rem;
            color: #fff;
            flex-wrap: wrap;
            justify-content: flex-end;
            min-width: 0;
            
        }

        .icon-link {
            color: #fff;
            font-size: 1.4rem;
            transition: color 0.3s ease, transform 0.3s ease;
        }

        .icon-link:hover {
            color: #ffd700;
            transform: scale(1.2);
        }

        .user-info {
            display: flex;
            align-items: center;
            gap: 6px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .welcome-text {
            font-weight: 600;
            font-size: 1rem;
            max-width: 150px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            color: white;
        }

        h2 {
            text-align: center;
            color: #1e90ff;
            font-size: 2rem;
            font-weight: 700;
            margin-bottom: 20px;
            letter-spacing: 1.2px;
            text-shadow: 0 1px 2px rgba(30, 144, 255, 0.2);
        }

        .card {
            width: 320px;
            background-color: #ffffff;
            border-radius: 20px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            margin-bottom: 30px;
        }

        .card:hover {
            transform: translateY(-10px);
            box-shadow: 0 12px 30px rgba(0, 0, 0, 0.2);
        }

        .card img {
            width: 100%;
            height: 180px;
            object-fit: cover;
            border-bottom: 1px solid #e0e0e0;
        }

        .card h3 {
            margin: 15px 20px 8px;
            font-size: 20px;
            color: #023e8a;
        }

        .card p {
            margin: 5px 20px;
            font-size: 15px;
            color: #444;
            line-height: 1.5;
        }

        .action-buttons {
            display: flex;
            justify-content: space-between;
            gap: 10px;
            margin: 20px;
        }

        .action-buttons form {
            flex: 1;
        }

        .action-buttons button {
            width: 100%;
            padding: 10px 0;
            background-color: #1e90ff;
            border: none;
            border-radius: 10px;
            color: white;
            font-size: 14px;
            font-weight: bold;
            transition: background-color 0.3s ease, transform 0.2s ease;
            cursor: pointer;
        }

        .action-buttons button:hover {
            background-color: #0077b6;
            transform: scale(1.05);
        }

        .remove-btn {
            background-color: #dc3545 !important;
        }

        .remove-btn:hover {
            background-color: #c82333 !important;
        }

        @media (max-width: 500px) {
            .card {
                width: 90%;
            }

            .navbar {
                flex-direction: column;
                align-items: flex-start;
                padding: 10px 20px;
            }

            .profile-container {
                flex-direction: column;
                align-items: flex-start;
                gap: 10px;
            }
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

            <div class="user-info">
                <a href="profile.jsp" class="icon-link" title="User Profile"><i class="fas fa-user"></i></a>
                <span class="welcome-text">Welcome <%= loggedInUser.getU_name() %></span>
            </div>
        </div>
    </div>

    <h2>Your Favorite Restaurants</h2>

    <%
        if (favoriteRestaurants != null && !favoriteRestaurants.isEmpty()) {
            for (RestaurantPojo r : favoriteRestaurants) {
    %>
        <div class="card">
            <img src="<%= r.getImagePath() %>" alt="<%= r.getName() %>">
            <h3><%= r.getName() %></h3>
            <p><strong>Address:</strong> <%= r.getAddress() %></p>
            <p><strong>Cuisine:</strong> <%= r.getCuisineType() %></p>
            <p><strong>Rating:</strong> <%= r.getRatings() %></p>
            <div class="action-buttons">
                <form action="RemoveFavoriteServlet" method="get">
                    <input type="hidden" name="restaurantId" value="<%= r.getRestaurantId() %>">
                    <button class="remove-btn" type="submit">Remove</button>
                </form>
                <form action="showMenu" method="get">
                    <input type="hidden" name="RestaurantId" value="<%= r.getRestaurantId() %>">
                    <input type="hidden" name="restaurantName" value="<%= r.getName() %>">
                    <button type="submit">View Menu</button>
                </form>
            </div>
        </div>
    <%
            }
        } else {
    %>
        <p style="text-align:center;">You haven't added any favorites yet.</p>
    <%
        }
    %>
</body>
</html>
