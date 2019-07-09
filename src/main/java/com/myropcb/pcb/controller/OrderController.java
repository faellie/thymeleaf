package com.myropcb.pcb.controller;


import com.myropcb.pcb.core.OrderService;
import com.myropcb.pcb.model.CustomOrder;
import com.myropcb.pcb.model.SummaryDto;
import com.myropcb.pcb.model.WorkOrder;
import com.myropcb.pcb.model.WorkOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/all")
    public String customOrder(Model model) {
        model.addAttribute("form", orderService.getOrder());
        return "customOrder";
    }

    @GetMapping(value = "/summary")
    public String summary(Model model) {
        model.addAttribute("form", orderService.getSummary());
        return "summary";
    }

    @PostMapping(value = "/process")
    public String process(@ModelAttribute CustomOrder form, Model model) {

        ArrayList<WorkOrder> lworkOrders  = orderService.processOrder(form);
        model.addAttribute("workOrderDto", new WorkOrderDto(lworkOrders));
        return "workOrder";
    }

    @PostMapping(value = "/processAll")
    public String process(@ModelAttribute SummaryDto form, Model model) {

        ArrayList<WorkOrder> lworkOrders  = orderService.processOrder(form.getCustomOrder());

        model.addAttribute("form", new SummaryDto(form.getCustomOrder(), lworkOrders));
        return "summary";
    }
}
