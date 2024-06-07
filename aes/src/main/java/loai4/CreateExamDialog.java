/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loai4;
import ABC.*;
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
import javax.crypto.SecretKey;
/**
 *
 * @author dungi
 */
public class CreateExamDialog extends JDialog {

     private JTextField codeField, titleField, durationField, creatorIDField;
    private JTextArea questionField, answerField;
    private JCheckBox isCorrectCheckbox;
    private JButton addQuestionButton, addAnswerButton, saveExamButton;

    private List<Question> questions;
    private SecretKey secretKey;
    private JLabel jLabel1;

    private Integer examID = null;

    public CreateExamDialog(JFrame parent, SecretKey secretKey, Integer examID) {
        super(parent, "Create Exam", true);
        setLayout(new BorderLayout());

        this.secretKey = secretKey;
        this.examID = examID;
        questions = new ArrayList<>();

        JPanel inputPanel = new JPanel(new GridLayout(10, 2, 5, 5));

        codeField = new JTextField();
        titleField = new JTextField();
        durationField = new JTextField();
        creatorIDField = new JTextField();
        questionField = new JTextArea();
        answerField = new JTextArea();
        isCorrectCheckbox = new JCheckBox("Correct");

        if (examID == null) {
            inputPanel.add(new JLabel("Code:"));
            inputPanel.add(codeField);
            inputPanel.add(new JLabel("Title:"));
            inputPanel.add(titleField);
            inputPanel.add(new JLabel("Duration:"));
            inputPanel.add(durationField);
        }
        inputPanel.add(new JLabel("CreatorID:"));
        inputPanel.add(creatorIDField);
        inputPanel.add(new JLabel("Question:"));
        inputPanel.add(new JScrollPane(questionField));
        inputPanel.add(new JLabel("Answer:"));
        inputPanel.add(new JScrollPane(answerField));
        inputPanel.add(isCorrectCheckbox);

        addQuestionButton = new JButton("Add Question");
        addAnswerButton = new JButton("Add Answer");
        saveExamButton = new JButton("Save Exam");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addQuestionButton);
        buttonPanel.add(addAnswerButton);
        buttonPanel.add(saveExamButton);

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

        jLabel1 = new JLabel(examID == null ? "Thêm mới đề thi" : "Thêm câu hỏi vào đề thi");
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setFont(new Font("Serif", Font.BOLD, 24));

        add(jLabel1, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void addQuestion() {
        String content = questionField.getText();
        if (!content.isEmpty()) {
            questions.add(new Question(content));
            questionField.setText("");
            JOptionPane.showMessageDialog(this, "Question added successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Question content cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void addAnswer() {
        if (!questions.isEmpty()) {
            String content = answerField.getText();
            boolean isCorrect = isCorrectCheckbox.isSelected();
            if (!content.isEmpty()) {
                questions.get(questions.size() - 1).addAnswer(new Answer(content, isCorrect));
                answerField.setText("");
                isCorrectCheckbox.setSelected(false);
                JOptionPane.showMessageDialog(this, "Answer added successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Answer content cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please add a question first.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void saveExam() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dldethi", "root", "root")) {
            conn.setAutoCommit(false);

            if (examID == null) {
                // Save new exam
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
                examID = rsExam.getInt(1);
            }

            for (Question question : questions) {
                String encryptedQuestionContent = AESUtil.encrypt(question.getContent(), secretKey);
                String sqlQuestion = "INSERT INTO Question (Content, CategoryID, TypeID, CreatorID, CreateDate) VALUES (?, NULL, NULL, ?, NOW())";
                PreparedStatement pstmtQuestion = conn.prepareStatement(sqlQuestion, Statement.RETURN_GENERATED_KEYS);
                pstmtQuestion.setString(1, encryptedQuestionContent);
                pstmtQuestion.setInt(2, Integer.parseInt(creatorIDField.getText()));
                pstmtQuestion.executeUpdate();

                ResultSet rsQuestion = pstmtQuestion.getGeneratedKeys();
                rsQuestion.next();
                int questionID = rsQuestion.getInt(1);

                String sqlExamQuestion = "INSERT INTO ExamQuestion (ExamID, QuestionID) VALUES (?, ?)";
                PreparedStatement pstmtExamQuestion = conn.prepareStatement(sqlExamQuestion);
                pstmtExamQuestion.setInt(1, examID);
                pstmtExamQuestion.setInt(2, questionID);
                pstmtExamQuestion.executeUpdate();

                for (Answer answer : question.getAnswers()) {
                    String encryptedAnswerContent = AESUtil.encrypt(answer.getContent(), secretKey);
                    String sqlAnswer = "INSERT INTO Answer (Content, QuestionID, isCorrect) VALUES (?, ?, ?)";
                    PreparedStatement pstmtAnswer = conn.prepareStatement(sqlAnswer);
                    pstmtAnswer.setString(1, encryptedAnswerContent);
                    pstmtAnswer.setInt(2, questionID);
                    pstmtAnswer.setBoolean(3, answer.isCorrect());
                    pstmtAnswer.executeUpdate();
                }
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
        private final List<Answer> answers;

        public Question(String content) {
            this.content = content;
            this.answers = new ArrayList<>();
        }

        public String getContent() {
            return content;
        }

        public void addAnswer(Answer answer) {
            this.answers.add(answer);
        }

        public List<Answer> getAnswers() {
            return answers;
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
