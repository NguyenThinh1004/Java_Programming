package GTS;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class XLTS implements IThisinh {
    @Override
    public Connection getCon() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+"DLTS","postgres","110123447");
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

    @Override
    public List<Thisinh> getTS(Connection conn, String table_name) {
        List<Thisinh> list = new ArrayList<>();

        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("select * from %s", table_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                list.add(new Thisinh(
                        rs.getString("sobd"),
                        rs.getString("hoten"),
                        rs.getString("gt"),
                        rs.getString("nganhh"),
                        rs.getInt("tongd")
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public void insertTS(Connection conn, String table_name, Thisinh ts) {
        Statement statement;
        try {
            String query = String.format("insert into %s values('%s', '%s', '%s', '%s', %d)", table_name, ts.getSoBD(), ts.getHoten(), ts.getGT(), ts.getNganhH(), ts.getTongD());
            statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
