package com.billWallet;
import com.bill.Bill;

public class BillWallet {
    private Bill[] bills;

    public BillWallet() {
        bills = new Bill[6]; 
        // Initialize the bill denominations
        bills[0] = new Bill(0, 10); // 10 Peso
        bills[1] = new Bill(0, 20); // 20 Peso
        bills[2] = new Bill(0, 50); // 50 Peso
        bills[3] = new Bill(0, 100); // 100 Peso
        bills[4] = new Bill(0, 200); // 200 Peso
        bills[5] = new Bill(0, 500); // 500 Peso
    }

    public Bill[] getBills() {
        return bills;
    }
    public void addBill(Bill billToAdd) {
        int value = billToAdd.getValue();
        for (int i = 0; i < bills.length; i++) {
            if (bills[i].getValue() == value) {
                bills[i].setQty(bills[i].getQty() + billToAdd.getQty());
                return;
            }
        }
    }   

    public int getTotalAmount() {
        int total = 0;
        for (int i = 0; i < bills.length; i++) {
            total += bills[i].getQty() * bills[i].getValue();
        }
        return total;
    }


    public void transferBills(BillWallet vndStock) {
        for (int i = 0; i < bills.length; i++) {
            vndStock.addBill(bills[i]);
            bills[i].setQty(0);
        }
    }
    public void clearWallet() {
        for (int i = 0; i < bills.length; i++) {
            bills[i].setQty(0);
        }
    }

    public boolean pay(int uCash, int price) {
        int remainingAmount = uCash - price;
        //System.out.println("Cash: " + uCash + "Price: " + price + "change: " + remainingAmount );
        for (int i = 0; i < bills.length; i++) {
            int billValue = bills[i].getValue();
            int billsToGive = remainingAmount / billValue;
            if (billsToGive <= bills[i].getQty()) {
                remainingAmount -= billsToGive * billValue;
                //System.out.println("change: " + remainingAmount );
            }

        }

        if (remainingAmount == 0) {
            remainingAmount = uCash - price;

            System.out.println("Payment successful. Dispensing item.");
            for (int i = 0; i < bills.length; i++) {
                int billValue = bills[i].getValue();
                int billsToGive = remainingAmount / billValue;
                if (billsToGive <= bills[i].getQty()) {
                    remainingAmount -= billsToGive * billValue;
                    bills[i].setQty(bills[i].getQty() - billsToGive);
                    System.out.println("Given " + billsToGive + " " + billValue + " Peso coins/bills as change");
                    
                }
                if(remainingAmount==0){
                    break;
                }
            }
            return true;
        } else {
            System.out.println("Insufficient bills. Payment failed.");
            return false;
        }
    }
}
