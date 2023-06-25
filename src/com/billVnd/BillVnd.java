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
    public void billMaintenance(){
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
    public void addNewCake(int cakeIndex){
        //implement add Cake here
    }
    public void deleteACake(int cakeIndex){
        //implement delete Cake here
    }
    public void editCake(int choice, int cakeIndex, int value){
        if(choice==3){
            //edit Calorie
        }
        else if(choice==4){
            //edit Price
        }
        else if(choice ==5){
            //Add inventory
        } 
        else if(choice==6){
            //Subtract inventory
        }
        else{
            //invalid
        }
    }
    public void editCake(int choice, int cakeIndex, String value){
        if(choice==1){
            //Edit name
        }
        else if(choice == 2){
            //Edit Description
        }
    }
    public void collectProfit(){
        System.out.println("Total Profit obtained: "+vndProfit.getTotalAmount()+ ". Clearing wallet.");
        vndProfit.clearWallet();
    }
    public void purchaseItem(){
        Scanner sc = new Scanner(System.in);
        boolean canGiveChange;
        int cakeIndex=-1;
        boolean isItemValid = false;
        boolean hasCakeInStock = vndItemStock.hasCakeInStock();
        if(hasCakeInStock==false){
            System.out.println("Cant buy items, no items in stock.");
        }
        else{
            System.out.println("Total amount of bills in Vending machine: " + vndStock.getTotalAmount());
            if(vndStock.getTotalAmount()==0){
                System.out.println("The Vending Machine does not have money to process change right now");
                canGiveChange = false;
            }
            else{
                System.out.println("The Vending Machine can process change right now");
                canGiveChange = true;
            }
            vndItemStock.displayMenu();
            int[] userBills = new int[userWallet.getBills().length];
            for (int i = 0; i < userBills.length; i++) {
                int billValue = userWallet.getBills()[i].getValue();
                System.out.print("Enter the number of " + billValue + " Peso bills to pay:");
                userBills[i] = sc.nextInt();
                userWallet.addBill(new Bill(userBills[i], billValue));
                if(i-1>userBills.length){
                    sc.nextLine();
                }
            }
            System.out.println("Total amount inserted:" + userWallet.getTotalAmount());
            while(isItemValid==false){
                
                System.out.print("Enter the number of the item you want to buy");
                cakeIndex = sc.nextInt();
                sc.nextLine();
                isItemValid = vndItemStock.validItem(cakeIndex);
                if(isItemValid==false){
                    System.out.println("Invalid item. Please try again");
                }
            }
            int price = vndItemStock.getCakePrice(cakeIndex);
            if(canGiveChange == false){
                if(userWallet.getTotalAmount()==price){
                    vndStock.pay(userWallet.getTotalAmount(),price);
                    userWallet.transferBills(vndStock);
                    vndItemStock.transferCake(vndItemSold,cakeIndex);
                }
                else if(userWallet.getTotalAmount()>price){
                    System.out.println("Please pay exact amount. No change available");
                    userWallet.clearWallet();
                }
            }
            else{
                if(userWallet.getTotalAmount()<price){
                    System.out.println("Insufficient funds. Please add more bills");
                    userWallet.clearWallet();
                }
                else if ((vndStock.pay(userWallet.getTotalAmount(),price)==true)&&(userWallet.getTotalAmount()>price)) {
                    userWallet.transferBills(vndProfit);
                    vndItemStock.transferCake(vndItemSold,cakeIndex);
                } 

                else{
                    System.out.println("Please pay exact amount. No change available");
                    userWallet.clearWallet();
                }
            }  
        } 
        //sc.close();     
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
