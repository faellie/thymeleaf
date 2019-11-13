package com.myropcb.pcb.model.rest;


public class PcbEntry {
    private PcbBoard pcb;
    private Integer count;

    public PcbEntry(PcbBoard pcb, Integer count) {
        this.pcb = pcb;
        this.count = count;
    }

    public PcbEntry() {
    }

    public PcbBoard getPcb() {
        return pcb;
    }

    public void setPcb(PcbBoard pcb) {
        this.pcb = pcb;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
