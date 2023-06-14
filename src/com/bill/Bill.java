package com.bill;

public class Bill {
    private int intQty;
    private int intValue;

    public Bill(int intQty, int intValue) {
        this.intQty = intQty;
        this.intValue = intValue;
    }

    public int getQty() {
        return intQty;
    }

    public void setQty(int intQty) {
        this.intQty = intQty;
    }

    public int getValue() {
        return intValue;
    }
}
