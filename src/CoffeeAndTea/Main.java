package CoffeeAndTea;

import XComponent.XFrame;
import XComponent.XPanel;
import javax.swing.*;

public class Main extends XFrame {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        // new LogIn();
        new Main();
      }
    });
  }

  private JMenuBar menuBar = menuBar()
      .add(menu("ข้อมูลพื้นฐาน").add(item("พนักงาน")).add(item("สินค้า")).add(item("ลูกค้า")))
      .add(menu("การขาย").add(item("รับออร์เดอร์")).add(item("จัดการสินค้า"))
          .add(item("บันทึกข้อมูล")))
      .add(menu("รายงาน").add(item("การขายประจำวัน")).add(item("การขายตามชนิดสินค้า"))
          .add(item("ใบเสร็จรับเงิน")))
      .add(menu("บัญชีผู้ใช้").add(item("ออกจากระบบ").which(this::logout)));

  private final void logout() {
    new LogIn();
    dispose();
  }

  public Main() {
    super("Coffee and Tea by STOU");
    setJMenuBar(menuBar);
    packWith(this.page());
  }

  XPanel page() {
    return panel();
  }
}
