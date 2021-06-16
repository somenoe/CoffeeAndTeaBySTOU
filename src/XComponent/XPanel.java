package XComponent;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class XPanel extends JPanel {

  public XPanel() {
    super();
  }

  public XPanel setCardLayout(int hgap, int vgap) {
    this.setLayout(new CardLayout(hgap, vgap));
    return this;
  }

  public XPanel setBorderLayout() {
    setLayout(new BorderLayout());
    return this;
  }

  public XPanel center() {
    this.setAlignmentX(CENTER_ALIGNMENT);
    return this;
  }

  public XPanel setFlowLayoutCenter() {
    setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
    return this;
  }

  public XPanel setFlowLayoutRight() {
    setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 0));
    return this;
  }

  public XPanel setBorderTitle(String title) {
    setBorder(BorderFactory.createTitledBorder(title));
    return this;
  }

  public XPanel setBorderLine() {
    setBorder(BorderFactory.createLineBorder(Color.BLACK));
    return this;
  }

  public XPanel setColumn(int column) {
    setLayout(new GridLayout(1, column, 10, 10));
    return this;
  }

  public XPanel setRow(int row) {
    setLayout(new GridLayout(row, 1, 10, 10));
    return this;
  }

  public XPanel add(Component comp) {
    super.add(comp);
    return this;
  }

  public XPanel add(String position, Component comp) {
    switch (position) {
      case "N":
        add(comp, BorderLayout.NORTH);
        break;
      case "S":
        add(comp, BorderLayout.SOUTH);
        break;
      case "W":
        add(comp, BorderLayout.WEST);
        break;
      case "C":
        add(comp, BorderLayout.CENTER);
        break;
      case "E":
        add(comp, BorderLayout.EAST);
        break;
    }
    return this;
  }

  public XPanel lined() {
    return new XPanel() //
      .setBorderLine() //
      .add(this);
  }

  public XPanel edged() {
    return new XPanel() //
      .setCardLayout(20, 20) //
      .add(this);
  }

  public XPanel edged(int top, int left, int bottom, int right) {
    return new XPanel() //
      .setBorderEmpty(top, left, bottom, right) //
      .add(this);
  }

  public XPanel setBorderEmpty(int top, int left, int bottom, int right) {
    setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));

    return this;
  }

  public XPanel fixed() {
    return new XPanel() //
      .setGridBagLayout() //
      .setPreSize(this.getPreferredSize()) //
      .add(this);
  }

  public XPanel panel() {
    return new XPanel().add(this);
  }

  public XPanel setGridBagLayout() {
    setLayout(new GridBagLayout());
    return this;
  }

  public XPanel setPreferredSize(int width, int height) {
    setPreferredSize(new Dimension(width, height));
    return this;
  }

  private XPanel setPreSize(Dimension size) {
    setPreferredSize(size);
    return this;
  }

  public XPanel focus() {
    setBackground(Color.cyan);
    return this;
  }

  public XPanel then(Runnable method) {
    method.run();
    return this;
  }

  private static final long serialVersionUID = 1L;
}
