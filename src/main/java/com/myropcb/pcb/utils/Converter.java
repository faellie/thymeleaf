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
        lCustomOrder.getBaseBoards().add(new BaseBoard(aInDataModel.getProductionBoard().area(), aInDataModel.getProductionBoard().getId()));
        lCustomOrder.setMaxFitRate(0.95);
        LinkedList<PcbEntry>  lPcbEntryList = aInDataModel.getPcbOrders();
        for(PcbEntry lEntry : lPcbEntryList) {
            lCustomOrder.getPcbBoards().add(new PcbBoard(lEntry.getPcb().area(), lEntry.getCount(), lEntry.getPcb().getOrderId()));
        }
        lCustomOrder.setMaxFitRate(0.8);
        return lCustomOrder;
    }

    public static ArrayList<WorkOrder> getWorkOrderFromDataModel(DataModel aInDataModel) {
        ArrayList<WorkOrder> lWorkOrders= new ArrayList<>();
        LinkedList<ProductionOrder> lOrders = aInDataModel.getProductionList();
        for(ProductionOrder lOrder : lOrders) {
            Patern patern = new Patern(lOrder.getProductionBoard().getId(), getFillElementListFromPcbList(lOrder.getPcbList()));
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

    public static void updateDataModelFromWorkOrder(DataModel aInInput, ArrayList<WorkOrder> aInWorkOrders) {
        LinkedList<ProductionOrder>  lProductionOrders = aInInput.getProductionList();
        String lProductionBoardId = aInInput.getProductionBoard().getId();
        for(WorkOrder lNextWorkOrder : aInWorkOrders) {
            ProductionOrder lNextProductionOrder = new ProductionOrder();
            //these are patches, and shoudl be removed after we refactoring the old data model
            lNextProductionOrder.getProductionBoard().setId(lProductionBoardId);
            lNextProductionOrder.getProductionBoard().setHight(aInInput.getProductionBoard().getHight());
            lNextProductionOrder.getProductionBoard().setWidth(aInInput.getProductionBoard().getWidth());
            lNextProductionOrder.setCount(lNextWorkOrder.getDups());
            LinkedList<PcbEntry> pcbList = lNextProductionOrder.getPcbList();
            ArrayList<FillElement> lArrayList = lNextWorkOrder.getPatern().getPcbs();
            for(FillElement lNextElement : lArrayList) {
                pcbList.add(new PcbEntry(findPcbBoardFromId(aInInput, lNextElement.getPcbId()), lNextElement.getCount()));
            }
            lProductionOrders.add(lNextProductionOrder);
        }
    }

    private static com.myropcb.pcb.model.rest.PcbBoard findPcbBoardFromId(DataModel aInInput, String aInId) {
        LinkedList<PcbEntry> lList = aInInput.getPcbOrders();
        for(PcbEntry lEntry : lList) {
            if(lEntry.getPcb().getOrderId().equals(aInId)) {
                return lEntry.getPcb();
            }
        }
        return null;
    }
}
