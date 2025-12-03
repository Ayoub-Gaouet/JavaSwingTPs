package Tp2.GestionProfils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPopupMenu extends JPopupMenu {

    public MyPopupMenu(GProfils gProfils) {
        JMenuItem jmi_supprimer = new JMenuItem("Supprimer");
        JMenuItem jmi_modifier = new JMenuItem("Modifier");
        JMenuItem jmi_supprimer_tout = new JMenuItem("Supprimer tout");
        this.add(jmi_supprimer);
        this.add(jmi_modifier);
        this.add(new JSeparator());
        this.add(jmi_supprimer_tout);
        jmi_supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                int index = gProfils.listProfils.getSelectedIndex();
                if (index >= 0) {
                    gProfils.listModel.removeElementAt(index);
                    // Check if the tab exists before removing it
                    if (index < gProfils.tabbedPane.getTabCount()) {
                        gProfils.tabbedPane.removeTabAt(index);
                    }
                }
            }
        });

        jmi_modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = gProfils.listProfils.getSelectedIndex();
                if (index >= 0) {
                    String currentPseudo = (String) gProfils.listModel.getElementAt(index);
                    String newPseudo = JOptionPane.showInputDialog(gProfils, "Nouveau pseudo:", currentPseudo);
                    if (newPseudo != null && !newPseudo.trim().isEmpty()) {
                        gProfils.listModel.setElementAt(newPseudo, index);
                        // Update tab title if it exists
                        if (index < gProfils.tabbedPane.getTabCount()) {
                            gProfils.tabbedPane.setTitleAt(index, newPseudo);
                        }
                    }
                }
            }
        });

        jmi_supprimer_tout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gProfils.listModel.clear();
                gProfils.tabbedPane.removeAll();
            }
        });
    }
}
