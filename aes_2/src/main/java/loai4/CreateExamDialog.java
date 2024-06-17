package loai4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.SecretKey;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author dungi
 */
public class CreateExamDialog extends JDialog {

   
    private JTextField codeField, titleField, durationField, creatorIDField;
    private JTextArea questionField, answerField;
    private JCheckBox isCorrectCheckbox;
    private JButton addQuestionButton, addAnswerButton, saveExamButton;

    private JComboBox<String> existingQuestionsComboBox;

    private List<Question> questions;
    private SecretKey secretKey;
    private JLabel jLabel1;

    private Integer examID = null;
    private String maMonHoc;

    public CreateExamDialog(JFrame parent, SecretKey secretKey, Integer examID,String maMonHoc) {
    
      
       super(parent, "Create Exam", true);
        setLayout(new BorderLayout());
        
        this.secretKey = secretKey;
        this.examID = examID;
        this.maMonHoc =maMonHoc;
        questions = new ArrayList<>();
        
       
        
        JPanel inputPanel = new JPanel(new GridLayout(11, 2, 5, 10));
         inputPanel.setPreferredSize(new Dimension(800, 500));
         inputPanel.setBorder(new EmptyBorder(5,35,5,70));
         inputPanel.setBackground(new java.awt.Color(0,153,153));
        
         
        codeField = new JTextField();
        codeField.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        
        titleField = new JTextField();
        titleField.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        durationField = new JTextField();
        durationField.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        creatorIDField = new JTextField();
        creatorIDField.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        questionField = new JTextArea();
        questionField.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        answerField = new JTextArea();
        answerField.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        isCorrectCheckbox = new JCheckBox(" Correct");
  
        isCorrectCheckbox.setBackground(new java.awt.Color(0,153,153));
        if (examID == null) {
            
            JLabel x1 =  new JLabel("Code:");
            x1.setBorder(new EmptyBorder(10,10,10,10));
            x1.setFont(new java.awt.Font("Times New Roman", 1, 18));
            inputPanel.add(x1);
            inputPanel.add(codeField);
            //
            JLabel x2 =  new JLabel("Title:");
            x2.setBorder(new EmptyBorder(10,10,10,10));
            x2.setFont(new java.awt.Font("Times New Roman", 1, 18));
            inputPanel.add(x2);
            inputPanel.add(titleField);
            //
            JLabel x3 =  new JLabel("Duration:");
            x3.setBorder(new EmptyBorder(10,10,10,10));
            x3.setFont(new java.awt.Font("Times New Roman", 1, 18));
            inputPanel.add(x3);
            inputPanel.add(durationField);
        }

        JLabel x4 =  new JLabel("CreatorID:");
        x4.setBorder(new EmptyBorder(10,10,10,10));
        x4.setFont(new java.awt.Font("Times New Roman", 1, 18));
        inputPanel.add(x4);
        inputPanel.add(creatorIDField);
        
        JLabel x5 =  new JLabel("Question:");
        x5.setBorder(new EmptyBorder(10,10,10,10));
        x5.setFont(new java.awt.Font("Times New Roman", 1, 18));
        inputPanel.add(x5);
        inputPanel.add(new JScrollPane(questionField));
        
        JLabel x6 =  new JLabel("Answer:");
        x6.setBorder(new EmptyBorder(10,10,10,10));
        x6.setFont(new java.awt.Font("Times New Roman", 1, 18));
        inputPanel.add(x6);
        inputPanel.add(new JScrollPane(answerField));
        isCorrectCheckbox.setBorder(new EmptyBorder(10,10,10,10));
        isCorrectCheckbox.setFont(new java.awt.Font("Times New Roman", 1, 18));
        isCorrectCheckbox.setBackground(new java.awt.Color(0,153,153));
        inputPanel.add(isCorrectCheckbox);

        JLabel e = new JLabel("");
        e.setBorder(new EmptyBorder(0,0,0,0));
        inputPanel.add(e);
        existingQuestionsComboBox = new JComboBox<>();
        JLabel eq = new JLabel("Existing Questions:");
        eq.setBorder(new EmptyBorder(10,10,10,10));
        eq.setFont(new java.awt.Font("Times New Roman", 1, 18));
        eq.setBorder(new EmptyBorder(0,0,0,0));
        inputPanel.add(eq);
        inputPanel.add(existingQuestionsComboBox);
        
        addQuestionButton = new JButton("Add Question");
        addQuestionButton.setBackground(new java.awt.Color(0,204,255));
        addQuestionButton.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        addQuestionButton.setForeground(new java.awt.Color(255, 255, 255));
        addAnswerButton = new JButton("Add Answer");
        addAnswerButton.setBackground(new java.awt.Color(0,204,255));
        addAnswerButton.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        addAnswerButton.setForeground(new java.awt.Color(255, 255, 255));
        saveExamButton = new JButton("Save Exam");
        saveExamButton.setBackground(new java.awt.Color(0,204,255));
        saveExamButton.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        saveExamButton.setForeground(new java.awt.Color(255, 255, 255));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new java.awt.Color(204,210,225));
        buttonPanel.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        buttonPanel.setForeground(new java.awt.Color(204,210,225));
        
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

