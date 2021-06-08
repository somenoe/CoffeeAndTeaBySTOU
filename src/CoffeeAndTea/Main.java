package CoffeeAndTea;

import java.util.ArrayList;

import javax.swing.*;

import XComponent.XFrame;
import XComponent.XPanel;

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

    private JMenuBar menuBar = menuBar()//
            .add(menu("ข้อมูลพื้นฐาน")//
                    .add(item("พนักงาน"))//
                    .add(item("สินค้า"))//
                    .add(item("ลูกค้า")))//
            .add(menu("การขาย")//
                    .add(item("รับออร์เดอร์"))//
                    .add(item("จัดการสินค้า"))//
                    .add(item("บันทึกข้อมูล")))//
            .add(menu("รายงาน")//
                    .add(item("การขายประจำวัน"))//
                    .add(item("การขายตามชนิดสินค้า"))//
                    .add(item("ใบเสร็จรับเงิน")))
            .add(menu("บัญชีผู้ใช้")//
                    .add(item("ออกจากระบบ")//
                            .which(this::logout)));

    private final void logout() {
        new LogIn();
        dispose();
    };

    public Main() {
        super("Coffee and Tea by STOU");
        setJMenuBar(menuBar);
        packWith(this.page());
    }

    XPanel page() {
        return panel()//
                .setBorderLayout()//
                .add("S", buttonPanel().edged())//
                .add("W", formPanel())//
                .add(panel()//
                        .setCardLayout(0, 0)//
                        .add(table())//
                        .setBorderEmpty(20, 20, 0, 20))//
        ;
    }

    private XPanel formPanel() {
        return panel()//
                .setRow(5)//
                .add(detail("เลขที่ลูกค้า (CUST_NUM)"))//
                .add(detail("ชี่อลูกค้า (CUST_NAME)"))//
                .add(detail("ที่อยู่ลูกค้า (CUST_ADDR)"))//
                .add(detail("โทรศัพท์ลูกค้า (CUST_PHONE)"))//
                .add(detail("อีเมล์ลูกค้า (CUST_MAIL)"))//
                .edged(20, 20, 0, 0);
    }

    private ArrayList<JTextField> details = new ArrayList<>();

    private XPanel detail(String title) {
        JTextField detail = new JTextField(15);
        details.add(detail);

        return panel()//
                .setBorderTitle(title)//
                .add(detail);
    }

    private XPanel buttonPanel() {
        return panel()//
                .setFlowLayoutRight()//
                .add(button("เพิ่มข้อมูล")//
                        .which(this::insert))//
                .add(button("แก้ไข")//
                        .which(this::update))//
                .add(button("ลบ")//
                        .which(this::delete))
                .add(button("ยกเลิก")//
                        .which(this::cancle))//
        ;
    }

    private void insert() {
    }

    private void cancle() {
    }

    private void update() {
    }

    private void delete() {
    }
}
