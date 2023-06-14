package com.billvnd;

import java.util.Scanner;

import com.bill.Bill;
import com.billWallet.BillWallet;

public class BillVnd {
    
    private BillWallet userWallet;
    private BillWallet vndStock;
    public BillVnd(){
        userWallet = new BillWallet();
        vndStock = new BillWallet();
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
    public void purchaseItem(){
        Scanner sc = new Scanner(System.in);
        boolean canGiveChange;
        System.out.println("Total amount of bills in Vending machine: " + vndStock.getTotalAmount());
        if(vndStock.getTotalAmount()==0){
            System.out.println("The Vending Machine does not have money to process change right now");
            canGiveChange = false;
        }
        else{
            System.out.println("The Vending Machine can process change right now");
            canGiveChange = true;
        }
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
        System.out.print("Enter the price of your item:");
        int price = sc.nextInt();
        sc.nextLine();
        if(canGiveChange == false){
            if(userWallet.getTotalAmount()==price){
                vndStock.pay(userWallet.getTotalAmount(),price);
                userWallet.transferBills(vndStock);
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
                userWallet.transferBills(vndStock);
            } 

            else{
                System.out.println("Please pay exact amount. No change available");
                userWallet.clearWallet();
            }
        }   
        //sc.close();     
    }
    
}
