package GKH;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

abstract class Person {
    String SoTK;
    String Hoten;
    String GT;

    public abstract String Khuyenmai();

    public Person() {
    }

    public Person(String soTK, String hoten, String GT, String diachi, int sodu) {
        SoTK = soTK;
        Hoten = hoten;
        this.GT = GT;
    }

    public String getSoTK() {
        return SoTK;
    }

    public void setSoTK(String soTK) {
        SoTK = soTK;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public String getGT() {
        return GT;
    }

    public void setGT(String GT) {
        this.GT = GT;
    }
}

class KhachHang extends Person {
    String Diachi;
    int Sodu;
    @Override
    public String Khuyenmai() {
        if (GT.equals("nu")) {
            return "Khuyến mại";
        }
        return "";
    }

    public KhachHang() {
    }

    public KhachHang(String soTK, String hoten, String GT, String diachi, int sodu, String diachi1, int sodu1) {
        super(soTK, hoten, GT, diachi, sodu);
        Diachi = diachi1;
        Sodu = sodu1;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public int getSodu() {
        return Sodu;
    }

    public void setSodu(int sodu) {
        Sodu = sodu;
    }
}

class XLKH {
    private Connection con;

    public void getCon() {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DLKH;encrypt=false";
            String user = "sa";
            String password = "YourStrong!Passw0rd";
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("ERROR: Cannot get connection from database " + e.getMessage());
        }
    }

    public ArrayList<KhachHang> getKH() {
        ArrayList<KhachHang> list = new ArrayList<>();
        try {
            getCon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tbKhachhang");
            while (rs.next()) {
                KhachHang kh = new KhachHang(
                        rs.getString("SoTK"),
                        rs.getString("Hoten"),
                        rs.getString("GT"),
                        rs.getString("Diachi"),
                        rs.getInt("Sodu"),
                        rs.getString("Diachi"),
                        rs.getInt("Sodu")
                );
                list.add(kh);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertSP(KhachHang kh) {
        try {
            getCon();
            String sql = "INSERT INTO tbKhachhang (SoTK, Hoten, GT, Diachi, Sodu) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, kh.getSoTK());
            ps.setString(2, kh.getHoten());
            ps.setString(3, kh.getGT());
            ps.setString(4, kh.getDiachi());
            ps.setInt(5, kh.getSodu());
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class GUI_insertKH extends JFrame {
    JTextField txtSoTK, txtHoten, txtGT, txtSodu;
    JComboBox<String> cbDiachi;
    JButton btnAdd;
    JTable table;
    DefaultTableModel model;
    XLKH xlk = new XLKH();

    public GUI_insertKH() {
        setTitle("Customer Management");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblSoTK = new JLabel("Account Number:");
        JLabel lblHoten = new JLabel("Full Name:");
        JLabel lblGT = new JLabel("Gender:");
        JLabel lblDiachi = new JLabel("Address:");
        JLabel lblSodu = new JLabel("Account Balance:");

        lblSoTK.setBounds(20, 20, 120, 25);
        lblHoten.setBounds(20, 60, 120, 25);
        lblGT.setBounds(20, 100, 120, 25);
        lblDiachi.setBounds(20, 140, 120, 25);
        lblSodu.setBounds(20, 180, 120, 25);

        txtSoTK = new JTextField();
        txtHoten = new JTextField();
        txtGT = new JTextField();
        txtSodu = new JTextField();
        cbDiachi = new JComboBox<>(new String[]{"Hanoi", "Hai Phong", "Nam Dinh"});
        btnAdd = new JButton("Add customer");

        txtSoTK.setBounds(150, 20, 150, 25);
        txtHoten.setBounds(150, 60, 150, 25);
        txtGT.setBounds(150, 100, 150, 25);
        cbDiachi.setBounds(150, 140, 150, 25);
        txtSodu.setBounds(150, 180, 150, 25);
        btnAdd.setBounds(150, 220, 150, 30);

        add(lblSoTK); add(txtSoTK);
        add(lblHoten); add(txtHoten);
        add(lblGT); add(txtGT);
        add(lblDiachi); add(cbDiachi);
        add(lblSodu); add(txtSodu);
        add(btnAdd);

        String[] columnNames = {"Account Number", "Full Name", "Gender", "Address", "Account Balance", "Promotion"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(320, 20, 450, 300);
        add(scrollPane);

        btnAdd.addActionListener(_ -> {
            String soTK = txtSoTK.getText().trim();
            String hoten = txtHoten.getText().trim();
            String GT = txtGT.getText().trim();
            String diachi = cbDiachi.getSelectedItem().toString();
            int sodu;
            try {
                sodu = Integer.parseInt(txtSodu.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Account Balance must be a number.");
                return;
            }
            KhachHang kh = new KhachHang(soTK, hoten, GT, diachi, sodu, diachi, sodu);
            xlk.insertSP(kh);
            loadTable();
            txtSoTK.setText("");
            txtHoten.setText("");
            txtGT.setText("");
            txtSodu.setText("");
            cbDiachi.setSelectedIndex(0);
        });

        loadTable();
    }

    private void loadTable() {
        model.setRowCount(0);
        ArrayList<KhachHang> list = xlk.getKH();
        for (KhachHang kh : list) {
            Object[] row = {
                kh.getSoTK(),
                kh.getHoten(),
                kh.getGT(),
                kh.getDiachi(),
                kh.getSodu(),
                kh.Khuyenmai()
            };
            model.addRow(row);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new GUI_insertKH().setVisible(true);
    }
}
