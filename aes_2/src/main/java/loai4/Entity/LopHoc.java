package loai4.Entity;


import java.util.Date;

public class LopHoc {
  
    private String MaMH;
    private String MaGV;
    
    
    public String getMaMH() {
        return MaMH;
    }
    public void setMaMH(String maMH) {
        this.MaMH = maMH;
    }
    
    public String getMaGV() {
        return MaGV;
    }
    public void setMaGV(String maGV) {
        this.MaGV = maGV;
    }
    
    public LopHoc(){};
    public LopHoc( String maGV, String maMH) {
      
        this.MaGV = maGV;
        this.MaMH = maMH;
    }
}
