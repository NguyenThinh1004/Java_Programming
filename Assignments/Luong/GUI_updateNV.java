package GLuong;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class GUI_updateNV extends JFrame {
    XLLuong xl = new XLLuong();
    Connection conn = xl.getCon();

    JTextField txtMaNV, txtHoten, txtLuong;
    JComboBox cbDiachi;
    JButton btnTim, btnCapNhat;
    JTable table;
    DefaultTableModel model;

    public GUI_updateNV() {
        setTitle("Quan ly luong");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel p1 = new JPanel(new GridLayout(2,1));

        txtMaNV = new JTextField();
        txtHoten = new JTextField();
        cbDiachi = new JComboBox<>(new String[]{"Hai Phong", "Ha Noi", "Nam Dinh"});
        txtLuong = new JTextField();

        p1.add(new JLabel("MaNV:"));
        p1.add(txtMaNV);
        p1.add(new JLabel("Hoten:"));
        p1.add(txtHoten);
        p1.add(new JLabel("Diachi:"));
        p1.add(cbDiachi);
        p1.add(new JLabel("Luong:"));
        p1.add(txtLuong);

        add(p1, BorderLayout.NORTH);

        JPanel p2 = new JPanel();
        btnTim = new JButton("Tim");
        p2.add(btnTim);
        btnCapNhat = new JButton("Cap Nhat");
        p2.add(btnCapNhat);

        add(p2, BorderLayout.CENTER);

        model = new DefaultTableModel(new String[]{"MaNV", "Hoten", "Diachi", "Luong"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.SOUTH);

        loadData();

        btnTim.addActionListener(e -> {
            String maNV = txtMaNV.getText().trim();

            if (maNV.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui long nhap Ma NV");
                return;
            }

            Nhanvien nvSearch = new Nhanvien();
            nvSearch.MaNV = maNV;

            Nhanvien result = xl.getNVbyMa(conn, "tbnhanvien", nvSearch);

            if (result != null) {
                txtHoten.setText(result.getHoten());
                cbDiachi.setSelectedItem(result.getDiachi());
                txtLuong.setText(String.valueOf(result.getLuong()));
                JOptionPane.showMessageDialog(null, "Tim thay NV!");
            } else {
                JOptionPane.showMessageDialog(null, "Khong tim thay NV co ma: " + maNV);
            }
        });

        btnCapNhat.addActionListener(e -> {
            String maNV = txtMaNV.getText().trim();

            if (maNV.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui long nhap Ma NV");
                return;
            }

            Nhanvien nvUpdate = new Nhanvien(
                    txtMaNV.getText(),
                    txtHoten.getText(),
                    cbDiachi.getSelectedItem().toString(),
                    Integer.parseInt(txtLuong.getText())
            );

            xl.updateNV(conn, "tbnhanvien", nvUpdate);
            loadData();
            JOptionPane.showMessageDialog(this, "Da cap nhat NV co ma NV : " + maNV);
        });
    }

    private void loadData() {
        List<Nhanvien> ds = xl.getNV(conn, "tbnhanvien");
        model.setRowCount(0);
        for (Nhanvien nv : ds) {
            model.addRow(new Object[]{
                    nv.getMaNV(), nv.getHoten(), nv.getDiachi(), nv.getLuong()
            });
        }
    }

    public static void main(String[] args) {
        new GUI_updateNV().setVisible(true);
    }
}
