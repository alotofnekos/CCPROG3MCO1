
/**
 * The class BillVnd recieves all the vending machine operations and sends it to the appropriate class
 */ 
public class RgVnd {
    protected RgStock vndItemStock;
    protected RgStock vndItemSold;
    protected BillWallet userWallet;
    protected BillWallet vndStock;
    protected int profit=0;
/**
 * This is a constructor that initializes the RgStocks and BillWallets to be used throughout the program. 
 * vndStock is the stock of bills the vending machine can use to produce change, the userWallet is the stock of bills the user uses to pay the vending machine.
 * vndItemStock is the stock of items the vending machine has, while the vndItemSold is the stock of items bought by the user, which gets cleared upon taking the profit. 
 */
    public RgVnd(){
        userWallet = new BillWallet();
        vndStock = new BillWallet();
        vndItemSold = new RgStock();
        vndItemStock = new RgStock();
    }

    public void clearUserWallet(){
        userWallet.clearWallet();        
    }

    public String getCakeDetails(int cakeIndex){
        return vndItemStock.displayDetails(cakeIndex);
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
            System.out.println(vndBills[i] + " " + billValue + " Pesos added to the vending machine through bill maintenance!");
            vndStock.addBill(new Bill(vndBills[i], billValue));
        }
    }
/**
 * Adds bills to the vending machine's stock
 * @param usrBills the array of bills corresponding to the number of [10, 20, 50, 100, 200, 500] peso bills
 */
    public int addUserBill(int[] vndBills) {
        if (vndBills.length != 6) {
            System.out.println("Invalid bill quantities array length. Expected length: 6");
            return 0;
        }

        for (int i = 0; i < vndBills.length; i++) {
            int billValue = userWallet.getBills()[i].getValue();
            System.out.println(vndBills[i] + " " + billValue + " Pesos added to the vending machine from User's Wallet!");
            userWallet.addBill(new Bill(vndBills[i], billValue));
        }
        return userWallet.getTotalAmount();
    }
/**
 * Adds a new cake to the vending machine's stock. Reciept's copy also adds the new cake to prevent discrepancies, but the quantity is set to zero since its not bought.
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
 * Deletes the cake at cakeIndex. Reciept's copy also deletes the cake to prevent discrepancies.
 * @param cakeIndex the index of the cake to be deleted
 */
    public void deleteACake(int cakeIndex){
        vndItemStock.deleteCake(cakeIndex);
        vndItemSold.deleteCake(cakeIndex);
    }
/**
 * Edits the cake at cakeIndex, based on choice, with an integer value. Some features are also passed down to the reciept's copy of the inventory, unless it pertains about the quantity.
 * @param choice tells which parameter to edit (3 for Calorie, 4 for Price, 5 for adding Stock, 6 for removing Stock)
 * @param cakeIndex the index of the cake to be edited
 * @param value the new value
 */
    public String editCake(int choice, int cakeIndex, int value){
        String output="";
        if(profit!=0){
            output=output.concat("Get the profit first before editing the cake parameters. Operation aborted");
        }
        else{
            if(choice==3){
                output=output.concat("Calorie of " + vndItemStock.getCakeName(cakeIndex) + " changed to " + value +"!");
                vndItemStock.editCalorie(cakeIndex, value);
                vndItemSold.editCalorie(cakeIndex, value);
            }
            else if(choice==4){
                output=output.concat("Price of " + vndItemStock.getCakeName(cakeIndex) +  " changed to " + value +"!");
                vndItemStock.editPrice(cakeIndex, value);
                vndItemSold.editPrice(cakeIndex, value);
            }
            else if(choice ==5){
                output=output.concat(vndItemStock.addInt(cakeIndex, value));
            } 
            else if(choice==6){
                output=output.concat(vndItemStock.subInt(cakeIndex, value));
            }
            else{
                output=output.concat("Invalid...");
            }
            
        }
        return output;
    }
