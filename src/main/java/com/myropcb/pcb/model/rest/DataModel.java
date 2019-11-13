package com.myropcb.pcb.model.rest;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedList;

public class DataModel {

    @JsonProperty("List of ProductionOrders")
    @ApiModelProperty(value = "List of ProductionOrders")
    private LinkedList<ProductionOrder> productionList;

    @JsonProperty("pcbOrders")
    @ApiModelProperty(value = "List of PCB orders")
    private LinkedList<PcbEntry> pcbOrders;

    private ProductionBoard productionBoard;

    public DataModel(LinkedList<ProductionOrder> productionOrders, LinkedList<PcbEntry> pcbOrders, ProductionBoard productionBoard) {
        this.productionList = productionOrders;
        this.pcbOrders = pcbOrders;
        this.productionBoard = productionBoard;
    }

    public DataModel() {
        this.productionList = new LinkedList<>();
        this.pcbOrders = new LinkedList<>();
        this.productionBoard = new ProductionBoard();
    }

    public LinkedList<ProductionOrder> getProductionList() {
        return productionList;
    }

    public void setProductionList(LinkedList<ProductionOrder> productionOrders) {
        this.productionList = productionOrders;
    }

    public LinkedList<PcbEntry> getPcbOrders() {
        return pcbOrders;
    }

    public void setPcbOrders(LinkedList<PcbEntry> pcbEntries) {
        this.pcbOrders = pcbEntries;
    }

    public ProductionBoard getProductionBoard() {
        return productionBoard;
    }

    public void setProductionBoard(ProductionBoard baseBoard) {
        this.productionBoard = baseBoard;
    }
}
