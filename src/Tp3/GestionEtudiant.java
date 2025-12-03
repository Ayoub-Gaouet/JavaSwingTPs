package Tp3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.ResultSet;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import DataBase.EtudiantDAO;

public class GestionEtudiant extends JInternalFrame {
    EtudiantDAO dao = new EtudiantDAO();
    JLabel labelNom, labelPrenom, labelCIN, labelMoyenne;
    JTextField textNom, textPrenom, textCIN, textMoyenne;
    JButton buttonValider;
    MyEtudiantTableModel model;
    JTable jt_etudiants;

    public GestionEtudiant() {
        super("Gestion etudiants",
                true,
                true,
                true,
                true);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(1600, 600);
        labelNom = new JLabel("Nom");
        labelPrenom = new JLabel("Prenom");
        labelCIN = new JLabel("CIN");
        labelMoyenne = new JLabel("Moyenne");
        textNom = new JTextField(15);
        textPrenom = new JTextField(15);
        textCIN = new JTextField(15);
        textMoyenne = new JTextField(15);
        buttonValider = new JButton("Valider");
        JPanel panel = new JPanel();
        panel.add(labelNom);
        panel.add(textNom);
        panel.add(labelPrenom);
        panel.add(textPrenom);
        panel.add(labelCIN);
        panel.add(textCIN);
        panel.add(labelMoyenne);
        panel.add(textMoyenne);
        panel.add(buttonValider);
        this.add(panel, BorderLayout.NORTH);
        ResultSet rs = dao.selectEtudiant("select * from etudiant");
        model = new MyEtudiantTableModel(rs, dao);
        jt_etudiants = new JTable(model);
        this.add(new JScrollPane(jt_etudiants), BorderLayout.CENTER);
        jt_etudiants.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopup(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger())
                    showPopup(e);
            }

            private void showPopup(MouseEvent e) {
                int row = jt_etudiants.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    jt_etudiants.setRowSelectionInterval(row, row);
                } else {
                    jt_etudiants.clearSelection();
                }
                // Instancier et afficher le menu contextuel pour la table
                MyPopupMenu menu = new MyPopupMenu(jt_etudiants, model);
                menu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        // EVENT
        buttonValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cin = Integer.parseInt(textCIN.getText());
                String nom = textNom.getText();
                String prenom = textPrenom.getText();
                double moyenne = Double.parseDouble(textMoyenne.getText());
                int a = dao.insertEtudiant(cin, nom, prenom, moyenne);

                if (a != 0) {
                    // Met à jour l'interface après l'insertion
                    model.ajoutEtudiant(cin, nom, prenom, moyenne);
                    JOptionPane.showMessageDialog(null, "Etudiant enregistré avec succès !");
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement de l'étudiant.");
                }
            }
        });
    }

}
