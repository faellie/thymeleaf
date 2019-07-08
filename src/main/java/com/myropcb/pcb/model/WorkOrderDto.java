package com.myropcb.pcb.model;

import java.util.ArrayList;
import java.util.List;

public class WorkOrderDto {

    private List<WorkOrder> orders;

    public WorkOrderDto() {
        this.orders = new ArrayList<>();
    }

    public WorkOrderDto(List<WorkOrder> orders) {
        this.orders = orders;
    }

    public List<WorkOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<WorkOrder> orders) {
        this.orders = orders;
    }
}
