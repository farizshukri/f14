package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/traffic")
public class TrafficServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/traffic_control";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("view".equals(action)) {
            viewTraffic(request, response);
        } else if ("update".equals(action)) {
            updateTraffic(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    private void viewTraffic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT r.name AS road_name, t.position, t.status " +
                         "FROM traffic_lights t " +
                         "JOIN roads r ON t.road_id = r.road_id";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            request.setAttribute("trafficData", rs);
            request.getRequestDispatcher("viewTraffic.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTraffic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int lightId = Integer.parseInt(request.getParameter("lightId"));
        String status = request.getParameter("status");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE traffic_lights SET status = ? WHERE light_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setInt(2, lightId);
            pstmt.executeUpdate();
            
            response.sendRedirect("traffic?action=view");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
