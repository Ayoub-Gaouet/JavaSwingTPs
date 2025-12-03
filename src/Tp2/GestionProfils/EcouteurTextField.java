package Tp2.GestionProfils;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;

public class EcouteurTextField implements FocusListener {
    private JLabel helpLabel;
    GProfils gProfils;

    public EcouteurTextField(JLabel helpLabel, GProfils gProfils) {
        this.helpLabel = helpLabel;
        this.gProfils = gProfils;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == gProfils.textFieldNom) {
            helpLabel.setText("HELP: Veuillez saisir le nom");
        } else if (e.getSource() == gProfils.textFieldPrenom) {
            helpLabel.setText("HELP: Veuillez saisir le prenom");
        } else if (e.getSource() == gProfils.textFieldPseudo) {
            helpLabel.setText("HELP: Veuillez saisir le pseudo");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        helpLabel.setText("HELP: "); // Réinitialise le texte du label
    }
}
