package XComponent;

import java.awt.event.*;

import javax.swing.JMenuItem;

public class XItem extends JMenuItem {
    XItem(String name) {
        super(name);
    }

    public XItem which(Runnable function) {
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                function.run();

            }
        });
        return this;
    }
}
