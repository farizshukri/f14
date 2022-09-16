package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/seats")
public class SeatServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/exam_seating";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT s.name, s.roll_no, se.seat_number " +
                         "FROM students s " +
                         "LEFT JOIN seats se ON s.student_id = se.student_id";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            request.setAttribute("seats", rs);
            request.getRequestDispatcher("seats.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
