/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loai4;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.crypto.SecretKey;
import javax.swing.table.DefaultTableModel;/**
 *
 * @author dungi
 */
public class ViewExamDialog extends JDialog {
    private JComboBox<String> examComboBox;
    private JTextArea examDetailsArea;
    private SecretKey secretKey;

    public ViewExamDialog(JFrame parent, SecretKey secretKey) {
        super(parent, "View Exam", true);
        setLayout(new BorderLayout());

        this.secretKey = secretKey;

        examComboBox = new JComboBox<>();
        examDetailsArea = new JTextArea();
        examDetailsArea.setEditable(false);

        add(examComboBox, BorderLayout.NORTH);
        add(new JScrollPane(examDetailsArea), BorderLayout.CENTER);

        loadExams();

        examComboBox.addActionListener(e -> displayExamDetails());

        setSize(400, 300);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void loadExams() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dldethi", "root", "root")) {
            String sql = "SELECT ExamID, Title FROM Exam";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

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
    // Các dòng mã khác
        System.out.println("Dc goi");
        String selectedExam = (String) examComboBox.getSelectedItem();
        if (selectedExam == null) return;

        int examID = Integer.parseInt(selectedExam.split(":")[0]);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dldethi", "root", "root")) {
            String sql = "SELECT q.Content as QuestionContent, a.Content as AnswerContent, a.isCorrect " +
                         "FROM Exam e " +
                         "JOIN ExamQuestion eq ON e.ExamID = eq.ExamID  " +
                         "JOIN Question q ON eq.QuestionID = q.QuestionID " +
                         "JOIN Answer a ON q.QuestionID = a.QuestionID  " +
                         "WHERE e.ExamID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, examID);
            ResultSet rs = pstmt.executeQuery();

            StringBuilder details = new StringBuilder();
            while (rs.next()) {
                String question_content = rs.getString("QuestionContent");
                String questionContent = AESUtil.decrypt(question_content, secretKey);
                String answer_content = rs.getString("AnswerContent");
                String answerContent = AESUtil.decrypt(answer_content, secretKey);
                boolean isCorrect = rs.getBoolean("isCorrect");

                details.append("Question: ").append(questionContent).append("\n");
                details.append("Answer: ").append(answerContent).append(" (Correct: ").append(isCorrect).append(")\n\n");
            }
            examDetailsArea.setText(details.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
