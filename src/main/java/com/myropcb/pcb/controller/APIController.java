package com.myropcb.pcb.controller;


import com.myropcb.pcb.core.Fit;
import com.myropcb.pcb.core.OrderService;
import com.myropcb.pcb.model.CustomOrder;
import com.myropcb.pcb.model.WorkOrder;
import com.myropcb.pcb.model.rest.DataModel;
import com.myropcb.pcb.model.rest.Rect;
import com.myropcb.pcb.utils.Converter;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/rest")

public class APIController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/rect")
    public Double rect(
            @RequestBody Rect rect) {
        return rect.area();
    }


    @PostMapping(value = "/order")
    public DataModel customOrder(
            @RequestBody DataModel input) {

        Fit lFit = new Fit(Converter.getCustomOrderFromDataModel(input), Converter.getWorkOrderFromDataModel(input));
        lFit.doFit();
        ArrayList<WorkOrder> lworkOrders = lFit.getWorkOrders();
        return input;
    }
}