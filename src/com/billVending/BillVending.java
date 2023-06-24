package com.billVending;

import java.util.Scanner;

import com.billVnd.BillVnd;

public class BillVending {
    public static void main(String[] args) {
        Scanner scmain = new Scanner(System.in);
        int choice = 0;
        int subChoice=0;
        BillVnd vnd = new BillVnd();
        while(choice!=3){
            subChoice=0;
            System.out.println("Press 1 to access maintenance features, 2 to test vending features, and 3 to exit");
            choice = scmain.nextInt();
            scmain.nextLine();
            if(choice==1){
                while(subChoice!=6){
                    System.out.println("Press 1 for coin maintenance, 2 for item maintenance, 3 to set defaults, 4 to collect the payment incurred so far, 5 for the tally of items bought, and 6 to return to the main menu.");
                    subChoice=scmain.nextInt();
                    scmain.nextLine();
                    if(subChoice==1){
                        vnd.billMaintenance();
                    }
                    else if(subChoice==2){
                        vnd.itemMaintenance();
                    }
                    else if(subChoice==3){
                        vnd.setDefaults();
                    }
                    else if(subChoice==4){
                        vnd.collectProfit();
                    }
                    else if(subChoice==5){
                        vnd.receipt();
                    }
                    else if(subChoice==6){
                        System.out.println("Returning to the main menu...");
                    }
                    else{
                        System.out.println("Invalid choice. Please try again");
                    }
                }
                
            }
            else if(choice==2){
                do{
                    vnd.purchaseItem();
                    while(subChoice!=2){
                        System.out.println("Buy another item? Press 1 if Yes, 2 if No");
                        subChoice=scmain.nextInt();
                        scmain.nextLine();
                        
                    }
                    System.out.println("Returning to the main menu...");
                }while(subChoice!=2);
                
            }
            else if(choice==3){
                System.out.println("You are exiting the vending machine. Have a nice day!");
            }
            else{
                System.out.println("Invalid choice. Please try again");
            }
        }
        scmain.close();
    }
}

