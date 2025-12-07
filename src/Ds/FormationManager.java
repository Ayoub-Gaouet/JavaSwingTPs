package Ds;

import DataBase.FormationDAO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class FormationManager extends JFrame {

    FormationDAO dao = new FormationDAO();
    JTable jtable;
    JLabel lbIdFormation, lbTitre, lbFormateur, lbHeuresTotal, lbHeuresRealisees, lbStatut;
    JTextField tfIdFormation, tfTitre, tfFormateur, tfHeuresTotal, tfHeuresRealisees;
    JComboBox<String> cbStatut;
    JButton btnIncrement, btnValider;
    JCheckBox cbboxFiltrer;
    MyFormationTableModel model;
    public FormationManager() {
        this.setTitle("Gestion Formations");
        this.setSize(1100, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // --- Panel formulaire ---
        JPanel ps = new JPanel(new GridLayout(6, 2, 10, 10));

        // id
        lbIdFormation = new JLabel("ID Formation :");
        tfIdFormation = new JTextField(10);
        tfIdFormation.setEditable(false);

        // titre
        lbTitre = new JLabel("Titre Formation :");
        tfTitre = new JTextField(15);

        // formateur
        lbFormateur = new JLabel("Formateur :");
        tfFormateur = new JTextField(15);

        // heures total
        lbHeuresTotal = new JLabel("Heures Total :");
        tfHeuresTotal = new JTextField(10);

        // heures réalisées
        lbHeuresRealisees = new JLabel("Heures Réalisées :");
        tfHeuresRealisees = new JTextField("0", 5);
        btnIncrement = new JButton("+");

        btnIncrement.addActionListener(e -> {
            try {
                int val = Integer.parseInt(tfHeuresRealisees.getText());
                tfHeuresRealisees.setText(String.valueOf(val + 1));
            } catch (Exception ex) {
                tfHeuresRealisees.setText("0");
            }
        });

        // statut
        lbStatut = new JLabel("Statut :");
        cbStatut = new JComboBox<>(new String[]{"En cours", "Terminé", "Suspendu"});

        // bouton valider
        btnValider = new JButton("Valider");

        // checkbox
        cbboxFiltrer=new JCheckBox("Filtrer");

        // ---- Ajout des composants ----
        ps.add(lbIdFormation);      ps.add(tfIdFormation);
        ps.add(lbTitre);            ps.add(tfTitre);
        ps.add(lbFormateur);        ps.add(tfFormateur);
        ps.add(lbHeuresTotal);      ps.add(tfHeuresTotal);

        // ligne heures réalisées avec bouton +
        ps.add(lbHeuresRealisees);
        JPanel pe = new JPanel();
        pe.add(tfHeuresRealisees);
        pe.add(btnIncrement);
        ps.add(pe);

        // statut
        ps.add(lbStatut);
        ps.add(cbStatut);

        // panel sud
        JPanel psouth = new JPanel();
        //psouth.add(ps);
        //psouth.add(btnValider);
        psouth.add(cbboxFiltrer);

        this.add(psouth, BorderLayout.SOUTH);

        // JTable
        ResultSet rs = dao.selectFormation("select * from Formation");
        model = new MyFormationTableModel(rs, dao);
        jtable = new JTable(model);
        JScrollPane scroll = new JScrollPane(jtable);
        jtable.addMouseListener(new MouseAdapter() {
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
                int row = jtable.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    jtable.setRowSelectionInterval(row, row);
                } else {
                    jtable.clearSelection();
                }
                // Instancier et afficher le menu contextuel pour la table
                MyPopupMenu menu = new MyPopupMenu(jtable, model);
                menu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        scroll.setBorder(BorderFactory.createTitledBorder("Liste des formations"));
        add(scroll, BorderLayout.CENTER);

        cbboxFiltrer.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // filtrer
            }
        });

    }

    public static void main(String[] args) {
        new FormationManager().setVisible(true);
    }
}
