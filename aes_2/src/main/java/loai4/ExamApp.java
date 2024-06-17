/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loai4;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author dungi
 */
public class ExamApp extends JFrame {
    
    private JTable examTable;
    private DefaultTableModel examTableModel;
    private SecretKey secretKey;
    private int examID;
    private String maMonHoc;
    private String maGiangVien;
    
    public ExamApp(String maGiangVien) {
        this.maGiangVien = maGiangVien;
    //    this.examID =examID;
        

        setTitle("Exam Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(157, 157, 238));
        setBackground(new java.awt.Color(157,157,238));
        Container contentPane = getContentPane();
        contentPane.setBackground(new java.awt.Color(157, 157, 238));
        
        try {
            secretKey = AESUtil.loadKey();
            if (secretKey == null) {
                secretKey = AESUtil.generateRandomKey();
                AESUtil.saveKey(secretKey);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] columnNames = {"Code", "Title", "Duration", "CreatorID", "CreateDate"};
        examTableModel = new DefaultTableModel(columnNames, 0);
        examTable = new JTable(examTableModel);

        JButton createExamButton = new JButton("Create Exam");
        createExamButton.setBackground(new java.awt.Color(0, 102, 255));
        createExamButton.setFont(new java.awt.Font("Times New Roman", 1, 16)); 
        createExamButton.setForeground(new java.awt.Color(255, 255, 255));
        createExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateExamDialog(ExamApp.this, secretKey,null,maMonHoc);
            }
        });

        JButton addQuestionButton = new JButton("Add Question to Existing Exam");
        addQuestionButton.setBackground(new java.awt.Color(0, 102, 255));
        addQuestionButton.setFont(new java.awt.Font("Times New Roman", 1, 16)); 
        addQuestionButton.setForeground(new java.awt.Color(255, 255, 255));
        addQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String examCode = JOptionPane.showInputDialog(ExamApp.this, "Enter Exam Code:");
                if (examCode != null && !examCode.trim().isEmpty()) {
                    Integer examID = getExamIDByCode(examCode.trim());
                    if (examID != null) {
                        new CreateExamDialog(ExamApp.this, secretKey, examID,maMonHoc);
                    } else {
                        JOptionPane.showMessageDialog(ExamApp.this, "Exam not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JButton deleteExamButton = new JButton("Delete Exam");
        deleteExamButton.setBackground(new java.awt.Color(0, 102, 255));
        deleteExamButton.setFont(new java.awt.Font("Times New Roman", 1, 16)); 
        deleteExamButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String examCode = JOptionPane.showInputDialog(ExamApp.this, "Enter Exam Code to Delete:");
                if (examCode != null && !examCode.trim().isEmpty()) {
                    Integer examID = getExamIDByCode(examCode.trim());
                    if (examID != null) {
                        deleteExam(examID);
                        loadExamData(maMonHoc);
                    } else {
                        JOptionPane.showMessageDialog(ExamApp.this, "Exam not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createExamButton);
        buttonPanel.add(addQuestionButton);
        buttonPanel.add(deleteExamButton);

        JButton viewExamButton = new JButton("View Exam");
        viewExamButton.setBackground(new java.awt.Color(0, 102, 255));
        viewExamButton.setFont(new java.awt.Font("Times New Roman", 1, 16)); 
        viewExamButton.setForeground(new java.awt.Color(255, 255, 255));
        viewExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewExamDialog(ExamApp.this, secretKey,maMonHoc);
            }
        });
        buttonPanel.add(viewExamButton);

        JLabel jLabel1 = new JLabel("Danh sách đề thi");
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setFont(new Font("Serif", Font.BOLD, 24));

        getContentPane().add(jLabel1, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(examTable), BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

       // loadExamData(maMonHoc);
    }

    public void loadExamData(String maMonHoc ) {
        examTableModel.setRowCount(0); 
        Connection conn;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dldethi", "root", "root");
                
            PreparedStatement pr = conn.prepareStatement("SELECT * FROM Exam where MaMonHoc=?") ;
            pr.setString(1, maMonHoc);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Object[] row = {
                        rs.getString("Code"),
                        rs.getString("Title"),
                        rs.getInt("Duration"),
                        rs.getInt("MaGiangVien"),
                        rs.getTimestamp("CreateDate")
                };
                examTableModel.addRow(row);
            }
            } catch (SQLException ex) {
                Logger.getLogger(ExamApp.class.getName()).log(Level.SEVERE, null, ex);
            }
             
               
//             ResultSet rs = stmt.executeQuery("SELECT * FROM Exam where creatorid=?")) {
            
        
    }

    private Integer getExamIDByCode(String code) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dldethi", "root", "root")) {
            String sql = "SELECT ExamID FROM Exam WHERE Code = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, code);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ExamID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
    private void printTableStructure() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dldethi", "root", "root");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SHOW COLUMNS FROM Exam")) {

            System.out.println("Table structure for 'Exam':");
            while (rs.next()) {
                System.out.println(rs.getString("Field") + " - " + rs.getString("Type"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteExam(int examID) {
    	  printTableStructure(); // Print the table structure before deleting

    	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dldethi", "root", "root")) {
    	        conn.setAutoCommit(false);

    	        String deleteAnswersSQL = "DELETE FROM Answer WHERE QuestionID IN (SELECT QuestionID FROM ExamQuestion WHERE ExamID = ?)";
    	        try (PreparedStatement deleteAnswersStmt = conn.prepareStatement(deleteAnswersSQL)) {
    	            deleteAnswersStmt.setInt(1, examID);
    	            int rowsDeleted = deleteAnswersStmt.executeUpdate();
    	            System.out.println("Rows deleted from Answer: " + rowsDeleted);
    	        }

    	        String deleteQuestionsSQL = "DELETE FROM Question where QuestionID in (SELECT QuestionID FROM ExamQuestion WHERE ExamID = ?)";
    	        try (PreparedStatement deleteQuestionsStmt = conn.prepareStatement(deleteQuestionsSQL)) {
    	            deleteQuestionsStmt.setInt(1, examID);
    	            int rowsDeleted = deleteQuestionsStmt.executeUpdate();
    	            System.out.println("Rows deleted from Question: " + rowsDeleted);
    	        }

    	        String deleteExamSQL = "DELETE FROM Exam WHERE ExamID = ?";
    	        try (PreparedStatement deleteExamStmt = conn.prepareStatement(deleteExamSQL)) {
    	            deleteExamStmt.setInt(1, examID);
    	            int rowsDeleted = deleteExamStmt.executeUpdate();
    	            System.out.println("Rows deleted from Exam: " + rowsDeleted);
    	        }

    	        conn.commit();
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    }
    public void setMaMonHoc(String maMH) {
        this.maMonHoc = maMH;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DangNhapFrame loginFrame = new DangNhapFrame();
            loginFrame.setVisible(true);
        });
    }
    
}