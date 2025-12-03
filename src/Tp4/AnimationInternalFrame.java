package Tp4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnimationInternalFrame extends JInternalFrame {
    double a, b, a2, b2;

    boolean isRunning = false;
    int x1i = 20;
    int y1i = 250;
    int x1 = 20;
    int y1 = 250;
    int xf, yf;
    int x2i = 900;
    int y2i = 250;
    int x2 = 900;
    int y2 = 250;
    MonPanneau pCenter;

    public AnimationInternalFrame() {
        super("Animation", true, true, true, true);
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);

        JButton btnstart, btnstop;

        btnstop = new JButton("stop");
        btnstart = new JButton("start");

        JPanel pn = new JPanel();
        pn.add(btnstart);
        pn.add(btnstop);
        this.add(pn, BorderLayout.NORTH);

        pCenter = new MonPanneau();
        this.add(pCenter);

        AnimationA anim1 = new AnimationA();
        anim1.start();
        AnimationB anim2 = new AnimationB();
        anim2.start();

        btnstart.addActionListener(e -> isRunning = true);
        btnstop.addActionListener(e -> isRunning = false);

        pCenter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xf = e.getX();
                yf = e.getY();

                // calcul pente et ordonnée à l'origine pour chaque boule
                a = (double) (yf - y1i) / (double) (xf - x1i);
                b = yf - a * xf;

                a2 = (double) (yf - y2i) / (double) (xf - x2i);
                b2 = yf - a2 * xf;

                // afficher le point cliqué
                Graphics g = pCenter.getGraphics();
                g.setColor(Color.blue);
                g.drawRect(xf, yf, 100, 100);
            }
        });
    }

    class AnimationA extends Thread {
        int pas = 10;

        public void run() {
            while (true) {
                if (isRunning) {
                    // si on n'est pas encore arrivé au point cliqué, avancer
                    if (pas > 0 && x1 < xf)
                        x1 += pas;
                    else if (pas < 0 && x1 > x1i)
                        x1 += pas;
                    else
                        pas *= -1; // rebond/reverse

                    y1 = (int) (a * x1 + b);
                    pCenter.repaint();
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class AnimationB extends Thread {
        int pas = -10;

        public void run() {
            while (true) {
                if (isRunning) {
                    if (pas < 0 && x2 > xf)
                        x2 += pas; // vers la gauche
                    else if (pas > 0 && x2 < x2i)
                        x2 += pas; // vers la droite
                    else
                        pas *= -1; // rebond/reverse

                    y2 = (int) (a2 * x2 + b2);
                    pCenter.repaint();
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class MonPanneau extends JPanel {
        MonPanneau() {
            this.setBackground(Color.white);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.red);
            g.fillOval(x1, y1, 100, 100);

            g.setColor(Color.green);
            g.fillOval(x2, y2, 100, 100);
        }
    }
}
