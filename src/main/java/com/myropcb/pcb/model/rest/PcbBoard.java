package com.myropcb.pcb.model.rest;


public class PcbBoard extends Rect {
    String orderId;

    public PcbBoard(double width, double hight, String id) {
        super(width, hight);
        this.orderId = id;
    }

    public PcbBoard(String id) {
        this.orderId = id;
    }

    public PcbBoard() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
