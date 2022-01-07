package Interfaz;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private Panel panel;

    public Frame() {
        panel = new Panel();
        setTitle("Biojava - Alignment: Demos Selector");
        add(panel);
        setSize(1920, 1000);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
