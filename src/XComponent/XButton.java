package XComponent;

import java.awt.event.*;
import javax.swing.*;

public class XButton extends JButton {

    XButton(String text) {
        super(text);
    }

    public XButton which(Runnable function) {
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                function.run();

            }
        });
        return this;
    }

    private static final long serialVersionUID = 1L;
}
