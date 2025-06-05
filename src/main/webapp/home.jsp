<%@ page import="java.util.List" %>
<%@ page import="com.FoodApp.Model.RestaurantPojo" %>
<%@ page import="com.FoodApp.Impl.RestaurantDAOImpl" %>
<%@ page import="com.tap.foodAppServlet.pojo.user" %>

<%
    String searchQuery = request.getParameter("searchQuery");
    RestaurantDAOImpl restaurantDao = new RestaurantDAOImpl();
    List<RestaurantPojo> restaurants = restaurantDao.getAllRestaurants();

    user loggedInUser = (user) session.getAttribute("loggedInUser");
    if (loggedInUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Explore Restaurants</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <style>
      body {
    font-family: 'Segoe UI', sans-serif;
    margin: 0;
    background-color: #f8f9fa;
}

.navbar {
 background-color: #0077b6;
            color: white;
            padding: 15px 30px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            flex-wrap: wrap;
            gap: 15px;
            font-size: 1.6rem;
}



.logo {
    font-size: 38px;
    font-weight: bold;
    letter-spacing: 0px;
}

.profile-container {
    display: flex;
    align-items: center;
    gap: 15px;
    font-size: 30px;
    
}

.icon-link {
    color: white;
    font-size: 30px;
    transition: transform 0.2s ease;
}

.icon-link:hover {
    transform: scale(1.1);
}

.username {
    font-weight: bold;
}

.search-bar {
    text-align: center;
    margin: 25px auto 10px;
}

.search-bar input {
    width: 400px;
    padding: 12px 18px;
    font-size: 16px;
    border-radius: 10px;
    border: 1px solid #ccc;
    transition: border 0.3s ease;
}

.search-bar input:focus {
    border: 1px solid #1e90ff;
    outline: none;
}

h2 {
    text-align: center;
    color: #023e8a;
    margin-top: 30px;
    font-size: 38px;
    
    
}

.restaurant-grid {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 30px;
    padding: 30px;
}

.card {
    width: 300px;
    background-color: #ffffff;
    border-radius: 20px;
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
    overflow: hidden;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    display: flex;
    flex-direction: column;
}

.card:hover {
    transform: translateY(-10px);
    box-shadow: 0 12px 30px rgba(0, 0, 0, 0.15);
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
    line-height: 1.4;
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
    transition: background-color 0.3s ease;
}

.action-buttons button:hover {
    background-color: #0077b6;
}

@media (max-width: 600px) {
    .search-bar input {
        width: 90%;
    }

    .restaurant-grid {
        flex-direction: column;
        align-items: center;
    }
}
      
    </style>

    <script>
        function filterRestaurants() {
            const filter = document.getElementById("searchInput").value.toLowerCase();
            const cards = document.getElementsByClassName("card");

            for (let i = 0; i < cards.length; i++) {
                const name = cards[i].querySelector("h3").innerText.toLowerCase();
                cards[i].style.display = name.includes(filter) ? "block" : "none";
            }
        }
    </script>
</head>
<body>

<div class="navbar">
    <div class="logo">Nandi Food</div>
    <div class="search-bar">
    <input type="text" id="searchInput" placeholder="Search restaurants..." onkeyup="filterRestaurants()">
</div>
    <div class="profile-container">
        <a href="profile.jsp" class="icon-link" title="User Profile"><i class="fas fa-user"></i></a>
        <div class="username">Welcome <%= loggedInUser.getU_name() %></div>
    </div>
</div>



<h2>Explore Restaurants</h2>

<div class="restaurant-grid">
    <% if (restaurants != null && !restaurants.isEmpty()) {
        for (RestaurantPojo r : restaurants) { %>
            <div class="card">
                
                <h3><%= r.getName() %></h3>
                <p><strong>Address:</strong> <%= r.getAddress() %></p>
                <p><strong>Cuisine:</strong> <%= r.getCuisineType() %></p>
                <p><strong>Rating:</strong> <%= r.getRatings() %></p>
                <div class="action-buttons">
                    <form action="FavoriteServlet" method="post">
                        <input type="hidden" name="restaurantId" value="<%= r.getRestaurantId() %>">
                        <button type="submit">Add to Favorites</button>
                    </form>
                    <form action="showMenu" method="get">
                        <input type="hidden" name="RestaurantId" value="<%= r.getRestaurantId() %>">
                        <input type="hidden" name="restaurantName" value="<%= r.getName() %>">
                        <button type="submit">View Menu</button>
                    </form>
                </div>
            </div>
    <%  }
       } else { %>
        <p style="text-align: center;">No restaurants available.</p>
    <% } %>
</div>

</body>
</html>
