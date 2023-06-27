package com.vndtstscript;

import com.billVnd.BillVnd;

public class VndTstScript {
    public static void main(String[] args) {
        BillVnd billVnd = new BillVnd();
        billVnd.setDefaults();
        
        // Test bill maintenance
        int[] vndBills = {10, 10, 10, 10, 10, 10};
        billVnd.billMaintenance(vndBills);

        // Test adding a new cake
        billVnd.addNewCake("Chocolate Cake", "Delicious chocolate cake", 200, 150, 5);

        // Test purchasing an item
        int[] userBills = {1, 1, 1, 1, 0, 0};
        int cakeIndex = 0;
        billVnd.purchaseItem(userBills, cakeIndex);

        // Test collecting profit
        billVnd.collectProfit();

        // Test receipt
        billVnd.receipt();

        // Test deleting a cake
        int cakeToDelete = 0;
        billVnd.deleteACake(cakeToDelete);

        // Test editing a cake's Name
        billVnd.editCake(1, 1, "Blackforest Cake");

        // Test editing a cake's Description
        billVnd.editCake(2, 1, "Decadent Blackforest Cake");

        // Test editing a cake's Calorie
        billVnd.editCake(3, 1, 250);

        // Test editing a cake's Price
        billVnd.editCake(4, 1, 200);

        // Test editing a cake's Qty (Adding)
        billVnd.editCake(5, 1, 3);

        // Test editing a cake's Qty (Subtracting)
        billVnd.editCake(6, 1, 5);
    }
}
