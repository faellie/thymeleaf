package com.myropcb.pcb.model.rest;


import java.util.LinkedList;
import java.util.Map;

public class ProductionOrder {
    private String id;  //Production Board ID
    private int count;  //how many to producer
    private LinkedList<PcbEntry> pcbList;  //list of pcb id/number fitted into this  Production Board.

    public ProductionOrder(String id) {
        this.id = id;
        count = 0;
    }

    public ProductionOrder() {
        this.id = id;
        count = 0;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LinkedList<PcbEntry> getPcbList() {
        return pcbList;
    }

    public void setPcbList(LinkedList<PcbEntry> pcbList) {
        this.pcbList = pcbList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
