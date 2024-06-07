    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aes;

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
   private JTextField examField;
    private JButton saveButton;
    private JButton loadButton;
    private JTextArea resultArea;
    private JTextField idField;

    public ExamStorageApp() {
        JFrame frame = new JFrame("Exam Storage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        JPanel panel = new JPanel();
        examField = new JTextField(20);
        saveButton = new JButton("Save Exam");
        loadButton = new JButton("Load Exam");
        resultArea = new JTextArea(10, 30);
        idField = new JTextField(5);
        
        panel.add(new JLabel("Exam:"));
        panel.add(examField);
        panel.add(saveButton);
        panel.add(new JLabel("Exam ID:"));
        panel.add(idField);
        panel.add(loadButton);
        panel.add(new JScrollPane(resultArea));
        
        frame.add(panel);
        frame.setVisible(true);
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String exam = examField.getText();
                try {
                    ExamDAO.saveExam(exam);
                    resultArea.setText("Exam saved successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    resultArea.setText("Error saving exam.");
                }
            }
        });
        
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String exam = ExamDAO.loadExam(id);
                    if (exam != null) {
                        resultArea.setText(exam);
                    } else {
                        resultArea.setText("Exam not found.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    resultArea.setText("Error loading exam.");
                }
            }
        });
    }
    
    public static void main(String[] args) {
        new ExamStorageApp();
    }
}
