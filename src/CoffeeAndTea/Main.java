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
          // new Main().login();
          /* FOR TEST */
          new Main().render();
        }
      }
    );
  }

  public JMenuBar menuBar = menuBar()
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
  }

  private String firstName, lastName, permission;

  public void setAccount(String firstName, String lastName, String permission) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.permission = permission;
  }

  public XPanel page() {
    return panel() //
      .setRow(2)
      .add(labelTitle(permission))
      .add(
        panel() //
          .add(label(firstName))
          .add(label(lastName))
      );
  }

  public void render() {
    setJMenuBar(menuBar);
    packWith(page());
  }

  public final void login() {
    LogIn login = new LogIn();
    new Main().packWith(login.page());
  }

  private final void logout() {
    login();
    dispose();
  }

  private final void staff() {
    packWith(new Staff().page());
  }

  private final void product() {}

  private final void customer() {
    packWith(new Customer().page());
  }

  private final void order() {}

  private final void reportDaily() {}

  private final void reportByProduct() {}

  private final void bill() {}
}
