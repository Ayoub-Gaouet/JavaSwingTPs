package Tp2.CurriculumVitae;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ecouteur implements ActionListener {

    CurriculumVitae cv;

    public Ecouteur(CurriculumVitae cv) {
        this.cv = cv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cv.btnQuitter)
            System.exit(0);
        if (e.getSource() == cv.btnVal) {
            // Ecriture dans un fichier
            File f = new File("cv.html");// declaration chemin de fichier
            try {
                FileWriter fw = new FileWriter(f, false);
                fw.write("<html>" +
                        "<head><title> CV </title></head>" +
                        "<body>" +
                        "<h1> Curriculum Vitae </h1>" +
                        "<p><a href='https://ayoubgaouet.vercel.app'>https://ayoubgaouet.vercel.app</a></p>" +
                        "</body>" +
                        "</html>");
                fw.close();// enregistrement

                // lancement du fichier
                Desktop.getDesktop().open(f);

            } catch (IOException ex) {
                System.out.println("Erreur d'ecriture :" + ex.getMessage());
            }
            // ouverture du fichier en mode ecriture, mode append false: ecraser le contenu
        }
    }
}
