package com.myropcb.pcb.controller;


import com.myropcb.pcb.core.OrderService;
import com.myropcb.pcb.model.CustomOrder;
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
    public String showAll(Model model) {
        model.addAttribute("form", orderService.getOrder());
        return "customOrder";
    }


    @PostMapping(value = "/process")
    public String process(@ModelAttribute CustomOrder form, Model model) {

        ArrayList<WorkOrder> lworkOrders  = orderService.processOrder(form);
        model.addAttribute("workOrderDto", new WorkOrderDto(lworkOrders));
        return "workOrder";
    }
}
