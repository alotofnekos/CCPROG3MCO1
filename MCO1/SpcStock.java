import java.util.List;

/**
 * The SpcStock class represents a set of special items available in the vending machine. 
 * It handles the management of special items, including adding, displaying, and checking compatibility with other items.
 */
public class SpcStock {
    private SpcItem[] spitem;

    /**
     * This is the constructor that initializes the SpcStock with default special items and their properties.
     */
    public SpcStock() {
        spitem = new SpcItem[16];
        // Initialize default special items
        spitem[0] = new SpcItem("Happy Birthday Dedication", 0, 20, 74, "A special option for your cake, allowing you to greet your special person (or yourself) a Happy Birthday with this edible topper", "dedication");
        spitem[1] = new SpcItem("Happy Mothers Day Dedication", 0, 20, 74, "A special option for your cake, allowing you to greet your special person (or yourself) a Happy Mothers Day with this edible topper", "dedication");
        spitem[2] = new SpcItem("Happy Fathers Day Dedication", 0, 20, 74, "A special option for your cake, allowing you to greet your special person (or yourself) a Happy Fathers Day with this ", "dedication");
        spitem[3] = new SpcItem("Cake Candle", 0, 10, 0, "A special option for your cake, allowing you to bring a warm glow to your cake. Dont forget to make a wish before blowing!", "candle");
        spitem[4] = new SpcItem("Candy Flowers", 0, 40, 55, "A special option for your cake, allowing you to bring more color with these vibrant candied flowers. Not suitable for Crumbling cakes.", "topper");
        spitem[4].addIncompatibleCake("Angel's Apple Crumble");
        spitem[5] = new SpcItem("Candy Hearts", 0, 40, 60, "A special option for your cake, allowing you to bring more love with these cute candied hearts. Not suitable for Crumbling cakes.", "topper");
        spitem[5].addIncompatibleCake("Angel's Apple Crumble");
        spitem[6] = new SpcItem("Fruits", 0, 30, 24, "Add natural sweetness to your dessert with fruits! Tristan's Carrot Cake and Black Forest Cake cannot add fruit to the top of the cake.", "fruit");
        spitem[6].addIncompatibleCake("Tristan's Carrot Cake");
        spitem[6].addIncompatibleCake("Black Forest Cake");
        spitem[7] = new SpcItem("Chocolate", 0, 40, 70, "Add a hint of bitter-sweet to your day with these chocolate toppings. Unavailable to Chocolate Mousse Cake,Black Forest Cake, and Premium Chocolate Cake as these are already chocolate cakes.", "chocolate");
        spitem[7].addIncompatibleCake("Chocolate Mousse Cake");
        spitem[7].addIncompatibleCake("Black Forest Cake");
        spitem[7].addIncompatibleCake("Premium Chocolate Cake");
        spitem[8] = new SpcItem("Vanilla Ice Cream", 0, 30, 66, "Beat the heat by adding a scoop of fragrant and rich vanilla ice cream on your cake!Unavailable to Tristan's Carrot Cake, Black Forest Cake, Strawberry Cake, Chocolate Mousse Cake, and Lemon Blueberry Cake due to these cakes already have a delicate topping. ", "ice cream");
        spitem[8].addIncompatibleCake("Tristan's Carrot Cake");
        spitem[8].addIncompatibleCake("Black Forest Cake");
        spitem[8].addIncompatibleCake("Strawberry Cake");
        spitem[8].addIncompatibleCake("Chocolate Mousse Cake");
        spitem[8].addIncompatibleCake("Lemon Blueberry Cake");
        spitem[9] = new SpcItem("Upgrade to B-Day Cake", 0, 40, 74, "Greet your loved ones with a Happy Birthday with this package! Includes a dedication topper and a candle.", "birthday package");
        spitem[10] = new SpcItem("Upgrade to Chocolate a la Mode", 0, 60, 136, "Ice Cream, Chocolate, and Cake? What a sweet combination! Inherits restrictions from Ice Cream and Chocolate.", "Ice Cream Chocolate Package", new SpcItem[]{spitem[6], spitem[7]});
        spitem[11] = new SpcItem("Upgrade to Fruit a la Mode", 0, 60, 90, "Ice Cream, Fruit, and Cake? What a refreshing combination! Inherits restrictions from Ice Cream and Fruits.", "Ice Cream Fruit Package", new SpcItem[]{spitem[5], spitem[7]});
        for (int i = 12; i < spitem.length; i++) {
            spitem[i] = new SpcItem(); // Initialize the remaining elements with default SpcItem objects
        }
    }

