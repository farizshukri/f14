package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/exam_seating";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("addStudent".equals(action)) {
            addStudent(request, response);
        } else if ("allocateSeats".equals(action)) {
            allocateSeats(request, response);
        }
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String rollNo = request.getParameter("rollNo");
        String section = request.getParameter("section");
        String branch = request.getParameter("branch");
        int year = Integer.parseInt(request.getParameter("year"));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO students (name, roll_no, section, branch, year) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, rollNo);
            pstmt.setString(3, section);
            pstmt.setString(4, branch);
            pstmt.setInt(5, year);
            pstmt.executeUpdate();

            response.sendRedirect("admin.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void allocateSeats(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Fetch all students
            String sql = "SELECT student_id FROM students";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            int seatNumber = 1;
            while (rs.next()) {
                int studentId = rs.getInt("student_id");

                // Allocate seat
                String seatSql = "INSERT INTO seats (student_id, seat_number) VALUES (?, ?)";
                PreparedStatement seatPstmt = conn.prepareStatement(seatSql);
                seatPstmt.setInt(1, studentId);
                seatPstmt.setString(2, "S" + seatNumber++);
                seatPstmt.executeUpdate();
            }

            response.sendRedirect("admin.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
