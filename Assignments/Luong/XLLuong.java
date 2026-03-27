package GLuong;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class XLLuong {
    public Connection getCon() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+"DLLuong","postgres","110123447");
            if(conn != null) {
                System.out.println("Connection Established");
            }
            else {
                System.out.println("Connection Failed");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }

    public Nhanvien getNVbyMa(Connection conn, String table_name, Nhanvien nv) {
        Statement statement;
        Nhanvien result = null;
        try {
            String query = String.format("select * from %s where manv='%s'", table_name, nv.getMaNV());
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                result = new Nhanvien(
                        rs.getString("manv"),
                        rs.getString("hoten"),
                        rs.getString("diachi"),
                        rs.getInt("luong")
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public void updateNV(Connection conn, String table_name, Nhanvien nv) {
        Statement statement;
        try {
            String query = String.format("update %s set hoten='%s', diachi='%s', luong=%d where manv='%s'", table_name, nv.getHoten(), nv.getDiachi(), nv.getLuong(), nv.getMaNV());
            statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Nhanvien> getNV(Connection conn, String table_name) {
        List<Nhanvien> list = new ArrayList<>();

        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("select * from %s", table_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                list.add(new Nhanvien(
                        rs.getString("manv"),
                        rs.getString("hoten"),
                        rs.getString("diachi"),
                        rs.getInt("luong")
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
