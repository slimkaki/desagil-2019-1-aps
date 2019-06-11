package br.pro.hashi.ensino.desagil.aps.model;

public class HalfAdder extends Gate {
    private final NandGate nandBottom;
    private final NandGate nandLeft;
    private final NandGate nandRight;
    private final NandGate nandTop;
    private final NandGate nandCarry;

    public HalfAdder() {
        super("Half-Adder", 2, 2);
        nandBottom = new NandGate();
        nandLeft = new NandGate();
        nandRight = new NandGate();
        nandTop = new NandGate();
        nandCarry = new NandGate();

        nandTop.connect(1, nandLeft);
        nandBottom.connect(0, nandLeft);

        nandRight.connect(0, nandTop);
        nandRight.connect(1, nandBottom);

        nandCarry.connect(0, nandLeft);
        nandCarry.connect(1, nandLeft);
    }

    @Override
    public boolean read(int outputPin) {
        if (outputPin == 0) {
            return nandRight.read();
        } else if (outputPin == 1) {
            return nandCarry.read();
        } else {
            return false;
        }
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandLeft.connect(0, emitter);
                nandTop.connect(0, emitter);
                break;
            case 1:
                nandLeft.connect(1, emitter);
                nandBottom.connect(1, emitter);
                break;
        }
    }
}
