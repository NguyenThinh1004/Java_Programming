package GTS;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class GUI_insertTS extends JFrame {
    XLTS xl = new XLTS();
    Connection conn = xl.getCon();

    JTextField txtSoBD, txtHoten, txtTongD;
    JComboBox cbNganhH;
    JRadioButton rbNam, rbNu;
    JButton btnThem;
    JTable table;
    DefaultTableModel model;

    public GUI_insertTS() {
        setTitle("Quan ly thi sinh");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel p1 = new JPanel(new GridLayout(5, 2));

        txtSoBD = new JTextField();
        txtHoten = new JTextField();
        rbNam = new JRadioButton("Nam");
        rbNu = new JRadioButton("Nu");
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbNam);
        bg.add(rbNu);
        JPanel pGT = new JPanel();
        pGT.add(rbNam);
        pGT.add(rbNu);
        cbNganhH = new JComboBox<>(new String[]{"TTNT", "CK", "CTT"});
        txtTongD = new JTextField();

        p1.add(new JLabel("SoBD:"));
        p1.add(txtSoBD);
        p1.add(new JLabel("Hoten:"));
        p1.add(txtHoten);
        p1.add(new JLabel("GT:"));
        p1.add(pGT);
        p1.add(new JLabel("NganhH:"));
        p1.add(cbNganhH);
        p1.add(new JLabel("TongD:"));
        p1.add(txtTongD);

        add(p1, BorderLayout.NORTH);

        JPanel p2 = new JPanel();
        btnThem = new JButton("Them");
        p2.add(btnThem);

        add(p2, BorderLayout.CENTER);

        model = new DefaultTableModel(new String[]{"SoBD", "Hoten", "GT", "NganhH", "TongD", "HocBong"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.SOUTH);

        loadData();

        btnThem.addActionListener(e -> {
            String gt = rbNam.isSelected() ? "Nam" : "Nu";
            Thisinh ts = new Thisinh(
                    txtSoBD.getText(),
                    txtHoten.getText(),
                    gt,
                    cbNganhH.getSelectedItem().toString(),
                    Integer.parseInt(txtTongD.getText())
            );
            xl.insertTS(conn, "tbthisinh", ts);
            loadData();
        });
    }

    private void loadData() {
        List<Thisinh> ds = xl.getTS(conn, "tbthisinh");
        model.setRowCount(0);
        for (Thisinh ts : ds) {
            model.addRow(new Object[]{
                    ts.getSoBD(), ts.getHoten(), ts.getGT(), ts.getNganhH(), ts.getTongD(), ts.Hocbong()
            });
        }
    }

    public static void main(String[] args) {
        new GUI_insertTS().setVisible(true);
    }
}
