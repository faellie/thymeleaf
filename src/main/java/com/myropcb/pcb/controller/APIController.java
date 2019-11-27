package com.myropcb.pcb.controller;


import com.myropcb.nest.NestNBI;
import com.myropcb.pcb.core.Fit;
import com.myropcb.pcb.core.OrderService;
import com.myropcb.pcb.model.WorkOrder;
import com.myropcb.pcb.model.rest.DataModel;
import com.myropcb.pcb.model.rest.ProductionOrder;
import com.myropcb.pcb.model.rest.Rect;
import com.myropcb.pcb.utils.Converter;
import com.myropcb.tocc.HelloJNI;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:4200" })
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

    @GetMapping(value = "/rect")
    public Rect rect(
            ) {
        return new Rect(10.0, 10.0);
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
            String lStr = NestNBI.runNest(lOrder);
            lret.add(lStr);
        }

        return lret;
    }

    @PostMapping(value = "/cc")
    public String cc(
            @RequestBody String input) {

        System.loadLibrary("Hello");
        HelloJNI api = new HelloJNI();
        return api.getString(input);
    }

    private void verify(DataModel aInInput) {
        for(ProductionOrder lOrder : aInInput.getProductionList()) {
            NestNBI.runNest(lOrder);
        }
    }
}
