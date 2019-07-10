package com.myropcb.pcb.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.ArrayList;

public class Patern {
    private String baseBoardId;
    private ArrayList<FillElement> pcbs = new ArrayList<>();

    //a string represenation of the pcbs list
    //this is for pass data between clien and server
    private String pcbsStr = "";

    public Patern() {
    }

    public Patern(String aInBaseBoardId, ArrayList<FillElement> pcbs) {
        this.baseBoardId = aInBaseBoardId;
        this.pcbs = pcbs;

        ObjectMapper mapper = new ObjectMapper();
        try {
            pcbsStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pcbs);
        } catch (JsonProcessingException e) {

        }
    }

    public void updatePcbStr() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            pcbsStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pcbs);
        } catch (JsonProcessingException e) {

        }
    }
    public void initPcbsFromStr() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            pcbs = objectMapper.readValue(pcbsStr,
                    new TypeReference<ArrayList<FillElement>>() {
                    });
        } catch (IOException e) {
            System.out.println("Failed to parse " + pcbsStr);
        }
    }
    public String getBaseBoardId() {
        return baseBoardId;
    }

    public void setBaseBoardId(String baseBoardId) {
        this.baseBoardId = baseBoardId;
    }

    public ArrayList<FillElement> getPcbs() {
        return pcbs;
    }

    public void setPcbs(ArrayList<FillElement> pcbs) {
        this.pcbs = pcbs;
    }

    public static Patern initPatern(BaseBoard aInOutBoard, CustomOrder aInCustomOrder) {
        ArrayList<FillElement> list = new ArrayList<>();
        for(PcbBoard pcb : aInCustomOrder.getPcbBoards()) {
            FillElement lFill = new FillElement(pcb.getId(), 0, pcb.getArea());
            list.add(lFill);
        }
        return new Patern(aInOutBoard.getId(), list);
    }

    public void increCount(String aInPcbId) {
        for(FillElement lElement : pcbs) {
            if(lElement.getPcbId().equals(aInPcbId)) {
                int lCurrCount = lElement.getCount();
                lElement.setCount(lCurrCount + 1);
            }
        }
    }


    public void increCount(String aInId, int aInCount) {
        for(FillElement lElement : pcbs) {
            if(lElement.getPcbId().equals(aInId)) {
                int lCurrCount = lElement.getCount();
                lElement.setCount(lCurrCount + aInCount);
            }
        }
    }

    public String info() {

        String lRet = "BaseBoardID : " + baseBoardId + "; pcbs : [ " ;
        for(FillElement pcb : pcbs) {
            lRet = lRet + "(" +pcb.getPcbId() + " A:" + pcb.getArea() + ") X " + pcb.getCount() + "; ";
        }
        lRet = lRet + "]";
        return  lRet;
    }


    public double getUsedArea() {
        double usedArea = 0.0;
        for(FillElement lElement : pcbs) {
            usedArea = lElement.getArea() * lElement.getCount() + usedArea;
        }
        return usedArea;
    }

    public String getPcbsStr() {
        return pcbsStr;
    }

    public void setPcbsStr(String pcbsStr) {
        this.pcbsStr = pcbsStr;
    }
}
