package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class GateView extends JPanel implements ItemListener {

    private final Gate gate;

    private final JCheckBox[] entrada;
    private final JCheckBox saida;

    private final int[] pinos;

    private final Switch[] sinal;


    public GateView(Gate gate){

        this.gate = gate;

        entrada = new JCheckBox[this.gate.getInputSize()];
        saida = new JCheckBox();

        pinos = new int[this.gate.getInputSize()];

        sinal = new Switch[this.gate.getInputSize()];

        JLabel in = new JLabel("Entrada");
        JLabel out = new JLabel("Saída");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(in);

        for (int i = 0; i < this.gate.getInputSize(); i += 1){

            entrada[i] = new JCheckBox("entrada" + i);
            sinal[i] = new Switch();

            pinos[i] = i;

            gate.connect(i, sinal[i]);

            add(entrada[i]);
        }

        add(out);
        add(saida);

        saida.setEnabled(false);

        add(out);
        add(saida);

        out.setEnabled(false);
        update();

    }

    public void update(){
        boolean check;

        for (int s: pinos){

            check = entrada[s].isSelected();

            if (check){
                sinal[s].turnOn();
            }
            else{
                sinal[s].turnOff();
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        update();

    }
}
