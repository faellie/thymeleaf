package com.myropcb.pcb.model.rest;


public class Rect {
    private double width;
    private double hight;

    public Rect(double width, double hight) {
        this.width = width;
        this.hight = hight;
    }

    public Rect() {
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHight() {
        return hight;
    }

    public void setHight(double hight) {
        this.hight = hight;
    }

    public double area() {
        return width * hight;
    }
}
