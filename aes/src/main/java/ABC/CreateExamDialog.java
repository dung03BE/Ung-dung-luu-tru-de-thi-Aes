/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABC;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author dungi
 */
public class CreateExamDialog extends JDialog {
 private JTextField codeField, titleField, durationField, creatorIDField;
    private JTextField questionField, answerField;
    private JCheckBox isCorrectCheckbox;
    private JButton addQuestionButton, addAnswerButton, saveExamButton;

    private List<Question> questions;
    private List<Answer> answers;

    public CreateExamDialog(JFrame parent) {
        super(parent, "Create Exam", true);
        setLayout(new GridLayout(10, 2));

        questions = new ArrayList<>();
        answers = new ArrayList<>();

        codeField = new JTextField();
        titleField = new JTextField();
        durationField = new JTextField();
        creatorIDField = new JTextField();
        questionField = new JTextField();
        answerField = new JTextField();
        isCorrectCheckbox = new JCheckBox("Correct");

        add(new JLabel("Code:"));
        add(codeField);
        add(new JLabel("Title:"));
        add(titleField);
        add(new JLabel("Duration:"));
        add(durationField);
        add(new JLabel("CreatorID:"));
        add(creatorIDField);
        add(new JLabel("Question:"));
        add(questionField);
        add(new JLabel("Answer:"));
        add(answerField);
        add(isCorrectCheckbox);

        addQuestionButton = new JButton("Add Question");
        addAnswerButton = new JButton("Add Answer");
        saveExamButton = new JButton("Save Exam");

        add(addQuestionButton);
        add(addAnswerButton);
        add(saveExamButton);

        addQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addQuestion();
            }
        });

        addAnswerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAnswer();
            }
        });

        saveExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveExam();
            }
        });

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private int getCurrentExamID() {
        // Lấy ExamID của đề thi hiện tại từ cơ sở dữ liệu
        int examID = 0;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dldethi", "root", "root")) {
            String sql = "SELECT MAX(ExamID) FROM Exam";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                examID = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return examID;
    }

    private int getCurrentQuestionID() {
        // Lấy QuestionID của câu hỏi hiện tại từ cơ sở dữ liệu
        int questionID = 0;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dldethi", "root", "root")) {
            String sql = "SELECT MAX(QuestionID) FROM Question";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                questionID = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionID;
    }

    private void addQuestion() {
        String content = questionField.getText();
        if (!content.isEmpty()) {
            // Mã hóa nội dung câu hỏi
            String encryptedContent = AESUtil.encrypt(content);
            if (encryptedContent != null) {
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dldethi", "root", "root")) {
                    // Lưu câu hỏi đã được mã hóa
                    String sqlQuestion = "INSERT INTO Question (Content) VALUES (?)";
                    PreparedStatement pstmtQuestion = conn.prepareStatement(sqlQuestion, Statement.RETURN_GENERATED_KEYS);
                    pstmtQuestion.setString(1, encryptedContent);
                    pstmtQuestion.executeUpdate();

                    ResultSet rsQuestion = pstmtQuestion.getGeneratedKeys();
                    rsQuestion.next();
                    int questionID = rsQuestion.getInt(1);

                    // Lấy ExamID từ đề thi hiện tại
                    int examID = getCurrentExamID();

                    // Liên kết câu hỏi với đề thi
                    String sqlExamQuestion = "INSERT INTO ExamQuestion (ExamID, QuestionID) VALUES (?, ?)";
                    PreparedStatement pstmtExamQuestion = conn.prepareStatement(sqlExamQuestion);
                    pstmtExamQuestion.setInt(1, examID);
                    pstmtExamQuestion.setInt(2, questionID);
                    pstmtExamQuestion.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Question added successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Failed to save the question.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to encrypt question content.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Question content cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void addAnswer() {
        String content = answerField.getText();
        boolean isCorrect = isCorrectCheckbox.isSelected();

        if (!content.isEmpty()) {
            // Mã hóa nội dung câu trả lời
            String encryptedContent = AESUtil.encrypt(content);
            if (encryptedContent != null) {
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dldethi", "root", "root")) {
                    // Lưu câu trả lời đã được mã hóa
                    String sqlAnswer = "INSERT INTO Answer (Content, isCorrect) VALUES (?, ?)";
                    PreparedStatement pstmtAnswer = conn.prepareStatement(sqlAnswer, Statement.RETURN_GENERATED_KEYS);
                    pstmtAnswer.setString(1, encryptedContent);
                    pstmtAnswer.setBoolean(2, isCorrect);
                    pstmtAnswer.executeUpdate();

                    ResultSet rsAnswer = pstmtAnswer.getGeneratedKeys();
                    rsAnswer.next();
                    int answerID = rsAnswer.getInt(1);

                    // Lấy QuestionID từ câu hỏi hiện tại
                    int questionID = getCurrentQuestionID();

                    // Liên kết câu trả lời với câu hỏi
                    String sqlLinkAnswerToQuestion = "UPDATE Answer SET QuestionID = ? WHERE AnswerID = ?";
                    PreparedStatement pstmtLinkAnswerToQuestion = conn.prepareStatement(sqlLinkAnswerToQuestion);
                    pstmtLinkAnswerToQuestion.setInt(1, questionID);
                    pstmtLinkAnswerToQuestion.setInt(2, answerID);
                    pstmtLinkAnswerToQuestion.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Answer added successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Failed to save the answer.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to encrypt answer content.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Answer content cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void saveExam() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dldethi", "root", "root")) {
            conn.setAutoCommit(false);

            // Lưu đề thi
            String code = codeField.getText();
            String title = titleField.getText();
            int duration = Integer.parseInt(durationField.getText());
            int creatorID = Integer.parseInt(creatorIDField.getText());

            String sqlExam = "INSERT INTO Exam (Code, Title, Duration, CreatorID, CreateDate) VALUES (?, ?, ?, ?, NOW())";
            PreparedStatement pstmtExam = conn.prepareStatement(sqlExam, Statement.RETURN_GENERATED_KEYS);
            pstmtExam.setString(1, code);
            pstmtExam.setString(2, title);
            pstmtExam.setInt(3, duration);
            pstmtExam.setInt(4, creatorID);
            pstmtExam.executeUpdate();

            ResultSet rsExam = pstmtExam.getGeneratedKeys();
            rsExam.next();
            int examID = rsExam.getInt(1);

            // Lưu câu hỏi và liên kết với đề thi
            for (Question question : questions) {
                String content = question.getContent();
                String sqlQuestion = "INSERT INTO Question (Content) VALUES (?)";
                PreparedStatement pstmtQuestion = conn.prepareStatement(sqlQuestion, Statement.RETURN_GENERATED_KEYS);
                pstmtQuestion.setString(1, content);
                pstmtQuestion.executeUpdate();

                ResultSet rsQuestion = pstmtQuestion.getGeneratedKeys();
                rsQuestion.next();
                int questionID = rsQuestion.getInt(1);

                // Liên kết câu hỏi với đề thi
                String sqlExamQuestion = "INSERT INTO ExamQuestion (ExamID, QuestionID) VALUES (?, ?)";
                PreparedStatement pstmtExamQuestion = conn.prepareStatement(sqlExamQuestion);
                pstmtExamQuestion.setInt(1, examID);
                pstmtExamQuestion.setInt(2, questionID);
                pstmtExamQuestion.executeUpdate();
            }

            conn.commit();
            JOptionPane.showMessageDialog(this, "Exam saved successfully!");
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to save the exam.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static class Question {
        private final String content;

        public Question(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }

    private static class Answer {
        private final String content;
        private final boolean isCorrect;

        public Answer(String content, boolean isCorrect) {
            this.content = content;
            this.isCorrect = isCorrect;
        }

        public String getContent() {
            return content;
        }

        public boolean isCorrect() {
            return isCorrect;
        }
    }
}
