package CoffeeAndTea;

import XComponent.XConnect;
import XComponent.XFrame;
import XComponent.XPanel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class LogIn extends XFrame {

  public static void main(String[] args) {
    LogIn login = new LogIn();
    login.packWith(login.page());
  }

  public LogIn() {
    super("Coffee and Tea by STOU");
  }

  XPanel page() {
    return panel() //
      .setRow(3) //
      .add(
        panel() //
          .setFlowLayoutRight() //
          .add(label("ชื่อผู้ใช้ : ")) //
          .add(usernameField)
      ) //
      .add(
        panel() //
          .setFlowLayoutRight() //
          .add(label("รหัสผ่าน : ")) //
          .add(passwordField)
      ) //
      .add(
        panel() //
          .add(
            button("เข้าสู่ระบบ") //
              .which(this::check)
          )
      ) //
      .edged(20, 0, 10, 0) //
      .lined() //
      .fixed();
  }

  private JTextField usernameField = textField(15);
  private JPasswordField passwordField = new JPasswordField() {
    {
      setPreferredSize(usernameField.getPreferredSize());
    }
  };

  private String firstName, lastName, permission;

  private String getUsername() {
    return usernameField.getText().trim();
  }

  private String getPassword() {
    return new String(passwordField.getPassword());
  }

  private void check() {
    String currentUsername = getUsername();
    String currentPassword = getPassword();

    if (currentUsername.length() > 0 && currentPassword.length() > 0) {
      if (isValid(currentUsername, currentPassword)) {
        Main main = new Main();
        main.setJMenuBar(main.menuBar);
        main.setAccount(
          firstName,
          lastName,
          (permission == "ADMIN") ? "ผู้ดูแลระบบ" : "พนักงาน"
        );
        main.packWith(main.page());

        this.dispose();
      } else {
        message("ผลการเข้าสู่ระบบ", "ชื่อผู้ใช้ หรือ รหัสผ่าน ไม่ถูกต้อง");
      }
    } else {
      message("ผลการเข้าสู่ระบบ", "กรุณาใส่ ชื่อผู้ใช้ และ รหัสผ่าน");
    }
  }

  private boolean isValid(String username, String password) {
    Connection connection = XConnect.getConnection();

    try {
      String queryString =
        "SELECT * FROM staff where USERNAME LIKE '%" +
        username +
        "%' AND PASSWORD LIKE '%" +
        password +
        "%'";

      ResultSet results = connection
        .createStatement()
        .executeQuery(queryString);

      if (!results.next()) {
        return false;
      } else {
        firstName = results.getObject(3).toString();
        lastName = results.getObject(4).toString();
        permission = results.getObject(5).toString();

        return true;
      }
    } catch (SQLException sql) {
      System.out.println(sql);
      return false;
    }
  }
}
