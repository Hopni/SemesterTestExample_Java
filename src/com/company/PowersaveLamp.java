package com.company;

public class PowersaveLamp extends Light {
    private String shape;

    public PowersaveLamp(String m, String c, int p, String pp, String s){
        super(m, c, p, pp);
        shape = s;
    }

    @Override
    public String toString() {
        return super.toString() + " " + shape;
    }
}
