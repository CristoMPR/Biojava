package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel implements ActionListener {
    private JButton buttonStart;
    private JLabel tittleLabel, msgLabel;
    private JComboBox<String> demoBox;
    private DisplayPanel displayPanel;

    // Creates every Shape we want to draw
    public Panel() {
        setLayout(null);
        buttonInit();
        labelInit();
        setBackground(Color.decode("#91bdc8"));
        displayPanel = new DisplayPanel();
        add(displayPanel);
    }

    private void buttonInit() {
        buttonStart = new JButton("Comenzar");
        demoBox = new JComboBox<>();

        buttonStart.setBounds(460, 120, 125, 30);
        demoBox.setBounds(650, 70, 200, 30);
        //demoBox.addItem("CookbookMSA");
        //demoBox.addItem("DemoAlignProteins");
        //demoBox.addItem("DemoDistanceTree");
        //demoBox.addItem("DemoLoadSubstMax");
        demoBox.addItem("TestDNANeedlemanWunsch");

        add(buttonStart);
        add(demoBox);

        buttonStart.addActionListener(this);
    }

    private void labelInit() {
        tittleLabel = new JLabel();
        msgLabel = new JLabel();

        tittleLabel.setBounds(460, 30, 200, 30);
        msgLabel.setBounds(400, 70, 250, 30);

        tittleLabel.setText("¡Bienvenido al selector de demos!");
        msgLabel.setText("Seleccione una de las siguientes demos: ");

        add(tittleLabel);
        add(msgLabel);
    }

    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == buttonStart) {
           displayPanel.clear();
           repaint();
           if(demoBox.getSelectedItem().toString().equals("TestDNANeedlemanWunsch")) {
               displayPanel.wunsch();
           }
           //TODO IF
       }
    }


}
