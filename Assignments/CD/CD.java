package GCD;

public class CD {
    String MaCD;
    String TenCD;
    String CaSi;
    int SoBaiHat;
    int GiaTien;

    public CD() {}

    public CD(String MaCD, String TenCD, String CaSi, int SoBaiHat, int GiaTien) {
        this.MaCD = MaCD;
        this.TenCD = TenCD;
        this.CaSi = CaSi;
        this.SoBaiHat = SoBaiHat;
        this.GiaTien = GiaTien;
    }

    public String getMaCD() {
        return MaCD;
    }

    public void setMaCD(String maCD) {
        MaCD = maCD;
    }

    public String getTenCD() {
        return TenCD;
    }

    public void setTenCD(String tenCD) {
        TenCD = tenCD;
    }

    public String getCaSi() {
        return CaSi;
    }

    public void setCaSi(String caSi) {
        CaSi = caSi;
    }

    public int getSoBaiHat() {
        return SoBaiHat;
    }

    public void setSoBaiHat(int soBaiHat) {
        SoBaiHat = soBaiHat;
    }

    public int getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(int giaTien) {
        GiaTien = giaTien;
    }
}
