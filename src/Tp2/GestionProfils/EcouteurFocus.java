package Tp2.GestionProfils;

import javax.swing.*;
import java.awt.event.FocusListener;

public class EcouteurFocus implements FocusListener {
    GProfils gp;

    public EcouteurFocus(GProfils gProfils) {
        this.gp = gProfils;
    }

    @Override
    public void focusGained(java.awt.event.FocusEvent e) {
        JTextField tfsource = (JTextField) e.getSource();
        tfsource.setText("");
    }

    @Override
    public void focusLost(java.awt.event.FocusEvent e) {
        if (gp.textFieldNom.getText().isEmpty()) {
            gp.textFieldNom.setText("Saisir le nom");
        }
        if (gp.textFieldPrenom.getText().isEmpty()) {
            gp.textFieldPrenom.setText("Saisir le prénom");
        }
        if (gp.textFieldPseudo.getText().isEmpty()) {
            gp.textFieldPseudo.setText("Saisir le pseudo");
        }
    }
}
