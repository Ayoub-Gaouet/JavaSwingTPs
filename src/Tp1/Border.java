package Tp1;

import javax.swing.*;
import java.awt.*;

public class Border extends JInternalFrame {
    public Border() {
        super("Border Layout",
                true,
                true,
                true,
                true);
        this.setSize(600, 400);
        this.setLayout(new BorderLayout());
        // Add buttons to demonstrate Border Layout
        this.add(new JButton("North"), BorderLayout.NORTH);
        this.add(new JButton("South"), BorderLayout.SOUTH);
        this.add(new JButton("East"), BorderLayout.EAST);
        this.add(new JButton("West"), BorderLayout.WEST);
        this.add(new JButton("Center"), BorderLayout.CENTER);

        this.setVisible(true);
    }
}
