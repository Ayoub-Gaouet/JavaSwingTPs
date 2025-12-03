package Tp1;

import javax.swing.*;
import java.awt.FlowLayout;

public class Flow extends JInternalFrame {
    // Decalaration des boutons
    public Flow() {
        super("Flow layout",
                true,
                true,
                true,
                true);
        this.setSize(600, 400);
        this.setLayout(new FlowLayout());
        // Add buttons to demonstrate Flow Layout
        for (int i = 1; i <= 5; i++) {
            this.add(new JButton("Button " + i));
        }
        this.setVisible(true);

    }

}
