package com.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.FoodApp.Impl.OrderDaoImpl;
import com.FoodApp.Impl.OrderItemDAOImpl;
import com.FoodApp.Model.CartItem;
import com.FoodApp.Model.Order;
import com.FoodApp.Model.OrderItemPojo;
import com.tap.foodAppServlet.pojo.user;

import java.util.Map;


public class CheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve session and check if user is logged in
        HttpSession session = request.getSession(false); // false = don't create a session if not already exists

        if (session == null || session.getAttribute("loggedInUser") == null) {
            // If the session is null or user is not logged in, redirect to login page
            response.sendRedirect("login.jsp");  // Redirect to login page
            return;
        }

        user loggedInUser = (user) session.getAttribute("loggedInUser");  // Get logged-in user
        int userId = loggedInUser.getU_id();
        String username = loggedInUser.getU_name();

        try {
            // Retrieve form data
            String address = request.getParameter("address");
            String paymentMode = request.getParameter("paymentMode");
            String grandTotalStr = request.getParameter("grandTotal");

            // Validate if the required data is present
            if (address == null || paymentMode == null || grandTotalStr == null || grandTotalStr.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid form input");
                return;
            }

            double grandTotal = Double.parseDouble(grandTotalStr);  // Convert grand total to double

            // Create and insert the order
            int restaurantId = 1; // You can modify this as needed
            Order order = new Order(0, userId, restaurantId, grandTotal, "Pending", paymentMode, address);
            OrderDaoImpl orderDao = new OrderDaoImpl();
            int orderId = orderDao.insert(order);

            if (orderId <= 0) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Order creation failed");
                return;
            }

            // Retrieve cart from session
            @SuppressWarnings("unchecked")
            Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");

            // Check if the cart is not empty
            if (cart != null && !cart.isEmpty()) {
                OrderItemDAOImpl orderItemDao = new OrderItemDAOImpl();
                for (CartItem item : cart.values()) {
                    // Create an order item and insert into the database
                    OrderItemPojo orderItem = new OrderItemPojo();
                    orderItem.setOrderId(orderId);
                    orderItem.setMenuId(item.getMenuID());
                    orderItem.setRestaurantId(item.getRestaurantID());
                    orderItem.setName(item.getName());
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setPrice(item.getPrice());

                    orderItemDao.insertOrderItem(orderItem);  // Insert order item into DB
                }
            }

            // Clear the cart from session after order is placed
            session.removeAttribute("cart");

            // Save order details into session
            session.setAttribute("orderId", orderId);
            session.setAttribute("UserId", userId);
            session.setAttribute("Username", username);
            session.setAttribute("orderAddress", address);
            session.setAttribute("paymentMode", paymentMode);
            session.setAttribute("grandTotal", grandTotal);

            // Redirect to order summary page
            response.sendRedirect("orderConfirmation.jsp");

        } catch (NumberFormatException e) {
            // Handle invalid number format for grandTotal
            System.err.println("Invalid grandTotal value: " + request.getParameter("grandTotal"));
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid grand total format");
        } catch (Exception e) {
            // Handle general exceptions
            e.printStackTrace();
            throw new ServletException("Checkout error", e);
        }
    }
}
