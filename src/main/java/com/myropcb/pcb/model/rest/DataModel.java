package com.myropcb.pcb.model.rest;


import java.util.LinkedList;

public class DataModel {
    private LinkedList<ProductionOrder> productionOrders ;
    private LinkedList<PcbEntry> pcbEntries;
    private ProductionBoard baseBoard;

    public DataModel(LinkedList<ProductionOrder> productionOrders, LinkedList<PcbEntry> pcbEntries, ProductionBoard baseBoard) {
        this.productionOrders = productionOrders;
        this.pcbEntries = pcbEntries;
        this.baseBoard = baseBoard;
    }

    public DataModel() {
    }

    public LinkedList<ProductionOrder> getProductionOrders() {
        return productionOrders;
    }

    public void setProductionOrders(LinkedList<ProductionOrder> productionOrders) {
        this.productionOrders = productionOrders;
    }

    public LinkedList<PcbEntry> getPcbEntries() {
        return pcbEntries;
    }

    public void setPcbEntries(LinkedList<PcbEntry> pcbEntries) {
        this.pcbEntries = pcbEntries;
    }

    public ProductionBoard getBaseBoard() {
        return baseBoard;
    }

    public void setBaseBoard(ProductionBoard baseBoard) {
        this.baseBoard = baseBoard;
    }
}
