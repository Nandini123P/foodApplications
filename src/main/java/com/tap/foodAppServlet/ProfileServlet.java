package com.tap.foodAppServlet;

import com.tap.foodAppServlet.pojo.user;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ProfileServlet extends HttpServlet {
    private userDAO dao;

    @Override
    public void init() throws ServletException {
        // Initialize userDAO
        dao = new userDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the logged-in user's email from the session
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("userEmail");  // Assuming the user's email is stored in the session

        if (email != null) {
            // Fetch user details from the database using the email
            user loggedInUser = dao.getUser(email);

            if (loggedInUser != null) {
                // Set the user details as a request attribute
                request.setAttribute("user", loggedInUser);
                // Forward to profile.jsp to display user details
                RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
                dispatcher.forward(request, response);
            } else {
                response.getWriter().println("User not found.");
            }
        } else {
            response.getWriter().println("User not logged in.");
        }
    }
}

