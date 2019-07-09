package com.myropcb.pcb.model;

import java.util.ArrayList;
import java.util.List;

public class SummaryDto {

    private CustomOrder customOrder;
    private List<WorkOrder> workOrders;


    public SummaryDto() {
        this.customOrder = new CustomOrder();
        this.workOrders = new ArrayList<>();
    }

    public SummaryDto(CustomOrder customOrder, List<WorkOrder> workOrders) {
        this.customOrder = customOrder;
        this.workOrders = workOrders;
    }

    public CustomOrder getCustomOrder() {
        return customOrder;
    }

    public void setCustomOrder(CustomOrder customOrder) {
        this.customOrder = customOrder;
    }

    public List<WorkOrder> getWorkOrders() {
        return workOrders;
    }

    public void setWorkOrders(List<WorkOrder> workOrders) {
        this.workOrders = workOrders;
    }
}
