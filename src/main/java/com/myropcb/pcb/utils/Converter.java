package com.myropcb.pcb.utils;

import com.myropcb.pcb.model.*;
import com.myropcb.pcb.model.rest.DataModel;
import com.myropcb.pcb.model.rest.PcbEntry;
import com.myropcb.pcb.model.rest.ProductionOrder;

import java.util.ArrayList;
import java.util.LinkedList;

public class Converter {
    public static CustomOrder getCustomOrderFromDataModel(DataModel aInDataModel) {
        CustomOrder lCustomOrder = new CustomOrder();
        lCustomOrder.getBaseBoards().add(new BaseBoard(aInDataModel.getBaseBoard().area(), aInDataModel.getBaseBoard().getId()));
        lCustomOrder.setMaxFitRate(0.95);
        LinkedList<PcbEntry>  lPcbEntryList = aInDataModel.getPcbEntries();
        for(PcbEntry lEntry : lPcbEntryList) {
            lCustomOrder.getPcbBoards().add(new PcbBoard(lEntry.getPcb().area(), lEntry.getCount(), lEntry.getPcb().getOrderId()));
        }
        return lCustomOrder;
    }

    public static ArrayList<WorkOrder> getWorkOrderFromDataModel(DataModel aInDataModel) {
        ArrayList<WorkOrder> lWorkOrders= new ArrayList<>();
        LinkedList<ProductionOrder> lOrders = aInDataModel.getProductionOrders();
        for(ProductionOrder lOrder : lOrders) {
            Patern patern = new Patern(lOrder.getId(), getFillElementListFromPcbList(lOrder.getPcbList()));
            lWorkOrders.add(new WorkOrder());
        }
        return lWorkOrders;
    }

    private static ArrayList<FillElement> getFillElementListFromPcbList(LinkedList<PcbEntry> pcbList) {
        ArrayList<FillElement> lFillList = new ArrayList<>();
        for(PcbEntry lEntry : pcbList) {
            lFillList.add(new FillElement(lEntry.getPcb().getOrderId(), lEntry.getCount(), lEntry.getPcb().area()));
        }
        return lFillList;
    }
}
