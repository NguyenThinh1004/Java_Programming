package GKH;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.util.List;

public class GUI_insertKH extends JFrame {
    XLKH xl = new XLKH();
    Connection conn = xl.getCon("DLKH", "postgres", "110123447");

    JTextField txtSoTK, txtHoten, txtSoDu;
    JComboBox<String> cbGT, cbDiachi;
    JButton btnThem, btnXoa;
    JTable table;
    DefaultTableModel model;

    public GUI_insertKH() {
        setTitle("Quản lý khách hàng");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel p1 = new JPanel(new GridLayout(5, 2));
        p1.add(new JLabel("Số TK:")); txtSoTK = new JTextField(); p1.add(txtSoTK);
        p1.add(new JLabel("Họ tên:")); txtHoten = new JTextField(); p1.add(txtHoten);
        p1.add(new JLabel("Giới tính:")); cbGT = new JComboBox<>(new String[]{"Nam", "Nu"}); p1.add(cbGT);
        p1.add(new JLabel("Địa chỉ:")); cbDiachi = new JComboBox<>(new String[]{"Ha Noi", "Hai Phong", "Nam Dinh"}); p1.add(cbDiachi);
        p1.add(new JLabel("Số dư:")); txtSoDu = new JTextField(); p1.add(txtSoDu);

        add(p1, BorderLayout.NORTH);

        JPanel p2 = new JPanel();
        btnThem = new JButton("Thêm khách hàng");
        btnXoa = new JButton("Xóa khách hàng");
        p2.add(btnThem);
        p2.add(btnXoa);
        add(p2, BorderLayout.CENTER);

        model = new DefaultTableModel(new String[]{"Số TK", "Họ tên", "GT", "Địa chỉ", "Số dư", "Khuyến mãi"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.SOUTH);

        loadData();

        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Khachhang kh = new Khachhang(
                        txtSoTK.getText(),
                        txtHoten.getText(),
                        cbGT.getSelectedItem().toString(),
                        cbDiachi.getSelectedItem().toString(),
                        Double.parseDouble(txtSoDu.getText())
                );
                xl.insertSP(conn, "DLKH", kh);
                model.addRow(new Object[]{
                        kh.getSoTK(), kh.getHoten(), kh.getGT(), kh.getDiachi(), kh.getSodu(), kh.Khuyenmai()
                });
            }
        });

        btnXoa.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Hãy chọn 1 khách hàng để xóa!");
                return;
            }
            String soTK = model.getValueAt(row, 0).toString();
            xl.deleteKH(conn, "DLKH", soTK);
            model.removeRow(row);
            JOptionPane.showMessageDialog(this, "Đã xóa khách hàng có Số TK: " + soTK);
        });
    }

    private void loadData() {
        List<Khachhang> ds = xl.getKH(conn, "DLKH");
        for (Khachhang kh : ds) {
            model.addRow(new Object[]{
                    kh.getSoTK(), kh.getHoten(), kh.getGT(), kh.getDiachi(), kh.getSodu(), kh.Khuyenmai()
            });
        }
    }

    public static void main(String[] args) {
        new GUI_insertKH().setVisible(true);
    }
}
