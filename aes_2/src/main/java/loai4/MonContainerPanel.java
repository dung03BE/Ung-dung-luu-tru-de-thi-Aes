package loai4;



import java.util.ArrayList;
import javax.swing.*;

public final class MonContainerPanel extends JPanel{
    private final ArrayList<JPanel> list;
    
    public MonContainerPanel(ArrayList<JPanel> list) {
        this.list = list;
        display();
    }
    
    public void display() {
        setBackground(new java.awt.Color(255, 255, 255));
        javax.swing.GroupLayout monContainerPanelLayout = new javax.swing.GroupLayout(this);
        setLayout(monContainerPanelLayout);
                
        GroupLayout.SequentialGroup SGroup = monContainerPanelLayout.createSequentialGroup();
        SGroup.addContainerGap()
              .addComponent(list.get(0), javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE);
        
        GroupLayout.ParallelGroup PGroup = monContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        PGroup.addComponent(list.get(0), javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE);
       
        monContainerPanelLayout.setHorizontalGroup(
            monContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SGroup)
        );
        monContainerPanelLayout.setVerticalGroup(
            monContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(monContainerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PGroup)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
}
