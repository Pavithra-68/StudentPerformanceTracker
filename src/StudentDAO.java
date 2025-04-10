import java.sql.*;
import java.util.*;

public class StudentDAO {
    public void addStudent(Student student) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO students (name, roll_no, subject, marks) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, student.getName());
            ps.setString(2, student.getRollNo());
            ps.setString(3, student.getSubject());
            ps.setInt(4, student.getMarks());
            ps.executeUpdate();
            System.out.println("Student added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewAllStudents() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM students";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | Name: " + rs.getString("name") +
                        " | Roll No: " + rs.getString("roll_no") +
                        " | Subject: " + rs.getString("subject") +
                        " | Marks: " + rs.getInt("marks"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewStudentPerformance(String rollNo) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT subject, marks FROM students WHERE roll_no = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, rollNo);
            ResultSet rs = ps.executeQuery();

            int totalMarks = 0, count = 0;
            System.out.println("Subjects and Marks:");
            while (rs.next()) {
                System.out.println(rs.getString("subject") + ": " + rs.getInt("marks"));
                totalMarks += rs.getInt("marks");
                count++;
            }

            if (count > 0) {
                float avg = (float) totalMarks / count;
                System.out.println("Average Marks: " + avg);
                System.out.println("Grade: " + getGrade(avg));
            } else {
                System.out.println("No records found for Roll No: " + rollNo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getGrade(float avg) {
        if (avg >= 90) return "A+";
        else if (avg >= 80) return "A";
        else if (avg >= 70) return "B";
        else if (avg >= 60) return "C";
        else return "Fail";
    }

    public void updateMarks(int id, int newMarks) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE students SET marks = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, newMarks);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Updated successfully." : "Student ID not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM students WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Deleted successfully." : "Student ID not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
