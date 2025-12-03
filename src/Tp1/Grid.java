package Tp1;

import javax.swing.*;
import java.awt.*;

public class Grid extends JInternalFrame {
    public Grid() {
        super("Grid layout",
                true,
                true,
                true,
                true);
        this.setSize(600, 400);
        this.setLayout(new GridLayout(3, 3)); // 3x3 Grid

        // Add buttons to demonstrate Grid Layout
        for (int i = 1; i <= 9; i++) {
            this.add(new JButton("Button " + i));
        }

        this.setVisible(true);
    }
}
