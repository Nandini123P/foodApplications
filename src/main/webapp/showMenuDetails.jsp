<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.FoodApp.Model.MenuPojo" %> <!-- Import MenuPojo class from com.FoodApp.Model -->
<!DOCTYPE html>
<html>
<head>
    <title>Menu Details</title>
</head>
<body>
    <h1>Menu Details</h1>
    <%
        // Retrieve the menu object from request attributes
        MenuPojo menu = (MenuPojo) request.getAttribute("menu");

        // Check if the menu object is null
        if (menu != null) {
    %>
        <p><strong>Menu ID:</strong> <%= menu.getMenuID() %></p>
        <p><strong>Restaurant ID:</strong> <%= menu.getRestaurantID() %></p>
        <p><strong>Menu Name:</strong> <%= menu.getName() %></p>
        <p><strong>Description:</strong> <%= menu.getDescription() %></p>
        <p><strong>Price:</strong> <%= menu.getPrice() %></p>
        <p><strong>Rating:</strong> <%= menu.getRating() %></p>
        <p><strong>Availability:</strong> <%= menu.getIsAvailable() %></p>
        <p><strong>Image:</strong> <img src="<%= menu.getImage() %>" alt="<%= menu.getName() %>"></p>
    <%
        } else {
    %>
        <p>Menu not found.</p>
    <%
        }
    %>
</body>
</html>
