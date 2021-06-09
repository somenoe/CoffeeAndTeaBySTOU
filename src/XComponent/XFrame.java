package XComponent;

import java.awt.*;
import java.util.stream.*;
import javax.swing.*;

public class XFrame extends JFrame {

  public XFrame(String string) {
    super(string);
    setThaiFont();
  }

  private static final void setThaiFont() {
    Stream
      .of(
        "Button.font",
        "Label.font",
        "Table.font",
        "TextField.font",
        "TitledBorder.font",
        "TableHeader.font"
      )
      .forEach(
        font -> {
          UIManager.put(font, new Font("Sarabun", Font.PLAIN, 15));
        }
      );

    UIManager.put("MenuItem.font", new Font("Sarabun", Font.PLAIN, 17));
    UIManager.put("Menu.font", new Font("Sarabun", Font.BOLD, 20));
  }

  protected void packWith(Container container) {
    setContentPane(container);

    pack();
    setVisible(true);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  protected XMenu menu(String textOnMenu) {
    return new XMenu(textOnMenu);
  }

  protected XItem item(String name) {
    return new XItem(name);
  }

  public static void message(String title, String message) {
    JOptionPane.showMessageDialog(
      new XFrame("string"), //
      message, //
      title, //
      JOptionPane.INFORMATION_MESSAGE
    );
  }

  protected XMenuBar menuBar() {
    return new XMenuBar();
  }

  protected XPanel panel() {
    return new XPanel();
  }

  protected XButton button(String textOnButton) {
    return new XButton(textOnButton);
  }

  protected Component titleLabel(String string) {
    JLabel label = new JLabel(string);
    label.setFont(new Font("Sarabun", Font.BOLD, 30));
    label.setForeground(Color.RED);
    return label;
  }

  protected Component label(String textOnLabel) {
    return new JLabel(textOnLabel);
  }

  protected JScrollPane table() {
    return new XTable().tablePanel();
  }

  protected JTextField textField(int column) {
    return new JTextField(column);
  }

  private static final long serialVersionUID = 1L;
}
