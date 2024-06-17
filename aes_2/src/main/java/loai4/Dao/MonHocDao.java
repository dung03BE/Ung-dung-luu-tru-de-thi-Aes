
package loai4.Dao;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import loai4.Entity.MonHoc;

public class MonHocDao extends ConnectDB {
    public ArrayList<MonHoc> listSubject(String maGV) {
        ArrayList<MonHoc> dsmh = new ArrayList<>();
        String sqlMH = "select distinct MaMonHoc from GiangVienMonHoc where MaGiangVien = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sqlMH);
            pst.setString(1, maGV);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maMH = rs.getString("MaMonHoc");
                PreparedStatement tmpStatement = conn.prepareStatement("select * from MonHoc where MaMonHoc = ?");
                tmpStatement.setString(1, maMH);
                ResultSet tmpResult = tmpStatement.executeQuery();
                tmpResult.next();
                MonHoc monHoc = new MonHoc(
                        tmpResult.getString(1),
                        tmpResult.getString(2),
                        tmpResult.getInt(3),
                        tmpResult.getString(4)
       
                );
                dsmh.add(monHoc);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        return dsmh;
    }
}
