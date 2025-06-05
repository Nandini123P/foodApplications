package com.Servlets;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.FoodApp.Impl.CartDAOImpl;
import com.FoodApp.Model.CartItem;

public class DeleteCartItemServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Retrieve the cart from session
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) req.getSession().getAttribute("cart");
        if (cart == null) {
            resp.sendRedirect("cart.jsp");
            return;
        }

        // Get the itemId from the request parameter
        int itemId = Integer.parseInt(req.getParameter("itemId"));

        // Remove the item from the cart using CartDAOImpl
        CartDAOImpl cartDao = new CartDAOImpl();
        cartDao.getItems().putAll(cart); // Copy session cart to DAO
        cartDao.removeItem(itemId); // Remove item using DAO method

        // Update the session cart with the updated items
        cart = cartDao.getItems();
        req.getSession().setAttribute("cart", cart);

        // Redirect back to the cart page
        resp.sendRedirect("cart.jsp");
    }
}

