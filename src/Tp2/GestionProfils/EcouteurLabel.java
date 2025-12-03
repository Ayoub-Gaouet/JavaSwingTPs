package Tp2.GestionProfils;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class EcouteurLabel implements MouseListener {
    private JLabel label;

    public EcouteurLabel(JLabel label) {
        this.label = label;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        label.setForeground(Color.RED); // Change la couleur en rouge
    }

    @Override
    public void mouseExited(MouseEvent e) {
        label.setForeground(Color.BLACK); // Rétablit la couleur par défaut
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
