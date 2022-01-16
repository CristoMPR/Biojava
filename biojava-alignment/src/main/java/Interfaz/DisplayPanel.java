package Interfaz;

import demo.DNANeedlemanWunsch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class DisplayPanel extends JPanel{

    private JTextArea queryArea, targetArea, displayArea;
    private JButton startButton, showButton;
    private JComboBox<Integer> wunschBox;
    private Integer optNumber = 1;

    public DisplayPanel() {
        setBounds(0, 180, 1200, 680);
        setBackground(Color.decode("#f0f8ff"));
        setLayout(null);
        setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }


    public void clear() {
        repaint();
    }

    public void wunsch() {
        JLabel descLabel = new JLabel("Introduzca las dos cadenas.");
        JLabel queryLabel = new JLabel("Query: ");
        JLabel targetLabel = new JLabel("Target: ");
        JLabel selLabel = new JLabel("Elige un opción: ");
        JLabel expLabel = new JLabel("Seleccione una las opciones que ha ingresado para ver los resultados:");
        queryArea = new JTextArea();
        targetArea = new JTextArea();
        startButton = new JButton("Procesar");
        showButton = new JButton("Mostrar");
        wunschBox = new JComboBox<>();

        descLabel.setBounds(50, 10, 200, 30);
        queryLabel.setBounds(50, 30, 200, 30);
        targetLabel.setBounds(50, 210, 200, 30);
        selLabel.setBounds(580, 50, 100, 30);
        expLabel.setBounds(580, 80, 450, 30);
        queryArea.setBounds(60, 60, 500, 150);
        targetArea.setBounds(60, 240, 500, 150);
        startButton.setBounds(460, 400, 100, 30);
        showButton.setBounds(700, 120, 100, 30);
        wunschBox.setBounds(580, 120, 100, 30);

        add(descLabel);
        add(queryLabel);
        add(targetLabel);
        add(selLabel);
        add(expLabel);
        add(queryArea);
        add(targetArea);
        add(startButton);
        add(showButton);
        add(wunschBox);

        startButton.addActionListener(new startAction());
        showButton.addActionListener(new showAction());
    }

    class startAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String query = queryArea.getText();
            String target = targetArea.getText();
            try {
                DNANeedlemanWunsch.TestDNANeedlemanWunsch(query, target);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            wunschBox.addItem(optNumber);
            optNumber++;
        }
    }

    class showAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            HashMap myMap = DNANeedlemanWunsch.getWunschMap();
            String[] info = (String[]) myMap.get(Integer.parseInt(wunschBox.getSelectedItem().toString()));
            displayArea = new JTextArea();
            displayArea.setBackground(Color.WHITE);
            displayArea.setText(    "Resultados de la opción nº " + wunschBox.getSelectedItem().toString() + ":" +
                                    "\nAlignment: " + info[0] +
                                    "\nNúmero de residuos idénticos: " + info[1] +
                                    "\n% de Query idéntico: " + (float) (Float.parseFloat(info[1]) / Float.parseFloat(info[2])) +
                                    "\n% de Target idéntico: " + (float) (Float.parseFloat(info[1]) / Float.parseFloat(info[3]))
                                );
            displayArea.setBounds(830, 120, 350, 330);
            add(displayArea);
            displayArea.setVisible(true);
            displayArea.setEditable(false);
        }
    }
}
