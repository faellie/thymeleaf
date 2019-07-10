package com.myropcb.pcb.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myropcb.pcb.model.CustomOrder;
import com.myropcb.pcb.model.SummaryDto;
import com.myropcb.pcb.model.WorkOrder;
import com.myropcb.pcb.utils.CommonUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


@Service
public class InMemoryOrderService implements OrderService {


    //todo need to find a better way
    private static String inputFile = "/opt/LI/nesting/thymeleaf/in/customOrder3.json";
    private static CustomOrder customOrder = null;
    private static  String inputJsonStr = "{\n" +
            "  \"pcbBoards\" : [ {\n" +
            "    \"area\" : 50.0,\n" +
            "    \"count\" : 200,\n" +
            "    \"id\" : \"board1\"\n" +
            "  }, {\n" +
            "    \"area\" : 100.0,\n" +
            "    \"count\" : 200,\n" +
            "    \"id\" : \"board2\"\n" +
            "  }, {\n" +
            "    \"area\" : 100.0,\n" +
            "    \"count\" : 300,\n" +
            "    \"id\" : \"board3\"\n" +
            "  },\n" +
            "    {\n" +
            "      \"area\" : 20.0,\n" +
            "      \"count\" : 500,\n" +
            "      \"id\" : \"board4\"\n" +
            "    } ],\n" +
            "  \"baseBoards\" : [ {\n" +
            "    \"area\" : 1000.0,\n" +
            "    \"id\" : \"mainBoard\"\n" +
            "  } ],\n" +
            "  \"maxFitRate\" : 0.96\n" +
            "}\n";

    @Override
    public CustomOrder getOrder() {
        //todo handle errors
        ObjectMapper mapper = new ObjectMapper();
        if(null == customOrder) {
            try {
                File lInputFile = new File(inputFile);
                if (lInputFile.exists()) {
                    customOrder = mapper.readValue(lInputFile, CustomOrder.class);
                } else {
                    customOrder = mapper.readValue(inputJsonStr, CustomOrder.class);
                }

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return customOrder;
    }


    @Override
    public SummaryDto getSummary() {
        ObjectMapper mapper = new ObjectMapper();
        if(null == customOrder) {
            try {
                File lInputFile = new File(inputFile);
                if (lInputFile.exists()) {
                    customOrder = mapper.readValue(lInputFile, CustomOrder.class);
                } else {
                    customOrder = mapper.readValue(inputJsonStr, CustomOrder.class);
                }

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //todo shall allow user to input init pattern but....
        Fit lFit = new Fit(customOrder, new ArrayList<WorkOrder>());
        lFit.doFit();
        ArrayList<WorkOrder> lworkOrders = lFit.getWorkOrders();

        SummaryDto lSummaryDto = new SummaryDto(customOrder, lworkOrders);
        CommonUtils.processOutputSummary(lSummaryDto);
        return lSummaryDto;
    }

    @Override
    public SummaryDto processOrder(SummaryDto aInSummaryDto) {
        Fit lFit = new Fit(aInSummaryDto.getCustomOrder(), aInSummaryDto.getWorkOrders());
        lFit.doFit();
        ArrayList<WorkOrder> lworkOrders = lFit.getWorkOrders();
        SummaryDto lDto = new SummaryDto(aInSummaryDto.getCustomOrder(), lworkOrders);
        CommonUtils.processOutputSummary(lDto);

        return lDto;
    }




}
