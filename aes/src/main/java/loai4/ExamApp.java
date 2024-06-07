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
import java.sql.Statement;
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
    private JLabel jLabel1;
    

    public ExamApp() {
        setTitle("Exam Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Load or generate the secret key
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
        createExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateExamDialog(ExamApp.this, secretKey);
            }
        });
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createExamButton);
        JButton viewExamButton = new JButton("View Exam");
        viewExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewExamDialog(ExamApp.this, secretKey);
            }
        });
        buttonPanel.add(viewExamButton);

        getContentPane().add(new JScrollPane(examTable), BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

         
        jLabel1 = new JLabel("Danh sách đề thi");
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setFont(new Font("Serif", Font.BOLD, 24)); 

        // Add the JLabel to the JFrame
        getContentPane().add(jLabel1, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(examTable), BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        loadExamData();
    }

    private void loadExamData() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dldethi", "root", "root");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Exam")) {

            while (rs.next()) {
                Object[] row = {
                        rs.getString("Code"),
                        rs.getString("Title"),
                        rs.getInt("Duration"),
                        rs.getInt("CreatorID"),
                        rs.getTimestamp("CreateDate")
                };
                examTableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ExamApp app = new ExamApp();
            app.setVisible(true);
        });
    }
}