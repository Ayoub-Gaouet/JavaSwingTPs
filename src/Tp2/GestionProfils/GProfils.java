package Tp2.GestionProfils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GProfils extends JInternalFrame {

    JLabel labelNom, labelPrenom, labelPseudo, labelHelp;
    public JTextField textFieldNom, textFieldPrenom, textFieldPseudo;
    JButton buttonValider;
    JList listProfils;
    JSplitPane splitPane;
    DefaultListModel listModel;
    JTabbedPane tabbedPane;

    public GProfils() {
        super("Gestion profils",
                true,
                true,
                true,
                true);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(1600, 600);

        labelNom = new JLabel("Nom");
        textFieldNom = new JTextField(15);// 15 * M
        textFieldNom.setText("Saisir le nom");
        labelPrenom = new JLabel("Prenom");
        textFieldPrenom = new JTextField(15);
        textFieldPrenom.setText("Saisir le prénom");
        labelPseudo = new JLabel("Pseudo");
        textFieldPseudo = new JTextField(15);// 15 * M
        textFieldPseudo.setText("Saisir le pseudo");
        buttonValider = new JButton("Valider");

        JPanel pn = new JPanel();
        pn.setLayout(new FlowLayout());
        pn.add(labelNom);
        pn.add(textFieldNom);
        pn.add(labelPrenom);
        pn.add(textFieldPrenom);
        pn.add(labelPseudo);
        pn.add(textFieldPseudo);
        pn.add(buttonValider);

        labelHelp = new JLabel("HELP:");
        this.setLayout(new BorderLayout());
        this.add(labelHelp, BorderLayout.SOUTH);
        this.add(pn, BorderLayout.NORTH);
        splitPane = new JSplitPane();

        listModel = new DefaultListModel();

        listProfils = new JList(listModel);
        listProfils.setPreferredSize(new Dimension(200, 0));
        splitPane.setLeftComponent(listProfils);

        tabbedPane = new JTabbedPane();

        splitPane.setRightComponent(tabbedPane);

        this.add(splitPane, BorderLayout.CENTER);

        this.setVisible(true);

        // Evenement
        buttonValider.addActionListener(new ActionListener() {
            // classe anonyme
            @Override
            public void actionPerformed(ActionEvent e) {
                String pseudo = textFieldPseudo.getText();
                listModel.addElement(pseudo);
                JOptionPane.showMessageDialog(GProfils.this, "DONE");
            }
        });

        textFieldNom.addFocusListener(new EcouteurFocus(this));
        textFieldPrenom.addFocusListener(new EcouteurFocus(this));
        textFieldPseudo.addFocusListener(new EcouteurFocus(this));
        /*
         * modifier la couleur de chaque label lors deu passage de la souris
         * => utiliser un ecouteur label
         * modifier le texte du label help lors du passage du souris sur le text filed
         * => utiliser Ecouteur TextField
         */
        // Ajout des écouteurs pour les labels
        labelNom.addMouseListener(new EcouteurLabel(labelNom));
        labelPrenom.addMouseListener(new EcouteurLabel(labelPrenom));
        labelPseudo.addMouseListener(new EcouteurLabel(labelPseudo));

        // Ajout de l'écouteur pour les champs de texte
        textFieldNom.addFocusListener(new EcouteurTextField(labelHelp, this));
        textFieldPrenom.addFocusListener(new EcouteurTextField(labelHelp, this));
        textFieldPseudo.addFocusListener(new EcouteurTextField(labelHelp, this));

        listProfils.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = listProfils.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        String pseudo = listModel.getElementAt(index).toString();
                        FormPanel p = new FormPanel();
                        tabbedPane.addTab(pseudo, p);
                    }
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    // afficher un popup menu
                    int index = listProfils.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        listProfils.setSelectedIndex(index);
                        MyPopupMenu jpm = new MyPopupMenu(GProfils.this);
                        jpm.show(listProfils, e.getX(), e.getY());
                    }
                }
            }
        });
    }
}
