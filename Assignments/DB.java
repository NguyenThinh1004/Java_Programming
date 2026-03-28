import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {
    public Connection connect_to_db(String dbname, String user, String pass) {
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

    public void insert_row(Connection conn, String table_name, String SoTK, String Hoten, String GT, String Diachi, double Sodu) {
        Statement statement;
        try {
            String query = String.format("insert into %s(SoTK, Hoten, GT, Diachi, Sodu) values('%s', '%s', '%s', '%s', %s);", table_name, SoTK, Hoten, GT, Diachi, Sodu);
            statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void read_data(Connection conn, String table_name) {
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("select * from %s", table_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.print(rs.getString("SoTK") + " ");
                System.out.print(rs.getString("Hoten") + " ");
                System.out.print(rs.getString("GT") + " ");
                System.out.print(rs.getString("Diachi") + " ");
                System.out.println(rs.getString("Sodu") + " ");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void delete_row(Connection conn, String table_name, String SoTK) {
        Statement statement;
        try{
            String query = String.format("delete from %s where SoTK='%s'", table_name, SoTK);
            statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
