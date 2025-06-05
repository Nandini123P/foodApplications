package com.Servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Connector.ConnectorFactory;

public class CartImageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the menu ID from the request parameters
        String menuId = request.getParameter("menuID");

        if (menuId == null || menuId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Menu ID is required.");
            return;
        }

        try (Connection connection = ConnectorFactory.requestConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT Image FROM menu WHERE menuID = ?")) {

            // Set the menu ID in the query
            pstmt.setInt(1, Integer.parseInt(menuId));

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    byte[] imageData = rs.getBytes("Image");

                    if (imageData != null) {
                        // Set the content type for the image response
                        response.setContentType("image/jpeg"); // Change if your image format differs

                        // Write the image data to the response output stream
                        try (OutputStream out = response.getOutputStream()) {
                            out.write(imageData);
                        }
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image data not found.");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Menu item not found.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving the image.");
        }
    }
}
