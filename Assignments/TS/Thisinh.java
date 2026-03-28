package GTS;

public class Thisinh {
    String SoBD;
    String Hoten;
    String GT;
    String NganhH;
    int TongD;

    String Hocbong() {
        if (this.TongD >= 29) {
            return "HB";
        }
        else {
            return "";
        }
    }

    public Thisinh() {}

    public Thisinh(String SoBD, String Hoten, String GT, String NganhH, int TongD) {
        this.SoBD = SoBD;
        this.Hoten = Hoten;
        this.GT = GT;
        this.NganhH= NganhH;
        this.TongD = TongD;
    }

    public String getSoBD() {
        return SoBD;
    }

    public void setSoBD(String soBD) {
        SoBD = soBD;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public String getGT() {
        return GT;
    }

    public void setGT(String GT) {
        this.GT = GT;
    }

    public String getNganhH() {
        return NganhH;
    }

    public void setNganhH(String nganhH) {
        NganhH = nganhH;
    }

    public int getTongD() {
        return TongD;
    }

    public void setTongD(int tongD) {
        TongD = tongD;
    }
}
