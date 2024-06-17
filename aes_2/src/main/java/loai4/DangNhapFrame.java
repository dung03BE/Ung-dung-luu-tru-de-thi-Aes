package loai4;


import java.awt.Image;
import javax.swing.*;
import java.sql.*;

public class DangNhapFrame extends javax.swing.JFrame {

    private static final String URL = "jdbc:mysql://localhost:3306/dldethi";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private Connection connection;
    /**
     * Creates new form DangNhapFrame
     */
    public DangNhapFrame() {
        initComponents();
        createConnection();
        this.icon2.setVisible(false);
        
    }
    private void createConnection(){
            try {
            // Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Create connection
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database connection successful!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        MainPanel = new javax.swing.JPanel();
        Panel = new javax.swing.JPanel();
        Logo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        User = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        UserText = new javax.swing.JTextField();
        Password = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        PasswordText = new javax.swing.JPasswordField();
        icon1 = new javax.swing.JLabel();
        icon2 = new javax.swing.JLabel();
        LogIn = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        PanelSchoolName = new javax.swing.JPanel();
        SchoolName = new javax.swing.JLabel();
        jSeparator = new javax.swing.JSeparator();
        PanelError = new javax.swing.JPanel();
        ErrorMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng nhập");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.white);
        setMinimumSize(new java.awt.Dimension(500, 450));
        setSize(new java.awt.Dimension(500, 450));

        MainPanel.setBackground(new java.awt.Color(166, 166, 185));
        MainPanel.setLayout(new java.awt.GridBagLayout());

        Panel.setBackground(new java.awt.Color(255, 255, 255));
        Panel.setForeground(new java.awt.Color(255, 255, 255));
        Panel.setMinimumSize(new java.awt.Dimension(500, 450));

        Logo.setBackground(new java.awt.Color(255, 255, 255));
        Logo.setLayout(new javax.swing.BoxLayout(Logo, javax.swing.BoxLayout.Y_AXIS));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/logo-haui.png")).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
        jLabel1.setAlignmentX(0.5F);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setMaximumSize(new java.awt.Dimension(120, 120));
        jLabel1.setMinimumSize(new java.awt.Dimension(120, 120));
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 120));
        Logo.add(jLabel1);

