package GCD;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ICD {
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

//    public printCD() {
//        Statement statement;
//        try {
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//    public sumPrice() {
//    }
}
