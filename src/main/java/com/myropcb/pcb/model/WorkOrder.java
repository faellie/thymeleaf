package com.myropcb.pcb.model;


public class WorkOrder {
    private Patern patern;
    private int dups = 0;
    private Double usagePercent = 0.0;


    public WorkOrder() {
    }

    /*
        indicate what improvement we can perform on the workorder
        0 = init, mean its just a suggestion, we should update both the dup and patern
        1 = patern fixed; we should only update the dups
        2 = fixed: both patern and dups are fixed and we should simply decrease the custom order accordingly
         */
    private int status = 0;
    public WorkOrder(Patern patern, int dups, double usagePercent) {
        this.patern = patern;
        this.dups = dups;
        this.usagePercent = usagePercent;
        //fixed, i.e. final
        status = 4;
    }

    public WorkOrder(Patern patern, int dups, Double usagePercent, int status) {
        this.patern = patern;
        this.dups = dups;
        this.usagePercent = usagePercent;
        this.status = status;
    }

    public Patern getPatern() {
        return patern;
    }

    public void setPatern(Patern patern) {
        this.patern = patern;
    }

    public int getDups() {
        return dups;
    }

    public void setDups(int dups) {
        this.dups = dups;
    }

    public Double getUsagePercent() {
        return usagePercent;
    }

    public void setUsagePercent(Double usagePercent) {
        this.usagePercent = usagePercent;
    }

    public void IncreDups() {
        this.dups ++;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String info() {
        String lStr =
                "Dups = " + dups + "; partern : " +(null == patern? "null" :patern.info()) + "; Usage Percent =  " + (usagePercent*100) + "%" ;
        return lStr;
    }

}
