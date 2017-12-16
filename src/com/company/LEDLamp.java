package com.company;

public class LEDLamp extends Light {
    private int numberOfLEDs;

    public LEDLamp(String m, String c, int p, String pp, int n){
        super(m, c, p, pp);
        numberOfLEDs = n;
    }

    @Override
    public String toString() {
        return super.toString() + " " + numberOfLEDs;
    }
}
