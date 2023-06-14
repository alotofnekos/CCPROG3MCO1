package com.billMain;

import java.util.Scanner;

import com.bill.Bill;
import com.billWallet.BillWallet;

public class BillMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BillWallet userWallet = new BillWallet();
        BillWallet vndStock = new BillWallet();
        vndStock.addBill(new Bill(10, 10));  
        vndStock.addBill(new Bill(10, 20));    
        vndStock.addBill(new Bill(10, 50));    
        vndStock.addBill(new Bill(10, 100));   
        vndStock.addBill(new Bill(10, 200));
        vndStock.addBill(new Bill(10, 500));  
        while (vndStock.getTotalAmount() > 0) {
            System.out.println("Total amount of bills in Vending machine: " + vndStock.getTotalAmount());
            System.out.print("Enter the price of your item:");
            int price = sc.nextInt();
            sc.nextLine();
            int[] userBills = new int[userWallet.getBills().length];

            for (int i = 0; i < userBills.length; i++) {
                int billValue = userWallet.getBills()[i].getValue();
                System.out.print("Enter the number of " + billValue + " Peso bills to pay:");
                userBills[i] = sc.nextInt();
                sc.nextLine();
                userWallet.addBill(new Bill(userBills[i], billValue));
                if(userWallet.getTotalAmount()>price){
                    break;
                }
            }


            if ((vndStock.pay(userWallet.getTotalAmount(),price)==true)&&(userWallet.getTotalAmount()>price)) {
                userWallet.transferBills(vndStock);
            } 
            else if(userWallet.getTotalAmount()<price){
                System.out.println("Insufficient funds. Please add more bills");
                userWallet.clearWallet();
            }
            else{
                System.out.println("Please pay exact amount. No change available");
                userWallet.clearWallet();
            }
        }
    }
}
