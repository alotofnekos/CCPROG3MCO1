package com.billVnd;

import com.bill.Bill;
import com.billWallet.BillWallet;
import com.rgStock.RgStock;
/**
 * The class BillVnd recieves all the vending machine operations and sends it to the appropriate class
 */ 
public class BillVnd {
    private RgStock vndItemStock;
    private RgStock vndItemSold;
    private BillWallet userWallet;
    private BillWallet vndStock;
    private int profit=0;
/**
 * This is a constructor that initializes the RgStocks and BillWallets to be used throughout the program 
 */
    public BillVnd(){
        userWallet = new BillWallet();
        vndStock = new BillWallet();
        vndItemSold = new RgStock();
        vndItemStock = new RgStock();
    }
/**
 * Adds bills to the vending machine's stock
 * @param vndBills the array of bills corresponding to the number of [10, 20, 50, 100, 200, 500] peso bills
 */
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
/**
 * Adds a new cake to the vending machine's stock 
 * @param strName the name of the cake
 * @param strDesc the description of the cake
 * @param intCalorie the calorie amount of the cake
 * @param intPrice the price of the cake
 * @param intQuantity the amount of cakes to be added
 */
    public void addNewCake(String strName, String strDesc, int intCalorie, int intPrice, int intQuantity){
        vndItemStock.addNewCake(strName,strDesc, intCalorie,intPrice, intQuantity);
        vndItemSold.addNewCake(strName,strDesc, intCalorie,intPrice, 0);
    }
/**
 * deletes the cake at cakeIndex
 * @param cakeIndex the index of the cake to be deleted
 */
    public void deleteACake(int cakeIndex){
        vndItemStock.deleteCake(cakeIndex);
        vndItemSold.deleteCake(cakeIndex);
    }
/**
 * edits the cake at cakeIndex, based on choice, with value
 * @param choice tells which parameter to edit (3 for Calorie, 4 for Price, 5 for adding Stock, 6 for removing Stock)
 * @param cakeIndex the index of the cake to be edited
 * @param value the new value
 */
    public void editCake(int choice, int cakeIndex, int value){
        if(choice==3){
            vndItemStock.editCalorie(cakeIndex, value);
            vndItemSold.editCalorie(cakeIndex, value);
        }
        else if(choice==4){
            vndItemStock.editPrice(cakeIndex, value);
            vndItemSold.editPrice(cakeIndex, value);
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
/**
 * edits the cake at cakeIndex, based on choice, with value
 * @param choice tells which parameter to edit (1 for name, 2 for description)
 * @param cakeIndex the index of the cake to be edited
 * @param value the new value
 */
    public void editCake(int choice, int cakeIndex, String value){
        if(choice==1){
            vndItemStock.editName(cakeIndex, value);
            vndItemSold.editName(cakeIndex, value);
        }
        else if(choice == 2){
            vndItemStock.editDesc(cakeIndex, value);
            vndItemSold.editDesc(cakeIndex, value);
        }
    }
/**
 * Collects the current profit of the vending machine, and clears the reciept since profit has been obtained already.
 */
    public void collectProfit(){
        System.out.println("Total Profit obtained: "+profit+ ". Clearing wallet.");
        profit=0;
        vndItemSold.setStock(0);
    }
/**
 * Lets the user purchase an item at cakeIndex with userBills. Gets the change too.
 * @param userBills the array of bills corresponding to the number of [10, 20, 50, 100, 200, 500] peso bills
 * @param cakeIndex the index of the item to be bought.
 */
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
                    userWallet.clearWallet();
                    profit =+ price;
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
                    userWallet.clearWallet();
                    profit =+ price;
                    vndItemStock.transferCake(vndItemSold, cakeIndex);
                } else {
                    System.out.println("Please pay exact amount. No change available");
                    userWallet.clearWallet();
                }
            }
        }
    }
/**
 * Gets the list of items bought before a profit is collected
 */
    public void receipt(){
        vndItemSold.getReceipt();
    }
/**
 * Sets the defaults (10 for every item and bill)
 */
    public void setDefaults(){
        //sets stock of ten for all bills
        int defaultStock = 10;
        for (int i = 0; i < vndStock.getBills().length; i++) {
                int billValue = vndStock.getBills()[i].getValue();
                vndStock.addBill(new Bill(defaultStock, billValue));
        }
        //set a preset stock for cakes
        vndItemStock.setStock(defaultStock);
    }
    
}
