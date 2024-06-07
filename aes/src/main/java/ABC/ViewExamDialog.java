/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABC;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.crypto.SecretKey;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author dungi
 */
public class ViewExamDialog extends JDialog {
//     private JTable questionTable;
//    private DefaultTableModel questionTableModel;
//
//    public ViewExamDialog(JFrame parent, int examID) {
//        super(parent, "View Exam", true);
//        setLayout(new BorderLayout());
//
//        String[] columnNames = {"Question", "Answer", "Correct"};
//        questionTableModel = new DefaultTableModel(columnNames, 0);
//        questionTable = new JTable(questionTableModel);
//
//        loadExamDetails(examID);
//
//        add(new JScrollPane(questionTable), BorderLayout.CENTER);
//        pack();
//        setLocationRelativeTo(parent);
//        setVisible(true);
//    }
//
//    private void loadExamDetails(int examID) {
//        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dldethi", "root", "root");
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT * FROM Question q JOIN Answer a ON q.QuestionID = a.QuestionID WHERE q.ExamID = " + examID)) {
//
//            while (rs.next()) {
//                String decryptedQuestion = AESUtil.decrypt(rs.getString("q.Content"));
//                String decryptedAnswer = AESUtil.decrypt(rs.getString("a.Content"));
//                boolean isCorrect = rs.getBoolean("a.isCorrect");
//
//                Object[] row = {decryptedQuestion, decryptedAnswer, isCorrect};
//                questionTableModel.addRow(row);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
private JTable questionTable;
    private DefaultTableModel questionTableModel;
    private JComboBox<String> examComboBox;

    public ViewExamDialog(JFrame parent) {
        super(parent, "View Exam", true);
        setLayout(new BorderLayout());

        // Create a combo box to display available exams
        examComboBox = new JComboBox<>();
        JPanel examPanel = new JPanel();
        examPanel.add(new JLabel("Select Exam: "));
        examPanel.add(examComboBox);
        add(examPanel, BorderLayout.NORTH);

        JButton loadButton = new JButton("Load Exam");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedExamID = getSelectedExamID();
                if (selectedExamID != -1) {
                    loadExamDetails(selectedExamID);
                }
            }
        });
        add(loadButton, BorderLayout.SOUTH);

        String[] columnNames = {"Question", "Answer", "Correct"};
        questionTableModel = new DefaultTableModel(columnNames, 0);
        questionTable = new JTable(questionTableModel);

        add(new JScrollPane(questionTable), BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);

        populateExamComboBox();
    }

    private void populateExamComboBox() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dldethi", "root", "root");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT ExamID, Title FROM Exam")) {

            while (rs.next()) {
                int examID = rs.getInt("ExamID");
                String title = rs.getString("Title");
                examComboBox.addItem(examID + ": " + title);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getSelectedExamID() {
        String selectedExam = (String) examComboBox.getSelectedItem();
        if (selectedExam != null) {
            String[] parts = selectedExam.split(": ");
            return Integer.parseInt(parts[0]);
        }
        return -1;
    }

    private void loadExamDetails(int examID) {
        // Clear existing rows
        questionTableModel.setRowCount(0);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dldethi", "root", "root");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Question q JOIN Answer a ON q.QuestionID = a.QuestionID WHERE q.ExamID = " + examID)) {

            while (rs.next()) {
                String decryptedQuestion = AESUtil.decrypt(rs.getString("q.Content"));
                String decryptedAnswer = AESUtil.decrypt(rs.getString("a.Content"));
                boolean isCorrect = rs.getBoolean("a.isCorrect");

                Object[] row = {decryptedQuestion, decryptedAnswer, isCorrect};
                questionTableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
