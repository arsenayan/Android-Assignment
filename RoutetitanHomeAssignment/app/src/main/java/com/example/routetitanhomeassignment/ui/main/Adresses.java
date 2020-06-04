package com.example.routetitanhomeassignment.ui.main;

import java.util.Random;

public class Adresses {
    private static String CITY = "Yerevan";
    private String street;
    private Random randomInt = new Random();
    private double latitude;
    private double longitude;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int intVal;

    public Adresses(String street, double latitude, double longitude) {
        this.street = street;
        this.latitude=latitude;
        this.longitude=longitude;
        intVal = randomInt.nextInt(50000);
        type = 0;
    }

    public Random getRandomInt() {
        return randomInt;
    }

    public void setRandomInt(Random randomInt) {
        this.randomInt = randomInt;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getIntVal() {
        return intVal;
    }

    public void setIntVal(int intVal) {
        this.intVal = intVal;
    }

    public static void setCITY(String CITY) {
        Adresses.CITY = CITY;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setRandom(Random random) {
        this.randomInt = random;
    }

    public static String getCITY() {
        return CITY;
    }

    public String getStreet() {
        return street;
    }

    public int getRandom() {
        return intVal;
    }


}
