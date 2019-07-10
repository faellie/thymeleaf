package com.myropcb.pcb.utils;



import com.myropcb.pcb.model.Patern;
import com.myropcb.pcb.model.PcbBoard;
import com.myropcb.pcb.model.SummaryDto;
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

    //This when we get data from front end, the pcbs field in Patern is not saved, however the
    //the pcbsStr field is saved, we need to intialize pcbs field with pcbsStr
    public static void processInputSummary(SummaryDto aInSumaryDto) {
        ArrayList<WorkOrder> newList = new ArrayList<>();
        for (WorkOrder lWorkOrder : aInSumaryDto.getWorkOrders()) {
            if(lWorkOrder.getDups() == 0) {
                continue;
            }
            if(null != lWorkOrder.getPatern()) {
                lWorkOrder.getPatern().initPcbsFromStr();
                newList.add(lWorkOrder);
            } else {
                System.out.println("Workorder have null patern : " + lWorkOrder);
            }

        }
        aInSumaryDto.setWorkOrders(newList);
    }


    //when we send data from front end, the pcbsStr field in Patern might not be updated, however the
    //we need to update pcbsStr field according ro pcbs field
    public static void processOutputSummary(SummaryDto aInSumaryDto) {
        ArrayList<WorkOrder> newList = new ArrayList<>();
        for (WorkOrder lWorkOrder : aInSumaryDto.getWorkOrders()) {
            if(lWorkOrder.getDups() == 0) {
                continue;
            }
            if(null != lWorkOrder.getPatern()) {
                lWorkOrder.getPatern().updatePcbStr();
                newList.add(lWorkOrder);
            } else {
                System.out.println("Workorder have null patern : " + lWorkOrder);
            }

        }
        aInSumaryDto.setWorkOrders(newList);

    }
}