    /**
     * Checks if a special item at the given index is compatible with the specified cake name.
     *
     * @param index The index of the special item in the SpcItem array.
     * @param cakeName The name of the cake to check compatibility with.
     * @return true if the special item is compatible with the cake, false otherwise.
     */
    public boolean isCompatible(int index, String cakeName) {
        return spitem[index].isCompatible(cakeName);
    }

    /**
     * Sets the quantity of special items in the SpcStock.
     * If intQty = 0, it clears the quantity for all special items, used for clearing receipts.
     *
     * @param intQty The quantity of special items to be set.
     */
    public void setStock(int intQty) {
        if (intQty != 0) {
            for (int i = 0; i < spitem.length; i++) {
                if (!spitem[i].getName().equals(" ")) {
                    System.out.println(intQty + " " + spitem[i].getName() + "s added!");
                    spitem[i].setQty(intQty);
                }
            }
        } else {
            for (int i = 0; i < spitem.length; i++) {
                if (!spitem[i].getName().equals(" ")) {
                    spitem[i].setQty(intQty);
                }
            }
        }
    }

    /**
     * Displays the menu of special items available in the SpcStock.
     */
    public void displayMenu() {
        for (int i = 0; i < spitem.length; i++) {
            if (validItem(i)) {
                int j = i + 1;
                System.out.println("Item number: " + j);
                spitem[i].displayItem();
            }
        }
    }
    /**
     * Displays item menu
     * @param index of the item
     * @return item details
     */
    public String displayDetails(int index){
        if(!spitem[index].getName().equals(" ")){
            return spitem[index].displayItem();
        }
        else{
            return  "Name: " 
            + "\nDescription: " 
            + "\nCalorie Count: " 
            + "\nPrice: " 
            + "\nAvailable Quantity: ";
        }
        
    }
    /**
     * Checks if a special item at the given index is valid (exists and has a quantity greater than 0).
     *
     * @param index The index of the special item in the SpcItem array.
     * @return true if the special item is valid, false otherwise.
     */
    public boolean validItem(int index) {
        return (index >= 0 && index < spitem.length && !spitem[index].getName().equals(" ") && spitem[index].getQty() > 0);
    }

    /**
     * Gets the name of the special item at the given index.
     *
     * @param index The index of the special item in the SpcItem array.
     * @return The name of the special item.
     */
    public String getName(int index) {
        return spitem[index].getName();
    }

    /**
     * Gets the list of incompatible cake names for the special item at the given index.
     *
     * @param index The index of the special item in the SpcItem array.
     * @return The list of incompatible cake names for the special item.
     */
    public List<String> getIncompatibleCakes(int index) {
        return spitem[index].getIncompatibleCakes();
    }

