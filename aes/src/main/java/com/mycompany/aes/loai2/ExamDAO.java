/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aes.loai2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dungi
 */
public class ExamDAO {
  public static void saveExam(String title, String description, String examData) throws Exception {
        String encryptedExamData = AESUtil.encrypt(examData);
        System.out.println("Encrypted Exam Data: " + encryptedExamData); // In dữ liệu đã mã hóa
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO exams (title, description, exam_data) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, encryptedExamData);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static String loadExam(int examId) throws Exception {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "SELECT exam_data FROM exams WHERE exam_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, examId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String encryptedExamData = resultSet.getString("exam_data");
                System.out.println("Loaded Encrypted Exam Data: " + encryptedExamData); // In dữ liệu đã mã hóa
                return AESUtil.decrypt(encryptedExamData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }

    public static void main(String[] args) {
        try {
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
