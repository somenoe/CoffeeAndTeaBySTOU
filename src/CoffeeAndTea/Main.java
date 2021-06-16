package CoffeeAndTea;

import XComponent.XFrame;
import XComponent.XPanel;
import javax.swing.*;

public class Main extends XFrame {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(
      new Runnable() {
        @Override
        public void run() {
          new LogIn();
          // new Main();
        }
      }
    );
  }

  private JMenuBar menuBar = menuBar()
    .add(
      menu("ข้อมูลพื้นฐาน")
        .add(item("พนักงาน").which(this::staff))
        .add(item("สินค้า").which(this::product))
        .add(item("ลูกค้า").which(this::customer))
    )
    .add(
      menu("การขาย")
        .add(item("รับออร์เดอร์").which(this::order))
        .add(item("แสดงใบเสร็จรับเงิน").which(this::bill))
    )
    .add(
      menu("รายงาน")
        .add(item("การขายประจำวัน").which(this::reportDaily))
        .add(item("การขายตามชนิดสินค้า").which(this::reportByProduct))
    )
    .add(menu("บัญชีผู้ใช้").add(item("ออกจากระบบ").which(this::logout)));

  public Main() {
    super("Coffee and Tea by STOU");
    setJMenuBar(menuBar);
    packWith(this.page());
  }

  private XPanel page() {
    return panel().setPreferredSize(700, 500);
  }

  private final void logout() {
    new LogIn();
    dispose();
  }

  private final void staff() {}

  private final void product() {}

  private final void customer() {
    // getContentPane().removeAll();
    // getContentPane().add(new FormCustomer().page());
    // repaint();
    packWith(new FormCustomer().page());
  }

  private final void order() {}

  private final void reportDaily() {}

  private final void reportByProduct() {}

  private final void bill() {}
}
