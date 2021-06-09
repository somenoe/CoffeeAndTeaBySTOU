package XComponent;

import java.awt.Dimension;
import java.awt.event.*;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class XTable {

    private Vector<Vector<Object>> data = new Vector<Vector<Object>>() {
        {
            for (int i = 0; i < 5; i++) {
                add(new Vector<Object>(5));
            }
        }
    };

    private Vector<String> columnNames = Stream
            .of("เลขที่ลูกค้า", "ชี่อลูกค้า", "ที่อยู่ลูกค้า", "หมายเลขโทรศัพท์ลูกค้า", "อีเมล์ลูกค้า")
            .collect(Collectors.toCollection(Vector::new));

    private JTable table;

    JScrollPane tablePanel() {
        return new JScrollPane() {
            {
                table = new JTable(new DefaultTableModel(data, columnNames)) {
                    {
                        setRowHeight(25);
                        addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent e) {
                                // int index = table.getSelectedRow();
                                // details.get(0).setEditable(false);
                                // for (int i = 0; i <= 4; i++) {
                                // details.get(i).setText(//
                                // table.getValueAt(index, i).toString());
                                // }
                            };
                        });
                    }
                };
                setViewportView(table);
                setPreferredSize(new Dimension(850, 427));
            }
        };
    }

}
