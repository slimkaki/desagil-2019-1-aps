package br.pro.hashi.ensino.desagil.aps.model;

public class AndGate extends Gate {
    private final NandGate nand1;
    private final NandGate nand2;

    public AndGate() {
        super(2);

        nand1 = new NandGate();
        nand2 = new NandGate();

        nand2.connect(0, nand1);
        nand2.connect(1, nand1);

    }

    @Override
    public boolean read() {
        return nand2.read();
    }


    @Override
    public void connect(int inputPin, SignalEmitter emitter) {

        if (inputPin != 0 && inputPin != 1) {
            throw new IndexOutOfBoundsException(inputPin);
        }

        if (inputPin == 1) {
            nand1.connect(1, emitter);
        }

        if (inputPin == 0) {
            nand1.connect(0, emitter);
        }

    }

}
