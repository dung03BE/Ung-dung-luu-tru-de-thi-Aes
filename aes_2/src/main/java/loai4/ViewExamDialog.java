/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loai4;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.swing.table.DefaultTableModel;/**
 *
 * @author dungi
 */
public class ViewExamDialog extends JDialog {
    private JComboBox<String> examComboBox;
    private JTextArea examDetailsArea;
    private SecretKey secretKey;
    private String maMonHoc;

    public ViewExamDialog(JFrame parent, SecretKey secretKey,String maMonHoc ) {
        super(parent, "View Exam", true);
        setLayout(new BorderLayout());

        this.secretKey = secretKey;
        this.maMonHoc = maMonHoc;
        examComboBox = new JComboBox<>();
        examDetailsArea = new JTextArea();
        examDetailsArea.setEditable(false);

        add(examComboBox, BorderLayout.NORTH);
        add(new JScrollPane(examDetailsArea), BorderLayout.CENTER);

        loadExams();

        examComboBox.addActionListener(e -> displayExamDetails());

        setSize(600, 400);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void loadExams() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dldethi", "root", "root")) {
          
            String sql = "SELECT * FROM Exam where MaMonHoc=?";
            PreparedStatement pr = conn.prepareStatement(sql) ;
            pr.setString(1, maMonHoc);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ExamID");
                String title = rs.getString("Title");
                examComboBox.addItem(id + ": " + title);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private  void displayExamDetails() {
    	 JOptionPane.showMessageDialog(this, "displayExamDetails() is called."); // Thông báo
    	    // Check
    	    System.out.println("Dc goi");
    	    String selectedExam = (String) examComboBox.getSelectedItem();

    	    if (selectedExam == null) return;

    	    int examID = Integer.parseInt(selectedExam.split(":")[0]);

    	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dldethi", "root", "root")) {
    	        String sql = "SELECT q.QuestionID, q.Content as QuestionContent, a.Content as AnswerContent, a.isCorrect " +
    	                     "FROM Exam e " +
    	                     "JOIN ExamQuestion eq ON e.ExamID = eq.ExamID " +
    	                     "JOIN Question q ON eq.QuestionID = q.QuestionID " +
    	                     "JOIN Answer a ON q.QuestionID = a.QuestionID " +
    	                     "WHERE e.ExamID = ?";
    	        PreparedStatement pstmt = conn.prepareStatement(sql);
    	        pstmt.setInt(1, examID);
    	        ResultSet rs = pstmt.executeQuery();

    	        Map<Integer, String> questions = new LinkedHashMap<>();
    	        Map<Integer, java.util.List<String>> answers = new LinkedHashMap<>();

    	        while (rs.next()) {
    	            int questionID = rs.getInt("QuestionID");
    	            String question_content = rs.getString("QuestionContent");
    	            String questionContent = AESUtil.decrypt(question_content, secretKey);
    	            String answer_content = rs.getString("AnswerContent");
    	            String answerContent = AESUtil.decrypt(answer_content, secretKey);
    	            boolean isCorrect = rs.getBoolean("isCorrect");
    	            questions.putIfAbsent(questionID, questionContent);
    	            answers.computeIfAbsent(questionID, k -> new ArrayList<>())
    	                   .add("Answer: " + answerContent + " (Correct: " + isCorrect + ")");
    	        }

    	        StringBuilder details = new StringBuilder();
    	        for (Map.Entry<Integer, String> entry : questions.entrySet()) {
    	            details.append("Question: ").append(entry.getValue()).append("\n");
    	            java.util.List<String> answerList = answers.get(entry.getKey());
    	            if (answerList != null) {
    	                for (String answer : answerList) {
    	                    details.append(answer).append("\n");
    	                }
    	            }
    	            details.append("\n");
    	        }
    	        examDetailsArea.setFont(new java.awt.Font("Times New Roman", 1, 14));
    	        examDetailsArea.setText(details.toString());
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    }

}
