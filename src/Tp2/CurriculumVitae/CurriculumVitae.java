package Tp2.CurriculumVitae;

import javax.swing.*;
import java.awt.*;

public class CurriculumVitae extends JInternalFrame {
    // Declaration des composantes
    JButton btnVal, btnQuitter;

    public CurriculumVitae() {
        // Creation de l'interface IHM
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("Projet Java");

        // positionnement
        this.setLayout(new BorderLayout());

        btnVal = new JButton("Valider");
        btnVal.setPreferredSize(new Dimension(200, 100));
        this.add(btnVal, BorderLayout.WEST);

        btnQuitter = new JButton("Quitter");
        btnQuitter.setPreferredSize(new Dimension(0, 100));

        this.add(btnQuitter, BorderLayout.SOUTH);

        this.setVisible(true);

        // Evenement : Ecouteurs
        btnQuitter.addActionListener(new Ecouteur(this));
        btnVal.addActionListener(new Ecouteur(this));

    }

}
