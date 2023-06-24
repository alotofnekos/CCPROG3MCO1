package com.rgStock;
import java.util.Scanner;
import com.rgItem.RgItem;
/**
 * The class Rg stock
 */ 
public class RgStock{
    private RgItem[] Item;
    private Scanner intMenu = new Scanner(System.in);
    private Scanner strEdit = new Scanner(System.in);
/**
 * Initialize
 *
 * @param Item  The Item Array. 
 */
    public RgStock() {
        Item = new RgItem[20]; // Initialize the Item array with 20 elements

        Item[0] = new RgItem("Angel's Apple Crumble", 0, 160, 156, "Treat yourself with our irresistible apple crumble! Indulge in layers of tender apples, delicately spiced with cinnamon, and crowned with a mouthwatering crumbly crust.");
        Item[1] = new RgItem("Black Forest Cake", 0, 140, 282, "Savor the taste of the classic Black Forest Cake, where the tangy sweetness of cherries perfectly complements the rich chocolate layers, all brought together with light and fluffy cream.");
        Item[2] = new RgItem("Premium Chocolate Cake", 0, 190, 389, "Treat yourself with this moist, rich, and bittersweet Chocolate cake, adorned with a lucious dark chocolate ganache.");
        Item[3] = new RgItem("Strawberry Shortcake", 0, 250, 247, "Indulge in the freshness of Benguet's finest strawberries with our Strawberry Bliss Short Cake. Enjoy the perfect blend of delicate sponge cake, heavenly cream, and juicy strawberries, sourced directly from the strawberry capital of the Philippines.");
        Item[4] = new RgItem("Lemon Blueberry Cake", 0, 240, 244, "Enjoy the vibrant combination of zesty lemon-infused cake, bursting blueberries, and the smooth cream cheese frosting that creates a perfect balance of flavors with this Lemon Blueberry cake.");
        Item[5] = new RgItem("Chocolate Mousse Cake", 0, 220, 349, "Revel in joy with a decadent dessert featuring layers of moist chocolate cake and creamy chocolate mousse, altogether forming a rich and indulgent treat.");
        Item[6] = new RgItem("Butterscotch Cake", 0, 280, 379, "A delectable dessert with a moist and fluffy base infused with the rich and caramel-like flavor of butterscotch, topped with creamy butterscotch frosting and caramel sauce.");
        Item[7] = new RgItem("Mocha Delight Cake", 0, 210, 308, "Enjoy a heavenly dessert featuring layers of moist chocolate cake infused with the bold flavors of coffee and espresso, complemented by a velvety mocha frosting for a delightful and indulgent treat.");
        Item[8] = new RgItem("Mango Surprise Cake", 0, 230, 228, "A tropical delight that combines layers of moist mango-infused cake with a luscious mango filling, providing a delightful surprise of fruity sweetness in every bite.");
        Item[9] = new RgItem("Tristan's Carrot Cake", 0, 180, 266, "Treat yourself with a moist and flavorful dessert made with grated carrots, warm spices, and a rich cream cheese frosting, offering a delightful combination of sweet and spiced flavors.");

        for (int i = 10; i < Item.length; i++) {
            Item[i] = new RgItem(); // Initialize the remaining elements with default RgItem objects
        }
    }

