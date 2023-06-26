package com.billVnd;

import java.util.Scanner;

import com.bill.Bill;
import com.billWallet.BillWallet;
import com.rgStock.RgStock;

public class BillVnd {
    private RgStock vndItemStock;
    private RgStock vndItemSold;
    private BillWallet userWallet;
    private BillWallet vndStock;
    private BillWallet vndProfit;
    public BillVnd(){
        userWallet = new BillWallet();
        vndStock = new BillWallet();
        vndProfit = new BillWallet();
        vndItemSold = new RgStock();
        vndItemStock = new RgStock();
    }
    public void billMaintenance(int[] vndBills) {
        if (vndBills.length != 6) {
            System.out.println("Invalid bill quantities array length. Expected length: 6");
            return;
        }

        for (int i = 0; i < vndBills.length; i++) {
            int billValue = vndStock.getBills()[i].getValue();
            vndStock.addBill(new Bill(vndBills[i], billValue));
        }
    }

    /*public void billMaintenance(){
        Scanner sc = new Scanner(System.in);
        int[] vndBills = new int[vndStock.getBills().length];
        for (int i = 0; i < vndBills.length; i++) {
                int billValue = vndStock.getBills()[i].getValue();
                System.out.print("Enter the number of " + billValue + " Peso bills to be restocked to the machine:");
                vndBills[i] = sc.nextInt();
                vndStock.addBill(new Bill(vndBills[i], billValue));
                if (i + 1 < vndBills.length) {
                    sc.nextLine();
                }
        }
        //sc.close();
    }
    */
    /* 
    public void itemMaintenance(){
        //vndItemSold.stockMenu();
        Scanner sc = new Scanner(System.in);
        int intChosen=0;
        while(intChosen != 3){
            System.out.println("[1] Edit Cake");
            System.out.println("[2] Add Cake");
            System.out.println("[3] Return");
            System.out.println("------------------------");
            System.out.print("Enter Choice: ");
            intChosen = sc.nextInt();
            switch(intChosen){
                case 1:
                    vndItemStock.editMenu(vndItemSold);
                    break;
                case 2:
                    vndItemStock.addCake(vndItemSold);
                    break;
                case 3:
                    System.out.println("Returning...");
                    System.out.println("------------------------");
                    break;
                default:
                    System.out.println("Invalid Input...");
                    break;
            }
        }
    }
    */
    public void addNewCake(String strName, String strDesc, int intCalorie, int intPrice, int intQuantity){
        vndItemStock.addNewCake(strName,strDesc, intCalorie,intPrice, intQuantity);
        vndItemSold.addNewCake(strName,strDesc, intCalorie,intPrice, 0);
    }
    public void deleteACake(int cakeIndex){
        vndItemStock.deleteCake(cakeIndex);
        vndItemSold.deleteCake(cakeIndex);
    }
    public void editCake(int choice, int cakeIndex, int value){
        if(choice==3){
            vndItemStock.editCalorie(cakeIndex, value);
            vndItemSold.editCalorie(cakeIndex, value);
        }
        else if(choice==4){
            vndItemStock.editPrice(cakeIndex, value);
        }
        else if(choice ==5){
            vndItemStock.addInt(cakeIndex, value);
        } 
        else if(choice==6){
            vndItemStock.subInt(cakeIndex, value);
        }
        else{
            System.out.println("Invalid...");
        }
    }
    public void editCake(int choice, int cakeIndex, String value){
        if(choice==1){
            vndItemStock.editName(cakeIndex, value);
        }
        else if(choice == 2){
            vndItemStock.editDesc(cakeIndex, value);
        }
    }
    public void collectProfit(){
        System.out.println("Total Profit obtained: "+vndProfit.getTotalAmount()+ ". Clearing wallet.");
        vndProfit.clearWallet();
    }
    public void purchaseItem(int[] userBills, int cakeIndex) {
        boolean canGiveChange;
        boolean isItemValid = false;
        boolean hasCakeInStock = vndItemStock.hasCakeInStock();
        if (hasCakeInStock == false) {
            System.out.println("Can't buy items, no items in stock.");
        } else {
            System.out.println("Total amount of bills in Vending machine: " + vndStock.getTotalAmount());
            if (vndStock.getTotalAmount() == 0) {
                System.out.println("The Vending Machine does not have money to process change right now");
                canGiveChange = false;
            } else {
                System.out.println("The Vending Machine can process change right now");
                canGiveChange = true;
            }
            vndItemStock.displayMenu();
            if (userBills.length != 6) {
                System.out.println("Invalid bill quantities array length. Expected length: 6");
                return;
            }
            for (int i = 0; i < userBills.length; i++) {
                int billValue = vndStock.getBills()[i].getValue();
                userWallet.addBill(new Bill(userBills[i], billValue));
            }
            System.out.println("Total amount inserted: " + userWallet.getTotalAmount());
            while (isItemValid == false) {
                isItemValid = vndItemStock.validItem(cakeIndex);
                if (isItemValid == false) {
                    System.out.println("Invalid item. Please try again");
                }
            }
            int price = vndItemStock.getCakePrice(cakeIndex);
            if (canGiveChange == false) {
                if (userWallet.getTotalAmount() == price) {
                    vndStock.pay(userWallet.getTotalAmount(), price);
                    userWallet.transferBills(vndStock);
                    vndItemStock.transferCake(vndItemSold, cakeIndex);
                } else if (userWallet.getTotalAmount() > price) {
                    System.out.println("Please pay exact amount. No change available");
                    userWallet.clearWallet();
                }
            } else {
                if (userWallet.getTotalAmount() < price) {
                    System.out.println("Insufficient funds. Please add more bills");
                    userWallet.clearWallet();
                } else if ((vndStock.pay(userWallet.getTotalAmount(), price) == true) && (userWallet.getTotalAmount() > price)) {
                    userWallet.transferBills(vndProfit);
                    vndItemStock.transferCake(vndItemSold, cakeIndex);
                } else {
                    System.out.println("Please pay exact amount. No change available");
                    userWallet.clearWallet();
                }
            }
        }
    }

    public void receipt(){
        vndItemSold.getReceipt();
    }
    public void setDefaults(){
        //sets stock of ten for all bills
        int defaultStock = 10;
        for (int i = 0; i < vndStock.getBills().length; i++) {
                int billValue = vndStock.getBills()[i].getValue();
                vndStock.addBill(new Bill(defaultStock, billValue));
        }
        //set a preset stock for cakes
        vndItemStock.setStockDefaults();
    }
    
}