/**
 * Edits the cake at cakeIndex, based on choice, with a String value. These features are also passed down to the reciept's copy of the inventory.
 * @param choice tells which parameter to edit (1 for name, 2 for description)
 * @param cakeIndex the index of the cake to be edited
 * @param value the new value
 */
    public String editCake(int choice, int cakeIndex, String value){
        String output="";
        if(profit!=0){
           output=output.concat("Get the profit first before editing the cake parameters. Operation aborted");
        }
        else{
            if(choice==1){
                output=output.concat("Name of " + vndItemStock.getCakeName(cakeIndex) +" changed to " + value +"!");
                vndItemStock.editName(cakeIndex, value);
                vndItemSold.editName(cakeIndex, value);
            }
            else if(choice == 2){
                output=output.concat("Description of " +vndItemStock.getCakeName(cakeIndex) +" changed to " + value +"!");
                vndItemStock.editDesc(cakeIndex, value);
                vndItemSold.editDesc(cakeIndex, value);
            }
        }
        return output;
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
 * Displays the menu, the list of items available to be bought at that moment.
 */
    public String getCakeName(int index){
        return vndItemStock.getCakeName(index);
    }
/**
 * Lets the user purchase an item at cakeIndex with userBills. Gets the change too.
 * @param totalUserBills the total amount inserted by the user
 * @param cakeIndex the index of the item to be bought.
 * @return output the entire transaction script
 */
    public String purchaseItem(int totalUserBill, int cakeIndex) {
        boolean canGiveChange;
        boolean isItemValid = false;
        boolean hasCakeInStock = vndItemStock.hasCakeInStock();
        String output="";
        if (hasCakeInStock == false) {
            output = output.concat("Can't buy items, no items in stock.");
            return output;
        } else {
            if (vndStock.getTotalAmount() == 0) {
                output = output.concat("The Vending Machine does not have money to process change right now \n");
                canGiveChange = false;
            } else {
                output = output.concat("The Vending Machine can process change right now \n");
                canGiveChange = true;
            }
            output = output.concat("Total amount inserted: " + userWallet.getTotalAmount()+"\n");
            isItemValid = vndItemStock.validItem(cakeIndex);
            if (isItemValid == false) {
                output = output.concat("Item is out of stock. Please try again");
                userWallet.clearWallet();
                return output;
            }
            int price = vndItemStock.getCakePrice(cakeIndex);
            output = output.concat("Total Price: " + price +"\n");
            if (canGiveChange == false) {
                if (userWallet.getTotalAmount() == price) {
                    userWallet.clearWallet();
                    profit += price;
                    output =output.concat("Payment successful. Dispensing " + vndItemStock.getCakeName(cakeIndex)+".");
                    vndItemStock.transferCake(vndItemSold, cakeIndex);
                    return output;
                } else if (userWallet.getTotalAmount() > price) {
                    output =output.concat("Please pay exact amount. No change available");
                    userWallet.clearWallet();
                    return output;
                }
            } else {
                if (!vndStock.pay(userWallet.getTotalAmount(), price).contains("Insufficient bills") && (userWallet.getTotalAmount() >= price)) {
                    output = output.concat(vndStock.pay(userWallet.getTotalAmount(), price));
                    userWallet.clearWallet();
                    profit += price;
                    output =output.concat("Payment successful. Dispensing " + vndItemStock.getCakeName(cakeIndex)+".");
                    vndItemStock.transferCake(vndItemSold, cakeIndex);
                    return output;
                } 
                else if (userWallet.getTotalAmount() < price) {
                    output =output.concat("Insufficient funds. Please add more bills");
                    userWallet.clearWallet();
                    return output;
                }
                else {
                    System.out.println("Please pay exact amount. No change available");
                    userWallet.clearWallet();
                    return output;
                }
            }
        }
        return output;
    }
/**
 * Gets the list of items bought before a profit is collected
 */
    public String receipt(){
        return vndItemSold.getReceipt();
    }
/**
 * Sets the defaults (10 for every item and bill)
 */
    public void setDefaults(){
        //sets stock of ten for all bills
        int defaultStock = 10;
        System.out.println("Setting defaults...");
        for (int i = 0; i < vndStock.getBills().length; i++) {
                int billValue = vndStock.getBills()[i].getValue();
                System.out.println(defaultStock + " " + billValue + " Pesos added to the vending machine by setting defaults!");
                vndStock.addBill(new Bill(defaultStock, billValue));
        }
        //set a preset stock for cakes
        vndItemStock.setStock(defaultStock);
        System.out.println("Defaults set!");
    }
/**
 * Gets the value of a bill (eg 20 for a 20 peso bill)
 * @param index The index of the bill in the wallet
 * @return the value of the bill
 */
    public int getBillAmt(int index){
        int billValue = vndStock.getBills()[index].getValue();
        return billValue;
    }
/**
 * Checks if a cake stock exists
 * @return true if there's a cake with a stock, false otherwise
 */
    public boolean hasStock() {
        return vndItemStock.hasCakeInStock();
    }
/**
 * Checks if a cake is valid
 * @param cakeIndex the index of the cake to be checked.
 * @return true if cake is valid, false otherwise
 */
    public boolean validItem(int cakeIndex){
        return vndItemStock.validItem(cakeIndex);
    }

/**
 * Checks cake price
 * @param cakeIndex the index of the cake to be checked.
 * @return cake price
 */
    public int getCakePrice(int cakeIndex){
        return vndItemStock.getCakePrice(cakeIndex);
    }
    
}
