package com.Servlets;

import com.FoodApp.Impl.FavoriteDAOImpl;
import com.tap.foodAppServlet.pojo.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class RemoveFavoriteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        user loggedInUser = (user) session.getAttribute("loggedInUser");

        int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oct_jdbc", "root", "root")) {
            FavoriteDAOImpl dao = new FavoriteDAOImpl(conn);
            dao.removeFavorite(loggedInUser.getU_id(), restaurantId);
            response.sendRedirect("favorites.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
