package com.myropcb.pcb.core;

import com.myropcb.pcb.model.CustomOrder;
import com.myropcb.pcb.model.SummaryDto;
import com.myropcb.pcb.model.WorkOrder;

import java.util.ArrayList;

public interface OrderService {

    CustomOrder getOrder();

    SummaryDto getSummary();

    ArrayList<WorkOrder> processOrder(CustomOrder aInCustomOrder);
}
