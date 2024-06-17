package loai4;



import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MonPanel extends NewPanel {
    private String maMonHoc;
    private ExamApp examApp;
    private Boolean hienThiLop;
    private final Color borderColor = new java.awt.Color(110, 110, 110);
    
    public MonPanel(String tenMon) {
        super(tenMon);
        setBorder(javax.swing.BorderFactory.createLineBorder(borderColor, 2, true));
        hienThiLop = false;
        
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt) {
                monMouseClicked(evt);
            }
        });
    }

    public void setExamApp(ExamApp examApp) {
        this.examApp = examApp;
        examApp.setVisible(hienThiLop);
    }
    
    private void monMouseClicked(MouseEvent evt) {
        hienThiLop = true;
        examApp.loadExamData(maMonHoc);
        examApp.setMaMonHoc(maMonHoc);
        examApp.setVisible(hienThiLop);
    }
    
     public void setMaMonHoc(String maMH) {
        this.maMonHoc = maMH; 
    }
}
