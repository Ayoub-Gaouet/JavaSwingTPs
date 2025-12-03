package app;

import javax.swing.*;
import java.awt.*;

public class TpJava extends JFrame {
    // Declaration des variables
    JMenuBar menuBar;
    JMenu menuTp1, menuTp2, menuTp3, menuTp4;
    JMenuItem itemFlow, itemGrid, itemBorder, itemCv, itemGp, itemEtudiant, itemAnimation;
    JDesktopPane desktop;

    public TpJava() {
        this.setTitle("Projet java");
        this.setSize(1600, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Creation
        menuBar = new JMenuBar();
        menuTp1 = new JMenu("TP1");
        itemFlow = new JMenuItem("Flow layout");
        menuTp1.add(itemFlow);
        itemGrid = new JMenuItem("Grid layout");
        menuTp1.add(itemGrid);
        itemBorder = new JMenuItem("Border layout");
        menuTp1.add(itemBorder);
        menuBar.add(menuTp1);

        menuTp2 = new JMenu("TP 2");
        itemCv = new JMenuItem("Curriculum Vitae");
        itemGp = new JMenuItem("Gestion profils");
        menuTp2.add(itemCv);
        menuTp2.add(itemGp);
        menuBar.add(menuTp2);

        menuTp3 = new JMenu("TP 3");
        itemEtudiant = new JMenuItem("Gestion etudiants");
        menuTp3.add(itemEtudiant);
        menuBar.add(menuTp3);

        menuTp4 = new JMenu("TP 4");
        itemAnimation = new JMenuItem("Animation");
        menuTp4.add(itemAnimation);
        menuBar.add(menuTp4);

        this.setJMenuBar(menuBar);

        desktop = new JDesktopPane();
        this.setLayout(new BorderLayout());
        this.add(desktop, BorderLayout.CENTER);

        // event

        itemFlow.addActionListener(new EcouteurMenu(this));
        itemGrid.addActionListener(new EcouteurMenu(this));
        itemBorder.addActionListener(new EcouteurMenu(this));
        itemGp.addActionListener(new EcouteurMenu(this));
        itemCv.addActionListener(new EcouteurMenu(this));
        itemEtudiant.addActionListener(new EcouteurMenu(this));
        itemAnimation.addActionListener(new EcouteurMenu(this));

    }
}
