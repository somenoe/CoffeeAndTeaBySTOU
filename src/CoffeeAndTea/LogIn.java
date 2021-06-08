package CoffeeAndTea;

import javax.swing.*;

import XComponent.XFrame;
import XComponent.XPanel;

public class LogIn extends XFrame {
    public static void main(String[] args) {
        new LogIn();
    }

    public LogIn() {
        super("Coffee and Tea by STOU");
        packWith(this.page());
    }

    XPanel page() {
        return panel()//
                .setRow(3)//
                .add(panel()//
                        .setFlowLayoutRight()//
                        .add(label("ชื่อผู้ใช้ : "))//
                        .add(username))//
                .add(panel()//
                        .setFlowLayoutRight()//
                        .add(label("รหัสผ่าน : "))//
                        .add(password))//
                .add(panel()//
                        .add(button("เข้าสู่ระบบ")//
                                .which(this::check)))//
                .edged(20, 0, 10, 0)//
                .lined()//
                .fixed();
    }

    private JTextField username = textField(15);
    private JPasswordField password = new JPasswordField() {
        {
            setPreferredSize(username.getPreferredSize());
        }
    };

    private void check() {
        String currentUsername = username.getText().trim();
        String currentPassword = new String(password.getPassword());

        System.out.println("username : " + currentUsername);
        System.out.println("password : " + currentPassword);

        if (isValid(currentUsername, currentPassword)) {
            new Main();
            dispose();
        } else {
            String message = "ชื่อผู้ใช้ หรือ รหัสผ่าน ไม่ถูกต้อง";
            JOptionPane.showMessageDialog(this, message);
        }
    }

    // NEXT: Check in database.
    private boolean isValid(String username, String password) {
        // for Test
        String validUsername = "";
        String validPassword = "";

        return validUsername.equals(username) && validPassword.equals(password);
    }
}
