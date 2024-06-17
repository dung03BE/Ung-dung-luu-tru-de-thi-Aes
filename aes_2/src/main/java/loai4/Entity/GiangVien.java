package loai4.Entity;


public class GiangVien {
    private String MaGV;
    private String TenGV;
    private String SDT;
    private String Khoa;
    
    public String getMaGV() {
        return MaGV;
    }
    public void setMaGV(String maGV) {
        this.MaGV = maGV;
    }
    
    public String getTenGV() {
        return TenGV;
    }
    public void setTenGV(String tenGV) {
        this.TenGV = tenGV;
    }
    
    public String getSDT() {
        return SDT;
    }
    public void setSDT(String sdt) {
        this.SDT = sdt;
    }
    
    public String getKhoa() {
        return Khoa;
    }
    public void setKhoa(String khoa) {
        this.Khoa = khoa;
    }
    
    public GiangVien(){};
    public GiangVien(String maGV, String tenGV, String sdt, String khoa) {
        this.MaGV = maGV;
        this.TenGV = tenGV;
        this.SDT = sdt;
        this.Khoa = khoa;
    }
}
