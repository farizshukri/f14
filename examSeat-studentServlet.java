package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/exam_seating";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rollNo = request.getParameter("rollNo");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT s.name, s.roll_no, se.seat_number " +
                         "FROM students s " +
                         "LEFT JOIN seats se ON s.student_id = se.student_id " +
                         "WHERE s.roll_no = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rollNo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                request.setAttribute("studentName", rs.getString("name"));
                request.setAttribute("seatNumber", rs.getString("seat_number"));
                request.getRequestDispatcher("student.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.jsp?error=notfound");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
