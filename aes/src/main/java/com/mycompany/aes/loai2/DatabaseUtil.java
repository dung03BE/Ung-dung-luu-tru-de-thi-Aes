/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aes.loai2;

import static com.mycompany.aes.ExamDAO.loadExam;
import static com.mycompany.aes.loai2.ExamDAO.saveExam;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author dungi
 */
public class DatabaseUtil {
     private static final String URL = "jdbc:mysql://localhost:3306/exam_system";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static void main(String[] args) {
    try (Connection connection = DatabaseUtil.getConnection()) {
        // Xóa các bản ghi không hợp lệ
        String deleteQuery = "DELETE FROM exams";
        PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
        deleteStatement.executeUpdate();
        System.out.println("Deleted all existing exam records.");

        // Lưu đề thi mẫu
        saveExam("Math Exam", "Final exam for math course", "Math Exam Data: Questions and Answers");
        saveExam("History Exam", "Midterm exam for history course", "History Exam Data: Questions and Answers");

        // Tải đề thi mẫu
        String examData1 = loadExam(1);
        System.out.println("Loaded Exam Data 1: " + examData1);

        String examData2 = loadExam(2);
        System.out.println("Loaded Exam Data 2: " + examData2);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
