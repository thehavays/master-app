package com.vestel.tv.middleware;

public class VestelNonLinearPqCurve {
    private int value0;
    private int value25;
    private int value50;
    private int value75;
    private int value100;

    public VestelNonLinearPqCurve() {}

    public void copy(VestelNonLinearPqCurve curve) {
        this.value0 = curve.value0;
        this.value25 = curve.value25;
        this.value50 = curve.value50;
        this.value75 = curve.value75;
        this.value100 = curve.value100;
    }

    public int getValue0() {
        return value0;
    }

    public void setValue0(int value0) {
        this.value0 = value0;
    }

    public int getValue25() {
        return value25;
    }

    public void setValue25(int value25) {
        this.value25 = value25;
    }

    public int getValue50() {
        return value50;
    }

    public void setValue50(int value50) {
        this.value50 = value50;
    }

    public int getValue75() {
        return value75;
    }

    public void setValue75(int value75) {
        this.value75 = value75;
    }

    public int getValue100() {
        return value100;
    }

    public void setValue100(int value100) {
        this.value100 = value100;
    }
}
