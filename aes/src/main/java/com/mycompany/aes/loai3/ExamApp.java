/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aes.loai3;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;
/**
 *
 * @author dungi
 */
public class ExamApp extends JFrame {
     private JTable table;
    private DefaultTableModel model;
    private Connection connection;

    public ExamApp() {
        super("Exam Management App");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        model = new DefaultTableModel();
        table = new JTable(model);

        // Add columns to the table model
        model.addColumn("Code");
        model.addColumn("Title");
        model.addColumn("Duration");
        model.addColumn("CreatorID");
        model.addColumn("CreateDate");

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Connect to the database
        connectToDatabase();

        // Populate the table with exam data
        loadExams();

        // Handle row selection event
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String examCode = (String) model.getValueAt(selectedRow, 0);
                    displayQuestions(examCode);
                }
            }
        });
    }

    private void connectToDatabase() {
        try {
            // Connect to your MySQL database
            String url = "jdbc:mysql://localhost:3306/dldethi";
            String user = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadExams() {
        try {
            // Execute a query to get exam data
            String query = "SELECT * FROM Exam";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Populate the table with exam data
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultSet.getString("Code"));
                row.add(resultSet.getString("Title"));
                row.add(String.valueOf(resultSet.getInt("Duration")));
                row.add(String.valueOf(resultSet.getInt("CreatorID")));
                row.add(resultSet.getString("CreateDate"));
                model.addRow(row);
            }

            // Close resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayQuestions(String examCode) {
        // Create a new frame to display questions
        JFrame questionFrame = new JFrame("Questions for Exam " + examCode);
        questionFrame.setSize(600, 400);
        questionFrame.setLocationRelativeTo(null);

        // Create a table to display questions
        DefaultTableModel questionModel = new DefaultTableModel();
        JTable questionTable = new JTable(questionModel);
        questionModel.addColumn("Question Content");

        // Add the table to a scroll pane
        JScrollPane questionScrollPane = new JScrollPane(questionTable);
        questionFrame.getContentPane().add(questionScrollPane, BorderLayout.CENTER);

        // Load questions for the selected exam
        try {
            String query = "SELECT Content, EncryptedContent FROM Question " +
                           "INNER JOIN ExamQuestion ON Question.QuestionID = ExamQuestion.QuestionID " +
                           "WHERE ExamID = (SELECT ExamID FROM Exam WHERE Code = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, examCode);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String content = decrypt(resultSet.getString("EncryptedContent"));
                questionModel.addRow(new Object[]{content});
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        questionFrame.setVisible(true);
    }

    private String decrypt(String encryptedContent) {
        // Implement AES decryption here
        // Return the decrypted content
        return encryptedContent;
    }

    public static void main(String[] args) {
        // Run the application
        SwingUtilities.invokeLater(() -> new ExamApp().setVisible(true));
    }
}
