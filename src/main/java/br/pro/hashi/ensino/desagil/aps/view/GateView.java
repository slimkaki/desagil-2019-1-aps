package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GateView extends JPanel implements ActionListener {

    // iniciando as variáveis a serem utilizadas
    private final Gate gate;
    private final JCheckBox checkInput1;
    private final JCheckBox checkInput2;
    private final JCheckBox result;

    private final Switch switch1 = new Switch();
    private final Switch switch2 = new Switch();

    public GateView(Gate gate) {
        this.gate = gate; // Porta lógica escolhida

        checkInput1 = new JCheckBox("Entrada 1"); // CheckBox de entrada de sinal 1
        checkInput2 = new JCheckBox("Entrada 2"); // CheckBox de entrada de sinal 2
        result = new JCheckBox("Saída"); // CheckBox com o resultado dos sinais

        // iniciando ambas entradas como falsas, logo ambas são zero inicialmente!
        checkInput1.setSelected(false);
        checkInput2.setSelected(false);

        // nomes que serão os identificadores de cada um dos campos
        JLabel checkLabel1 = new JLabel("Entradas:");
        JLabel checkLabel2 = new JLabel("Entrada:");
        JLabel resultLabel = new JLabel("Saída:");

        // cria o layout como uma janela em forma de caixa
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // por conta do NotGate só possuir 1 switch, precisamos criar uma condição
        if (this.gate.getInputSize() == 1) {
            // cria a ordem da disposição dos elementos na tela
            add(checkLabel2);
            add(checkInput1);
            add(resultLabel);
            add(result);

            // faz a conexão da entrada no gate especificado
            this.gate.connect(0, switch1);

        } else {
            // cria a ordem da disposição dos elementos na tela
            add(checkLabel1);
            add(checkInput1);
            add(checkInput2);
            add(resultLabel);
            add(result);

            // faz a conexão da entrada no gate especificado
            this.gate.connect(0, switch1);
            this.gate.connect(1, switch2);
        }

        // permite que os check boxes dos inputs sejam marcados ou desmarcados
        checkInput1.addActionListener(this);
        checkInput2.addActionListener(this);

        // impossibilita a mudança do estado do checkbox do resultado pela interação do usuário
        result.setEnabled(false);

        // roda a função que checa o caso e retorna a saída
        update();
    }

    public void update() {
            if (checkInput1.isSelected()) {
                switch1.turnOn();
            } else {
                switch1.turnOff();
            }
            if (checkInput2.isSelected()){
                switch2.turnOn();
            }
            result.setSelected(this.gate.read());
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }
}