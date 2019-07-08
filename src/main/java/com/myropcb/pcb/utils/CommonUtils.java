package com.myropcb.pcb.utils;



import com.myropcb.pcb.model.PcbBoard;
import com.myropcb.pcb.model.WorkOrder;

import java.util.ArrayList;

public class CommonUtils {

    public static PcbBoard getWithId(ArrayList<PcbBoard> aInPcbBoardList, String aInId) {
        for(PcbBoard pcb : aInPcbBoardList ) {
            if(pcb.getId().equals(aInId)) {
                return pcb;
            }
        }
        //todo
        System.out.println("ERROR : Failed to found PCBboard with id " + aInId);
        return null;
    }

    public static boolean noMorePart(ArrayList<PcbBoard> aInTaregtList) {
        int parts = 0;
        for(PcbBoard pcb : aInTaregtList) {
            parts = parts + pcb.getCount();
        }
        return parts <= 0;
    }

    public static String printWorkOrders(ArrayList<WorkOrder> workOrders) {
        String lines = "\n\t";
        for(WorkOrder lWorkOrder : workOrders) {
            lines = lines + lWorkOrder.info() + "\n\t";
        }
        return lines;
    }
}
