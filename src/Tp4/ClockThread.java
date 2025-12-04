package Tp4;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClockThread extends Thread {

    private JLabel clockLabel;
    private boolean running = true;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public ClockThread(JLabel label) {
        this.clockLabel = label;
    }

    @Override
    public void run() {
        while (running) {
            String time = LocalDateTime.now().format(formatter);

            clockLabel.setText(time);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }

    public void stopClock() {
        running = false;
    }
}
