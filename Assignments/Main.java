import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DB db = new DB();
        Connection conn = db.connect_to_db("DLKH", "postgres", "110123447");
//        db.createTable(conn, "DLKH");
//        db.insert_row(conn, "DLKH", "2", "Ha", "Nu", "BacGiang", 10000);
//        db.delete_row(conn, "DLKH", "2");
//        db.read_data(conn, "DLKH");
    }
}