        User.setBackground(new java.awt.Color(255, 255, 255));
        User.setMinimumSize(new java.awt.Dimension(420, 40));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Tên đăng nhập:");
        jLabel2.setMaximumSize(new java.awt.Dimension(120, 40));
        jLabel2.setMinimumSize(new java.awt.Dimension(120, 40));
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 40));

        UserText.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        UserText.setMinimumSize(new java.awt.Dimension(200, 40));
        UserText.setPreferredSize(new java.awt.Dimension(300, 40));

        javax.swing.GroupLayout UserLayout = new javax.swing.GroupLayout(User);
        User.setLayout(UserLayout);
        UserLayout.setHorizontalGroup(
            UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(UserText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        UserLayout.setVerticalGroup(
            UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(UserText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        Password.setBackground(new java.awt.Color(255, 255, 255));
        Password.setMinimumSize(new java.awt.Dimension(420, 40));
        Password.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Mật khẩu:");
        jLabel3.setMaximumSize(new java.awt.Dimension(120, 40));
        jLabel3.setMinimumSize(new java.awt.Dimension(120, 40));
        jLabel3.setPreferredSize(new java.awt.Dimension(120, 40));
        Password.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 0, 79, -1));

        PasswordText.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        PasswordText.setMinimumSize(new java.awt.Dimension(200, 40));
        PasswordText.setPreferredSize(new java.awt.Dimension(300, 40));
        Password.add(PasswordText, new org.netbeans.lib.awtextra.AbsoluteConstraints(172, 0, -1, -1));

        icon1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/screenshot_1703567883.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        icon1.setMaximumSize(new java.awt.Dimension(20, 20));
        icon1.setMinimumSize(new java.awt.Dimension(20, 20));
        icon1.setPreferredSize(new java.awt.Dimension(20, 20));
        icon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                icon1MousePressed(evt);
            }
        });
        Password.add(icon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, -1, 34));

        icon2.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Images/dong.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        icon2.setMaximumSize(new java.awt.Dimension(20, 20));
        icon2.setMinimumSize(new java.awt.Dimension(20, 20));
        icon2.setPreferredSize(new java.awt.Dimension(20, 20));
        icon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                icon2MousePressed(evt);
            }
        });
        Password.add(icon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, -1, 34));

        LogIn.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(0, 102, 255));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Đăng nhập");
        jButton1.setAlignmentX(0.5F);
        jButton1.setBorder(null);
        jButton1.setMaximumSize(new java.awt.Dimension(120, 40));
        jButton1.setMinimumSize(new java.awt.Dimension(120, 40));
        jButton1.setPreferredSize(new java.awt.Dimension(120, 40));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        LogIn.add(jButton1);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(40, 40));
        jPanel1.setPreferredSize(new java.awt.Dimension(40, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        LogIn.add(jPanel1);

        jButton2.setBackground(new java.awt.Color(0, 102, 255));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Reset");
        jButton2.setBorder(null);
        jButton2.setMaximumSize(new java.awt.Dimension(120, 40));
        jButton2.setMinimumSize(new java.awt.Dimension(120, 40));
        jButton2.setPreferredSize(new java.awt.Dimension(120, 40));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        LogIn.add(jButton2);

        PanelSchoolName.setBackground(new java.awt.Color(255, 255, 255));
        PanelSchoolName.setLayout(new javax.swing.BoxLayout(PanelSchoolName, javax.swing.BoxLayout.PAGE_AXIS));

        SchoolName.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        SchoolName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SchoolName.setText("Trường Đại học Công nghiệp Hà Nội");
        SchoolName.setAlignmentX(0.5F);
        SchoolName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SchoolName.setMaximumSize(new java.awt.Dimension(300, 50));
        SchoolName.setMinimumSize(new java.awt.Dimension(300, 50));
        SchoolName.setPreferredSize(new java.awt.Dimension(300, 50));
        PanelSchoolName.add(SchoolName);

        jSeparator.setMaximumSize(new java.awt.Dimension(350, 10));
        PanelSchoolName.add(jSeparator);

        PanelError.setBackground(new java.awt.Color(255, 255, 255));

        ErrorMessage.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        ErrorMessage.setForeground(new java.awt.Color(255, 51, 51));
        ErrorMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PanelError.add(ErrorMessage);

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LogIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelSchoolName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(User, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Logo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelSchoolName, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(User, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelError, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LogIn, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        MainPanel.add(Panel, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        String user = UserText.getText();
        String password = new String(PasswordText.getPassword());
        
        try {
          String sqlQuery = "select * from Account where Username = ? and Password = ?";
          PreparedStatement preStatement = connection.prepareStatement(sqlQuery);
          preStatement.setString(1, user);
          preStatement.setString(2, password);
              
              ResultSet resultSet = preStatement.executeQuery(); 
              //Kiểm tra xem có ít nhất một dòng trong kết quả không. Nếu có, đồng nghĩa với việc tìm thấy một bản ghi với tên đăng nhập và mật khẩu đã nhập, người dùng được xác thực.
              if (resultSet.next()) {
                  String MaGiangVien = resultSet.getString("MaGiangVien");
                  HomeFrame frame1 = new HomeFrame(MaGiangVien);
                  frame1.setVisible(true);
            
              }
              else {
                     ErrorMessage.setText("Tên đăng nhập hoặc mật khẩu không đúng");
              }
              String sqlQuery1 = "select * from Account where Username = ? and Password = ?";
           
            //Kiểm tra xem có ít nhất một dòng trong kết quả không. Nếu có, đồng nghĩa với việc tìm thấy một bản ghi với tên đăng nhập và mật khẩu đã nhập, người dùng được xác thực.
            	
                
           
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Lỗi truy vấn trong cơ sở dữ liệu");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void icon1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon1MousePressed
        // TODO add your handling code here:
        icon2.setVisible(true);
        icon1.setVisible(false);
        PasswordText.setEchoChar((char)0);
    }//GEN-LAST:event_icon1MousePressed

    private void icon2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon2MousePressed
        icon1.setVisible(true);
        icon2.setVisible(false);
        PasswordText.setEchoChar('*');
    }//GEN-LAST:event_icon2MousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         UserText.setText("");
         PasswordText.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DangNhapFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhapFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhapFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhapFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhapFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ErrorMessage;
    private javax.swing.JPanel LogIn;
    private javax.swing.JPanel Logo;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel Panel;
    private javax.swing.JPanel PanelError;
    private javax.swing.JPanel PanelSchoolName;
    private javax.swing.JPanel Password;
    private javax.swing.JPasswordField PasswordText;
    private javax.swing.JLabel SchoolName;
    private javax.swing.JPanel User;
    private javax.swing.JTextField UserText;
    private javax.swing.JLabel icon1;
    private javax.swing.JLabel icon2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator;
    // End of variables declaration//GEN-END:variables
    
}