    public void setStockDefaults(){
        for(int i = 0;i<Item.length;i++){
            Item[i].setQty(10);
        }
    }
/**
 * Displays the menu.
 * 
 */
    public void displayMenu(){
        for(int i = 0;i<Item.length;i++){
            if(validItem(i)){
                System.out.println("Press " + i + " for this cake:");
                Item[i].displayCake();
            }
        }        
    }
/**
 * Edit menu
 *
 * @param Item  The Item Array. 
 * @param intMenu  the Integer Scanner. 
 * @param strEdit  the String Scanner. 
 */
    public void editMenu(){ 


        int intChosen;
        int intEditMenu;
        int intChange;
        int intCounter = countCakes();
        int intBooleanEdit = 0;
        String strLetter;

        System.out.print("Choose a cake to edit [1 - " + intCounter + "]: ");
        intChosen = intMenu.nextInt();
        System.out.println("------------------------");

        if((intChosen > 0)&&(intChosen <= intCounter)){
            intChosen = intChosen - 1;
                
            while(intBooleanEdit == 0){
                Item[intChosen].displayCake();
                System.out.println("[1] Edit Name");
                System.out.println("[2] Edit Description");
                System.out.println("[3] Edit Calorie");
                System.out.println("[4] Edit Price");
                System.out.println("[5] Add Inventory");
                System.out.println("[6] Subtract Inventory");
                System.out.println("[7] Delete Cake");
                System.out.println("[8] Return");
                System.out.println("------------------------");
                System.out.print("Enter Choice: ");
                intEditMenu = intMenu.nextInt();

                switch(intEditMenu) {
                    case 1:
                        intMenu.nextLine();
                        System.out.print("Enter new Name: ");
                        strLetter = strEdit.nextLine();
                        Item[intChosen].setName(strLetter);
                        break;
                    case 2:
                        System.out.print("Enter new Description: ");
                        strLetter = strEdit.nextLine();
                        Item[intChosen].setDesc(strLetter);
                        break;
                    case 3:
                        System.out.print("Enter new Calorie: ");
                        intChange = intMenu.nextInt();
                        Item[intChosen].setCalorie(intChange);
                        break;
                    case 4:
                        System.out.print("Enter new Price: ");
                        intChange = intMenu.nextInt();
                        Item[intChosen].setPrice(intChange);
                        break;
                    case 5:
                        System.out.print("Enter Inventory to Add: ");
                        intChange = intMenu.nextInt();
                        if(Item[intChosen].getQty() + intChange <= 10){
                            Item[intChosen].changeQtyAdd(intChange);
                        }
                        else{
                            System.out.println("Invalid Number. Exceeds Total Capacity of 10");
                        }
                        break;
                    case 6:
                        System.out.print("Enter Inventory to Subtract: ");
                        intChange = intMenu.nextInt();
                        if(Item[intChosen].getQty() - intChange >= 0){
                            Item[intChosen].changeQtySub(intChange);
                        }
                        else{
                            System.out.println("Invalid Number. Quantity Results to Less Than 0");
                        }
                        break;
                    case 7:
                        System.out.println("Cake Deleted...");
                        Item[intChosen].deleteCake();
                        intBooleanEdit = 1;
                        break;
                    case 8:
                        System.out.println("Returned...");
                        intBooleanEdit = 1;
                        break;
                    default:
                        System.out.println("Invalid Input...");
                        break;
                    }
                    System.out.println("------------------------");
                }
            }
            else{
                System.out.println("Invalid Cake...");
                System.out.println("------------------------");
            }
    }
/**
 * Add cake
 *
 * @param Item  The Item Array. 
 * @param intMenu  The Integer Scanner. 
 * @param strEdit  the String Scanner.
 */
    public void addCake(){ 
        int intValue;
        int intCounter = countCakes();
        String strName;
        String strDesc;

        System.out.print("Enter Name: ");
        strName = strEdit.nextLine();
        Item[intCounter].setName(strName);
        System.out.println("------------------------");

        System.out.print("Enter Description: ");
        strDesc = strEdit.nextLine();
        Item[intCounter].setDesc(strDesc);
        System.out.println("------------------------");
        
        System.out.print("Enter Calorie Count: ");
        intValue = intMenu.nextInt();
        Item[intCounter].setCalorie(intValue);
        System.out.println("------------------------");
        
        System.out.print("Enter Price Count: ");
        intValue = intMenu.nextInt();
        Item[intCounter].setPrice(intValue);
        System.out.println("------------------------");
        
        System.out.print("Enter Quantity Count [Max 10]: ");
        intValue = intMenu.nextInt();
        if(intValue > 10){
            intValue = 0;
            System.out.println("Invalid Quantity. Setting Quantity to 0.");
        }
        Item[intCounter].setQty(intValue);
        System.out.println("------------------------");
        System.out.println("Cake Registration Done");
        System.out.println("------------------------");
    }
/**
 * Count cakes
 *
 * @param Item  The Item Array. 
 * @return int  Number of Cakes in Item.
 */

        public int countCakes() {
            int intLoop;
            int intCounter = 0;

            for (intLoop = 0; intLoop < 20; intLoop++) {
                if (this.Item[intLoop].getName() != null) {
                    intCounter = intCounter + 1;
                }
            }
            return intCounter;
        }
/**
 * Transfer a cake to another when its bought
 *
 * @param Item  The Item Array. 
 * @return int  Number of Cakes in Item.
 */
    public void transferCake(RgStock rgStock, int cakeIndex) {
        rgStock.Item[cakeIndex].changeQtyAdd(1);
        this.Item[cakeIndex].changeQtySub(1);
    }

/**
 * Check if the cake at Item[cakeIndex] is valid (exists and is not null)
 *  @param cakeIndex  The index of the cake in the Item array.
 *  @return boolean  Returns true if the cake is valid, false otherwise.
 */
    public boolean validItem(int cakeIndex) {
        return (cakeIndex >= 0 && cakeIndex < Item.length && !Item[cakeIndex].getName().equals(" ") && Item[cakeIndex].getQty() > 0);
    }


/**
 * Display the receipt showing the cake name, quantity, and total price for each cake.
 */
    public void getReceipt() {
        System.out.println("Name\t\t\t\tQty\tPrice");
        for (int i = 0; i < Item.length; i++) {
            if (validItem(i)) {
                RgItem cake = Item[i];
                int quantity = cake.getQty();
                int totalPrice = cake.getPrice() * quantity;
                System.out.println(cake.getName() + "\t\t" + quantity + "\t" + "P" + totalPrice);
            }
        }
    }

/**
 * Get the price of the cake at the given index.
 *
 * @param cakeIndex The index of the cake in the Item array.
 * @return The price of the cake.
 */
    public int getCakePrice(int cakeIndex) {
        return Item[cakeIndex].getPrice();
    }

/**
 * Checks if there's any cake in stock
 * @return boolean true if any cake is in stock, false otherwise
 */
    public boolean hasCakeInStock() {
        for (int i = 0; i < Item.length; i++) {
            if (Item[i].getQty() > 0) {
                return true; // Found a cake with stock
            }
        }
        return false; // No cakes have stock
    }
}
