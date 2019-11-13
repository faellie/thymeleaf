package com.myropcb.pcb.model.rest;


import com.myropcb.pcb.model.Shape;

public class ProductionBoard extends Rect {
    String id;

    public ProductionBoard(double width, double hight, String id) {
        super(width, hight);
        this.id = id;
    }

    public ProductionBoard(String id) {
        this.id = id;
    }

    public ProductionBoard() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
