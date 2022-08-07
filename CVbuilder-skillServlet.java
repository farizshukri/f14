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

@WebServlet("/addSkill")
public class SkillServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("user_id");

        String skillName = request.getParameter("skillName");
        String proficiencyLevel = request.getParameter("proficiencyLevel");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cv_builder", "root", "password");
            String sql = "INSERT INTO skills (user_id, skill_name, proficiency_level) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setString(2, skillName);
            pstmt.setString(3, proficiencyLevel);
            pstmt.executeUpdate();

            conn.close();
            response.sendRedirect("home.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
