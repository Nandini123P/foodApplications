package com.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ClearCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Clear the cart in the session
        HttpSession session = request.getSession();
        session.removeAttribute("cart");
        
        // Redirect to the Cart page after clearing
        response.sendRedirect("cart.jsp");
    }
}

