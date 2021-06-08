package XComponent;

import javax.swing.JMenuBar;

public class XMenuBar extends JMenuBar {
    XMenuBar() {
        super();
    }

    public XMenuBar add(XMenu menu) {
        super.add(menu);
        return this;
    }
}
