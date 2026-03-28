package GTS;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface IThisinh {
    public Connection getCon();
    public List<Thisinh> getTS(Connection conn, String table_name);
    public void insertTS(Connection conn, String table_name, Thisinh ts);
}
