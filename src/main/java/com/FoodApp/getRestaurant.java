package com.FoodApp;

import com.FoodApp.DAO.RestaurantDAO;
import com.FoodApp.Impl.RestaurantDAOImpl;
import com.FoodApp.Model.RestaurantPojo;
import com.tap.foodAppServlet.pojo.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class getRestaurant extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private RestaurantDAO restaurantDAO = new RestaurantDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");  // Correct parameter name
        RestaurantDAOImpl restaurantDao = new RestaurantDAOImpl();
        List<RestaurantPojo> restaurants = null;

        try {
            
            // Get all restaurants
            restaurants = restaurantDao.getAllRestaurant();

            // If search query exists, filter restaurants based on name or cuisine
            if (searchQuery != null && !searchQuery.isEmpty()) {
                restaurants = restaurants.stream()
                        .filter(r -> r.getName().toLowerCase().contains(searchQuery.toLowerCase()) || 
                                     r.getCuisineType().toLowerCase().contains(searchQuery.toLowerCase()))
                        .collect(Collectors.toList());
            }

            // Set the filtered restaurant list to the request attribute
            request.setAttribute("restaurants", restaurants);

            // Forward to the JSP page to display the list of restaurants
           request.getRequestDispatcher("/home.jsp").forward(request, response);


        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while fetching restaurants.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } 
    }
}