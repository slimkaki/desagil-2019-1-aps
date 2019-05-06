package br.pro.hashi.ensino.desagil.aps.model;

public class HalfAdder extends Gate {
    private final NandGate nand1;
    private final NandGate nand2;
    private final NandGate nand3;
    private final NandGate nand4;
    private final NandGate nand5;
    private final NandGate nand6;
    private final NandGate nand7;

    public HalfAdder(){

        super("HALFADDER", 2, 2);

        nand1 = new NandGate();
        nand2 = new NandGate();
        nand3 = new NandGate();
        nand4 = new NandGate();
        nand5 = new NandGate();
        nand6 = new NandGate();
        nand7 = new NandGate();

        nand2.connect(0, nand1);

        nand3.connect(0, nand2);

        nand5.connect(0, nand4);

        nand7.connect(0, nand6);
        nand7.connect(1, nand6);
    }

    @Override

    public boolean read(int outputPin) {
        if (outputPin < 0 || outputPin > 1) {
            throw new IndexOutOfBoundsException(outputPin);
        }

        if (outputPin == 0){
            return nand3.read();
        }
        else {
            return nand7.read();
        }
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin == 0) {
            nand1.connect(0, emitter);
            nand1.connect(1, emitter);

            nand5.connect(1, emitter);

            nand6.connect(0, emitter);
        }

        else if (inputPin == 1){
            nand2.connect(1, emitter);

            nand4.connect(0, emitter);
            nand4.connect(1, emitter);

            nand6.connect(1, emitter);
        }

        else {
            throw new IndexOutOfBoundsException(inputPin);
        }
    }
}
