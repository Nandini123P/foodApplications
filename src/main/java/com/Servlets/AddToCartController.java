package com.Servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FoodApp.Impl.CartDAOImpl;
import com.FoodApp.Impl.menuDAOImpl;
import com.FoodApp.Model.CartItem;
import com.FoodApp.Model.MenuPojo;

public class AddToCartController extends HttpServlet {

    private Map<Integer, CartItem> cart;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // Retrieve cart from session
        cart = (Map<Integer, CartItem>) req.getSession().getAttribute("cart");
        
        // Initialize cart if it is null
        if (cart == null) {
            cart = new HashMap<Integer, CartItem>();
        }

        // Retrieve menuID and quantity from request parameters
        String menuIDStr = req.getParameter("menuID");
        String quantityStr = req.getParameter("quantity");

        // Check if menuID or quantity is missing or empty
        if (menuIDStr == null || menuIDStr.isEmpty() || quantityStr == null || quantityStr.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "menuID or quantity is missing.");
            return;
        }

        try {
            // Parse menuID and quantity
            int menuID = Integer.parseInt(menuIDStr);
            int quantity = Integer.parseInt(quantityStr);

            // Fetch menu details
            menuDAOImpl mdaoi = new menuDAOImpl();
            MenuPojo m = mdaoi.getMenu(menuID);

            // Create CartItem object
            CartItem ci = new CartItem(menuID, m.getRestaurantID(), m.getName(), quantity, m.getPrice());

            // Check if the item already exists in the cart
            if (cart.containsKey(menuID)) {
                CartItem existingItem = cart.get(menuID);
                existingItem.setQuantity(existingItem.getQuantity() + quantity); // Update quantity
            } else {
                cart.put(menuID, ci); // Add new item to the cart
            }

            // Update session with the cart
            req.getSession().setAttribute("cart", cart);

            // Redirect to the cart page
            resp.sendRedirect("cart.jsp");

        } catch (NumberFormatException e) {
            // Handle invalid number format
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format for menuID or quantity.");
        }
    }
}

