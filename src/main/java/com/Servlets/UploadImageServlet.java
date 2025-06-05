package com.Servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.Connector.ConnectorFactory;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 5,  // 2MB
    maxFileSize = 1024 * 1024 * 20,       // 10MB
    maxRequestSize = 1024 * 1024 * 100     // 50MB
)
public class UploadImageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String menuId = request.getParameter("menuId");
        Part filePart = request.getPart("image");

        if (menuId != null && filePart != null) {
            try (InputStream inputStream = filePart.getInputStream();
                 Connection connection = ConnectorFactory.requestConnection();
                 PreparedStatement pstmt = connection.prepareStatement("UPDATE menu SET Image = ? WHERE menuID = ?")) {

                pstmt.setBlob(1, inputStream);
                pstmt.setString(2, menuId);
                int rowsUpdated = pstmt.executeUpdate();

                if (rowsUpdated > 0) {
                    response.getWriter().write("Image uploaded successfully!");
                } else {
                    response.getWriter().write("No menu item found with ID: " + menuId);
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.setContentType("text/plain");
                response.getWriter().write("Error saving image: " + e.getMessage());
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input.");
        }
    }

    // To support GET requests when accessing images
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String menuId = request.getParameter("menuId");

        if (menuId != null) {
            try (Connection conn = ConnectorFactory.requestConnection();
                 PreparedStatement stmt = conn.prepareStatement("SELECT Image FROM menu WHERE menuID = ?")) {

                stmt.setString(1, menuId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next() && rs.getBlob("Image") != null) {
                    response.setContentType("image/jpeg");
                    InputStream imgStream = rs.getBlob("Image").getBinaryStream();
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = imgStream.read(buffer)) != -1) {
                        response.getOutputStream().write(buffer, 0, bytesRead);
                    }
                    imgStream.close();
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found.");
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving image.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing menuId parameter.");
        }
    }
}
