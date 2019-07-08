package com.myropcb.pcb.core;

import com.myropcb.pcb.model.CustomOrder;
import com.myropcb.pcb.model.WorkOrder;

import java.util.ArrayList;

public interface OrderService {

    CustomOrder getOrder();

    ArrayList<WorkOrder> processOrder(CustomOrder aInCustomOrder);
}
