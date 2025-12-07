package DS.V_Formation;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class FormationManager extends JFrame {

    JTable jtable;
    JLabel lbIdFormation, lbTitre, lbFormateur, lbHeuresTotal, lbHeuresRealisees, lbStatut;
    JTextField tfIdFormation, tfTitre, tfFormateur, tfHeuresTotal, tfHeuresRealisees;
    JComboBox<String> cbStatut;
    JButton btnIncrement, btnValider;
    JCheckBox cbboxFiltrer;

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
        jtable = new JTable();
        JScrollPane scroll = new JScrollPane(jtable);
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
