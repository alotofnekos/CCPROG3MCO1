package com.billWallet;
import com.bill.Bill;

/** 
 * This class BillWallet holds the Bills for the instance of the vending machine and the amount the user put into the machine. 
 */
public class BillWallet {
    private Bill[] bills;
/** 
 * It is a constructor. It initializes the array of bills to be 
 */
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
/** 
 * Gets the array of bills to be counted
 * @return the array of bills
 */
    public Bill[] getBills() {
        return bills;
    }
/** 
 * Adds a specified quantity of bills with a specific value an existing bill collection
 * @param billToAdd the bills to add to the current bill
 * @return void
 */ 
    public void addBill(Bill billToAdd) {
        int value = billToAdd.getValue();
        for (int i = 0; i < bills.length; i++) {
            if (bills[i].getValue() == value) {
                bills[i].setQty(bills[i].getQty() + billToAdd.getQty());
                break;
            }
        }
    }   
/** 
 * Gets the total amount of all the bills in a wallet
 * @return total amount of all the bills
 */ 
    public int getTotalAmount() {
        int total = 0;
        for (int i = 0; i < bills.length; i++) {
            total += bills[i].getQty() * bills[i].getValue();
        }
        return total;
    }
/** 
 * Transfers all the bills in a wallet to a different wallet
 * @param wallet the wallet to transfer the bills to
 * @return void
 */ 
    public void transferBills(BillWallet wallet) {
        for (int i = 0; i < bills.length; i++) {
            wallet.addBill(bills[i]);
            bills[i].setQty(0);
        }
    }

/** 
 * clears the Wallet by setting the quantity of bills stored in the wallet to zero
 * @return void
 */ 
    public void clearWallet() {
        for (int i = 0; i < bills.length; i++) {
            bills[i].setQty(0);
        }
    }
/** 
 *
 * Provides change based on the user's cash and price of the item to be bought
 *
 * @param uCash the total amount of bills provided.
 * @param price the price of the item.
 * @return true if the change was given, false if change was not given.
 */
    public boolean pay(int uCash, int price) {
        int remainingAmount = uCash - price;
        for (int i = 5; i > 0; i--) {
            int billValue = bills[i].getValue();
            int billsToGive = remainingAmount / billValue;
            if (billsToGive <= bills[i].getQty()) {
                remainingAmount -= billsToGive * billValue;
            }
        }

        if (remainingAmount == 0) {
            remainingAmount = uCash - price;

            for (int i = 5; i > 0; i--) {
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
