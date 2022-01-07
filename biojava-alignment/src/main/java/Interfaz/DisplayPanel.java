package Interfaz;

import demo.CookbookMSA;
import demo.TestDNANeedlemanWunsch;

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
        setBounds(0, 180, 1920, 820);
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

        descLabel.setBounds(100, 20, 200, 30);
        queryLabel.setBounds(100, 70, 200, 30);
        targetLabel.setBounds(100, 370, 200, 30);
        selLabel.setBounds(650, 150, 100, 30);
        expLabel.setBounds(650, 100, 400, 30);
        queryArea.setBounds(110, 100, 500, 270);
        targetArea.setBounds(110, 400, 500, 270);
        startButton.setBounds(510, 700, 100, 30);
        showButton.setBounds(910, 150, 100, 30);
        wunschBox.setBounds(780, 150, 100, 30);

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
                TestDNANeedlemanWunsch.TestDNANeedlemanWunsch(query, target);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            wunschBox.addItem(optNumber);
            optNumber++;
        }
    }

    class showAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            HashMap myMap = TestDNANeedlemanWunsch.getWunschMap();
            String[] info = (String[]) myMap.get(Integer.parseInt(wunschBox.getSelectedItem().toString()));
            displayArea = new JTextArea();
            displayArea.setBackground(Color.WHITE);
            displayArea.setText(    "Resultados de la opción nº " + wunschBox.getSelectedItem().toString() + ":" +
                                    "\nAlignment: " + info[0] +
                                    "\nNúmero de residuos idénticos: " + info[1] +
                                    "\n% de Query idéntico: " + (float) (Float.parseFloat(info[1]) / Float.parseFloat(info[2])) +
                                    "\n% de Target idéntico: " + (float) (Float.parseFloat(info[1]) / Float.parseFloat(info[3]))
                                );
            displayArea.setBounds(1100, 150, 500, 500);
            add(displayArea);
            displayArea.setVisible(true);
            displayArea.setEditable(false);
        }
    }
}
