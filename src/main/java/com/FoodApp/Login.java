package com.FoodApp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tap.foodAppServlet.userDAOImpl;
import com.tap.foodAppServlet.pojo.user;

public class Login extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get email and password from request parameters
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        // Create an instance of UserDAOImpl to interact with the database
        userDAOImpl udaoi = new userDAOImpl();
        
        // Fetch the user by email
        user u = udaoi.getUser(email);
        
        // Check if the user exists and the password matches
        if (u != null) {
            // Password verification
            if (u.getPassword().equals(password)) {
                // Create a new session or get the existing one
                HttpSession session = req.getSession();
                
                // Set the logged-in user as an attribute in the session
                session.setAttribute("loggedInUser", u);
                
                // Redirect the user to the getRestaurant servlet (or home page)
                resp.sendRedirect("GetRestaurant");
            } else {
                // Redirect to the password incorrect page if the password doesn't match
                resp.sendRedirect("passwordIncorrect.jsp");
            }
        } else {
            // Redirect to the invalid user page if no user is found with the provided email
            resp.sendRedirect("invalidUser.jsp");
        }
    }
}
