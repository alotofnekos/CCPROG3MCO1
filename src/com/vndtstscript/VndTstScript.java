package com.vndtstscript;

import com.billVnd.BillVnd;
//for easier running. Updates every thing. Dont forget to remove before passing!

public class VndTstScript {
    public static void main(String[] args) {
        BillVnd billVnd = new BillVnd();

        // Test set Default
        billVnd.setDefaults();
        
        System.out.println("\n------ Test Done ------\n");

        // Test bill maintenance
        int[] vndBills = {10, 10, 10, 10, 10, 10};
        billVnd.billMaintenance(vndBills);

        System.out.println("\n------ Test Done ------\n");

        // Test adding a new cake & displaying menu
        billVnd.addNewCake("Chocolate Cake", "Delicious chocolate cake", 200, 150, 5);
        billVnd.displayMenu();

        System.out.println("\n------ Test Done ------\n");

        // Test deleting a cake
        int cakeToDelete = 10;
        billVnd.deleteACake(cakeToDelete);
        billVnd.displayMenu();

        System.out.println("\n------ Test Done ------\n");

        // Test purchasing an item
        int[] userBills = {1, 1, 1, 1, 0, 0};
        int cakeIndex = 0;
        billVnd.purchaseItem(userBills, cakeIndex);

        System.out.println("\n------ Test Done ------\n");

        // Test collecting profit
        billVnd.collectProfit();

        System.out.println("\n------ Test Done ------\n");

        // Test receipt
        billVnd.receipt();

        System.out.println("\n------ Test Done ------\n");

        // Test editing a cake's Name
        billVnd.editCake(1, 1, "Blackforest Cake");

        // Test editing a cake's Description
        billVnd.editCake(2, 1, "Decadent Blackforest Cake");

        // Test editing a cake's Calorie
        billVnd.editCake(3, 1, 250);

        // Test editing a cake's Qty (Adding)
        billVnd.editCake(5, 1, 3);

        // Test editing a cake's Qty (Subtracting)
        billVnd.editCake(6, 1, 5);

        billVnd.displayMenu();

        System.out.println("\n------ Test Done ------\n");

        // Purchase another cake
        int[] userBills2 = {1, 2, 2, 1, 0, 0};
        cakeIndex = 1;
        billVnd.purchaseItem(userBills2, cakeIndex);
        billVnd.collectProfit();
        billVnd.receipt();

        System.out.println("\n------ Test Done ------\n");

    }
}
