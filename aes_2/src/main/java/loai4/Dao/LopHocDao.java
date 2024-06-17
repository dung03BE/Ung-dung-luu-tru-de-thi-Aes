package loai4.Dao;


import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import loai4.Entity.LopHoc;

public class LopHocDao extends ConnectDB {
    public ArrayList<LopHoc> listClassRoom(String maGV, String maMH) {
        ArrayList<LopHoc> dslh = new ArrayList<>();
        String sqlLH = "select * from GiangVienMonHoc where MaGiangVien = ? and MaMonHoc = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sqlLH);
            pst.setString(1, maGV);
            pst.setString(2, maMH);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                LopHoc lopHoc = new LopHoc(
                        rs.getString(1),
                        rs.getString(2)
                );
                dslh.add(lopHoc);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        return dslh;
    }
}
