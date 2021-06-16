package XComponent;

import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class XTable {

  public XTable(
    Vector<String> tableColumnNames,
    String sqlTableName,
    String[] sqlColumnId,
    Vector<JTextField> details
  ) {
    this.tableColumnNames = tableColumnNames;
    this.sqlTableName = sqlTableName;
    this.sqlColumnId = sqlColumnId;
    this.details = details;
  }

  private Vector<String> tableColumnNames;
  private String sqlTableName;
  private String[] sqlColumnId;
  private Vector<JTextField> details;

  public JTextField searchField = new JTextField(15) {
    {
      addKeyListener(
        new KeyAdapter() {
          @Override
          public void keyReleased(KeyEvent e) {
            showData(searchField.getText().trim());
          }
        }
      );
    }
  };

  public JTable table = new JTable(new DefaultTableModel()) {
    {
      setRowHeight(25);
      addMouseListener(
        new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {
            int index = table.getSelectedRow();
            details.get(0).setEditable(false);
            for (int i = 0; i < sqlColumnId.length; i++) {
              details
                .get(i)
                .setText( //
                  table.getValueAt(index, i).toString()
                );
            }
          }
        }
      );
    }
  };

  private Connection connection = XConnect.getConnection();

  public void showData(String search) {
    try {
      String sql = "SELECT * FROM " + sqlTableName;
      if (search != null) {
        sql += sqlSearch(search, sqlColumnId);
      }

      ResultSet rs = connection.createStatement().executeQuery(sql);

      Vector<Vector<Object>> data = new Vector<Vector<Object>>();

      while (rs.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int i = 1; i <= sqlColumnId.length; i++) {
          vector.add(rs.getObject(i));
        }
        data.add(vector);
      }

      table.setModel(new DefaultTableModel(data, tableColumnNames));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private String sqlSearch(String search, String[] cols) {
    String sql = "";
    boolean first = true;

    for (String string : cols) {
      if (first) {
        first = false;
        sql += " WHERE ";
      } else {
        sql += " OR ";
      }
      sql += string + " LIKE '%" + search + "%'";
    }
    return sql;
  }

  public void reset() {
    showData(null);
    details.get(0).setEditable(true);
    details.forEach(e -> e.setText(""));
    searchField.setText("");
  }

  public void insert() {
    try {
      String sql = "INSERT INTO " + sqlTableName + " VALUES (?,?,?,?,?)";
      PreparedStatement pre = connection.prepareStatement(sql);

      for (int i = 1; i <= sqlColumnId.length; i++) {
        pre.setString(i, details.get(i - 1).getText().trim());
      }
      if (pre.executeUpdate() != -1) {
        XFrame.message("ผลการบันทึกรายการ", "บันทึกข้อมูลลูกค้าเรียบร้อยแล้ว");
        reset();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void update() {
    try {
      if (table.getSelectedRow() == -1) return;

      String sql = "UPDATE " + sqlTableName + " SET ";
      for (int i = 1; i <= sqlColumnId.length - 2; i++) {
        sql += " " + sqlColumnId[i % sqlColumnId.length] + "=?, ";
      }
      sql +=
        "  " +
        sqlColumnId[sqlColumnId.length - 1] +
        "=? " + //
        " WHERE  " +
        sqlColumnId[0] +
        "=? ";

      PreparedStatement pre = connection.prepareStatement(sql);

      for (int i = 1; i <= sqlColumnId.length; i++) {
        pre.setString(i, details.get(i % sqlColumnId.length).getText().trim());
      }

      if (pre.executeUpdate() != -1) {
        XFrame.message("ผลการแก้ไขรายการ", "แก้ไขรายการแล้ว");
        reset();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void delete() {
    try {
      if (table.getSelectedRow() == -1) return;

      String sql =
        "DELETE FROM " +
        sqlTableName +
        " " + //
        " WHERE CUST_NUM=? ";

      PreparedStatement pre = connection.prepareStatement(sql);
      pre.setString(1, details.get(0).getText().trim());

      if (pre.executeUpdate() != -1) {
        XFrame.message("ผลการลบรายการ", "ลบรายการแล้ว");
        reset();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
