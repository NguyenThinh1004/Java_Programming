package GLuong;

public class Nhanvien extends Person {
    String Diachi;
    int Luong;

    public Nhanvien() {}

    public Nhanvien(String MaNV, String Hoten, String Diachi, int Luong) {
        super(MaNV, Hoten);
        this.Diachi = Diachi;
        this.Luong = Luong;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public int getLuong() {
        return Luong;
    }

    public void setLuong(int luong) {
        Luong = luong;
    }
}
