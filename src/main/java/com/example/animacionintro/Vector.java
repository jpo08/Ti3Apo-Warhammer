package com.example.animacionintro;

public class Vector {
    private double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void normalize(){
        double mag=Math.sqrt(x*x + y*y);
        double angle = Math.atan2(y,x);
        x=1*Math.cos(angle);
        y=1*Math.sin(angle);
    }

    public void setMag(int scalar){
        this.x *= scalar;
        this.y *= scalar;
    }

    public double getAngle(){
        return Math.toDegrees(Math.atan2(y,x));
    }

    public double getAmplitude(){
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