    /**
     * Deletes a item.
     * @param index the index of the cake to be deleted
     * 
     */
        public void deleteItem(int index){
            spitem[index].deleteCake();
        }
    /**
     * Edits the calorie of the Item at index with value.
     * @param index the index of the Item to be edited
     * @param intCalorie the calorie amount
     */
        public void editCalorie(int index, int intCalorie){
            spitem[index].setCalorie(intCalorie);
        }
    /**
     * Edits the price of the Item at index with value.
     * @param index the index of the Item to be edited
     * @param intPrice the new Price
     */
        public void editPrice(int index, int intPrice){
            spitem[index].setPrice(intPrice);
        }
    /**
     * Adds an amount value to Item at index.
     * @param index the index of the Item to be edited
     * @param value the amount to be added
     */
        public String addInt(int index, int value){
            String output="";
            if(spitem[index].getQty() + value <= 10){
                spitem[index].changeQtyAdd(value);
                output = output.concat("Stock of "+ spitem[index].getName() +  " increased by " + value +"!");
            }
            else{
                output = output.concat("Invalid Number. Exceeds Total Capacity of 10");
            }
            return output;
        }
    /**
     * Subtracts an amount value to Item at index.
     * @param index the index of the Item to be edited
     * @param value the amount to be subtracted
     */
        public String subInt(int index, int value){
            String output="";
            if(spitem[index].getQty() - value >= 0){
                spitem[index].changeQtySub(value);
                output = output.concat("Stock of "+ spitem[index].getName() + " decreased by " + value +"!");
            }
            else{
                output = output.concat("Invalid Number. Quantity Results to Less Than 0");
            }
            return output;
        }
    /**
     * Edits the name of the Item at index.
     * @param index the index of the Item to be edited
     * @param strName the new name of the Item
     */
        public void editName(int index, String strName){
            spitem[index].setName(strName);
        }
    /**
     * Edits the description of the Item at index.
     * @param index the index of the Item to be edited
     * @param value the new description of the Item
     */
        public void editDesc(int index, String value){
            spitem[index].setDesc(value);
        }
    /**
     * Adds a New Item.
     * @param strName the name of the Item
     * @param strDesc the description of the Item
     * @param intCalorie the calorie amount of the Item
     * @param intPrice the price of the Item
     * @param intQuantity the amount of cakes to be added
     * 
     */
        public void addNewSpcItem(String strName, String strDesc, int intCalorie, int intPrice, int intQuantity, String strTag){
            int index = countItems();
            spitem[index] = new SpcItem(strName, intQuantity, intPrice, intCalorie, strDesc, strTag);
        }
    /**
     * Provides the index number of named items. Items set by default are still counted, even though they are deleted.
     * @return int  Number of named items [not necessarily on stock] in spcStock
     * 
     */

        public int countItems() {
                int intLoop;
                int intCounter = 11;
                for (intLoop = 11; intLoop < 20; intLoop++) {
                    if (!spitem[intLoop].getName().equals(" ")) {
                        intCounter = intCounter + 1;
                    }
            }
            return intCounter;
        }
    /**
     * Transfer a Item to another inventory when its bought
     *
     * @param rgStock Where the Item is to be transferred. 
     * @param index The position of the Item in the spitem[] array
     *
     */
        public void transferItem(SpcStock spcStock, int index) {
            spcStock.spitem[index].changeQtyAdd(1);
            this.spitem[index].changeQtySub(1);
        }

    /**
     * Checks if there's any Item in stock
     * @return boolean true if any Item is in stock, false otherwise
     */
        public boolean hasSpcInStock() {
            for (int i = 0; i < spitem.length; i++) {
                if (spitem[i].getQty() > 0) {
                    return true; // Found a Item with stock
                }
            }
            return false; // No cakes have stock
        }

    /**
     * Display the receipt showing the Item name, quantity, and total price for each Item.
     */
        public String getReceipt() {
            String output="";
            output = output.concat("Item Name\t\t\tQty\tPrice\n");
            for (int i = 0; i < spitem.length; i++) {
                if (validItem(i)) {
                    SpcItem spcItem = spitem[i];
                    int quantity = spcItem.getQty();
                    int totalPrice = spcItem.getPrice() * quantity;
                    output = output.concat(spcItem.getName() + "\t" + quantity + "\t" + "P" + totalPrice+"\n");
                }
            }
            return output;
        }

    /**
     * Get the price of the Item at the given index.
     *
     * @param index The index of the Item in the spitem array.
     * @return The price of the Item.
     */
        public int getItemPrice(int index) {
            return spitem[index].getPrice();
        }
    /**
     * Gets the name of the Item.
     * @param index the index of the Item to be named.
     * @return the name of the Item
     */
        public String getItemName(int index) {
            return spitem[index].getName();
        }
        
}


