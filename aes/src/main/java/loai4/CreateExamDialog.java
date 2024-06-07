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

    public CreateExamDialog(JFrame parent,SecretKey secretKey) {
        

        super(parent, "Create Exam", true);
        setLayout(new GridLayout(10, 2));

        this.secretKey = secretKey;
        questions = new ArrayList<>();

        codeField = new JTextField();
        titleField = new JTextField();
        durationField = new JTextField();
        creatorIDField = new JTextField();
        questionField = new JTextArea();
        answerField = new JTextArea();
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
        add(new JScrollPane(questionField));
        add(new JLabel("Answer:"));
        add(new JScrollPane(answerField));
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

            // Lưu câu hỏi và câu trả lời
            for (Question question : questions) {
                String encryptedQuestionContent = AESUtil.encrypt(question.getContent(), secretKey);
                String sqlQuestion = "INSERT INTO Question (Content, CategoryID, TypeID, CreatorID, CreateDate) VALUES (?, NULL, NULL, ?, NOW())";
                PreparedStatement pstmtQuestion = conn.prepareStatement(sqlQuestion, Statement.RETURN_GENERATED_KEYS);
                pstmtQuestion.setString(1, encryptedQuestionContent);
                pstmtQuestion.setInt(2, creatorID);
               
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

                // Lưu câu trả lời
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
