package com.billVending;

import java.util.Scanner;

import com.billVnd.BillVnd;
//for easier running. Updates every thing. Dont forget to remove before passing!

public class BillVending {
    public static void main(String[] args) {
        Scanner scmain = new Scanner(System.in);
        Scanner str = new Scanner(System.in);
        BillVnd vnd = new BillVnd();
        int cakeIndex;
        int subChoice = 0;
        int choice = 0;
        int integer;
        int loop;
        String string;
        int[] vndBills = {0, 0, 0, 0, 0, 0};

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
                        System.out.println("Enter bills in order of 10, 20, 50, 100, 200, 500");
                        for(loop = 0; loop < 6; loop++){
                            vndBills[loop] = scmain.nextInt();
                        }
                        vnd.billMaintenance(vndBills);
                    }
                    else if(subChoice==2){
                        System.out.print("What do you want to edit?\nName [1]\nDescription [2]\nCalorie [3]\nPrice [4]\nAdd Stock [5]\nSubtract Stock [6]\nEnter Choice: ");
                        loop = scmain.nextInt();
                        System.out.print("Enter cake number you want this edit to occur\nEnter: ");
                        cakeIndex = scmain.nextInt();
                        if(loop > 2){
                            System.out.print("Enter Value: ");
                            integer = scmain.nextInt();
                            vnd.editCake(loop, cakeIndex, integer);
                        }
                        else{
                            System.out.print("Enter Value: ");
                            string = str.nextLine();
                            vnd.editCake(loop, cakeIndex, string);
                        }
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
                    System.out.println("Purchase Item\nEnter bills in order of 10, 20, 50, 100, 200, 500");
                    for(loop = 0; loop < 6; loop++){
                        vndBills[loop] = scmain.nextInt();
                    }
                    System.out.print("Enter cake number you want to buy\nEnter: ");
                    cakeIndex = scmain.nextInt();

                    vnd.purchaseItem(vndBills, cakeIndex);
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

