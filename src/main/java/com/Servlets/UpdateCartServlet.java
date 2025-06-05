package com.Servlets;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.FoodApp.Impl.CartDAOImpl;
import com.FoodApp.Model.CartItem;

public class UpdateCartServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // Retrieve cart from session
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) req.getSession().getAttribute("cart");
        if (cart == null) {
            resp.sendRedirect("cart.jsp");
            return;
        }
        
        // Fetch itemId, quantity, and action from request
        int itemId = Integer.parseInt(req.getParameter("itemId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String action = req.getParameter("action");

        // Handle increase or decrease actions
        if (action != null) {
            CartItem item = cart.get(itemId);
            if (item != null) {
                if ("increase".equals(action)) {
                    item.setQuantity(item.getQuantity() + 1);  // Increase quantity by 1
                } else if ("decrease".equals(action) && item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);  // Decrease quantity by 1, but not below 1
                }
            }
        } else {
            // Direct update of the quantity from form (i.e., if no action is provided)
            CartItem item = cart.get(itemId);
            if (item != null) {
                item.setQuantity(quantity);
            }
        }

        // Save updated cart back to session
        req.getSession().setAttribute("cart", cart);

        // Redirect back to cart.jsp
        resp.sendRedirect("cart.jsp");
    }
}

