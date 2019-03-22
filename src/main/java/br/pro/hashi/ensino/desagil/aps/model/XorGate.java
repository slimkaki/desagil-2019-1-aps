package br.pro.hashi.ensino.desagil.aps.model;

public class XorGate extends Gate {
    protected XorGate() {
        super(2);
    }

    @Override
    public boolean read() {
        return false;
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {

    }
}
