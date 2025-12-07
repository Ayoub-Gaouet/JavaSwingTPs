package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Ds.FormationManager;
import Tp1.*;
import Tp2.CurriculumVitae.CurriculumVitae;
import Tp2.GestionProfils.GProfils;
import Tp3.GestionEtudiant;
import Tp4.AnimationInternalFrame;

public class EcouteurMenu implements ActionListener {
    TpJava tp;

    public EcouteurMenu(TpJava tp) {
        this.tp = tp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tp.itemFlow) {
            Flow f = new Flow();
            f.setVisible(true);
            tp.desktop.add(f);
        }
        if (e.getSource() == tp.itemGrid) {
            Grid g = new Grid();
            g.setVisible(true);
            tp.desktop.add(g);
        }
        if (e.getSource() == tp.itemBorder) {
            Border b = new Border();
            b.setVisible(true);
            tp.desktop.add(b);
        }
        if (e.getSource() == tp.itemGp) {
            GProfils gp = new GProfils();
            gp.setVisible(true);
            tp.desktop.add(gp);
        }
        if (e.getSource() == tp.itemCv) {
            CurriculumVitae cv = new CurriculumVitae();
            cv.setVisible(true);
            tp.desktop.add(cv);
        }
        if (e.getSource() == tp.itemEtudiant) {
            GestionEtudiant ge = new GestionEtudiant();
            ge.setVisible(true);
            tp.desktop.add(ge);
        }
        if (e.getSource() == tp.itemAnimation) {
            AnimationInternalFrame anim = new AnimationInternalFrame();
            anim.setVisible(true);
            tp.desktop.add(anim);
        }
        if (e.getSource() == tp.itemAnimation) {
            FormationManager anim = new FormationManager();
            anim.setVisible(true);
            tp.desktop.add(anim);
        }
    }

}
