package com.student.manage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection conn;
    public void StudentDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_manage","root","8144");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (studentId, studentName, studentPhone, studentCity) VALUES (?, ?, ?, ?)";
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_manage","root","8144");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, student.getStudentId());
            stmt.setString(2, student.getStudentName());
            stmt.setString(3, student.getStudentCity());
            stmt.setString(4, student.getStudentPhone());
            stmt.executeUpdate();
            System.out.println("Student added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_manage","root","8144");
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Student(
                    rs.getInt("StudentId"),
                    rs.getString("StudentName"),
                    rs.getString("StudentCity"),
                    rs.getString("StudentPhone")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Student getStudentById(int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE StudentId = ?";
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_manage","root","8144");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Student(
                    rs.getInt("StudentId"),
                    rs.getString("StudentName"),
                    rs.getString("StudentCity"),
                    rs.getString("StudentPhone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET StudentName = ?, StudentCity = ?, StudentPhone = ? WHERE StudentId = ?";
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_manage","root","8144");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getStudentName());
            stmt.setString(2, student.getStudentCity());
            stmt.setString(3, student.getStudentPhone());
            stmt.setInt(4, student.getStudentId());
            stmt.executeUpdate();
            System.out.println("Student updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE StudentId = ?";
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_manage","root","8144");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Student deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
