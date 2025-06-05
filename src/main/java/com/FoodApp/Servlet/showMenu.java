package com.FoodApp.Servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.FoodApp.Impl.menuDAOImpl;
import com.FoodApp.Model.MenuPojo;
import java.util.List;

public class showMenu extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String restaurantIDString = request.getParameter("RestaurantId");
        String restaurantName = request.getParameter("RestaurantName");

        if (restaurantIDString != null && !restaurantIDString.isEmpty()) {
            try {
                int restaurantID = Integer.parseInt(restaurantIDString);
                request.setAttribute("restaurantID", restaurantID);

                menuDAOImpl menuDAO = new menuDAOImpl();
                List<MenuPojo> menuList = menuDAO.getMenuByRestaurantID(restaurantID);

                if (menuList != null && !menuList.isEmpty()) {
                    request.setAttribute("menuList", menuList);
                    request.setAttribute("restaurantName", restaurantName);
                    
                    request.getRequestDispatcher("/menu.jsp").forward(request, response);
                } else {
                    response.getWriter().write("No menus found for the selected restaurant.");
                }

            } catch (NumberFormatException e) {
                response.getWriter().write("Error: Invalid restaurantID format.");
            }
        } else {
            response.getWriter().write("Error: restaurantID is missing.");
            
        }
    }
}


