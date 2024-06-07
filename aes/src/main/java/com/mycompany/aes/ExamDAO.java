/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author dungi
 */
public class ExamDAO {
    public static void saveExam(String exam) throws Exception {
        String encryptedExam = AESUtil.encrypt(exam);
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO exams (exam_data) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, encryptedExam);
            statement.executeUpdate();
        }
    }

    public static String loadExam(int id) throws Exception {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "SELECT exam_data FROM exams WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String encryptedExam = resultSet.getString("exam_data");
                return AESUtil.decrypt(encryptedExam);
            }
        }
        return null;
    }
}
