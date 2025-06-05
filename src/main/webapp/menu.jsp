<%@ page import="java.util.List" %>
<%@ page import="com.FoodApp.Model.MenuPojo" %>
<%@ page import="com.tap.foodAppServlet.pojo.user" %>

<%
    List<MenuPojo> menuList = (List<MenuPojo>) request.getAttribute("menuList");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
      body {
    font-family: 'Segoe UI', sans-serif;
    margin: 0;
    background-color: #f8f9fa;
}

        .header {
            background-color: #0077b6;
            color: white;
            padding: 20px 35px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            flex-wrap: wrap;
            gap: 15px;
            font-size: 1.8rem;
            font-weight: bold;
        }
        .logo {
    font-size: 30px;
    font-weight: bold;
    letter-spacing: 0px;
}

        .profile-box {
            background-color: #023e8a;
            padding: 8px 16px;
            border-radius: 25px;
            display: flex;
            align-items: center;
            gap: 8px;
            font-size: 1rem;
            font-weight: 500;
            
        }

        .search-bar {
            flex: 1;
            display: flex;
            justify-content: center;
        }

        .search-bar input {
            padding: 12px 16px;
            border-radius: 5px;
            border: none;
            outline: none;
            width: 400px;
            max-width: 90%;
        }

.icon-link {
    color: white;
    font-size: 30px;
    transition: transform 0.2s ease;
}

.icon-link:hover {
    transform: scale(1.1);
}



        h1 {
            text-align: center;
            margin-top: 20px;
            font-size: 2.5rem;
            color: #2c3e50;
        }

        .menu-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
            margin: 20px;
        }

        .menu-item {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 8px;
            width: 250px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
            transition: transform 0.3s ease, box-shadow 0.3s ease, border 0.3s ease;
        }

        .menu-item:hover {
            transform: translateY(-10px);
            box-shadow: 0 0 15px rgba(0, 174, 255, 0.6), 0 6px 12px rgba(0, 0, 0, 0.2);
            border: 2px solid #00b4d8;
        }

        .menu-item h3 {
            font-size: 1.5rem;
            color: #e74c3c;
            margin-bottom: 10px;
        }

        .menu-item p {
            font-size: 1rem;
            margin: 5px 0;
        }

        .availability {
            font-weight: bold;
            color: #27ae60;
        }

        .unavailable {
            color: #e74c3c;
        }

        .no-items {
            text-align: center;
            font-size: 1.2rem;
            color: #e74c3c;
        }

        .profile-link {
            text-decoration: none;
            color: #333;
        }

        .username {
            display: inline-block;
            padding: 10px 15px;
            background-color: #f0f0f0;
            border-radius: 25px;
            
            font-size: 16px;
            transition: background-color 0.3s ease, transform 0.2s ease;
            cursor: pointer;
            font-weight: bold;
        }

        .username:hover {
            background-color: #e0e0e0;
            transform: scale(1.05);
            color: #000;
        }

        .button-group {
            display: flex;
            justify-content: center;
            gap: 10px;
            margin-top: 15px;
            flex-wrap: wrap;
        }

        .inline-form {
            display: inline-block;
            margin: 0;
        }

        .btn {
            padding: 10px 18px;
            font-size: 1rem;
            font-weight: 600;
            border: none;
            border-radius: 25px;
            cursor: pointer;
            transition: all 0.3s ease;
            color: white;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }

        .cart-btn {
            background: linear-gradient(135deg, #00b4d8, #0077b6);
        }

        .cart-btn:hover {
            background: #0077b6;
            box-shadow: 0 0 12px #00b4d8;
            transform: scale(1.05);
        }

        .order-btn {
            background: linear-gradient(135deg, #38b000, #70e000);
        }

        .order-btn:hover {
            background: #38b000;
            box-shadow: 0 0 12px #70e000;
            transform: scale(1.05);
        }
    </style>
</head>
<body>
 
        
    <div class="header">
    
    <div class="logo">Nandi Food</div> 
    
        <div class="search-bar">
            <input type="text" id="searchInput" placeholder="Search food items..." onkeyup="filterRestaurants()">
        </div>
            
            <a href="profile.jsp" class="icon-link" title="User Profile"><i class="fas fa-user"></i></a>
                <div>
                    <% user loggedInUser = (user) session.getAttribute("loggedInUser");
                       if (loggedInUser == null) {
                           response.sendRedirect("login.jsp");
                           return;
                       } %>
                     Welcome <%= loggedInUser.getU_name() %>
                </div>
    </div>

    <h1>Restaurant Menu</h1>

    <div class="menu-container" id="menuContainer">
        <%
            if (menuList != null && !menuList.isEmpty()) {
                for (MenuPojo menuItem : menuList) {
        %>
        <div class="menu-item" data-name="<%= menuItem.getName() %>">
            <img src="UploadImageServlet?menuId=<%= menuItem.getMenuID() %>" alt="<%= menuItem.getName() %>" width="200" height="150">
            <h3><%= menuItem.getName() %></h3>
            <p>Description: <%= menuItem.getDescription() %></p>
            <p>Price: ₹<%= menuItem.getPrice() %></p>
            <p>Rating: <%= menuItem.getRating() %></p>
            <p class="<%= menuItem.getIsAvailable().equalsIgnoreCase("yes") ? "availability" : "unavailable" %>">
                <%= menuItem.getIsAvailable().equalsIgnoreCase("yes") ? "Available" : "Not Available" %>
            </p>

            <div class="button-group">
                <form action="AddToCartController" method="POST" class="inline-form">
                    <input type="hidden" name="menuID" value="<%= menuItem.getMenuID() %>">
                    <input type="hidden" name="quantity" value="1">
                    <button type="submit" class="btn cart-btn"> Add to Cart</button>
                </form>

                <form action="checkout.jsp" method="POST" class="inline-form">
                    <input type="hidden" name="source" value="menu">
                    <input type="hidden" name="menuID" value="<%= menuItem.getMenuID() %>">
                    <input type="hidden" name="name" value="<%= menuItem.getName() %>">
                    <input type="hidden" name="price" value="<%= menuItem.getPrice() %>">
                    <input type="hidden" name="quantity" value="1">
                    <button type="submit" class="btn order-btn"> Order Now</button>
                </form>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <p class="no-items">No menu items available for this restaurant.</p>
        <%
            }
        %>
    </div>

    <script>
        function filterRestaurants() {
            const searchInput = document.getElementById('searchInput').value.toLowerCase();
            const menuItems = document.querySelectorAll('.menu-item');

            menuItems.forEach(item => {
                const itemName = item.getAttribute('data-name').toLowerCase();
                item.style.display = itemName.includes(searchInput) ? '' : 'none';
            });
        }
    </script>
    <button onclick="window.history.back()" style="position: fixed; bottom: 20px; left: 20px; padding: 10px 16px; background-color: #1e90ff; color: white; border: none; border-radius: 8px; cursor: pointer; font-size: 14px; z-index: 1000;"> Back</button>
    

</body>
</html>
