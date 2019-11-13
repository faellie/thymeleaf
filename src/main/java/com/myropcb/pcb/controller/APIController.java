package com.myropcb.pcb.controller;


import com.myropcb.nest.NestTest;
import com.myropcb.pcb.core.Fit;
import com.myropcb.pcb.core.OrderService;
import com.myropcb.pcb.model.WorkOrder;
import com.myropcb.pcb.model.rest.DataModel;
import com.myropcb.pcb.model.rest.ProductionOrder;
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
        Converter.updateDataModelFromWorkOrder(input, lworkOrders);
        verify(input);
        return input;
    }

    @PostMapping(value = "/nest")
    public ArrayList<String> nest(
            @RequestBody DataModel input) {
        ArrayList<String> lret = new ArrayList<>();
        for(ProductionOrder lOrder : input.getProductionList()) {
            String lStr = NestTest.runNest(lOrder);
            lret.add(lStr);
        }

        return lret;
    }

    private void verify(DataModel aInInput) {
        for(ProductionOrder lOrder : aInInput.getProductionList()) {
            NestTest.runNest(lOrder);
        }
    }
}
