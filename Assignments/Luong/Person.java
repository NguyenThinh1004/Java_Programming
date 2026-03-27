package GLuong;

public class Person {
    String MaNV;
    String Hoten;

    public Person() {}

    public Person(String MaNV, String HoTen) {
        this.MaNV = MaNV;
        this.Hoten = HoTen;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }
}
