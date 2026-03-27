package GCD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class XL {
    ICD icd = new ICD();
    Connection conn = icd.getCon("QLCD", "postgres", "110123447");

    public List<CD> getCD(Connection conn, String table_name) {
        List<CD> list = new ArrayList<>();
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("select * from %s", table_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                list.add(new CD(
                        rs.getString("MaCD"),
                        rs.getString("TenCD"),
                        rs.getString("CaSi"),
                        rs.getInt("SoBaiHat"),
                        rs.getInt("GiaTien")
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insert(Connection conn, String table_name, CD cd) {
        Statement statement;
        try {
            String query = String.format("insert into %s(MaCD, TenCD, CaSi, SoBaiHat, GiaTien) values('%s', '%s', '%s', '%s', %s);", table_name, cd.MaCD, cd.TenCD, cd.CaSi, cd.SoBaiHat, cd.GiaTien);
            statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void delete(Connection conn, String table_name, String MaCD) {
        Statement statement;
        try {
            String query = String.format("delete from %s where MaCD = '%s'", table_name, MaCD);
            statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update(Connection conn, String table_name, CD cd) {
        Statement statement;
        try {
            String query = String.format("update %s set tencd='%s', casi='%s', sobaihat=%d, giatien=%d where macd='%s'", table_name, cd.TenCD, cd.CaSi, cd.SoBaiHat, cd.GiaTien, cd.MaCD);
            statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public CD search(Connection conn, String table_name, CD cd) {
        Statement statement;
        CD result = null;
        try {
            String query = String.format("select * from %s where macd='%s'", table_name, cd.MaCD);
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                result = new CD(
                        rs.getString("MaCD"),
                        rs.getString("TenCD"),
                        rs.getString("CaSi"),
                        rs.getInt("SoBaiHat"),
                        rs.getInt("GiaTien")
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
