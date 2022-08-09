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

@WebServlet("/generateResume")
public class ResumeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("user_id");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cv_builder", "root", "password");

            // Fetch user data
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String resumeContent = "Name: " + rs.getString("username") + "\n";
                
                // Add skills
                sql = "SELECT * FROM skills WHERE user_id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, userId);
                ResultSet skillRs = pstmt.executeQuery();
                resumeContent += "Skills:\n";
                while (skillRs.next()) {
                    resumeContent += "- " + skillRs.getString("skill_name") + " (" + skillRs.getString("proficiency_level") + ")\n";
                }
                
                // Add jobs
                sql = "SELECT * FROM jobs WHERE user_id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, userId);
                ResultSet jobRs = pstmt.executeQuery();
                resumeContent += "Experience:\n";
                while (jobRs.next()) {
                    resumeContent += "- " + jobRs.getString("job_title") + " at " + jobRs.getString("company_name") + " (" + jobRs.getDate("start_date") + " to " + jobRs.getDate("end_date") + ")\n";
                }

                // Save resume content to database
                sql = "INSERT INTO resumes (user_id, resume_template, content) VALUES (?, 'Basic', ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, userId);
                pstmt.setString(2, resumeContent);
                pstmt.executeUpdate();
                
                // Send resume content to user
                response.setContentType("text/plain");
                response.getWriter().write(resumeContent);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
