package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Light;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GateView extends FixedPanel implements ItemListener, MouseListener {
    private final Switch[] switches;
    private final Gate gate;

    private final JCheckBox[] inputBoxes;

    private final Image image;

    private Light light;
    private Color color;

    private int r;
    private int g;
    private int b;

    private Boolean colorIsSetted; // Usamos um booleano para saber se o usuário já definiu a cor a ser usada

    public GateView(Gate gate) {
        super(320, 230); // Mudamos o tamanho da janela inicializada, calculado para caber todas as imagens
                                       // redimensionadas (cada uma possui 600x325 pixels)
        colorIsSetted = false;

        int r = 255;
        int g = 0;
        int b = 0;

        light = new Light();
        this.gate = gate;

        int inputSize = gate.getInputSize();

        switches = new Switch[inputSize];
        inputBoxes = new JCheckBox[inputSize];

        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            inputBoxes[i] = new JCheckBox();

            gate.connect(i, switches[i]);
        }

        if (inputBoxes.length>1){
                add(inputBoxes[0], 20, 71, 150, 25);
                add(inputBoxes[1], 20, 131, 150, 25);
        } else {
            add(inputBoxes[0], 20, 98, 150, 25);
        }

        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        this.image = getToolkit().getImage(url);

        addMouseListener(this);
        for (JCheckBox inputBox : inputBoxes) {
            inputBox.addItemListener(this);
        }

        update();
    }

    private void update() {
        for (int i = 0; i < gate.getInputSize(); i++) {
            if (inputBoxes[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }

        light.connect(0, gate);

        if (!colorIsSetted){
            this.r = 255;
            this.g = 0;
            this.b = 0;
        }

        this.light.setR(r);
        this.light.setG(g);
        this.light.setB(b);


        this.color = new Color(light.getR(), light.getG(), light.getB());
        repaint();
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();
        if (inputBoxes.length > 1){
            if (Math.sqrt(Math.pow(x-(290+5), 2) + Math.pow(y-(107+5), 2)) < 5) {         // função que calcula um círculo
                color = JColorChooser.showDialog(this, null, color);
                this.r = color.getRed();
                this.g = color.getGreen();
                this.b = color.getBlue();
                colorIsSetted = true;
                repaint();
            }
        } else {
            if (Math.sqrt(Math.pow(x-(288+7), 2) + Math.pow(y-(104+7), 2)) < 7) {        // função que calcula um círculo
                color = JColorChooser.showDialog(this, null, color);
                this.r = color.getRed();
                this.g = color.getGreen();
                this.b = color.getBlue();
                colorIsSetted = true;
                repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 10, 30, 300, 162, this);
        g.setColor(color);

        if (inputBoxes.length > 1) {
            g.fillOval(290, 107, 10, 10); // A função fillOval cria uma elipse que
        } else {                                               // se definida corretamente, gera um círculo
            g.fillOval(288, 104,14,14);
        }
        getToolkit().sync();
    }
}
