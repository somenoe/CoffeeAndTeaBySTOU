package CoffeeAndTea;

import XComponent.*;
import java.awt.Dimension;
import java.util.List;
import java.util.Vector;
import javax.swing.*;

public class FormCustomer extends XFrame {

  public static void main(String[] args) {
    FormCustomer customer = new FormCustomer();
    customer.packWith(customer.page());
  }

  public FormCustomer() {
    super("ข้อมูลพื้นฐาน: ลูกค้า");
  }

  public XPanel page() {
    return panel()
      .setBorderLayout()
      .add("S", panelButton())
      .add("W", panelForm())
      .add("C", panelTable())
      .then(() -> xTable.showData(null));
  }

  private XPanel panelTable() {
    return panel()
      .setCardLayout(0, 0)
      .add(tablePanel())
      .setBorderEmpty(20, 20, 0, 20);
  }

  private XPanel panelForm() {
    XPanel panel = panel().setRow(6).add(labelTitle("ข้อมูล: ลูกค้า"));

    for (String name : tableColumnNames) {
      panel.add(detail(name));
    }

    return panel.edged(20, 20, 0, 0);
  }

  private XPanel detail(String title) {
    details.add(new JTextField(15));

    return panel().setBorderTitle(title).add(details.lastElement());
  }

  private JScrollPane tablePanel() {
    return new JScrollPane() {
      {
        setViewportView(xTable.table);
        setPreferredSize(new Dimension(850, 127));
      }
    };
  }

  private XPanel panelButton() {
    return panel()
      .setFlowLayoutRight()
      .add(searchPanel())
      .add(button("เพิ่มข้อมูล").which(xTable::insert))
      .add(button("แก้ไข").which(xTable::update))
      .add(button("ลบ").which(xTable::delete))
      .add(button("ยกเลิก").which(xTable::reset))
      .panel()
      .setCardLayout(0, 20);
  }

  private Vector<String> tableColumnNames = new Vector<>(
    List.of("เลขที่", "ชี่อ", "นามสกุล", "ที่อยู่", "หมายเลขโทรศัพท์")
  );

  private String[] sqlColumnId = {
    "CUST_NUM",
    "CUST_FNAME",
    "CUST_LNAME",
    "CUST_ADDR",
    "CUST_PHONE",
  };

  private Vector<JTextField> details = new Vector<>();

  private XTable xTable = new XTable(
    tableColumnNames,
    "CUSTOMER",
    sqlColumnId,
    details
  );

  private XPanel searchPanel() {
    return panel() //
      .setFlowLayoutCenter() //
      .add(new JLabel("ค้นหา")) //
      .add(xTable.searchField) //
      .edged(5, 0, 0, 0);
  }
}
