package com.tap.foodAppServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.foodAppServlet.pojo.user;

@WebServlet("/login")
public class foodApp extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        // Create a mock user DAO for demonstration
        userDAOImpl udaoi = new userDAOImpl();
        user u = udaoi.getUser(email);
        
        if (u != null && u.getPassword().equals(password)) 
        {
            HttpSession session = req.getSession();
            session.setAttribute("loggedInUser", u);
            resp.sendRedirect("home.jsp");
            
        } 
        else if (u != null) 
        {
            resp.sendRedirect("passwordIncorrect.jsp");
        } 
        else 
        {
            resp.sendRedirect("invalidUser.jsp");
        }
    }
	
}
