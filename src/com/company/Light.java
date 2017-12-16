package com.company;

public class Light implements Comparable<Light> {
    private String manufacturer;
    private String color;
    private int power;
    private String picturePath;

    public Light(String m, String c, int p, String pp){
        manufacturer = m;
        color = c;
        power = p;
        picturePath = pp;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getColor() {
        return color;
    }

    public int getPower() {
        return power;
    }

    @Override
    public String toString() {
        return manufacturer + " " + power + " " + color;
    }

    @Override
    public int compareTo(Light light) {
        if(manufacturer.compareTo(light.manufacturer) == 0){
            if(power == light.power){
                return color.compareTo(light.color);
            } else {
                return light.power - power;
            }
        } else {
            return manufacturer.compareTo(light.manufacturer);
        }
    }
}
