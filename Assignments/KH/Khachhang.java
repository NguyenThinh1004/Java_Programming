package GKH;

public class Khachhang extends Person {
    String Diachi;
    double Sodu;

    public String Khuyenmai() {
        if (GT.equals("Nu")) {
            return "Khuyến mại";
        }
        return "";
    }

    public Khachhang() {}

    public Khachhang(String SoTK, String Hoten, String GT, String Diachi, double Sodu) {
        super(SoTK, Hoten, GT);
        this.Diachi = Diachi;
        this.Sodu = Sodu;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public double getSodu() {
        return Sodu;
    }

    public void setSodu(double sodu) {
        Sodu = sodu;
    }
}
