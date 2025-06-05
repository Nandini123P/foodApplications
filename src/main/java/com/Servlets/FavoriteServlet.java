package com.Servlets;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.FoodApp.Impl.FavoriteDAOImpl;
import com.FoodApp.Model.FavoritePojo;
import com.tap.foodAppServlet.pojo.user;


public class FavoriteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String restaurantIdStr = request.getParameter("restaurantId");
        if (restaurantIdStr == null || restaurantIdStr.trim().isEmpty()) {
            System.out.println("Error: restaurantId is missing or empty.");
            response.sendRedirect("error.jsp");
            return;
        }

        int restaurantId = Integer.parseInt(restaurantIdStr);
        user loggedInUser = (user) session.getAttribute("loggedInUser");

        FavoritePojo favorite = new FavoritePojo(loggedInUser.getU_id(), restaurantId);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oct_jdbc", "root", "root")) {
            FavoriteDAOImpl dao = new FavoriteDAOImpl(conn);
            if (dao.addFavorite(favorite)) {
                response.sendRedirect("favorites.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
