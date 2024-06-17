package loai4.Dao;


import java.sql.*;

import loai4.Entity.GiangVien;

public class GiangVienDao extends ConnectDB {
    public GiangVien inforTeacher(String MaGV) {
        GiangVien gv = new GiangVien();
        String sqlGV = "select * from GiangVien where MaGiangVien = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sqlGV);
            pst.setString(1, MaGV);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                
                gv.setTenGV(rs.getString(2));
                gv.setSDT(rs.getString(3));
                gv.setKhoa(rs.getString(4));
                
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return gv;
    }
}
