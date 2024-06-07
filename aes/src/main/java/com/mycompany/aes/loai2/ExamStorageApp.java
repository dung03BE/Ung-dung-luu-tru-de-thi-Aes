/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aes.loai2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author dungi
 */
public class ExamStorageApp {
   private JTextField titleField;
    private JTextField descriptionField;
    private JTextArea examDataField;
    private JTextField examIdField;
    private JButton saveButton;
    private JButton loadButton;
    private JTextArea resultArea;

    public ExamStorageApp() {
        JFrame frame = new JFrame("Exam Storage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel panel = new JPanel();
        titleField = new JTextField(20);
        descriptionField = new JTextField(20);
        examDataField = new JTextArea(5, 30);
        examIdField = new JTextField(5);
        saveButton = new JButton("Save Exam");
        loadButton = new JButton("Load Exam");
        resultArea = new JTextArea(10, 30);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);

        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);
        panel.add(new JLabel("Exam Data:"));
        panel.add(new JScrollPane(examDataField));
        panel.add(saveButton);
        panel.add(new JLabel("Exam ID:"));
        panel.add(examIdField);
        panel.add(loadButton);
        panel.add(new JScrollPane(resultArea));

        frame.add(panel);
        frame.setVisible(true);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String description = descriptionField.getText();
                String examData = examDataField.getText();
                try {
                    ExamDAO.saveExam(title, description, examData);
                    resultArea.setText("Exam saved successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    resultArea.setText("Error saving exam: " + ex.getMessage());
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int examId = Integer.parseInt(examIdField.getText());
                    String examData = ExamDAO.loadExam(examId);
                    if (examData != null) {
                        resultArea.setText(examData);
                    } else {
                        resultArea.setText("Exam not found.");
                    }
                } catch (NumberFormatException nfe) {
                    resultArea.setText("Invalid exam ID format.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    resultArea.setText("Error loading exam: " + ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        new ExamStorageApp();
    }
}
