package GCD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.util.List;

public class GUI extends JFrame {
    XL xl = new XL();
    ICD icd = new ICD();
    Connection conn = icd.getCon("QLCD", "postgres", "110123447");

    JTextField txtMaCD, txtTenCD, txtCaSi, txtSoBaiHat, txtGiaTien, txtTimKiem;
    JButton btnUpdate, btnLuu, btnXoa, btnTim;
    JTable tbCD;
    DefaultTableModel model;

    public GUI() {
        setTitle("Quan ly CD");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel p1 = new JPanel(new GridLayout(6, 2));
        p1.add(new JLabel("Ma CD:")); txtMaCD = new JTextField(); p1.add(txtMaCD);
        p1.add(new JLabel("Ten CD:")); txtTenCD = new JTextField(); p1.add(txtTenCD);
        p1.add(new JLabel("Ca si:")); txtCaSi = new JTextField(); p1.add(txtCaSi);
        p1.add(new JLabel("So bai hat:")); txtSoBaiHat = new JTextField(); p1.add(txtSoBaiHat);
        p1.add(new JLabel("Gia tien:")); txtGiaTien = new JTextField(); p1.add(txtGiaTien);
        p1.add(new JLabel("Tim kiem:")); txtTimKiem = new JTextField(); p1.add(txtTimKiem);
        add(p1, BorderLayout.NORTH);

        JPanel p2 = new JPanel();
        btnUpdate = new JButton("Update");
        btnLuu = new JButton("Luu");
        btnXoa = new JButton("Xoa");
        btnTim = new JButton("Tim");
        p2.add(btnLuu);
        p2.add(btnXoa);
        p2.add(btnTim);
        p2.add(btnUpdate);
        add(p2, BorderLayout.CENTER);

        model = new DefaultTableModel(new String[]{"Ma CD", "Ten CD", "Ca Si", "So Bai Hat", "Gia Tien"}, 0);
        tbCD = new JTable(model);
        add(new JScrollPane(tbCD), BorderLayout.SOUTH);

        loadData();

        btnLuu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CD cd = new CD(
                        txtMaCD.getText(),
                        txtTenCD.getText(),
                        txtCaSi.getText(),
                        Integer.parseInt(txtSoBaiHat.getText()),
                        Integer.parseInt(txtGiaTien.getText())
                );
                xl.insert(conn, "QLCD", cd);
                model.addRow(new Object[]{
                        cd.getMaCD(), cd.getTenCD(), cd.getCaSi(), cd.getSoBaiHat(), cd.getGiaTien()
                });
            }
        });

        btnXoa.addActionListener(e -> {
            int row = tbCD.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Hay chon CD de xoa!");
                return;
            }
            String maCD = model.getValueAt(row, 0).toString();
            xl.delete(conn, "QLCD", maCD);
            model.removeRow(row);
            JOptionPane.showMessageDialog(this, "Da xoa CD co so CD : " + maCD);
        });

        btnTim.addActionListener(e -> {
            String maCD = txtTimKiem.getText().trim();

            if (maCD.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui long nhap Ma CD");
                return;
            }

            CD cdSearch = new CD();
            cdSearch.MaCD = maCD;

            CD result = xl.search(conn, "QLCD", cdSearch);

            if (result != null) {
                txtMaCD.setText(result.MaCD);
                txtTenCD.setText(result.TenCD);
                txtCaSi.setText(String.valueOf(result.CaSi));
                txtSoBaiHat.setText(String.valueOf(result.SoBaiHat));
                txtGiaTien.setText(String.valueOf(result.GiaTien));
                JOptionPane.showMessageDialog(null, "Tìm thấy CD!");
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy CD có mã: " + maCD);
            }
        });

        btnUpdate.addActionListener(e -> {
            String maCD = txtMaCD.getText().trim();

            if (maCD.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui long nhap Ma CD");
                return;
            }

            CD cdUpdate = new CD(
                    txtMaCD.getText(),
                    txtTenCD.getText(),
                    txtCaSi.getText(),
                    Integer.parseInt(txtSoBaiHat.getText()),
                    Integer.parseInt(txtGiaTien.getText())
            );

            xl.update(conn, "QLCD", cdUpdate);
            loadData();
            JOptionPane.showMessageDialog(this, "Da cap nhat CD co so CD : " + maCD);
        });
    }

    private void loadData() {
        List<CD> ds = xl.getCD(conn, "QLCD");
        model.setRowCount(0);
        for (CD cd : ds) {
            model.addRow(new Object[]{
                    cd.getMaCD(), cd.getTenCD(), cd.getCaSi(), cd.getSoBaiHat(), cd.getGiaTien()
            });
        }
    }

    public static void main(String[] args) {
        new GUI().setVisible(true);
    }
}
