package CoffeeAndTea;

import XComponent.*;
import java.sql.*;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FormCustomer extends XFrame {

  public FormCustomer(String string) {
    super(string);
    packWith(this.page());
  }

  XPanel page() {
    return panel()
      .setBorderLayout()
      .add("S", buttonPanel())
      .add("W", formPanel())
      .add(
        panel().setCardLayout(0, 0).add(table()).setBorderEmpty(20, 20, 0, 20)
      ); //
  }

  public XPanel formPanel() {
    return panel()
      .setRow(5)
      .add(detail("เลขที่ลูกค้า (CUST_NUM)"))
      .add(detail("ชี่อลูกค้า (CUST_NAME)"))
      .add(detail("ที่อยู่ลูกค้า (CUST_ADDR)"))
      .add(detail("โทรศัพท์ลูกค้า (CUST_PHONE)"))
      .add(detail("อีเมล์ลูกค้า (CUST_MAIL)"))
      .edged(20, 20, 0, 0);
  }

  public XPanel detail(String title) {
    details.add(new JTextField(15));

    return panel().setBorderTitle(title).add(details.lastElement());
  }

  public XPanel buttonPanel() {
    return panel()
      .setFlowLayoutRight()
      .add(button("เพิ่มข้อมูล").which(this::insert))
      .add(button("แก้ไข").which(this::update))
      .add(button("ลบ").which(this::delete))
      .add(button("ยกเลิก").which(this::cancle))
      .panel()
      .setCardLayout(0, 20);
  }

  private void cancle() {}

  public void insert() {
    try {
      String sql = "INSERT INTO CUSTOMER VALUES (?,?,?,?,?)";
      PreparedStatement pre = connection.prepareStatement(sql);

      for (int i = 1; i <= 5; i++) {
        pre.setString(i, details.get(i - 1).getText().trim());
      }
      executeThenReset(
        pre.executeUpdate(),
        "ผลการบันทึกรายการ",
        "บันทึกข้อมูลลูกค้าเรียบร้อยแล้ว"
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  JTable table = new JTable();
  Vector<JTextField> details = new Vector<>();

  public void update() {
    try {
      if (table.getSelectedRow() == -1) return;

      String sql =
        "UPDATE CUSTOMER SET " + //
        " CUST_NAME=?, " + //
        " CUST_ADDR=?, " + //
        " CUST_PHONE=?, " + //
        " CUST_MAIL=? " + //
        " WHERE CUST_NUM=? ";

      PreparedStatement pre = connection.prepareStatement(sql);

      for (int i = 1; i <= 5; i++) {
        pre.setString(i, details.get(i % 5).getText().trim());
      }
      executeThenReset(
        pre.executeUpdate(),
        "ผลการแก้ไขรายการ",
        "แก้ไขรายการแล้ว"
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void delete() {
    try {
      if (table.getSelectedRow() == -1) return;

      String sql =
        "DELETE FROM CUSTOMER " + //
        " WHERE CUST_NUM=? ";

      PreparedStatement pre = connection.prepareStatement(sql);
      pre.setString(1, details.get(0).getText().trim());

      executeThenReset(pre.executeUpdate(), "ผลการลบรายการ", "ลบรายการแล้ว");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void executeThenReset(
    int updateStatus,
    String title,
    String message
  ) {
    if (updateStatus != -1) {
      XFrame.message(title, message);
      showData(null);
      details.get(0).setEditable(true);
      details.forEach(e -> e.setText(""));
    }
  }

  private Connection connection = XConnect.getConnection();

  private Vector<String> columnNames = Stream
    .of(
      "เลขที่ลูกค้า",
      "ชี่อลูกค้า",
      "ที่อยู่ลูกค้า",
      "หมายเลขโทรศัพท์ลูกค้า",
      "อีเมล์ลูกค้า"
    )
    .collect(Collectors.toCollection(Vector::new));

  public void showData(String search) {
    try {
      String[] columns = {
        "CUST_NUM",
        "CUST_NAME",
        "CUST_ADDR",
        "CUST_PHONE",
        "CUST_MAIL",
      };

      String sql = "SELECT * FROM Customer";
      if (search != null) {
        sql += sqlSearch(search, columns);
      }

      ResultSet rs = connection.createStatement().executeQuery(sql);

      Vector<Vector<Object>> data = new Vector<Vector<Object>>();

      while (rs.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int i = 1; i <= 5; i++) {
          vector.add(rs.getObject(i));
        }
        data.add(vector);
      }

      table.setModel(new DefaultTableModel(data, columnNames));
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
}
