public class SpcVnd extends RgVnd {
    private SpcStock spcItemStock;
    private SpcStock spcItemSold;
    private RgStock vndItemStock;
    private RgStock vndItemSold;
    private BillWallet userWallet;
    private BillWallet vndStock;
    private int profit=0;

    /**
     * This is the constructor for the SpcVnd class that initializes the SpcStocks for
     * special items and the BillWallets for the vending machine and the user.
     */
    public SpcVnd() {
        super();
        userWallet = new BillWallet();
        vndStock = new BillWallet();
        vndItemSold = new RgStock();
        vndItemStock = new RgStock();
        spcItemStock = new SpcStock();
        spcItemSold = new SpcStock();
    }
   
    /**
     * Lets the user purchase an item at cakeIndex with userBills. Gets the change too.
     * @param userBills the array of bills corresponding to the number of [10, 20, 50, 100, 200, 500] peso bills
     * @param cakeIndex the index of the cake item to be bought.
     * @param itemIndex the array of special item indices to be bought.
     */
    @Override
    public public String purchaseItem(int totalUserBill, int cakeIndex, int[] itemIndex) {
        boolean canGiveChange;
        boolean isItemValid = false;
        boolean isAddOnValid = false;
        boolean hasCakeInStock = vndItemStock.hasCakeInStock();
        String output="";
        if (!hasCakeInStock) {
            output = output.concat("Can't buy items, no items in stock.");
            return output;
        }

        if (vndStock.getTotalAmount() == 0) {
            output = output.concat("The Vending Machine does not have money to process change right now");
            canGiveChange = false;
        } else {
            output = output.concat("The Vending Machine can process change right now");
            canGiveChange = true;
        }

        output = output.concat("Total amount inserted: " + userWallet.getTotalAmount());

        isItemValid = vndItemStock.validItem(cakeIndex);
        if (!isItemValid) {
            output = output.concat("Item is out of stock. Please try again");
            userWallet.clearWallet();
            return output;
        }

        // Check if all special items are valid
        for (int i = 0; i < itemIndex.length; i++) {
            isAddOnValid = (spcItemStock.validItem(itemIndex[i])&&spcItemStock.isCompatible(itemIndex[i], vndItemStock.getCakeName(cakeIndex)));
            if (!isAddOnValid) {
                output = output.concat("Special item " + spcItemStock.getItemName(itemIndex[i]) + " is out of stock or is incompatible with the cake. Please try again");
                userWallet.clearWallet();
                return output;
            }
        }

        int totalPrice = vndItemStock.getCakePrice(cakeIndex);
        for (int i = 0; i < itemIndex.length; i++) {
            totalPrice += spcItemStock.getItemPrice(itemIndex[i]);
        }

        if (canGiveChange == false) {
            if (userWallet.getTotalAmount() == totalPrice) {
                output = output.concat(vndStock.pay(userWallet.getTotalAmount(), totalPrice));
                userWallet.clearWallet();
                profit += totalPrice;
                output = output.concat("Payment successful. Dispensing " + vndItemStock.getCakeName(cakeIndex) + ".");
                vndItemStock.transferCake(vndItemSold, cakeIndex);
                for (int i = 0; i < itemIndex.length; i++) {
                    spcItemStock.transferItem(spcItemSold, itemIndex[i]);
                    output = output.concat("Payment successful. Dispensing " + spcItemStock.getItemName(itemIndex[i]) + ".");
                }
            } else if (userWallet.getTotalAmount() > totalPrice) {
                output = output.concat("Please pay exact amount. No change available");
                userWallet.clearWallet();
                return output;
            }
        } else {
            if (userWallet.getTotalAmount() < totalPrice) {
                output = output.concat("Insufficient funds. Please add more bills");
                userWallet.clearWallet();
            } else if (!vndStock.pay(userWallet.getTotalAmount(), totalPrice).contains("Insufficient bills") && (userWallet.getTotalAmount() >= totalPrice)) {
                userWallet.clearWallet();
                output = output.concat(vndStock.pay(userWallet.getTotalAmount(), totalPrice));
                profit += totalPrice;
                output = output.concat("Payment successful. Dispensing " + vndItemStock.getCakeName(cakeIndex) + ".");
                vndItemStock.transferCake(vndItemSold, cakeIndex);
                for (int i = 0; i < itemIndex.length; i++) {
                    spcItemStock.transferItem(spcItemSold, itemIndex[i]);
                    output = output.concat("Payment successful. Dispensing " + spcItemStock.getItemName(itemIndex[i]) + ".");
                }
            } else {
                output = output.concat("Please pay exact amount. No change available");
                userWallet.clearWallet();
            }
        }
        return output;
    }
    /**
     * Sets the defaults (10 for every item, cake, and bill)
     */
    @Override
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
        spcItemStock.setStock(defaultStock);
        System.out.println("Defaults set!");
    }
    /**
     * Gets the list of cakes and items bought before a profit is collected
     */
    @Override
    public void receipt(){
        vndItemSold.getReceipt();
        spcItemSold.getReceipt();
    }
    /**
     * Displays the menu, the list of items available to be bought at that moment.
     */
    @Override
    public void displayMenu(){
        vndItemStock.displayMenu();
        spcItemStock.displayMenu();
    }
    /**
     * Collects the current profit of the vending machine, and clears the reciept since profit has been obtained already.
     */
    @Override
    public void collectProfit(){
        System.out.println("Total Profit obtained: "+profit+ ". Clearing wallet.");
        profit=0;
        vndItemSold.setStock(0);
        spcItemSold.setStock(0);
    }
    /**
     * Adds a new item to the vending machine's stock. Reciept's copy also adds the new cake to prevent discrepancies, but the quantity is set to zero since its not bought.
     * @param strName the name of the item
     * @param strDesc the description of the item
     * @param intCalorie the calorie amount of the item
     * @param intPrice the price of the item
     * @param intQuantity the amount of item to be added
     * @param strTag the item tag
     */
    public void addNewItem(String strName, String strDesc, int intCalorie, int intPrice, int intQuantity,String strTag){
        spcItemStock.addNewSpcItem(strName,strDesc, intCalorie,intPrice, intQuantity, strTag);
        spcItemSold.addNewSpcItem(strName,strDesc, intCalorie,intPrice, intQuantity, strTag);
    }
    /**
     * Deletes the item at index. Reciept's copy also deletes the cake to prevent discrepancies.
     * @param index the index of the item to be deleted
     */
    public void deleteAnItem(int index){
        spcItemStock.deleteItem(index);
        spcItemSold.deleteItem(index);
    }
    /**
     * Edits the cake at cakeIndex, based on choice, with an integer value. Some features are also passed down to the reciept's copy of the inventory, unless it pertains about the quantity.
     * @param choice tells which parameter to edit (13 for Calorie, 14 for Price, 15 for adding Stock, 16 for removing Stock)
     * @param index the index of the index to be edited
     * @param value the new value
     */
    public void editItem(int choice, int index, int value){
        if(profit!=0){
            System.out.println("Get the profit first before editing the cake parameters. Operation aborted");
        }
        else{
            if(choice==13){
                System.out.println("Calorie of " + spcItemStock.getItemName(index) + " changed to " + value +"!");
                spcItemStock.editCalorie(index, value);
                spcItemSold.editCalorie(index, value);
            }
            else if(choice==14){
                System.out.println("Price of " + spcItemStock.getItemName(index) +  " changed to " + value +"!");
                spcItemStock.editPrice(index, value);
                spcItemSold.editPrice(index, value);
            }
            else if(choice ==15){
                spcItemStock.addInt(index, value);
            } 
            else if(choice==16){
                spcItemStock.subInt(index, value);
            }
            else{
                System.out.println("Invalid...");
            }
        }
    }
    /**
     * Edits the iten at index, based on choice, with a String value. These features are also passed down to the reciept's copy of the inventory.
     * @param choice tells which parameter to edit (11 for name, 12 for description)
     * @param index the index of the item to be edited
     * @param value the new value
     */
    public void editItem(int choice, int index, String value){
        if(profit!=0){
            System.out.println("Get the profit first before editing the cake parameters. Operation aborted");
        }
        else{
            if(choice==11){
                System.out.println("Name of " + spcItemStock.getItemName(index)+" changed to " + value +"!");
                spcItemStock.editName(index, value);
                spcItemSold.editName(index, value);
            }
            else if(choice == 12){
                System.out.println("Description of " +spcItemStock.getItemName(index) +" changed to " + value +"!");
                spcItemStock.editDesc(index, value);
                spcItemSold.editDesc(index, value);
            }
        }
    }
}