        if (examID != null) {
            loadExistingQuestions(examID);
        }
        else
        {
            System.out.println("EXAM NULL");
        }
    }

    private void addQuestion() {
        String content = questionField.getText();
        if (!content.isEmpty()) {
            questions.add(new Question(content));
            questionField.setText("");
            JOptionPane.showMessageDialog(this, "Question added successfully!");
            // Update existing questions combo box
            existingQuestionsComboBox.addItem(content);
        } else {
            JOptionPane.showMessageDialog(this, "Question content cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void addAnswer() {
        String selectedQuestion = (String) existingQuestionsComboBox.getSelectedItem();
        if (selectedQuestion == null) {
            JOptionPane.showMessageDialog(this, "Please select a question first.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String content = answerField.getText();
        boolean isCorrect = isCorrectCheckbox.isSelected();
        if (!content.isEmpty()) {
            int index = existingQuestionsComboBox.getSelectedIndex();
            questions.get(index).addAnswer(new Answer(content, isCorrect));
            answerField.setText("");
            isCorrectCheckbox.setSelected(false);
            JOptionPane.showMessageDialog(this, "Answer added successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Answer content cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void saveExam() {
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dldethi", "root", "root")) {
        conn.setAutoCommit(false);

        String maGiangVien = creatorIDField.getText();
        
        // Kiểm tra sự tồn tại của MaGiangVien trong bảng account
        String checkAccountSql = "SELECT COUNT(*) FROM account WHERE MaGiangVien = ?";
        try (PreparedStatement checkAccountStmt = conn.prepareStatement(checkAccountSql)) {
            checkAccountStmt.setString(1, maGiangVien);
            try (ResultSet rs = checkAccountStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) == 0) {
                      JOptionPane.showMessageDialog(this, "Mã giảng viên không tồn tại trong bảng account.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return; 
                }
                
            }
        }

        if (examID == null) {
            // Lưu đề thi mới
            String code = codeField.getText();
            String title = titleField.getText();
            int duration = Integer.parseInt(durationField.getText());

            String sqlExam = "INSERT INTO Exam (Code, Title, Duration, CreateDate, MaGiangVien, MaMonHoc) VALUES (?, ?, ?, NOW(), ?, ?)";
            PreparedStatement pstmtExam = conn.prepareStatement(sqlExam, Statement.RETURN_GENERATED_KEYS);
            pstmtExam.setString(1, code);
            pstmtExam.setString(2, title);
            pstmtExam.setInt(3, duration);
            pstmtExam.setString(4, maGiangVien);
            pstmtExam.setString(5, maMonHoc);
            pstmtExam.executeUpdate();

            ResultSet rsExam = pstmtExam.getGeneratedKeys();
            rsExam.next();
            examID = rsExam.getInt(1);
        }

        for (Question question : questions) {
            String encryptedQuestionContent = AESUtil.encrypt(question.getContent(), secretKey);
            String sqlQuestion = "INSERT INTO Question (Content, MaGiangVien, CreateDate) VALUES (?, ?, NOW())";
            PreparedStatement pstmtQuestion = conn.prepareStatement(sqlQuestion, Statement.RETURN_GENERATED_KEYS);
            pstmtQuestion.setString(1, encryptedQuestionContent);
            pstmtQuestion.setString(2, maGiangVien);
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
        JOptionPane.showMessageDialog(this, "Lưu đề thi thành công!");
        dispose();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lưu đề thi thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}

    private void loadExistingQuestions(int examID) {
    
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dldethi", "root", "root")) {
            String sql = "SELECT q.QuestionID, q.Content FROM Question q JOIN ExamQuestion eq ON q.QuestionID = eq.QuestionID WHERE eq.ExamID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, examID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int questionID = rs.getInt("QuestionID");
                String questionContent = AESUtil.decrypt(rs.getString("Content"), secretKey);
                existingQuestionsComboBox.addItem(questionID + ": " + questionContent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
// public void setMaMonHoc(String maMH) {
//        this.maMonHoc = maMH;
//    }
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