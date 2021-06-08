package XComponent;

import javax.swing.*;

public class XMenu extends JMenu {
    XMenu(String textOnMenu) {
        super(textOnMenu);
    }

    public XMenu add(XItem item) {
        super.add(item);
        return this;
    }
}