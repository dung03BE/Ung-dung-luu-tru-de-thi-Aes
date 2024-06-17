package loai4;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;


public class TimKiemPanel extends JPanel{
    private final JLabel label;
    private final JTextField textField;
    public TimKiemPanel() {
        Border border = BorderFactory.createLineBorder(new Color(153, 153, 153), 2);

        label = new JLabel();
        label.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/search.png")).getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
        label.setPreferredSize(new Dimension(40, 18));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBorder(border);
        
        textField = new JTextField();
        textField.setBorder(border);
        textField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        textField.setSize(200, 20);
        
        setBackground(Color.white);
        
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        
        layout.setHorizontalGroup(
           layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(textField, GroupLayout.PREFERRED_SIZE, 200, Short.MAX_VALUE)
                    .addContainerGap()
        );
        
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addComponent(textField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    )
        );
    }

    public JTextField getTextField() {
        return textField;
    }
}
