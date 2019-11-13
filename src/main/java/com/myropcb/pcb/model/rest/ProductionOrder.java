package com.myropcb.pcb.model.rest;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedList;
import java.util.Map;

public class ProductionOrder {

    @JsonProperty("productionBoard")
    @ApiModelProperty(value = "Production Board")
    private ProductionBoard productionBoard;  //Production Board ID

    @JsonProperty("Count")
    @ApiModelProperty(value = "How many to produce")
    private int count;  //how many to producer


    private LinkedList<PcbEntry> pcbList;  //list of pcb id/number fitted into this  Production Board.

    public ProductionOrder(ProductionBoard  aInProductionBoard) {
        this.productionBoard = aInProductionBoard;
        count = 0;
        pcbList = new LinkedList<>();
    }

    public ProductionOrder() {
        productionBoard = new ProductionBoard();
        count = 0;
        pcbList = new LinkedList<>();
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

    public ProductionBoard getProductionBoard() {
        return productionBoard;
    }

    public void setProductionBoard(ProductionBoard aInProductionBoard) {
        productionBoard = aInProductionBoard;
    }
}
