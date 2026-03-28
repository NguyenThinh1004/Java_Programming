package GKH;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class XLKH {
    public Connection getCon(String dbname, String user, String pass) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,pass);
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

    public void createTable(Connection conn, String table_name) {
        Statement statement;
        try {
            String query = "create table " + table_name + " ("
                    + "SoTK varchar(50) primary key, "
                    + "Hoten varchar(50), "
                    + "GT varchar(10), "
                    + "Diachi varchar(100), "
                    + "Sodu numeric(15, 2)"
                    + ")";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertSP(Connection conn, String table_name, Khachhang kh) {
        Statement statement;
        try {
            String query = String.format("insert into %s(SoTK, Hoten, GT, Diachi, Sodu) values('%s', '%s', '%s', '%s', %s);", table_name, kh.getSoTK(), kh.getHoten(), kh.getGT(), kh.getDiachi(), kh.getSodu());
            statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteKH(Connection conn, String table_name, String SoTK) {
        Statement statement;
        try {
            String query = String.format("delete from %s where SoTK='%s'", table_name, SoTK);
            statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Khachhang> getKH(Connection conn, String table_name) {
        List<Khachhang> list = new ArrayList<>();

        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("select * from %s", table_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                list.add(new Khachhang(
                        rs.getString("SoTK"),
                        rs.getString("Hoten"),
                        rs.getString("GT"),
                        rs.getString("Diachi"),
                        rs.getDouble("Sodu")
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}

