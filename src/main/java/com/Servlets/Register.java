
package com.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.foodAppServlet.userDAOImpl;
import com.tap.foodAppServlet.pojo.user;

public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        // Retrieve and encrypt form data
        String name = encrypt(req.getParameter("name"));
        String email = encrypt(req.getParameter("email"));
        String mobileStr = encrypt(req.getParameter("mobile"));
        String password = encrypt(req.getParameter("password"));
        String address = encrypt(req.getParameter("address"));

        

        int mobile;
        try {
            mobile = Integer.parseInt(decrypt(mobileStr)); // Decrypt mobile number before parsing
        } catch (NumberFormatException e) {
            resp.sendRedirect("error.jsp");
            return;
        }

        // Create user object with decrypted data
        user newUser = new user(decrypt(name), decrypt(email), mobile, decrypt(password), decrypt(address));

        // DAO operation
        userDAOImpl userDao = new userDAOImpl();
        int result = userDao.addUser(newUser);

        if (result > 0) {
            // Registration successful
            resp.sendRedirect("success.jsp?msg=User registered successfully");
        } else {
            // Registration failed
            resp.sendRedirect("error.jsp?msg=Registration failed. Please try again.");
        }
    }

    // Encryption method
    private String encrypt(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append((char) (str.charAt(i) + 2)); // Shift each character by 2
        }
        return sb.toString();
    }

    // Decryption method
    private String decrypt(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append((char) (str.charAt(i) - 2)); // Reverse shift each character by 2
        }
        return sb.toString();
    }
}


