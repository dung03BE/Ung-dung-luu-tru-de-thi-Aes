package loai4;


import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NewPanel extends JPanel{
    protected GroupLayout panelLayout;
    
    public NewPanel(String str) {
        setBackground(new java.awt.Color(255, 255, 255));
                        
        JLabel label = new JLabel(str);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.setBorder(new EmptyBorder(0, 24, 0, 24));
        label.setFont(new java.awt.Font("Segoe UI", 0, 14));
                        
        panelLayout = new javax.swing.GroupLayout(this);
        setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
        panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                monMousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                monMouseReleasedOrExit(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                monMouseReleasedOrExit(e);
            }
        });
    }
    
    private void monMousePressed(MouseEvent evt) {
        setBackground(new java.awt.Color(204, 255, 255));
    }
    
    private void monMouseReleasedOrExit(MouseEvent evt) {
        setBackground(new java.awt.Color(255, 255, 255));
    }
}
