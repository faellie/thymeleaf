package com.myropcb.pcb.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myropcb.pcb.model.CustomOrder;
import com.myropcb.pcb.model.WorkOrder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


@Service
public class InMemoryOrderService implements OrderService {


    //todo need to find a better way
    private static String inputFile = "/opt/LI/nesting/thymeleaf/in/customOrder.json";
    @Override
    public CustomOrder getOrder() {
        //todo handle errors
        ObjectMapper mapper = new ObjectMapper();
        CustomOrder customOrder = null;
        try {
            File lInputFile = new File(inputFile);
            customOrder = mapper.readValue(lInputFile, CustomOrder.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customOrder;
    }

    @Override
    public ArrayList<WorkOrder> processOrder(CustomOrder aInCustomOrder) {
        Fit lFit = new Fit(aInCustomOrder);
        lFit.doFit();
        ArrayList<WorkOrder> lworkOrders = lFit.getWorkOrders();
        return lworkOrders;
    }


}
