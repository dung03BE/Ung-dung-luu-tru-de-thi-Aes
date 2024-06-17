package loai4.Entity;


public class MonHoc {
    private String MaMH;
    private String TenMH;
    private int SoTin;
    private String Khoa;
    
    
    public String getMaMH() {
        return MaMH;
    }
    public void setMaMH(String maMH) {
        this.MaMH = maMH;
    }
    
    public String getTenMH() {
        return TenMH;
    }
    public void setTenMH(String tenMH) {
        this.TenMH = tenMH;
    }
    
    public int getSoTin() {
        return SoTin;
    }
    public void setSoTin(int soTin) {
        this.SoTin = soTin;
    }
    
    public String getKhoa() {
        return Khoa;
    }
    public void setKhoa(String khoa) {
        this.Khoa = khoa;
    }
    
   
    public MonHoc(){};
    public MonHoc(String maMH, String tenMH, int soTin, String khoa) {
        this.MaMH = maMH;
        this.TenMH = tenMH;
        this.SoTin = soTin;
        this.Khoa = khoa;
        
    }
}
