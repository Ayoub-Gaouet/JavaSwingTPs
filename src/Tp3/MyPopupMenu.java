package Tp3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPopupMenu extends JPopupMenu {
    public MyPopupMenu(final JTable table, final MyEtudiantTableModel model) {
        JMenuItem jmi_supprimer = new JMenuItem("Supprimer");

        this.add(jmi_supprimer);
        jmi_supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int viewRow = table.getSelectedRow();
                if (viewRow >= 0) {
                    int modelRow = table.convertRowIndexToModel(viewRow);
                    boolean removed = model.removeRow(modelRow);
                    if (!removed) {
                        JOptionPane.showMessageDialog(table, "Erreur lors de la suppression.", "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
