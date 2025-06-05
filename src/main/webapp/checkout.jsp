<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.FoodApp.Model.CartItem" %>
<%@ page import="com.tap.foodAppServlet.pojo.user" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout</title>
    
    <!-- Font Awesome CDN for Icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">

<style>
    /* Reset and base */
    * {
        box-sizing: border-box;
    }
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
        font-size: 1.4rem;
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

    .container {
        background: #fff;
        max-width: 480px;
        width: 90%;
        border-radius: 12px;
        box-shadow: 0 12px 25px rgba(30,144,255,0.15);
        padding: 30px 35px;
        display: flex;
        flex-direction: column;
        gap: 22px;
    }

h2 {
    text-align: center;
    color: #1e90ff;
    font-weight: 700;
    margin-bottom: 10px;
    letter-spacing: 1.2px;
}

form {
    display: flex;
    flex-direction: column;
    gap: 18px;
}

label {
    font-weight: 600;
    font-size: 1.05rem;
    color: #333;
    margin-bottom: 6px;
}

/* Updated input styling for text, number, and readonly */
input[type="text"],
input[type="number"],
input[readonly],
textarea,
select {
    border: 2px solid #a3bffa;
    border-radius: 8px;
    padding: 12px 14px;
    font-size: 1rem;
    transition: border-color 0.3s ease;
    font-family: inherit;
    resize: vertical;
    color: #222;
    background-color: #fff;
}

/* Focus effect for all types */
input[type="text"]:focus,
input[type="number"]:focus,
input[readonly]:focus,
textarea:focus,
select:focus {
    outline: none;
    border-color: #1e90ff;
    box-shadow: 0 0 8px rgba(30, 144, 255, 0.3);
}

textarea {
    min-height: 80px;
}

button[type="submit"] {
    background: #1e90ff;
    color: #fff;
    border: none;
    border-radius: 25px;
    padding: 15px 0;
    font-size: 1.15rem;
    font-weight: 700;
    cursor: pointer;
    letter-spacing: 1.1px;
    transition: background-color 0.3s ease, box-shadow 0.3s ease;
    box-shadow: 0 6px 15px rgba(30, 144, 255, 0.35);
}

button[type="submit"]:hover {
    background: #176fca;
    box-shadow: 0 8px 22px rgba(23, 111, 202, 0.55);
}

.back-button {
    display: flex;
    justify-content: center;
    margin-top: 15px;
}

.back-button button {
    background: #6c757d;
    color: #fff;
    border: none;
    padding: 12px 30px;
    border-radius: 25px;
    font-size: 1rem;
    font-weight: 600;
    cursor: pointer;
    transition: background-color 0.3s ease, transform 0.25s ease;
    box-shadow: 0 3px 10px rgba(108, 117, 125, 0.4);
}

.back-button button:hover {
    background: #545b62;
    transform: scale(1.05);
}

/* Responsive layout for small screens */
@media (max-width: 500px) {
    .container {
        padding: 25px 20px;
    }

    .navbar {
        padding: 12px 20px;
    }
}



    /* Dark Mode Styles */
    .dark-mode {
        background: linear-gradient(135deg, #121212, #1e1e1e);
        color: #f5f5f5;
    }
    .dark-mode .navbar {
        background: #333;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.7);
    }
    .dark-mode .logo,
    .dark-mode .icon-link,
    .dark-mode .username {
        color: #fff;
    }
    .dark-mode .container {
        background: #1c1c1c;
        box-shadow: 0 12px 25px rgba(0, 0, 0, 0.5);
    }
    .dark-mode input,
    .dark-mode textarea,
    .dark-mode select {
        background-color: #2c2c2c;
        color: #f5f5f5;
        border-color: #555;
    }
    .dark-mode input:focus,
    .dark-mode textarea:focus,
    .dark-mode select:focus {
        border-color: #1e90ff;
        box-shadow: 0 0 8px rgba(30,144,255,0.5);
    }
    .dark-mode button[type="submit"] {
        background: #1e90ff;
        color: #fff;
    }
    .dark-mode .back-button button {
        background: #555;
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
                <div>
                    <% user loggedInUser = (user) session.getAttribute("loggedInUser");
                       if (loggedInUser == null) {
                           response.sendRedirect("login.jsp");
                           return;
                       } %>
                     Welcome 👋, <%= loggedInUser.getU_name() %>
                </div>
            
        </div>
    </div>
    <!-- Checkout Form Section -->
    <div class="container">
        <h2>Checkout</h2>

        <form action="CheckoutServlet" method="post">
            <!-- Address -->
            <label for="address">Delivery Address:</label>
            <textarea id="address" name="address" rows="4" required></textarea>

            <!-- Payment Method -->
            <label for="paymentMode">Payment Method:</label>
            <select id="paymentMode" name="paymentMode" required>
                <option value="UPI">UPI</option>
                <option value="Credit Card">Credit Card</option>
                <option value="Debit Card">Debit Card</option>
                <option value="Cash on Delivery">Cash on Delivery</option>
            </select>

            <!-- Grand Total Calculation -->
            <%
                // Get parameters from URL (passed from menu.jsp)
                String itemName = request.getParameter("name");
                String itemPriceStr = request.getParameter("price");
                String quantityStr = request.getParameter("quantity");

                // Default values if parameters are missing
                double itemPrice = 0;
                int quantity = 1;

                if (itemPriceStr != null && !itemPriceStr.isEmpty()) {
                    itemPrice = Double.parseDouble(itemPriceStr);
                }

                if (quantityStr != null && !quantityStr.isEmpty()) {
                    quantity = Integer.parseInt(quantityStr);
                }

                double grandTotal = itemPrice * quantity;
            %>

            <!-- Display Ordered Item Details -->
            <label for="orderedItem">Ordered Item:</label>
            <input type="text" id="orderedItem" name="orderedItem" readonly value="<%= itemName %>">

            <label for="price">Price per Item:</label>
            <input type="text" id="price" name="price" readonly value="Rs: <%= itemPrice %>">

            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" value="<%= quantity %>" min="1" oninput="updateTotal()">

            <label for="grandTotal">Total Amount:</label>
            <input type="number" id="grandTotal" name="grandTotal" value=" Rs: <%= grandTotal %>" readonly >

            <!-- Separate display for currency symbol -->

            <script>
                // Update the total whenever quantity changes
                function updateTotal() {
                    let price = <%= itemPrice %>; // Get the price from JSP
                    let quantity = document.getElementById("quantity").value;
                    
                    // Ensure quantity is at least 1
                    if (quantity < 1) {
                        quantity = 1;
                        document.getElementById("quantity").value = 1;
                    }

                    // Calculate new total price
                    let total = price * quantity;

                    // Update the numeric value in the grandTotal input field
                    document.getElementById("grandTotal").value = total.toFixed(2);

                    // Update the currency display with Rs: prefix
                    document.getElementById("currencyDisplay").textContent = "Rs: " + total.toFixed(2);
                }

                // Initialize the total price calculation when the page loads
                window.onload = function() {
                    updateTotal();
                };
            </script>

            <!-- Place Order Button -->
            <button type="submit">Place Order</button>
            
            <!-- Back Button -->
            <div class="back-button">
                <button type="button" onclick="window.history.back()">Back to Cart</button>
            </div>
        </form>
    </div>
    <!-- Dark Mode Toggle Button -->
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
